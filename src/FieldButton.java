import java.awt.*;
import javax.swing.*;

public class FieldButton extends JButton
{
	private int numArmy = 0;
	private int ownedByPlayer;
	private int maxArmy;
	private int xElement;
	private int yElement;
	private Color fieldColor;
	public static final int PLAYER_ORANGE = 1;
	public static final int PLAYER_CYAN = 2;
	public static final int PLAYER_GREEN = 3;
	public static final int PLAYER_YELLOW = 4;
	public static final int EMPTY_FIELD = 0;
	public static final int TERRAIN_FIELD = 5;
	public static final int TERRAIN_WATER = 1;
	public static final int TERRAIN_ROCK = 2;
	public static final int TERRAIN_TREE = 3;
	
	public FieldButton(int x, int y)
	{
		super();
		setX(x);
		setY(y);
		setMaxArmy(Options.getNumRows()*Options.getNumColumns()/Options.getNumPlayers());																																	
		setFieldColor(Color.GREEN);
		
		setOwnedBy(EMPTY_FIELD);				
	}
	
	public int getArmy()
	{
		return numArmy;
	}
	
	public int getOwner()
	{
		return ownedByPlayer;
	}
	
	public Color getFieldColor()
	{
		return fieldColor;
	}
	
	public static Color getColor(int player)
	{
		Color color = null;
		switch(player)
		{
		case PLAYER_ORANGE:
			color = Color.ORANGE;
			break;
		case PLAYER_CYAN:
			color = Color.CYAN;
			break;
		case PLAYER_GREEN:
			color = Color.GREEN;
			break;
		case PLAYER_YELLOW:
			color = Color.YELLOW;
			break;
		case EMPTY_FIELD:
			color = new Color(0, 128, 0);
			break;
		}
		return color;
	}
	
	public int getXElement()
	{
		return xElement;
	}
	
	public int getYElement()
	{
		return yElement;
	}
	
	public int getMaxArmy()
	{
		return maxArmy;
	}
	
	public void setX(int x)
	{
		xElement = x;
	}
	
	public void setY(int y)
	{
		yElement = y;
	}
	
	public void setArmy(int armySize)
	{
		numArmy = armySize;
		setText(numArmy + " / " + maxArmy);
	}
	
	public void setOwnedBy(int player)
	{
		ownedByPlayer = player;
	}
	
	public void setFieldColor(Color color)
	{
		fieldColor = color;
		setBackground(fieldColor);
	}
	
	public void setMaxArmy(int max)
	{
		maxArmy = max;
	}
	
	public void changeSelected()
	{
		if(!getFieldColor().equals(Color.WHITE))
			setFieldColor(Color.WHITE);
		else
		{
//			switch(getOwner())
//			{
//			case PLAYER_RED:
//				setFieldColor(Color.RED);
//				break;
//			case PLAYER_BLUE:
//				setFieldColor(Color.BLUE);
//				break;
//			case PLAYER_GREEN:
//				setFieldColor(Color.GREEN);
//				break;
//			case PLAYER_YELLOW:
//				setFieldColor(Color.YELLOW);
//				break;
//			}
			setFieldColor(getColor(getOwner()));
		}
	}
	
	public int rollSum(int times)
	{
		int sum = 0;
		for(int i = 0; i < times; i++)
			sum += (int)((Math.random()*6)+1);
		return sum;
	}
	
	public int[] rollAll(int times)
	{
		int[] rolls = new int[times];
		for(int i = 0; i < times; i++)
			rolls[i] = (int)((Math.random()*6)+1);
		return rolls;
	}
	
