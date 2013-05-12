package game.entity;

import game.screen.ScreenGame;

public class EntityBox extends Entity{

	public EntityBox(ScreenGame game, int x, int y) {
		super(game);
		this.x = x;
		this.y = y;
		this.sprite = 27;
	}
	
	@Override
	public void tick()
	{
		
	}
	
	@Override
	public void onCollideWithPlayer(int x, int y)
	{
		tryMoveEntity(1, 0);
	
	}
	
	

}
