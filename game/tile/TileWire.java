package game.tile;

import game.entity.Player;
import game.screen.ScreenGame;

public class TileWire extends WireProvider{

	public TileWire(ScreenGame game, int blockmask, int x, int y) {
		super(game, blockmask, x, y);
		sprite = 5;
	
	}
	
	@Override
	public void tick()
	{
	
		emitSignal(Player.EAST);
	}
	
	

}
