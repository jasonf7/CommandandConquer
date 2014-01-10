import java.awt.*;

import javax.swing.*;

import java.awt.event.*;

public class GameFrame extends JFrame implements ActionListener
{
	private static FieldButton[][] gridField;
	private static ImageIcon[] terrainImages;
	public static JLabel armyInfoLabel;
	private JButton endTurnButton;
	private JMenuItem newMap, saveGame, quit;
	private JMenuItem switchMap, switchOptions;
	private boolean placingArmy;
	private static int currentTurn;
	private int totalLand;
	private int gameType;
	
	public GameFrame(int state)
	{
		super("Command & Conquer");
		setDefaultCloseOperation(EXIT_ON_CLOSE);	
		setSize(600, 650);
		setLayout(null);
		setResizable(false);
		getContentPane().setBackground(Color.RED);
		
		setPlacingArmy(false);
		
		gameType = state;
		
		terrainImages = new ImageIcon[4]; 
		for(int i = 0; i < terrainImages.length; i++)
			terrainImages[i] = new ImageIcon("TerrainImages/jfang" + i + ".png");
		Image tempImage = Toolkit.getDefaultToolkit().getImage("TerrainImages/jfang" + 0 + ".png");
		Image newImage = tempImage.getScaledInstance(500/Options.getNumColumns(), 500/Options.getNumRows(), Image.SCALE_SMOOTH);
		terrainImages[FieldButton.EMPTY_FIELD] = new ImageIcon(newImage);
		
		new CustomMaps();
		
		createObjects();
		
		setVisible(true);
		repaint();
	}

	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == endTurnButton)
		{
			setPlacingArmy(true);
			totalLand = FieldButton.totalLand(currentTurn);
			armyInfoLabel.setText("Place your Armies - " + totalLand + " left.");
			endTurnButton.setVisible(false);
		}
		else if(e.getSource() == switchMap)
		{
			dispose();
			new MapFrame(true);
		}
		else if(e.getSource() == switchOptions)
		{
			dispose();
			new OptionsFrame(gameType-1);
		}
		else if(e.getSource() == newMap)
		{
			endTurnButton.setVisible(true);
			clearGrid(Options.getNumRows(), Options.getNumColumns(), getGrid());
			initArmy(Options.getNumRows(), Options.getNumColumns());
			setCurrentTurn(1);
			armyInfoLabel.setForeground(Color.ORANGE);
			armyInfoLabel.setText("Player " + 1 + ": Army - " + FieldButton.totalArmy(1) + " Land - " + FieldButton.totalLand(1));
			setPlacingArmy(false);
		}
		else if(e.getSource() == saveGame)
		{
			if(armyInfoLabel.getText().substring(0, 5).equals("Place"))
				JOptionPane.showMessageDialog(this, "Finish placing your armies!!!");
			else
			{
				Options.saveOptions();
				JOptionPane.showMessageDialog(this, "The game has been successfully saved!");
			}
		}
		else if(e.getSource() == quit)
		{
			dispose();
			new MenuFrame();
		}
		else
		{
			int selRow = Integer.parseInt(e.getActionCommand().substring(0, 1));
			int selColumn = Integer.parseInt(e.getActionCommand().substring(3, 4));
			if(isPlacingArmy())
			{
				if(totalLand != 0)
				{
					if(gridField[selRow][selColumn].getOwner() == currentTurn)
					{
						if(gridField[selRow][selColumn].getArmy() != gridField[selRow][selColumn].getMaxArmy())
						{
							if(gridField[selRow][selColumn].getBackground().equals(Color.WHITE))
								gridField[selRow][selColumn].changeSelected();
							gridField[selRow][selColumn].setArmy(gridField[selRow][selColumn].getArmy() + 1);
							totalLand--;
						}
					}
					armyInfoLabel.setText("Place your Armies - " + totalLand + " left.");
					
					if(totalLand == 0)
					{
						setPlacingArmy(false);
						endTurnButton.setVisible(true);
						changeTurn();
						while(FieldButton.totalLand(currentTurn) == 0)
						{
							changeTurn();
						}
						
						armyInfoLabel.setForeground(FieldButton.getColor(getTurn()));
						armyInfoLabel.setText("Player " + currentTurn + ": Army - " + FieldButton.totalArmy(currentTurn) + " Land - " + FieldButton.totalLand(currentTurn));
					}
				}
			}
			else
			{
				int whiteRow = 0;
				int whiteColumn = 0;
				boolean pieceSelected = false;
				for(int i = 0; i < Options.getNumRows(); i++)
				{
					for(int j = 0; j < Options.getNumColumns(); j++)
					{
						if(gridField[i][j].getBackground().equals(Color.WHITE))
						{
							pieceSelected = true;
							whiteRow = i;
							whiteColumn = j;
						}
					}
				}
				if(pieceSelected)
				{
					if(whiteRow == selRow && whiteColumn == selColumn)
					{
						gridField[selRow][selColumn].changeSelected();
					}
					else
					{
						gridField[whiteRow][whiteColumn].attack(gridField[selRow][selColumn]);
					}
				}
				else
				{
					if(gridField[selRow][selColumn].getOwner() == currentTurn)
					{
						gridField[selRow][selColumn].changeSelected();
					}
				}
				armyInfoLabel.setText("Player " + currentTurn + ": Army - " + FieldButton.totalArmy(currentTurn) + " Land - " + FieldButton.totalLand(currentTurn));
			}
		}
		
		if(gameWon())
		{
			JOptionPane.showMessageDialog(this, "Player " + currentTurn + " has won!");
			dispose();
			new MenuFrame();
		}
		repaint();
	}
	
	public void constructGrid(int rows, int columns)
	{
		gridField = new FieldButton[rows][columns];
		JPanel gridPanel = new JPanel();
		gridPanel.setBackground(Color.RED);
		gridPanel.setLayout(new GridLayout(rows, columns));
		gridPanel.setBounds(50, 50, 500, 500);
		add(gridPanel);
		for(int i = 0; i < rows; i++)
		{
			for(int j = 0; j < columns; j++)
			{
				gridField[i][j] = new FieldButton(j, i);
				gridField[i][j].setBorder(BorderFactory.createBevelBorder(0, Color.LIGHT_GRAY, Color.BLACK));
				gridField[i][j].setActionCommand(i + ", " + j);
				gridField[i][j].addActionListener(this);
				gridPanel.add(gridField[i][j]);
				if(gridField[i][j].getOwner() == FieldButton.EMPTY_FIELD)
				{
					gridField[i][j].setText("");
					gridField[i][j].setIcon(terrainImages[FieldButton.EMPTY_FIELD]);
				}
			}
		}
		
		initArmy(Options.getNumRows(), Options.getNumColumns());
	}
	
	public void constructGrid(FieldButton[][] grid)
	{
		gridField = new FieldButton[grid.length][grid[0].length];
		JPanel gridPanel = new JPanel();
		gridPanel.setBackground(Color.RED);
		gridPanel.setLayout(new GridLayout(grid.length, grid[0].length));
		gridPanel.setBounds(50, 50, 500, 500);
		add(gridPanel);
		for(int i = 0; i < grid.length; i++)
		{
			for(int j = 0; j < grid[0].length; j++)
			{
				gridField[i][j] = new FieldButton(j, i);
				gridField[i][j].setBorder(BorderFactory.createBevelBorder(0, Color.LIGHT_GRAY, Color.BLACK));
				gridField[i][j].setOwnedBy(grid[i][j].getOwner());
				gridField[i][j].setArmy(grid[i][j].getArmy());
				gridField[i][j].setFieldColor(grid[i][j].getFieldColor());
				gridField[i][j].setActionCommand(i + ", " + j);
				if(gridField[i][j].getOwner() == FieldButton.TERRAIN_FIELD || gridField[i][j].getOwner() == FieldButton.EMPTY_FIELD)
				{
					gridField[i][j].setIcon(grid[i][j].getIcon());
					gridField[i][j].setText("");
				}
				gridField[i][j].addActionListener(this);
				gridPanel.add(gridField[i][j]);
				
					
			}
		}
	}
	
	public void clearGrid(int rows, int columns, FieldButton[][] grid)
	{
		for(int i = 0; i < rows; i++)
		{
			for(int j = 0; j < columns; j++)
			{
				grid[i][j].setIcon(terrainImages[FieldButton.EMPTY_FIELD]);
				grid[i][j].setText("");
				grid[i][j].setOwnedBy(FieldButton.EMPTY_FIELD);
				grid[i][j].setFieldColor(Color.GRAY);
			}
		}
	}
	
	public void initArmy(int rows, int columns)
	{
		if(Options.isLandGrab())
		{
			for(int i = 0; i < Options.getNumPlayers(); i++)
			{
				int randRow = (int)(Math.random()*rows);
				int randColumn = (int)(Math.random()*columns);
				while(gridField[randRow][randColumn].getOwner() != FieldButton.EMPTY_FIELD)
				{
					randRow = (int)(Math.random()*rows);
					randColumn = (int)(Math.random()*columns);
				}
				gridField[randRow][randColumn].setFieldColor(FieldButton.getColor(i+1));
				gridField[randRow][randColumn].setOwnedBy(i+1);
				gridField[randRow][randColumn].setArmy(2);
				gridField[randRow][randColumn].setIcon(null);
			}
		}
		else
		{
			int numLand = (rows*columns)/Options.getNumPlayers();
			for(int i = 1; i <= Options.getNumPlayers(); i++)
			{
				int armyNum = numLand*2;
				int landLeft = numLand;
				for(int j = numLand; j > 0; j--)
				{
					int randRow = (int)(Math.random()*rows);
					int randColumn = (int)(Math.random()*columns);
					while(gridField[randRow][randColumn].getOwner() != FieldButton.EMPTY_FIELD)
					{
						randRow = (int)(Math.random()*rows);
						randColumn = (int)(Math.random()*columns);
					}
					gridField[randRow][randColumn].setFieldColor(FieldButton.getColor(i));
					gridField[randRow][randColumn].setOwnedBy(i);
					gridField[randRow][randColumn].setArmy(1);
					gridField[randRow][randColumn].setIcon(null);
					
					int randArmy = (int)((Math.random()*armyNum)+1);
					
					while((armyNum - randArmy) < (landLeft-1))
					{
						randArmy = (int)((Math.random()*armyNum)+1);
					}
					gridField[randRow][randColumn].setArmy(randArmy);
					armyNum -= randArmy;
					landLeft--;
				}
			}
		}
	}
	
	public static FieldButton[][] getGrid()
	{
		return gridField;
	}
	
	public static int getTurn()
	{
		return currentTurn;
	}
	
	public static ImageIcon getTerrainImage(int index)
	{
		return terrainImages[index];
	}
	
	public boolean isPlacingArmy()
	{
		return placingArmy;
	}
	
	public void setPlacingArmy(boolean state)
	{
		placingArmy = state;
	}
	
	public static void setGrid(FieldButton[][] grid)
	{
		gridField = grid;
		for(int i = 0; i < grid.length; i++)
		{
			for(int j = 0; j < grid[0].length; j++)
			{
				gridField[i][j].setArmy(grid[i][j].getArmy());
				gridField[i][j].setOwnedBy(grid[i][j].getOwner());
				gridField[i][j].setFieldColor(grid[i][j].getFieldColor());
			}
		}
	}
	
	public static void setCurrentTurn(int player)
	{
		currentTurn = player;
	}
	
	public void placeArmy(FieldButton button)
	{
		button.setArmy(button.getArmy() + 1);
	}
	
	public void changeTurn()
	{
		if(currentTurn != Options.getNumPlayers())
			setCurrentTurn(currentTurn + 1);
		else
			setCurrentTurn(1);
	}
	
	public boolean gameWon()
	{
		int numDead = 0;
		for(int i = 1; i <= Options.getNumPlayers(); i++)
		{
			if(FieldButton.totalLand(i) == 0)
				numDead++;
		}
		
		if(numDead == Options.getNumPlayers()-1)
		{
			return true;
		}
		return false;
	}
	
	private void createObjects() 
	{
		System.out.println(gameType);
		if(gameType == Options.NORMAL_GAME)
		{
			setCurrentTurn(1);
			constructGrid(Options.getNumRows(), Options.getNumColumns());
		}	
		else if(gameType == Options.LOAD_GAME)
			constructGrid(Options.getSaveGrid());
		else
		{
			setCurrentTurn(1);
			System.out.println("Construct Custom map " + gameType);
			constructGrid((FieldButton[][])IO.readObject("Maps/jfang" + (gameType-1) + ".map"));
		}
		
		armyInfoLabel = new JLabel("Player " + currentTurn + ": Army - " + FieldButton.totalArmy(currentTurn) + " Land - " + FieldButton.totalLand(currentTurn));
		armyInfoLabel.setOpaque(true);
		armyInfoLabel.setBackground(Color.BLACK);
		armyInfoLabel.setForeground(FieldButton.getColor(getTurn()));
		armyInfoLabel.setHorizontalAlignment(JLabel.CENTER);
		armyInfoLabel.setFont(new Font("Impact", Font.BOLD, 30));
		armyInfoLabel.setBounds(0, 0, 600, 30);
		add(armyInfoLabel);
		
		endTurnButton = new JButton("End Turn");
		endTurnButton.setBounds(50, 550, 500, 50);
		endTurnButton.addActionListener(this);
		endTurnButton.setForeground(Color.WHITE);
		endTurnButton.setBackground(Color.BLACK);
		endTurnButton.setFont(new Font("Impact", Font.BOLD, 30));
		add(endTurnButton);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu fileBar = new JMenu("File");
		menuBar.add(fileBar);
		
		JMenu optionsBar = new JMenu("Options");
		menuBar.add(optionsBar);
		
		newMap = new JMenuItem("Restart");
		newMap.addActionListener(this);
		fileBar.add(newMap);
		
		saveGame = new JMenuItem("Save Game");
		saveGame.addActionListener(this);
		fileBar.add(saveGame);
		
		quit = new JMenuItem("Quit Game");
		quit.addActionListener(this);
		fileBar.add(quit);
		
		switchMap = new JMenuItem("Change Map");
		switchMap.addActionListener(this);
		optionsBar.add(switchMap);
		
		switchOptions = new JMenuItem("Change Options");
		switchOptions.addActionListener(this);
		optionsBar.add(switchOptions);
		
		if(gameType != Options.NORMAL_GAME && gameType != Options.LOAD_GAME)
		{
			switchOptions.setVisible(false);
		}
	}
}
