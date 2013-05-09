package game.tile;

import game.Game;
import game.screen.ScreenGame;

import java.awt.Graphics;
import java.io.Serializable;

public class Tile implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2215788549581724737L;
	public static Tile[] tiles = new Tile[32];

	boolean canPassThrough = true;
	public int sprite = 1;
	protected ScreenGame game;
	
	public Tile()
	{
		this.game = game;
		tiles[0] = null;
		tiles[1] = new TileGrass();
		tiles[2] = new TilePaving();
		tiles[3] = new TileRubble();
		tiles[4] = new TileWall(TileWall.BOTTOM_CORNER_LEFT);
		tiles[5] = new TileWall(TileWall.BOTTOM_CORNER_RIGHT);
		tiles[6] = new TileWall(TileWall.TOP_CORNER_LEFT);
		tiles[7] = new TileWall(TileWall.TOP_CORNER_RIGHT);
		tiles[8] = new TileWall(TileWall.WALL_HORIZONTAL);
		tiles[9] = new TileWall(TileWall.WALL_HORIZONTAL_LEFT);
		tiles[10] = new TileWall(TileWall.WALL_HORIZONTAL_RIGHT);
		tiles[11] = new TileWall(TileWall.WALL_VERTICAL);
		tiles[12] = new TileWall(TileWall.WALL_VERTICAL_BOTTOM);
		tiles[13] = new TileWall(TileWall.WALL_VERTICAL_DAMAGED_TOP);
		tiles[14] = new TileWall(TileWall.WALL_VERTICAL_TOP);
		
	}
	
	public boolean isPassable()
	{
		return canPassThrough;
	}
	
	
	
	public void tick()
	{
		
	}
}
