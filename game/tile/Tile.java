package game.tile;

import game.Game;
import java.io.Serializable;

public class Tile implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2215788549581724737L;
	public static Tile[] tiles = { new TileNoTile(), new TileGrass(),
			new TilePaving(), new TileWall(TileWall.BOTTOM_CORNER_LEFT),
			new TileWall(TileWall.BOTTOM_CORNER_RIGHT),
			new TileWall(TileWall.TOP_CORNER_LEFT),
			new TileWall(TileWall.TOP_CORNER_RIGHT),
			new TileWall(TileWall.WALL_HORIZONTAL),
			new TileWall(TileWall.WALL_HORIZONTAL_LEFT),
			new TileWall(TileWall.WALL_HORIZONTAL_RIGHT),
			new TileWall(TileWall.WALL_VERTICAL),
			new TileWall(TileWall.WALL_VERTICAL_BOTTOM),
			new TileWall(TileWall.WALL_VERTICAL_TOP), new TileWater(),
			new TileNoTile(), new TileRubble(TileRubble.RUBBLE_VERTICAL_TOP),
			new TileRubble(TileRubble.RUBBLE_VERTICAL_MIDDLE),
			new TileRubble(TileRubble.RUBBLE_VERTICAL_BOTTOM),
			new TileRubble(TileRubble.RUBBLE_TOP_CORNER_LEFT),
			new TileRubble(TileRubble.RUBBLE_TOP_CORNER_RIGHT),
			new TileRubble(TileRubble.RUBBLE_BOTTOM_CORNER_LEFT),
			new TileRubble(TileRubble.RUBBLE_BOTTOM_CORNER_RIGHT), };

	boolean canPassThrough = true;
	public int sprite = 0;

	public Tile() {

	}

	public void init() {
		Game.log("Tile initizalizing...");

		Game.log("Tile initizalized.");
	}

	public boolean isPassable() {
		return canPassThrough;
	}

	public void tick() {

	}
}
