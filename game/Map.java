package game;

import game.entity.Entity;
import game.tile.Tile;

import java.awt.Rectangle;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Map implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6213525753733162443L;

	public String name = "Untitled", desc = "Untitled Map", version = "v0.0.0"; 

	public HashMap<Rectangle, Tile>tiles = new HashMap<Rectangle, Tile>();
	public ArrayList<Entity>entities = new ArrayList<Entity>();
	
	public Map(String name, String desc, String  version, HashMap<Rectangle, Tile> tiles, ArrayList<Entity> entities)
	{
		this.name = name;
		this.desc = desc;
		this.version = version;
		this.tiles = tiles;
		this.entities = entities;
	}
	
}
