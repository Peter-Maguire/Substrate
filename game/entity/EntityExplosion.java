package game.entity;


import game.screen.ScreenGame;

import java.awt.Graphics;
import java.util.Random;

public class EntityExplosion extends Entity{

	private int x, y, size, animstage = 0;
	private ScreenGame game;
	private Random rand = new Random();
	public EntityExplosion(ScreenGame game, int x, int y, int size) {
		super(game);
		this.x = x;
		this.y = y;
		this.size = size;
		this.game = game;
		this.sprite = -1;
	}
	
	public EntityExplosion()
	{
		sprite = 35;
	}
	
	
	@Override
	public void tick()
	{
		animstage ++;
		if(animstage == 256)this.forRemoval = true;

	}
	
	@Override
	public void render(Graphics g)
	{
		int scale = size*16;
	
		
		g.drawImage(game.game.sheetExplosions.getImage(animstage), x+20, y+5, scale, scale, game.game);



		
	}

}
