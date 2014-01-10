import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;

public class CustomMaps implements ActionListener
{
	public CustomMaps()
	{
		createMaps(getCleanGrid());
	}
	
	private FieldButton[][] getCleanGrid() 
	{
		FieldButton[][] clearGridField = new FieldButton[7][7];
		for(int i = 0; i < 7; i++)
		{
			for(int j = 0; j < 7; j++)
			{
				clearGridField[i][j] = new FieldButton(j, i);
				clearGridField[i][j].setOwnedBy(FieldButton.EMPTY_FIELD);
				clearGridField[i][j].setMaxArmy(12);
				clearGridField[i][j].setArmy(0);
				clearGridField[i][j].setText("");
				clearGridField[i][j].setBorder(BorderFactory.createBevelBorder(0, Color.LIGHT_GRAY, Color.BLACK));
				clearGridField[i][j].setIcon(GameFrame.getTerrainImage(FieldButton.EMPTY_FIELD));
				clearGridField[i][j].setActionCommand(i + ", " + j);
				clearGridField[i][j].addActionListener(this);
			}
		}
		
		return clearGridField;
	}
	
	private void createMaps(FieldButton[][] initGrid)
	{
		createBridgeMap(initGrid);
		createKOTHMap(initGrid);
		createValleyMap(initGrid);
		createIslandsMap(initGrid);
		createFFAMap(initGrid);
	}
	
