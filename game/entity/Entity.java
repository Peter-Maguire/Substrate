package game.entity;

import game.Game;
import game.screen.ScreenGame;
import game.tile.Tile;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Entity {

	public int sprite = 1;
	protected ScreenGame game;

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

	public void render(Graphics g) {

		g.drawImage(game.game.sheet.getImage(sprite), x, y, 16 * Game.SCALE,
				16 * Game.SCALE, game.game);
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

		
		
		

		
		if (x == 0 && y == 0)
			return false;
		if ((this.x + x) - Game.SCALE == Game.WIDTH
				|| (this.y + y) - Game.SCALE == Game.HEIGHT)
			return false;

		Tile tile;
		if (x == -1) { // If entity is moving backwards
			if(this instanceof Player){
				ArrayList<Entity>eib = game.getEntitiesInBox(new Rectangle(this.x + x - 15, this.y, Game.SIZE, Game.SIZE));
			if(eib.size() > 1)
			{
				for(Entity e : eib)
				{
					if(e instanceof Player == false)
					{
						e.onCollideWithPlayer(this.x, this.y);
						return false;
					}
				}
					
								
			}}
			tile = game.getTileAt(this.x + x - 15, this.y);

			if (tile == null)
				return false;

			if (tile.isPassable()) {

				setPos(x + this.x, y + this.y);
				return true;
			} else {

				return false;
			}
		}

		if (x == 1) // If entity is moving forwards
		{
			if(this instanceof Player){
				ArrayList<Entity>eib = game.getEntitiesInBox(new Rectangle(this.x + x + 15, this.y, Game.SIZE, Game.SIZE));
			if(eib.size() > 1)
			{
				for(Entity e : eib)
				{
					if(e instanceof Player == false)
					{
						e.onCollideWithPlayer(this.x, this.y);
					}
				}
					
								
			}}
			tile = game.getTileAt(this.x + x + 15, this.y);

			if (tile == null)
				return false;

			if (tile.isPassable()) {

				setPos(x + this.x, y + this.y);
				return true;
			} else {

				return false;
			}
		}
		if (y == -1) { // If entity is moving up
			if(this instanceof Player){
				ArrayList<Entity>eib = game.getEntitiesInBox(new Rectangle(this.x + x, this.y - 15, Game.SIZE, Game.SIZE));
			if(eib.size() > 1)
			{
				for(Entity e : eib)
				{
					if(e instanceof Player == false)
					{
						e.onCollideWithPlayer(this.x, this.y);
					}
				}
					
								
			}}
			tile = game.getTileAt(this.x + x, this.y - 15);

			if (tile == null)
				return false;

			if (tile.isPassable()) {

				setPos(x + this.x, y + this.y);
				return true;
			} else {

				return false;
			}
		}

		if (y == 1) // If entity is moving down
		{
			if(this instanceof Player){
				ArrayList<Entity>eib = game.getEntitiesInBox(new Rectangle(this.x + x, this.y + 15, Game.SIZE, Game.SIZE));
			if(eib.size() > 1)
			{
				for(Entity e : eib)
				{
					if(e instanceof Player == false)
					{
						e.onCollideWithPlayer(this.x, this.y);
					}
				}
					
								
			}}
			tile = game.getTileAt(this.x + x, this.y + 15);

			if (tile == null)
				return false;

			if (tile.isPassable()) {

				setPos(x + this.x, y + this.y);
				return true;
			} else {

				return false;
			}
		}
		


		return false;

	}
	
	public void onCollideWithPlayer(int x, int y)
	{
		
	}

}
