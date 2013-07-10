package game.utils;

import game.Game;
import game.Map;
import game.entity.Entity;
import game.tile.Tile;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashMap;

public class MapUtils {

	/**
	 * Broken pathfinding
	 * @param startingPoint Point to start at
	 * @param destination Point to end at
	 * @param map Map
	 * @return Points to travel to
	 */
	public static ArrayList<Point> findPath(Point startingPoint,
			Point destination, Map map) {
		ArrayList<Point> path = new ArrayList<Point>();

		HashMap<Rectangle, Tile> tiles = map.tiles;

/*		for (int x = 0; x <= destination.x; x++) {
			for (int y = 0; y <= destination.y; y++) {

			}
		}*/

		return path;
	}

	/**
	 * Used to retrieve tiles at a certain point on the map
	 * @param x X Coordinate
	 * @param y Y Coordinate
	 * @param tiles List of tiles
	 * @return The tile instance at that point
	 */
	public static Tile getTileAt(int x, int y, HashMap<Rectangle, Tile> tiles) {
		Rectangle rec = new Rectangle(MathHelper.round(x, 16 * Game.SCALE),
				MathHelper.round(y, 16 * Game.SCALE), 16 * Game.SCALE,
				16 * Game.SCALE);
		return tiles.get(rec);
	}

	/**
	 * Used to set a tile at a certain point on the map
	 * @param x X Coordinate
	 * @param y Y Coordinate
	 * @param tile The tile to change to
	 * @param tiles The tiles
	 * @return New tile list
	 */
	public static HashMap<Rectangle, Tile> setTileAt(int x, int y, int tile,
			HashMap<Rectangle, Tile> tiles) {
		Rectangle rec = new Rectangle(MathHelper.round(x, 16 * Game.SCALE),
				MathHelper.round(y, 16 * Game.SCALE), 16 * Game.SCALE,
				16 * Game.SCALE);

		tiles.put(rec, Tile.tiles[tile]);
		return tiles;
	}

	/**
	 * Retrieves <b>one</b> entity in a rectangle.
	 * Quicker if only one entity is needed.
	 * @param rec Rectangle to search in
	 * @param entities Entity list
	 * @return The Entity instance
	 */
	public static Entity getEntityInBox(Rectangle rec,
			ArrayList<Entity> entities) {
		for (Entity ent : entities) {
			if (rec.contains(new Point(ent.x, ent.y))) {
				return ent;
			}
		}
		return null;
	}

	/**
	 * Same as <code>getEntityInBox</code> but retrieves more than one Entity
	 * @param rec Rectangle to search in
	 * @param entities Entities to search
	 * @return List of entities
	 */
	public static ArrayList<Entity> getEntitiesInBox(Rectangle rec,
			ArrayList<Entity> entities) {
		ArrayList<Entity> ents = new ArrayList<Entity>();
		for (Entity ent : entities) {
			if (rec.contains(new Rectangle(ent.x, ent.y, 32, 32))) {
				ents.add(ent);
			}
		}
		return ents;
	}
}