	private void createBridgeMap(FieldButton[][] initGrid)
	{
		FieldButton[][] customGrid1 = FieldButton.clone(initGrid);
		for(int i = 0; i < customGrid1[3].length; i++)
		{
			if(i != 3)
			{
				customGrid1[3][i].setOwnedBy(FieldButton.TERRAIN_FIELD);
				customGrid1[3][i].setIcon(GameFrame.getTerrainImage(FieldButton.TERRAIN_WATER));
				customGrid1[3][i].setText("");
				System.out.println("Set Image");
			}
				
		}
		
		customGrid1[0][3].setArmy(2);
		customGrid1[0][3].setOwnedBy(1);
		customGrid1[0][3].setFieldColor(FieldButton.getColor(customGrid1[0][3].getOwner()));
		
		customGrid1[6][3].setArmy(2);
		customGrid1[6][3].setOwnedBy(2);
		customGrid1[6][3].setFieldColor(FieldButton.getColor(customGrid1[6][3].getOwner()));
		
		
		try {
			IO.writeObject(customGrid1, "Maps/jfang1.map");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void createKOTHMap(FieldButton[][] initGrid)
	{
		FieldButton[][] customGrid2 = FieldButton.clone(initGrid);
		customGrid2[0][0].setArmy(2);
		customGrid2[0][0].setOwnedBy(1);
		customGrid2[0][0].setFieldColor(FieldButton.getColor(customGrid2[0][0].getOwner()));
		
		customGrid2[0][6].setArmy(2);
		customGrid2[0][6].setOwnedBy(2);
		customGrid2[0][6].setFieldColor(FieldButton.getColor(customGrid2[0][6].getOwner()));
		
		customGrid2[6][0].setArmy(2);
		customGrid2[6][0].setOwnedBy(3);
		customGrid2[6][0].setFieldColor(FieldButton.getColor(customGrid2[6][0].getOwner()));
		
		customGrid2[6][6].setArmy(2);
		customGrid2[6][6].setOwnedBy(4);
		customGrid2[6][6].setFieldColor(FieldButton.getColor(customGrid2[6][6].getOwner()));
		
		for(int i = 0; i < initGrid[1].length; i++)
		{
			if(i != 0 && i != 3 && i != 6)
			{
				customGrid2[1][i].setOwnedBy(FieldButton.TERRAIN_FIELD);
				customGrid2[1][i].setIcon(GameFrame.getTerrainImage(FieldButton.TERRAIN_WATER));
			}
		}
		
		for(int i = 0; i < initGrid[1].length; i++)
		{
			if(i != 0 && i != 3 && i != 6)
			{
				customGrid2[5][i].setOwnedBy(FieldButton.TERRAIN_FIELD);
				customGrid2[5][i].setIcon(GameFrame.getTerrainImage(FieldButton.TERRAIN_WATER));
			}
		}
		
		for(int i = 0; i < initGrid[1].length; i++)
		{
			if(i != 0 && i != 3 && i != 6)
			{
				customGrid2[i][1].setOwnedBy(FieldButton.TERRAIN_FIELD);
				customGrid2[i][1].setIcon(GameFrame.getTerrainImage(FieldButton.TERRAIN_WATER));
			}	
		}
		
		for(int i = 0; i < initGrid[1].length; i++)
		{
			if(i != 0 && i != 3 && i != 6)
			{
				customGrid2[i][5].setOwnedBy(FieldButton.TERRAIN_FIELD);
				customGrid2[i][5].setIcon(GameFrame.getTerrainImage(FieldButton.TERRAIN_WATER));
			}
				
		}
		
		customGrid2[3][3].setOwnedBy(FieldButton.TERRAIN_FIELD);
		customGrid2[3][3].setIcon(GameFrame.getTerrainImage(FieldButton.TERRAIN_TREE));
		
		try {
			IO.writeObject(customGrid2, "Maps/jfang2.map");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private void createValleyMap(FieldButton[][] initGrid) 
	{
		FieldButton[][] customGrid3 = FieldButton.clone(initGrid);
		customGrid3[0][6].setArmy(2);
		customGrid3[0][6].setOwnedBy(1);
		customGrid3[0][6].setFieldColor(FieldButton.getColor(customGrid3[0][6].getOwner()));
		
		customGrid3[6][0].setArmy(2);
		customGrid3[6][0].setOwnedBy(2);
		customGrid3[6][0].setFieldColor(FieldButton.getColor(customGrid3[6][0].getOwner()));
		
		customGrid3[0][2].setOwnedBy(FieldButton.TERRAIN_FIELD);
		customGrid3[0][2].setIcon(GameFrame.getTerrainImage(FieldButton.TERRAIN_ROCK));
		
		customGrid3[1][2].setOwnedBy(FieldButton.TERRAIN_FIELD);
		customGrid3[1][2].setIcon(GameFrame.getTerrainImage(FieldButton.TERRAIN_TREE));
		
		customGrid3[6][4].setOwnedBy(FieldButton.TERRAIN_FIELD);
		customGrid3[6][4].setIcon(GameFrame.getTerrainImage(FieldButton.TERRAIN_ROCK));
		
		customGrid3[5][4].setOwnedBy(FieldButton.TERRAIN_FIELD);
		customGrid3[5][4].setIcon(GameFrame.getTerrainImage(FieldButton.TERRAIN_TREE));
		
		for(int i = 1; i < customGrid3.length-1; i++)
		{
			if(i < 3)
			{
				if(i == 2)
				{
					customGrid3[i][5].setOwnedBy(FieldButton.TERRAIN_FIELD);
					customGrid3[i][5].setIcon(GameFrame.getTerrainImage(FieldButton.TERRAIN_WATER));
				}
				customGrid3[i][0].setOwnedBy(FieldButton.TERRAIN_FIELD);
				customGrid3[i][0].setIcon(GameFrame.getTerrainImage(FieldButton.TERRAIN_WATER));
				
				customGrid3[i][4].setOwnedBy(FieldButton.TERRAIN_FIELD);
				customGrid3[i][4].setIcon(GameFrame.getTerrainImage(FieldButton.TERRAIN_WATER));
			}
			else if(i > 3)
			{
				if(i == 4)
				{
					customGrid3[i][1].setOwnedBy(FieldButton.TERRAIN_FIELD);
					customGrid3[i][1].setIcon(GameFrame.getTerrainImage(FieldButton.TERRAIN_WATER));
				}
				customGrid3[i][6].setOwnedBy(FieldButton.TERRAIN_FIELD);
				customGrid3[i][6].setIcon(GameFrame.getTerrainImage(FieldButton.TERRAIN_WATER));
				
				customGrid3[i][2].setOwnedBy(FieldButton.TERRAIN_FIELD);
				customGrid3[i][2].setIcon(GameFrame.getTerrainImage(FieldButton.TERRAIN_WATER));
			}
			else
			{
				customGrid3[i][0].setOwnedBy(FieldButton.TERRAIN_FIELD);
				customGrid3[i][0].setIcon(GameFrame.getTerrainImage(FieldButton.TERRAIN_WATER));
				
				customGrid3[i][1].setOwnedBy(FieldButton.TERRAIN_FIELD);
				customGrid3[i][1].setIcon(GameFrame.getTerrainImage(FieldButton.TERRAIN_WATER));
				
				customGrid3[i][5].setOwnedBy(FieldButton.TERRAIN_FIELD);
				customGrid3[i][5].setIcon(GameFrame.getTerrainImage(FieldButton.TERRAIN_WATER));
				
				customGrid3[i][6].setOwnedBy(FieldButton.TERRAIN_FIELD);
				customGrid3[i][6].setIcon(GameFrame.getTerrainImage(FieldButton.TERRAIN_WATER));
			}
		}
		
		try {
			IO.writeObject(customGrid3, "Maps/jfang3.map");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void createIslandsMap(FieldButton[][] initGrid) 
	{
		FieldButton[][] customGrid4 = FieldButton.clone(initGrid);
		
		customGrid4[0][0].setArmy(2);
		customGrid4[0][0].setOwnedBy(1);
		customGrid4[0][0].setFieldColor(FieldButton.getColor(customGrid4[0][0].getOwner()));
		
		customGrid4[6][6].setArmy(2);
		customGrid4[6][6].setOwnedBy(2);
		customGrid4[6][6].setFieldColor(FieldButton.getColor(customGrid4[6][6].getOwner()));
		
		if(Options.getNumPlayers() == 4)
		{
			customGrid4[0][6].setArmy(2);
			customGrid4[0][6].setOwnedBy(3);
			customGrid4[0][6].setFieldColor(FieldButton.getColor(customGrid4[0][6].getOwner()));
			
			customGrid4[6][0].setArmy(2);
			customGrid4[6][0].setOwnedBy(4);
			customGrid4[6][0].setFieldColor(FieldButton.getColor(customGrid4[6][0].getOwner()));
		}
		
		for(int i = 0; i < customGrid4.length; i++)
		{
			if(i != 2 && i != 4)
			{
				if(i == 0 || i == 6)
				{
					customGrid4[3][i].setOwnedBy(FieldButton.TERRAIN_FIELD);
					customGrid4[3][i].setIcon(GameFrame.getTerrainImage(FieldButton.TERRAIN_WATER));
					
					customGrid4[i][3].setOwnedBy(FieldButton.TERRAIN_FIELD);
					customGrid4[i][3].setIcon(GameFrame.getTerrainImage(FieldButton.TERRAIN_WATER));
				}
				
				customGrid4[2][i].setOwnedBy(FieldButton.TERRAIN_FIELD);
				customGrid4[2][i].setIcon(GameFrame.getTerrainImage(FieldButton.TERRAIN_WATER));
				
				customGrid4[4][i].setOwnedBy(FieldButton.TERRAIN_FIELD);
				customGrid4[4][i].setIcon(GameFrame.getTerrainImage(FieldButton.TERRAIN_WATER));
				
				customGrid4[i][2].setOwnedBy(FieldButton.TERRAIN_FIELD);
				customGrid4[i][2].setIcon(GameFrame.getTerrainImage(FieldButton.TERRAIN_WATER));
				
				customGrid4[i][4].setOwnedBy(FieldButton.TERRAIN_FIELD);
				customGrid4[i][4].setIcon(GameFrame.getTerrainImage(FieldButton.TERRAIN_WATER));
			}
		}
		
		try {
			IO.writeObject(customGrid4, "Maps/jfang4.map");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void createFFAMap(FieldButton[][] initGrid) 
	{
		FieldButton[][] customGrid5 = FieldButton.clone(initGrid);
		
		customGrid5[0][0].setArmy(2);
		customGrid5[0][0].setOwnedBy(1);
		customGrid5[0][0].setFieldColor(FieldButton.getColor(customGrid5[0][0].getOwner()));
		
		customGrid5[0][6].setArmy(2);
		customGrid5[0][6].setOwnedBy(2);
		customGrid5[0][6].setFieldColor(FieldButton.getColor(customGrid5[0][6].getOwner()));
		
		customGrid5[6][0].setArmy(2);
		customGrid5[6][0].setOwnedBy(3);
		customGrid5[6][0].setFieldColor(FieldButton.getColor(customGrid5[6][0].getOwner()));
		
		customGrid5[6][6].setArmy(2);
		customGrid5[6][6].setOwnedBy(4);
		customGrid5[6][6].setFieldColor(FieldButton.getColor(customGrid5[6][6].getOwner()));
		
		for(int i = 0; i < customGrid5.length; i++)
		{
			if(i != 2 && i != 4)
			{
				customGrid5[i][2].setOwnedBy(FieldButton.TERRAIN_FIELD);
				customGrid5[i][2].setIcon(GameFrame.getTerrainImage(FieldButton.TERRAIN_WATER));
				
				customGrid5[i][4].setOwnedBy(FieldButton.TERRAIN_FIELD);
				customGrid5[i][4].setIcon(GameFrame.getTerrainImage(FieldButton.TERRAIN_WATER));
			}
			
			if(i != 1 && i != 3 && i != 5)
			{
				customGrid5[i][3].setOwnedBy(FieldButton.TERRAIN_FIELD);
				customGrid5[i][3].setIcon(GameFrame.getTerrainImage(FieldButton.TERRAIN_WATER));
			}
			
			if(i == 3)
			{
				customGrid5[i][3].setOwnedBy(FieldButton.TERRAIN_FIELD);
				customGrid5[i][3].setIcon(GameFrame.getTerrainImage(FieldButton.TERRAIN_TREE));
			}
		}
		
		try {
			IO.writeObject(customGrid5, "Maps/jfang5.map");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void actionPerformed(ActionEvent e) 
	{
		
	}
}
