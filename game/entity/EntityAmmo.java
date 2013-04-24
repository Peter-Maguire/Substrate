package game.entity;

import game.Game;
import game.screen.ScreenGame;

import java.awt.Graphics;
import java.awt.Rectangle;

public class EntityAmmo extends Entity{

	
	private ScreenGame game;
	public EntityAmmo(ScreenGame game, int x, int y) {
		super(game);
		this.game = game;
		this.x = x;
		this.y = y;
		sprite = 9;
	}
	
	@Override
	public void tick()
	{
		if(game.getEntityInBox(new Rectangle(x, y, 16, 16)) != null)
		{
			Entity ent = game.getEntityInBox(new Rectangle(x, y, 16 * Game.SCALE, 16 * Game.SCALE));
			if(ent instanceof Player)
			{
				
				if(((Player) ent).getAmmo() < 20)
				{
					((Player) ent).setAmmo(((Player) ent).getAmmo()+5);
					this.forRemoval = true;
				}
				
			
			}
			
		}
	}
	


}
