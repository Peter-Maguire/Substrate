package game.entity;

import game.screen.ScreenGame;

import java.awt.Graphics;
import java.util.Random;

public class EntityCloud extends Entity
{

	Random rand = new Random();
	
	public EntityCloud()
	{
		sprite = 36;
	}
	public EntityCloud(ScreenGame game, int x, int y) {
		super(game);
		this.x = x;
		this.y = y;
		this.game = game.game;
		rand = new Random();
		this.sprite = 36+rand.nextInt(1);
	}
	
	@Override
	public void tick() {

		
		if(rand.nextInt(200) == 1)
		this.x -= 1;
			
		
	}
	
	
}
