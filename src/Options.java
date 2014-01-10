import java.io.IOException;


public class Options 
{
	private static FieldButton[][] saveGrid;
	private static boolean chooseFullMap; 
	private static boolean chooseLandGrab; 
	private static boolean chooseAttrition; 
	private static boolean chooseWinnerKeepsAll; 
	private static int numPlayers; 
	private static int numRows; 
	private static int numColumns;
	
	public static final int LOAD_GAME = 0;
	public static final int NORMAL_GAME = 1;
	public static final int CUSTOM_GAME = 2;
	
	public static void setOptions()
	{
		IO.openInputFile("Options.fangerbanger");
		try 
		{
			setFullMap(Boolean.parseBoolean(IO.readLine()));
			setLandGrab(Boolean.parseBoolean(IO.readLine()));
			setAttrition(Boolean.parseBoolean(IO.readLine()));
			setWinnerKeepsAll(Boolean.parseBoolean(IO.readLine()));
			setNumPlayers(Integer.parseInt(IO.readLine()));
			setNumRows(Integer.parseInt(IO.readLine()));
			setNumColumns(Integer.parseInt(IO.readLine()));
			GameFrame.setCurrentTurn(Integer.parseInt(IO.readLine()));
			IO.closeInputFile();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		setSaveGrid((FieldButton[][]) IO.readObject("Game.fangerbanger"));
	}
	
	public static void saveOptions()
	{
		IO.createOutputFile("Options.fangerbanger");
		IO.println(isFullMap() + "");
		IO.println(isLandGrab() + "");
		IO.println(isAttrition() + "");
		IO.println(isWinnerKeepsAll() + "");
		IO.println(getNumPlayers() + "");
		IO.println(getNumRows() + "");
		IO.println(getNumColumns() + "");
		IO.println(GameFrame.getTurn() + "");
		IO.closeOutputFile();
		
		IO.createOutputFile("Game.fangerbanger");
		try 
		{
			for(int i = 0; i < getNumRows(); i++)
			{
				for(int j = 0; j < getNumColumns(); j++)
					System.out.println(GameFrame.getGrid()[i][j].toString());
			}
			IO.writeObject(GameFrame.getGrid(), "Game.fangerbanger");
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		IO.closeOutputFile();
	}
	
	public static int getNumPlayers()
	{
		return numPlayers;
	}
	
	public static int getNumRows()
	{
		return numRows;
	}
	
	public static int getNumColumns()
	{
		return numColumns;
	}
	
	public static FieldButton[][] getSaveGrid()
	{
		return saveGrid;
	}
	
	public static boolean isFullMap()
	{
		return chooseFullMap;
	}
	
	public static boolean isLandGrab()
	{
		return chooseLandGrab;
	}
	
	public static boolean isAttrition()
	{
		return chooseAttrition;
	}
	
	public static boolean isWinnerKeepsAll()
	{
		return chooseWinnerKeepsAll;
	}
	
	public static void setNumPlayers(int players)
	{
		numPlayers = players;
	}
	
	public static void setNumRows(int rows)
	{
		numRows = rows;
	}
	
	public static void setNumColumns(int columns)
	{
		numColumns = columns;
	}
	
	public static void setFullMap(boolean state)
	{
		chooseFullMap = state;
	}
	
	public static void setLandGrab(boolean state)
	{
		chooseLandGrab = state;
	}
	
	public static void setAttrition(boolean state)
	{
		chooseAttrition = state;
	}
	
	public static void setWinnerKeepsAll(boolean state)
	{
		chooseWinnerKeepsAll = state;
	}
	
	public static void setSaveGrid(FieldButton[][] grid)
	{
		saveGrid = grid;
	}
}
