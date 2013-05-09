package game.tile;

import game.entity.Player;
import game.screen.ScreenGame;

public class TileWire extends WireProvider{

	public TileWire(int blockmask, int x, int y) {
		super(blockmask, x, y);
		sprite = 5;
	
	}
	
	@Override
	public void tick()
	{
	
		emitSignal(Player.EAST);
	}
	
	

}
