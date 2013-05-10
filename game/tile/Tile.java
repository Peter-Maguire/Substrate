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
	public static Tile[]  tiles =
			{
		   null,
		   new TileGrass(),
		   new TilePaving(),
		   new TileRubble(),
		   new TileWall(TileWall.BOTTOM_CORNER_LEFT),
		   new TileWall(TileWall.BOTTOM_CORNER_RIGHT),
		   new TileWall(TileWall.TOP_CORNER_LEFT),
		   new TileWall(TileWall.TOP_CORNER_RIGHT),
		  new TileWall(TileWall.WALL_HORIZONTAL),
		   new TileWall(TileWall.WALL_HORIZONTAL_LEFT),
		   new TileWall(TileWall.WALL_HORIZONTAL_RIGHT),
		    new TileWall(TileWall.WALL_VERTICAL),
		    new TileWall(TileWall.WALL_VERTICAL_BOTTOM),
		    new TileWall(TileWall.WALL_VERTICAL_DAMAGED_TOP),
		 new TileWall(TileWall.WALL_VERTICAL_TOP)

			};

	boolean canPassThrough = true;
	public int sprite = 1;
	
	public Tile()
	{

		
	}
	
	public void init()
	{
		Game.log("Tile initizalizing...");
		
		Game.log("Tile initizalized.");
	}
	
	public boolean isPassable()
	{
		return canPassThrough;
	}
	
	
	
	public void tick()
	{
		
	}
}
