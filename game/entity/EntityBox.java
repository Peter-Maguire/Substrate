package game.entity;

import game.Game;
import game.screen.ScreenGame;

import java.awt.Color;
import java.awt.Graphics;

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
	public boolean tryMoveEntity(int x, int y)
	{
		super.tryMoveEntity(x, y);
		
		return false;
	}
	

	
	@Override
	public void onCollideWithPlayer(int x, int y)
	{
		tryMoveEntity(x, y);	
	}
	
	

}
