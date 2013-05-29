package game.entity;

import game.Game;
import game.screen.ScreenGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

public class EntityBox extends Entity{
	

	public EntityBox(ScreenGame game, int x, int y) {
		super(game);
		this.x = x;
		this.y = y;
		this.sprite = 8;
	}
	
	public EntityBox() {
		this.sprite = 8;
	}
	
	@Override
	public void tick()
	{
		
	}
	
	@Override
	public boolean tryMoveEntity(int x, int y)
	{
		
		
		if(x == 1) // Player is moving forwards 
		{
				ArrayList<Entity>eib = ((ScreenGame) game).getEntitiesInBox(new Rectangle(this.x+x - 10 , this.y+y - 20, Game.SIZE, Game.SIZE));
			if(eib.size() > 1)
			{
				for(Entity e : eib)
				{
					if(e instanceof EntityBox)
						return false;
					}
				}				
			}
		
		if(x == -1) // Player is moving backwards
		{
				ArrayList<Entity>eib = ((ScreenGame) game).getEntitiesInBox(new Rectangle(this.x+x - 25 , this.y+y - 20, Game.SIZE, Game.SIZE));
			if(eib.size() > 1)
			{
				for(Entity e : eib)
				{
					if(e instanceof EntityBox)
					{
						return false;
					}
				}				
			}
		}
		if(y == 1) // Player is moving up 
		{
				ArrayList<Entity>eib = ((ScreenGame) game).getEntitiesInBox(new Rectangle(this.x+x - 25 , this.y+y - 5, Game.SIZE, Game.SIZE));
			if(eib.size() > 1)
			{
				for(Entity e : eib)
				{
					if(e instanceof EntityBox)
					{
						return false;
					}
				}				
			}
		}
		if(y == -1) // Player is moving down
		{
				ArrayList<Entity>eib = ((ScreenGame) game).getEntitiesInBox(new Rectangle(this.x+x - 25 , this.y+y - 25, Game.SIZE, Game.SIZE));
			if(eib.size() > 1)
			{
				for(Entity e : eib)
				{
					if(e instanceof EntityBox)
					{
						return false;
					}
				}				
			}
		}
		super.tryMoveEntity(x, y);
		return false;
		}

	

	
	@Override
	public void onCollideWithPlayer(int x, int y)
	{
		tryMoveEntity(x, y);	
	}
	
	

}
