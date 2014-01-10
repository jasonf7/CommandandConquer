import java.awt.*;

import javax.swing.*;

import java.awt.event.*;

/**
 * This class operates the map choosing frame of the game's GUI.
 * @author Jason Fang
 * @since January 9, 2011
 */
public class MapFrame extends JFrame implements ActionListener
{
	private ImageIcon[] mapChoiceImages;
	private ImageIcon mapImage;
	private JLabel titleLabel;
	private JLabel mapLabel;
	private JComboBox mapChooserBox;
	private JButton startGameButton;
	private JButton backButton;
	private static int mapNum;
	private boolean isFromGame;
	public static final int MAP_RANDOM = 0;
	public static final int MAP_BRIDGE = 1;
	public static final int MAP_KOTH = 2;
	public static final int MAP_VALLEY = 3;
	public static final int MAP_ISLANDS = 4;
	public static final int MAP_FFA = 5;
	
	public MapFrame(boolean fromGame)
	{
		super("Choose your map!");
		
		isFromGame = fromGame;
		setDefaultCloseOperation(EXIT_ON_CLOSE);	
		setSize(600, 550);
		setLayout(null);
		setResizable(false);
		getContentPane().setBackground(Color.RED);
		
		mapChoiceImages = new ImageIcon[6];
		for(int i = 0; i < mapChoiceImages.length; i++)
			mapChoiceImages[i] = new ImageIcon(Toolkit.getDefaultToolkit().getImage("MapImages/jfang" + i + ".jpg"));
		
		mapImage = mapChoiceImages[0];
		
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
		else if(e.getSource() == mapChooserBox)
		{
			mapLabel.setIcon(mapChoiceImages[mapChooserBox.getSelectedIndex()]);
		}
		else if(e.getSource() == startGameButton)
		{
			mapNum = mapChooserBox.getSelectedIndex();
			if(mapNum > MAP_RANDOM)
			{
				System.out.println("CUSTOM MAP");
				new OptionsFrame(mapNum);
			}
			else
			{
				if(isFromGame)
					new GameFrame(Options.NORMAL_GAME);
				else
					new OptionsFrame(MAP_RANDOM);
			}
			dispose();
		}
	}
	
	public static int getMapNum()
	{
		return mapNum;
	}
	
	public void setMapNum(int map)
	{
		mapNum = map;
	}
	
	private void createObjects()
	{
		titleLabel = new JLabel("Map Chooser");
		titleLabel.setOpaque(true);
		titleLabel.setBackground(Color.BLACK);
		titleLabel.setForeground(Color.WHITE);
		titleLabel.setHorizontalAlignment(JLabel.CENTER);
		titleLabel.setFont(new Font("Impact", Font.BOLD, 30));
		titleLabel.setBounds(200, 25, 250, 50);
		add(titleLabel);
		
		backButton = new JButton("Back");
		backButton.setBounds(10, 25, 100, 50);
		backButton.setAlignmentX(CENTER_ALIGNMENT);
		backButton.setForeground(Color.WHITE);
		backButton.setBackground(Color.BLACK);
		backButton.setFont(new Font("Impact", Font.BOLD, 20));
		backButton.addActionListener(this);
		add(backButton);
		
		mapLabel = new JLabel();
		mapLabel.setIcon(mapImage);
		mapLabel.setBounds(150, 100, 300, 300);
		add(mapLabel);
		
		mapChooserBox = new JComboBox(new String[]{"Random", "Bridge", "King of the Hill", "Valley", "Islands", "FFA"});
		mapChooserBox.setFont(new Font("Impact", Font.PLAIN, 25));
		mapChooserBox.setBounds(150, 425, 300, 25);
		mapChooserBox.addActionListener(this);
		add(mapChooserBox);
		
		startGameButton = new JButton("Initiate Battle!");
		startGameButton.setBounds(150, 460, 300, 50);
		startGameButton.setAlignmentX(CENTER_ALIGNMENT);
		startGameButton.setForeground(Color.WHITE);
		startGameButton.setBackground(Color.BLACK);
		startGameButton.setFont(new Font("Impact", Font.BOLD, 40));
		startGameButton.addActionListener(this);
		add(startGameButton);
	}

} 
