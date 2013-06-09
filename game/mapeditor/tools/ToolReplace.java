package game.mapeditor.tools;

import java.awt.Rectangle;

import game.screen.ScreenMapEditor;
import game.tile.Tile;

public class ToolReplace extends Tool {

	public ToolReplace(String toolName, int sprite) {
		super(toolName, sprite);

	}

	@Override
	public void onToolUsed(int x, int y, ScreenMapEditor screen) {
		Tile clickedTile = screen.getTileAt(x, y);

		for (int i = 0; i < screen.tiles.keySet().size(); i++) {
			Rectangle rec = (Rectangle) screen.tiles.keySet().toArray()[i];

			if (screen.tiles.get(rec) == clickedTile) {
				screen.setTileAt(rec.x, rec.y, screen.currentTile);
			}

		}

	}

}
