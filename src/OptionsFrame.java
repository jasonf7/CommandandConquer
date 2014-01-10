import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * This class operates the options screen of the GUI
 * @author Jason Fang
 * @since December 22, 2011
 */
public class OptionsFrame extends JFrame implements ActionListener
{
	private JComboBox playerChooserBox; 
	private JComboBox chooseRowBox; 
	private JComboBox chooseColumnBox; 
	private JCheckBox fullMapBox; 
	private JCheckBox landGrabBox; 
	private JCheckBox attritionBox; 
	private JCheckBox winnerKeepsAllBox; 
	private JButton applyButton; 
	private JButton backButton; 
	private JLabel titleLabel; 
	private JLabel playerLabel; 
	private JLabel rowLabel; 
	private JLabel columnLabel; 
	private JLabel gameTypeLabel; 
	private JLabel battleTypeLabel;
	private int userMap;
	
	public OptionsFrame(int chosenMap)
	{
		super("Options");
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);	
		setSize(400, 500);
		setLayout(null);
		setResizable(false);
		getContentPane().setBackground(Color.RED);
		
		userMap = chosenMap;
		createObjects();
		
		setVisible(true);
		repaint();
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == backButton)
		{
			dispose();
			new MenuFrame();
		}
		else if(e.getSource() == applyButton)
		{
			if(Math.abs(Integer.parseInt((String)chooseRowBox.getSelectedItem()) - Integer.parseInt((String)chooseColumnBox.getSelectedItem())) <= 2)
			{
				new Options();
				
				if(userMap > MapFrame.MAP_RANDOM)
				{
					if(userMap == MapFrame.MAP_ISLANDS && (Integer.parseInt((String)playerChooserBox.getSelectedItem()) != 2 
							&& Integer.parseInt((String)playerChooserBox.getSelectedItem()) != 4))
						JOptionPane.showMessageDialog(this, "Error: Map can only be played by 2 or 4 players!");
					else
					{
						System.out.println("Custom Map!");
						if(attritionBox.isSelected())
						{
							Options.setAttrition(true);
							Options.setWinnerKeepsAll(false);
						}
						else
						{
							Options.setAttrition(false);
							Options.setWinnerKeepsAll(true);
						}
						
						if(userMap == MapFrame.MAP_BRIDGE || userMap == MapFrame.MAP_VALLEY)
							Options.setNumPlayers(2);
						else if(userMap == MapFrame.MAP_KOTH || userMap == MapFrame.MAP_FFA)
							Options.setNumPlayers(4);
						else
							Options.setNumPlayers(Integer.parseInt((String)playerChooserBox.getSelectedItem()));
						Options.setNumRows(7);
						Options.setNumColumns(7);
						
						Options.setFullMap(false);
						Options.setLandGrab(true);
						
						dispose();
						new GameFrame(userMap+1);
					}
				}
				else
				{
					Options.setNumPlayers(Integer.parseInt((String)playerChooserBox.getSelectedItem()));
					Options.setNumRows(Integer.parseInt((String)chooseRowBox.getSelectedItem()));
					Options.setNumColumns(Integer.parseInt((String)chooseColumnBox.getSelectedItem()));
					
					if(fullMapBox.isSelected())
					{
						Options.setFullMap(true);
						Options.setLandGrab(false);
					}
					else
					{
						Options.setFullMap(false);
						Options.setLandGrab(true);
					}
					
					if(attritionBox.isSelected())
					{
						Options.setAttrition(true);
						Options.setWinnerKeepsAll(false);
					}
					else
					{
						Options.setAttrition(false);
						Options.setWinnerKeepsAll(true);
					}
					
					dispose();
					new GameFrame(Options.NORMAL_GAME);
				}
				
				
			}
			else
				JOptionPane.showMessageDialog(this, "ERROR: Rows and Columns must be 2 or less apart!");
		}
	}
	

	
	
	private void createObjects()
	{
		titleLabel = new JLabel("Game Options");
		titleLabel.setOpaque(true);
		titleLabel.setBackground(Color.BLACK);
		titleLabel.setForeground(Color.WHITE);
		titleLabel.setHorizontalAlignment(JLabel.CENTER);
		titleLabel.setFont(new Font("Impact", Font.BOLD, 30));
		titleLabel.setBounds(125, 25, 250, 50);
		add(titleLabel);
		
		backButton = new JButton("Back");
		backButton.setBounds(10, 25, 100, 50);
		backButton.setAlignmentX(CENTER_ALIGNMENT);
		backButton.setForeground(Color.WHITE);
		backButton.setBackground(Color.BLACK);
		backButton.setFont(new Font("Impact", Font.BOLD, 20));
		backButton.addActionListener(this);
		add(backButton);
		
		playerLabel = new JLabel("Number of players:");
		playerLabel.setFont(new Font("Impact", Font.PLAIN, 25));
		playerLabel.setBounds(50, 75, 250, 50);
		add(playerLabel);
		
		playerChooserBox = new JComboBox(new String[]{"2", "3", "4"});
		playerChooserBox.setFont(new Font("Impact", Font.PLAIN, 25));
		playerChooserBox.setBounds(300, 85, 50, 25);
		playerChooserBox.setSelectedIndex(1);
		add(playerChooserBox);
		
		rowLabel = new JLabel("Grid Rows                     :");
		rowLabel.setFont(new Font("Impact", Font.PLAIN, 25));
		rowLabel.setBounds(50, 125, 250, 50);
		add(rowLabel);
		
		chooseRowBox = new JComboBox(new String[]{"3", "4", "5", "6", "7", "8", "9"});
		chooseRowBox.setFont(new Font("Impact", Font.PLAIN, 25));
		chooseRowBox.setBounds(300, 135, 50, 25);
		chooseRowBox.setSelectedIndex(1);
		add(chooseRowBox);
		
		columnLabel = new JLabel("Grid Columns            :");
		columnLabel.setFont(new Font("Impact", Font.PLAIN, 25));
		columnLabel.setBounds(50, 175, 250, 50);
		add(columnLabel);
		
		chooseColumnBox = new JComboBox(new String[]{"3", "4", "5", "6", "7", "8", "9"});
		chooseColumnBox.setFont(new Font("Impact", Font.PLAIN, 25));
		chooseColumnBox.setBounds(300, 185, 50, 25);
		chooseColumnBox.setSelectedIndex(1);
		add(chooseColumnBox);
		
		gameTypeLabel = new JLabel("Game Type        :");
		gameTypeLabel.setFont(new Font("Impact", Font.PLAIN, 25));
		gameTypeLabel.setBounds(50, 225, 250, 50);
		add(gameTypeLabel);
		
		fullMapBox = new JCheckBox("Full Map", false);
		fullMapBox.setBounds(200, 225, 100, 50);
		fullMapBox.setFont(new Font("Impact", Font.PLAIN, 20));
		fullMapBox.setOpaque(false);
		add(fullMapBox);
		
		landGrabBox = new JCheckBox("Land Grab", true);
		landGrabBox.setBounds(200, 250, 150, 50);
		landGrabBox.setFont(new Font("Impact", Font.PLAIN, 20));
		landGrabBox.setOpaque(false);
		add(landGrabBox);
		
		ButtonGroup gameGroup = new ButtonGroup();
		gameGroup.add(fullMapBox);
		gameGroup.add(landGrabBox);
		
		battleTypeLabel = new JLabel("Battle Type       :");
		battleTypeLabel.setFont(new Font("Impact", Font.PLAIN, 25));
		battleTypeLabel.setBounds(50, 300, 250, 50);
		add(battleTypeLabel);
		
		attritionBox = new JCheckBox("Attrition", false);
		attritionBox.setBounds(200, 300, 150, 50);
		attritionBox.setFont(new Font("Impact", Font.PLAIN, 20));
		attritionBox.setOpaque(false);
		add(attritionBox);
		
		winnerKeepsAllBox = new JCheckBox("Winner Keeps All", true);
		winnerKeepsAllBox.setBounds(200, 325, 200, 50);
		winnerKeepsAllBox.setFont(new Font("Impact", Font.PLAIN, 20));
		winnerKeepsAllBox.setOpaque(false);
		add(winnerKeepsAllBox);
		
		ButtonGroup battleGroup = new ButtonGroup();
		battleGroup.add(attritionBox);
		battleGroup.add(winnerKeepsAllBox);
		
		applyButton = new JButton("Apply");
		applyButton.setBounds(150, 375, 100, 50);
		applyButton.addActionListener(this);
		applyButton.setForeground(Color.WHITE);
		applyButton.setBackground(Color.BLACK);
		applyButton.setFont(new Font("Impact", Font.BOLD, 20));
		add(applyButton);
		
		if(userMap > MapFrame.MAP_RANDOM)
		{
			landGrabBox.doClick();
			landGrabBox.setEnabled(false);
			fullMapBox.setEnabled(false);
			chooseRowBox.setSelectedIndex(4);
			chooseColumnBox.setSelectedIndex(4);
			chooseRowBox.setEnabled(false);
			chooseColumnBox.setEnabled(false);
			if(userMap != MapFrame.MAP_ISLANDS)
				playerChooserBox.setEnabled(false);
			
			if(userMap == MapFrame.MAP_BRIDGE || userMap == MapFrame.MAP_VALLEY)
				playerChooserBox.setSelectedIndex(0);
			else if(userMap == MapFrame.MAP_KOTH || userMap == MapFrame.MAP_FFA)
				playerChooserBox.setSelectedIndex(2);
		}
	}
}
