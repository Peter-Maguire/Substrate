package game.entity;

import game.screen.ScreenGame;

import java.util.Random;

public class EntitySoldier extends Entity{

	private static final long serialVersionUID = 6404228773270715706L;
	
	
	private static final int 
			IDLE_LEFT = 16,
			IDLE_RIGHT = 17,
			IDLE_LEFT_HATLESS = 18,
			IDLE_RIGHT_HATLESS = 19,
			AIM_LEFT = 32, AIM_RIGHT = 33,
			AIM_LEFT_HATLESS = 34,
			AIM_RIGHT_HATLESS = 35;
	
	private Random rand = new Random();
	
	private ScreenGame screen;
	
	public EntitySoldier(ScreenGame game, int x, int y) {
		this.screen = game;
		this.game = game.game;
		sprite = 16;
		this.x = x;
		this.y = y;
	}
	
	@Override
	public void tick()
	{
		super.tick();

		pathTo(screen.player.x, screen.player.y);
	}
	
	
	private void idle()
	{
		int walkDirection = rand.nextInt(50);
		
		if(walkDirection == 0)return;
		
		switch(walkDirection)
		{
		case 1:
			tryMoveEntity(1,0);
			break;
		case 2:
			tryMoveEntity(-1,0);
			break;
		case 3:
			tryMoveEntity(0,1);
			break;
		case 4:
			tryMoveEntity(0,-1);
			break;
			
		}
	}
	
	private void pathTo(int x, int y)
	{
		
			if(this.x < x)
			{
				tryMoveEntity(1,0);
		
			}

			if(this.x > x)
			{
				tryMoveEntity(-1,0);
				
			}
			
			if(this.y < y)
			{
				tryMoveEntity(0, 1);
				
			}

			if(this.y > y)
			{
				tryMoveEntity(0,-1);
			}
		
		

	}


}
