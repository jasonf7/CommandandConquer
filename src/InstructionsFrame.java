import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class InstructionsFrame extends JFrame implements ActionListener
{
	private JLabel titleLabel;
	private JButton backButton;
	private JButton introButton;
	private JButton mapInfoButton;
	private JButton optionsInfoButton;
	private JButton gameplayInfoButton;
	private JTextArea infoArea;
	
	public InstructionsFrame()
	{
		super("Instructions");
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);	
		setSize(775, 525);
		setLayout(null);
		setResizable(false);
		getContentPane().setBackground(Color.RED);
		
		createObjects();
		
		introButton.doClick();
		
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
		else if(e.getSource() == introButton)
		{
			infoArea.setFont(new Font("Comic Sans Ms", Font.PLAIN, 17));
			infoArea.setText("Introduction:" + 
								"\n\nCommand and Conquer is a game of military strategy. You are " + 
								"battling to conquer the map, or to conquer your enemies. " + 
								"To win, you must launch daring attacks, " + 
								"defend yourself on all fronts, and sweep across the field with " + 
								"boldness and cunning. But remember, the dangers, as well as the rewards, " + 
								"are high. Just when the map is within your grasp, your opponent might " + 
								"strike and take it all away!");
		}
		else if(e.getSource() == mapInfoButton)
		{
			infoArea.setFont(new Font("Comic Sans Ms", Font.PLAIN, 17));
			infoArea.setText("Map Options:" +
								"\n\nBefore the game you can choose the map that you will be battling on. " +
								"There is a total of 3 two-player maps and 3 four-player maps. " +
								"\n2 Player: *Example*" +
								"\nThe Valley - an exquisite map where two armies fight in the battlefield " +
								"of nature. Credits to Aly Hassan for this map's concept art." +
								"\n4 Player: *Example*" +
								"\nKing of the Hill - a map where a fort w/ moat is placed in the middle. " +
								"Control over the middle fort is key to your army's victory." +
								"\nRandom: Creates a randomly generated map");
		}
		else if(e.getSource() == optionsInfoButton)
		{
			infoArea.setFont(new Font("Comic Sans Ms", Font.PLAIN, 14));
			infoArea.setText("Game Options: " +
								"\n\nAfter choosing the map, you can personalize how you want to play the game with various " +
								"options. NOTE: Many of the game options are NOT available when playing on custom maps. " +
								"\nGame Type: " +
								"\nFull Map: At the start of the game, almost the whole grid is covered with land owned by " +
								"different players. Each player is given the same amount of land with the same amount of  " +
								"armies. " +
								"\nLand Grab: Each player is given one piece of land with two armies in a random spot  " +
								"on the map. " +
								"\nBattle Type: Options for attacking other players NOTE: This is the ONLY option that is " +
								"changeable when playing on custom maps! " +
								"\nAttrition: When attacking land of an enemy player, you may lose armies in the process " +
								"based on how close your rolls are compared to your opponent's. " +
								"\nWinner Keeps All: When attacking the land of an enemy player, whoever rolls a higher sum " +
								"keeps all their armies and whoever rolls the lower sum loses all their armies in that " +
								"piece of land.");
		}
		else if(e.getSource() == gameplayInfoButton)
		{
			infoArea.setFont(new Font("Comic Sans Ms", Font.PLAIN, 17));
			infoArea.setText("During the game: " +
								"\n\nAttacking phase: During this phase, the player who has the current turn is able to move his " +
								"armies. The player first clicks on the piece that he would like to move. Then the player  " +
								"clicks on the place that he would like to move the selected piece to. " +
								"The player is able to move his land that has at least two armies in it. The player " +
								"can move to squares adjacent to or diagonally from the selected piece. The player can't move " +
								"armies from one of his land to another of his land. The player can choose to end his turn " +
								"at any time, entering the army reinforcement phase. " +
								"\nArmy Reinforcement phase: After a player ends his turn, he is able to reinforce the armies " +
								"in his land by receiving an extra amount of armies based on the amount of land he owns. " +
								"After placing all of his bonus armies, the turn switches to the next player.");
		}
	}

	private void createObjects()
	{
		titleLabel = new JLabel("About the Game");
		titleLabel.setOpaque(true);
		titleLabel.setBackground(Color.BLACK);
		titleLabel.setForeground(Color.WHITE);
		titleLabel.setHorizontalAlignment(JLabel.CENTER);
		titleLabel.setFont(new Font("Impact", Font.BOLD, 30));
		titleLabel.setBounds(300, 25, 250, 50);
		add(titleLabel);
		
		backButton = new JButton("Back");
		backButton.setBounds(50, 25, 100, 50);
		backButton.setAlignmentX(CENTER_ALIGNMENT);
		backButton.setForeground(Color.WHITE);
		backButton.setBackground(Color.BLACK);
		backButton.setFont(new Font("Impact", Font.BOLD, 20));
		backButton.addActionListener(this);
		add(backButton);
		
		infoArea = new JTextArea();
		infoArea.setBackground(Color.BLACK);
		infoArea.setForeground(Color.WHITE);
		infoArea.setEditable(false);
		infoArea.setLineWrap(true);
		infoArea.setWrapStyleWord(true);
		infoArea.setHighlighter(null);
		infoArea.setFont(new Font("Comic Sans Ms", Font.PLAIN, 17));
		infoArea.setBounds(50, 100, 700, 300);
		add(infoArea);
		
		introButton = new JButton("Introduction");
		introButton.setBounds(50, 425, 175, 50);
		introButton.setAlignmentX(CENTER_ALIGNMENT);
		introButton.setForeground(Color.WHITE);
		introButton.setBackground(Color.BLACK);
		introButton.setFont(new Font("Comic Sans Ms", Font.PLAIN, 20));
		introButton.addActionListener(this);
		add(introButton);
		
		mapInfoButton = new JButton("Map Info");
		mapInfoButton.setBounds(225, 425, 175, 50);
		mapInfoButton.setAlignmentX(CENTER_ALIGNMENT);
		mapInfoButton.setForeground(Color.WHITE);
		mapInfoButton.setBackground(Color.BLACK);
		mapInfoButton.setFont(new Font("Comic Sans Ms", Font.PLAIN, 20));
		mapInfoButton.addActionListener(this);
		add(mapInfoButton);
		
		optionsInfoButton = new JButton("Options Info");
		optionsInfoButton.setBounds(400, 425, 175, 50);
		optionsInfoButton.setAlignmentX(CENTER_ALIGNMENT);
		optionsInfoButton.setForeground(Color.WHITE);
		optionsInfoButton.setBackground(Color.BLACK);
		optionsInfoButton.setFont(new Font("Comic Sans Ms", Font.PLAIN, 20));
		optionsInfoButton.addActionListener(this);
		add(optionsInfoButton);
		
		gameplayInfoButton = new JButton("Gameplay Info");
		gameplayInfoButton.setBounds(575, 425, 175, 50);
		gameplayInfoButton.setAlignmentX(CENTER_ALIGNMENT);
		gameplayInfoButton.setForeground(Color.WHITE);
		gameplayInfoButton.setBackground(Color.BLACK);
		gameplayInfoButton.setFont(new Font("Comic Sans Ms", Font.PLAIN, 20));
		gameplayInfoButton.addActionListener(this);
		add(gameplayInfoButton);
	}
}
