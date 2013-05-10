package game.tile;

import game.Game;
import game.entity.Player;
import game.screen.ScreenGame;

public class WireProvider extends Tile{

	
	public boolean powered = false;
	public int blockMask = 0, x, y;
	public WireProvider(int blockmask, int x, int y) {
		this.blockMask = blockMask;
		this.x = x;
		this.y = y;
	}
	
	public void emitSignal(int direction)
	{
	/*	Tile t = null;
		switch(direction)
		{
		case Player.EAST:
		  t = game.getTileAt(x+Game.SIZE, y);
		  break;
		case Player.NORTH:
			  t = game.getTileAt(x, y-Game.SIZE);
			  break;
		case Player.SOUTH:
			  t = game.getTileAt(x, y+Game.SIZE);
			  break;
		case Player.WEST:
			  t = game.getTileAt(x-Game.SIZE, y);
			  break;
		}
		System.out.println(t+" tile");*/
	
	}
	
	public void onReceiveSignal(int direction)
	{
		
	}

	
	
}
