package game.tile;

import game.entity.Player;
import game.screen.ScreenGame;

public class TileWire extends WireProvider{

	public TileWire(int blockmask, int x, int y, ScreenGame game){
		super(blockmask, x, y, game);
		sprite = 5;
	
	}
	
	@Override
	public void tick()
	{
		sprite = 5;
	}
	
	@Override
	public void onReceiveSignal(int direction)
	{
		sprite = 6;
		emitSignal(Player.EAST);
	}	
	
	

}
