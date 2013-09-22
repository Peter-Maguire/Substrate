package game;

import game.entity.SerialEntity;
import game.tile.Tile;
import game.triggers.Trigger;

import java.awt.Rectangle;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Map implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6213525753733162443L;

	public String name = "Untitled", desc = "Untitled Map", version = "v0.0.0";
	public boolean isLocked = true, isLevel = false;

	//public HashMap<Rectangle, Tile> tiles = new HashMap<Rectangle, Tile>();
	public Tile[][] tiles = new Tile[Game.XTILES][Game.YTILES];
	public ArrayList<SerialEntity> entities = new ArrayList<SerialEntity>();
	public ArrayList<Trigger> triggers = new ArrayList<Trigger>();

	/**
	 * 
	 * @param name Name of map
	 * @param desc Description
	 * @param version Map version - Not game version
	 * @param tiles Tiles on map
	 * @param entities Saveable version of map entities, see <code>FileSaver.class</code>
	 */
	public Map(String name, String desc, String version,
			Tile[][] tiles, ArrayList<SerialEntity> entities, ArrayList<Trigger> triggers) {
		this.name = name;
		this.desc = desc;
		this.version = version;
		this.tiles = tiles;
		this.entities = entities;
		this.triggers = triggers;
	}
	/**
	 * Returns the tile at position
	 * @param map
	 * @param x
	 * @param y
	 * @return null if no tile
	 */
	public static Tile getTileAt(Map map, int x, int y) {
		return map.tiles[x][y];
	}
	
	public static Tile getTileAt(Tile[][] tiles, int x, int y) {
		return tiles[x][y];
	}
	
	public static Tile[][] setTileAt(Tile[][] tiles, int x, int y, int tile) {
		tiles[x][y] = Tile.tiles[tile];
		return tiles;
	}

	public static Map setTileAt(Map map, int x, int y, int tile) {
		map.tiles[x][y] = Tile.tiles[tile];
		return map;
	}
	
	public static Tile[][] setTileAt(Tile[][] tiles, int x, int y, Tile tile) {
		tiles[x][y] = tile;
		return tiles;
	}

	public static Map setTileAt(Map map, int x, int y, Tile tile) {
		map.tiles[x][y] = tile;
		return map;
	}


}
