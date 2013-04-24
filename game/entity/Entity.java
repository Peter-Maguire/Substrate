package game.entity;

import game.Game;
import game.screen.ScreenGame;
import game.tile.Tile;

import java.awt.Graphics;

public class Entity {

	public int sprite = 1;
	private ScreenGame game;

	public int x = 59, y = 50;
	public boolean forRemoval = false;
	int health = 20;

	public Entity(ScreenGame game) {
		this.game = game;
	}

	public void tick() {

	}

	/**
	 * Disregards any boundaries!
	 */
	public void setPos(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void render(Graphics g)
	{

		g.drawImage(game.game.sheet.getImage(sprite), x, y, 16 * Game.SCALE, 16 * Game.SCALE, game.game);
	}

	/**
	 * Attempts to move the entity.
	 * 
	 * @param x
	 *            Amount of pixels to move in the x direction
	 * @param y
	 *            Amount of pixels to move in the y direction
	 * @return If the movement is successful
	 */
	public boolean tryMoveEntity(int x, int y) {
		
		if(x == 0 && y == 0)return false;
		if((this.x+x)-Game.SCALE == Game.WIDTH || (this.y+y)-Game.SCALE == Game.HEIGHT)return false;
		
		Tile tile; 
		tile = game.getTileAt(this.x+x-15, this.y+y-15);
		
		
		if(tile == null)return false;
		
		if(tile.isPassable()) {

			setPos(x + this.x, y + this.y);
			return true;
		}else
		{
			
			return false;
		}

		
	}

}
