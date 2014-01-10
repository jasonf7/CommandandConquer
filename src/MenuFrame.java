import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * This class operates the menu of the GUI
 * @author Jason Fang
 * @since December 22, 2011
 */
public class MenuFrame extends JFrame implements ActionListener
{
	// declare global variables
	JTextArea titleArea;
	
	JButton playGameButton;
	JButton loadGameButton;
	JButton instructionsButton;
	JButton exitButton;
	
	/**
	 * This 
	 */
	public MenuFrame()
	{
		super("Menu");
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);	
		setSize(400, 650);
		setLayout(null);
		setResizable(false);
		getContentPane().setBackground(Color.RED);
		
		createObjects();
		
		setVisible(true);
		repaint();
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == playGameButton)
		{
			dispose();
			new MapFrame(false);
		}
		else if(e.getSource() == loadGameButton)
		{
			Options.setOptions();
			dispose();
			new GameFrame(Options.LOAD_GAME);
		}
		else if(e.getSource() == instructionsButton)
		{
			dispose();
			new InstructionsFrame();
		}
		else if(e.getSource() == exitButton)
			System.exit(0);
	}
	
	private void createObjects()
	{
		titleArea = new JTextArea("  Command\n         &\n  Conquer!");
		titleArea.setBackground(Color.BLACK);
		titleArea.setForeground(Color.WHITE);
		titleArea.setAlignmentX(CENTER_ALIGNMENT);
		titleArea.setEditable(false);
		titleArea.setLineWrap(true);
		titleArea.setWrapStyleWord(true);
		titleArea.setHighlighter(null);
		titleArea.setFont(new Font("Impact", Font.BOLD, 50));
		titleArea.setBounds(50, 25, 300, 200);
		add(titleArea);
		
		playGameButton = new JButton("Play Game");
		playGameButton.setBounds(50, 275, 300, 50);
		playGameButton.setAlignmentX(CENTER_ALIGNMENT);
		playGameButton.setForeground(Color.WHITE);
		playGameButton.setBackground(Color.BLACK);
		playGameButton.setFont(new Font("Impact", Font.BOLD, 20));
		playGameButton.addActionListener(this);
		add(playGameButton);
		
		loadGameButton = new JButton("Load Game");
		loadGameButton.setBounds(50, 350, 300, 50);
		loadGameButton.setAlignmentX(CENTER_ALIGNMENT);
		loadGameButton.setForeground(Color.WHITE);
		loadGameButton.setBackground(Color.BLACK);
		loadGameButton.setFont(new Font("Impact", Font.BOLD, 20));
		loadGameButton.addActionListener(this);
		add(loadGameButton);
		
		instructionsButton = new JButton("Instructions");
		instructionsButton.setBounds(50, 425, 300, 50);
		instructionsButton.setAlignmentX(CENTER_ALIGNMENT);
		instructionsButton.setForeground(Color.WHITE);
		instructionsButton.setBackground(Color.BLACK);
		instructionsButton.setFont(new Font("Impact", Font.BOLD, 20));
		instructionsButton.addActionListener(this);
		add(instructionsButton);
		
		exitButton = new JButton("Exit");
		exitButton.setBounds(50, 500, 300, 50);
		exitButton.setAlignmentX(CENTER_ALIGNMENT);
		exitButton.setForeground(Color.WHITE);
		exitButton.setBackground(Color.BLACK);
		exitButton.setFont(new Font("Impact", Font.BOLD, 20));
		exitButton.addActionListener(this);
		add(exitButton);
	}
}
