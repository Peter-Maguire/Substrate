package game.mapeditor.tools;



import game.screen.ScreenMapEditor;
import game.tile.Tile;

public class ToolReplace extends Tool {

	public ToolReplace(String toolName, int sprite) {
		super(toolName, sprite);

	}

	@Override
	public void onToolUsed(int x, int y, ScreenMapEditor screen) {
		Tile clickedTile = screen.getTileAt(x, y);

			for(int x2 = 0; x2 < screen.tiles.length; x2++)
			{
				for(int y2 = 0; y2 < screen.tiles[x].length; y2++)
				{
					if(screen.getTileAt(x2, y2) == clickedTile)
					screen.setTileAt(x2, y2, screen.currentTile);
				}
			}

	}

}
