package game.mapeditor.tools;

import game.screen.ScreenMapEditor;
import game.tile.Tile;

import java.awt.Point;

public class ToolBox extends Tool{

	
	private boolean hasPlacedFirstPoint = false;
	private Point point1, point2;
	
	
	public ToolBox(String toolName, int sprite) {
		super(toolName, sprite);
	}
	
	
	@Override
	public void onToolUsed(int x, int y, ScreenMapEditor screen)
	{
		if(!hasPlacedFirstPoint)
		{
			point1 = new Point(x, y);
			screen.setTileAt(x, y, Tile.tiles[3]);
			hasPlacedFirstPoint = true;
			return;
			
		}
		if(hasPlacedFirstPoint)
		{
			point2 = new Point(x, y);
			
			for(int cy = point1.y-32; cy != point2.y; cy--)
			{
				screen.setTileAt(point1.x, cy, Tile.tiles[10]);
				screen.setTileAt(point2.x, cy, Tile.tiles[10]);
			}
			screen.setTileAt(x, y, Tile.tiles[3]);
			for(int cx = point1.x+32; cx < point2.x-32; cx++)
			{
				screen.setTileAt(cx, point1.y, Tile.tiles[7]);
				screen.setTileAt(cx, point2.y, Tile.tiles[7]);
			}
			
			screen.setTileAt(point1.x, point1.y, Tile.tiles[3]);
			screen.setTileAt(point2.x, point1.y, Tile.tiles[4]);
			screen.setTileAt(point2.x, point2.y, Tile.tiles[5]);
			screen.setTileAt(point1.x, point2.y, Tile.tiles[6]);
			
			
			
		
		
		
			
			hasPlacedFirstPoint = false;
			point1 = null;
			point2 = null;
			return;
		}
	}

}
