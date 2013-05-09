package game.tile;

import game.Game;
import game.screen.ScreenGame;

public class TileWall extends Tile{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6093326443610622363L;
	public static final int TOP_CORNER_LEFT = 1;
	public static final int BOTTOM_CORNER_LEFT = 17;
	public static final int TOP_CORNER_RIGHT = 2;
	public static final int BOTTOM_CORNER_RIGHT = 18;
	
	public static final int WALL_VERTICAL_DAMAGED_TOP = 3;
	
	
	public static final int WALL_HORIZONTAL_LEFT = 4;
	public static final int WALL_HORIZONTAL = 5;
	public static final int WALL_HORIZONTAL_RIGHT = 6;
	
	public static final int WALL_VERTICAL_TOP = 0;
	public static final int WALL_VERTICAL = 16;
	public static final int WALL_VERTICAL_BOTTOM = 32;
	
	int x, y;
	
	
	public TileWall(int typ) {
		this.x = x;
		this.y = y;
		sprite = typ;
		canPassThrough = false;
	
	}
	
	@Override
	public void tick()
	{
		if(game.getTileAt(x, y-16*Game.SCALE) == this)
		{
			sprite = WALL_VERTICAL;
		}else
		{
			sprite = 16;
		}
	}

}
