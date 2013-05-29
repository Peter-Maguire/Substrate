package game.mapeditor.tools;

import game.Game;
import game.screen.ScreenMapEditor;
import game.tile.Tile;

public class ToolReplace extends Tool{

	public ToolReplace(String toolName, int sprite) {
		super(toolName, sprite);
		
	}
	
	@Override
	public void onToolUsed(int x, int y, ScreenMapEditor screen)
	{
		Tile clickedTile = screen.getTileAt(x, y);
		for(int tx = 0; tx < Game.WIDTH; tx++)
		{
			for(int ty = 0; ty < Game.HEIGHT-100; ty++)
			{
				if(screen.getTileAt(tx, ty) == clickedTile)
				{
					screen.setTileAt(tx, ty, screen.currentTile);
				}
			}
		}
	}

}
