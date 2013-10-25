package game;

import game.entity.SerialEntity;
import game.tile.Tile;
import game.triggers.Trigger;



import java.io.Serializable;
import java.util.ArrayList;


public class Map implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6213525753733162443L;

	public String name = "Corrupted map", desc = "This map is corrupted", version = "v0.0.0";
	public boolean isLocked = true, isLevel = false;
	public int tx, ty, ts;
	public int xTiles = Game.XTILES;
	public int yTiles = Game.YTILES;
	public String hint;
	

	//public HashMap<Rectangle, Tile> tiles = new HashMap<Rectangle, Tile>();
	
	public Tile[][] tiles = new Tile[xTiles][yTiles];
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
	
	public Map()
	{
	}
	/**
	 * Returns the tile at position
	 * @param map
	 * @param x
	 * @param y
	 * @return null if no tile
	 */
	public static Tile getTileAt(Map map, int x, int y) {
		return getTileAt(map.tiles, x, y);
	}
	
	/**
	 * Returns the tile at position
	 * @param tiles The arraylist of tiles
	 * @param x 
	 * @param y
	 * @return null if no tile
	 */
	public static Tile getTileAt(Tile[][] tiles, int x, int y) {
		if(x >= Game.XTILES || y >= Game.YTILES)
			return Tile.tiles[0];
		return tiles[x][y];
	}
	
	/**
	 * Sets the tile at the position
	 * @param tiles The tile array to modify
	 * @param x
	 * @param y
	 * @param tile The tile as a tile ID
	 * @return The modified array of tiles
	 */
	public static Tile[][] setTileAt(Tile[][] tiles, int x, int y, int tile) {
		if(x >= Game.XTILES || y >= Game.YTILES)
			return tiles;
		tiles[x][y] = Tile.tiles[tile];
		return tiles;
	}

	/**
	 * Sets the tile at the position
	 * Should be used as <code>m = setTileAt(m, x, y, t);</code>
	 * @param map The map to get the tile array from
	 * @param x
	 * @param y
	 * @param tile The tile as a tile ID
	 * @return The modified array of tiles
	 */
	public static Map setTileAt(Map map, int x, int y, int tile) {
		if(x >= Game.XTILES || y >= Game.YTILES)
			return map;
		map.tiles[x][y] = Tile.tiles[tile];
		return map;
	}
	
	/**
	 * Sets the tile at the position
	 * @param tiles The tile array to use
	 * @param x
	 * @param y
	 * @param tile The tile as a tile instance
	 * @return The modified tile array
	 */
	public static Tile[][] setTileAt(Tile[][] tiles, int x, int y, Tile tile) {
		if(x >= Game.XTILES || y >= Game.YTILES)
			return tiles;
		tiles[x][y] = tile;
		return tiles;
	}

	/**
	 * Sets the tile at the position
	 * Should be used as <code>m = setTileAt(m, x, y, t);</code>
	 * @param map The map to get the tile array from
	 * @param x
	 * @param y
	 * @param tile The tile as a tile instance
	 * @return The modified map
	 */
	public static Map setTileAt(Map map, int x, int y, Tile tile) {
		if(x >= Game.XTILES || y >= Game.YTILES)
			return map;
		map.tiles[x][y] = tile;
		return map;
	}


}
