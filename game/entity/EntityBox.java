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
		if(x >= this.x)
			tryMoveEntity(-1,0);
		if(x <= this.x)
			tryMoveEntity(1,0);
		if(y >= this.y)
			tryMoveEntity(0,-1);
		if(y <= this.y)
			tryMoveEntity(0,1);

		
	
	}
	
	

}
