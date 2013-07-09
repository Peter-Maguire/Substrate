package game;

import game.entity.SerialEntity;
import game.tile.Tile;

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

	public HashMap<Rectangle, Tile> tiles = new HashMap<Rectangle, Tile>();
	public ArrayList<SerialEntity> entities = new ArrayList<SerialEntity>();

	/**
	 * 
	 * @param name Name of map
	 * @param desc Description
	 * @param version Map version - Not game version
	 * @param tiles Tiles on map
	 * @param entities Saveable version of map entities, see <code>FileSaver.class</code>
	 */
	public Map(String name, String desc, String version,
			HashMap<Rectangle, Tile> tiles, ArrayList<SerialEntity> entities) {
		this.name = name;
		this.desc = desc;
		this.version = version;
		this.tiles = tiles;
		this.entities = entities;
	}

}