	public void attack(FieldButton f)
	{
//		System.out.println("Can attack: " + canAttack(f));
		if(canAttack(f))
		{
			if(Options.isWinnerKeepsAll())
			{
				if(rollSum(getArmy()) > f.rollSum(f.getArmy()))
				{
					f.setIcon(null);
					f.setArmy(getArmy()-1);
					setArmy(1);
					f.setFieldColor(getFieldColor());
					f.setOwnedBy(getOwner());
					f.changeSelected();
				}
				else
				{
					setArmy(1);
				}
				changeSelected();
			}
			else
			{
				if(f.getOwner() == FieldButton.EMPTY_FIELD)
				{
					f.setArmy(getArmy() - 1);
					f.setIcon(null);
					f.setFieldColor(getFieldColor());
					f.setOwnedBy(getOwner());
					f.changeSelected();
					setArmy(1);
				}
				else
				{
					while(getArmy() != 1)
					{
						int attackRoll = (int)((Math.random()*6)+1);
						int defenseRoll = (int)((Math.random()*6)+1);
						System.out.println("Attack: " + attackRoll);
						System.out.println("Defense: " + defenseRoll);
						System.out.println("Army A: " + getArmy());
						System.out.println("Army D: " + f.getArmy());
						
						if(getOwner() == f.getOwner())
						{
							System.out.println("Adding armies");
							setArmy(getArmy() - 1);
							f.setArmy(f.getArmy() + 1);
						}
						else
						{
							if(attackRoll > defenseRoll)
							{
								System.out.println("Win");
								f.setArmy(f.getArmy() - 1);
								if(f.getArmy() == 0)
								{
									System.out.println("Won land");
									f.setIcon(null);
									f.setFieldColor(getFieldColor());
									f.setOwnedBy(getOwner());
									f.changeSelected();
								}
							}
							else if(attackRoll < defenseRoll)
							{
								System.out.println("Loss");
								setArmy(getArmy() - 1);
							}
						}
						System.out.println("/////////");
					}
				}
					
				changeSelected();
			}
		}
		
	}
	
	private boolean canAttack(FieldButton f)
	{
//		System.out.println("Attacker: " + getOwner());
//		System.out.println("X: " + getXElement());
//		System.out.println("Y: " + getYElement());
//		System.out.println("Defender: " + f.getOwner());
//		System.out.println("X: " + f.getXElement());
//		System.out.println("Y: " + f.getYElement());
		if(getOwner() != f.getOwner() 
			&& (Math.abs(getXElement() - f.getXElement()) == 1
					|| Math.abs(getXElement() - f.getXElement()) == 0)
			&& (Math.abs(getYElement() - f.getYElement()) == 1
					|| Math.abs(getYElement() - f.getYElement()) == 0)
			&& getArmy() != 1
			&& f.getOwner() != TERRAIN_FIELD)
			return true;
		return false;
	}
	
	public static int totalArmy(int player)
	{
		FieldButton[][] currentGrid = GameFrame.getGrid();
		int numArmy = 0;
		for(int i = 0; i < Options.getNumRows(); i++)
		{
			for(int j = 0; j < Options.getNumColumns(); j++)
			{
				if(currentGrid[i][j].getOwner() == player)
					numArmy += currentGrid[i][j].getArmy();
			}
		}
		
		return numArmy;
	}
	
	public static int totalLand(int player)
	{
		FieldButton[][] currentGrid = GameFrame.getGrid();
		int numLand = 0;
		for(int i = 0; i < Options.getNumRows(); i++)
		{
			for(int j = 0; j < Options.getNumColumns(); j++)
			{
				if(currentGrid[i][j].getOwner() == player)
					numLand++;
			}
		}
		
		return numLand;
	}
	
	public static FieldButton[][] clone(FieldButton[][] init)
	{
		System.out.println("Cloning");
		System.out.println(init[0][0].getArmy());
		System.out.println(init[0][0].getMaxArmy());
		FieldButton[][] f = new FieldButton[7][7];
		for(int i = 0; i < f.length; i++)
		{
			for(int j = 0; j < f[0].length; j++)
			{
				f[i][j] = new FieldButton(j, i);
				f[i][j].setArmy(init[i][j].getArmy());
				f[i][j].setOwnedBy(init[i][j].getOwner());
				f[i][j].setFieldColor(getColor(f[i][j].getOwner()));
				f[i][j].setIcon(init[i][j].getIcon());
			}
		}
		return f;
	}
	
	public String toString()
	{
		return "Army: " + getArmy() + " Owner: " + getOwner();
	}
}
