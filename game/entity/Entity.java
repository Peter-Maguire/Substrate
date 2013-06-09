package game.entity;

import game.Game;
import game.screen.ScreenGame;
import game.tile.Tile;

import java.awt.Graphics;
import java.io.Serializable;

public class Entity implements Serializable {

	private static final long serialVersionUID = -1098294243454921583L;
	public int sprite = 1;
	public Game game;

	public int x = 59, y = 50;
	public boolean forRemoval = false;
	int health = 20;

	public Entity(ScreenGame game) {
		this.game = game.game;
	}

	public Entity() {

	}

	public void tick() {
		if (health <= 0)
			onDeath();
	}

	/**
	 * Disregards any boundaries!
	 */
	public void setPos(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void render(Graphics g) {

		g.drawImage(game.sheetEntities.getImage(sprite), x, y, 16 * Game.SCALE,
				16 * Game.SCALE, game);
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

			tile = ((ScreenGame) game.currentScreen).getTileAt(this.x + x - 15,
					this.y);

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

			tile = ((ScreenGame) game.currentScreen).getTileAt(this.x + x + 15,
					this.y);

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

			tile = ((ScreenGame) game.currentScreen).getTileAt(this.x + x,
					this.y - 15);

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

			tile = ((ScreenGame) game.currentScreen).getTileAt(this.x + x,
					this.y + 15);

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

	public void onCollideWithPlayer(int x, int y, Player p) {

	}

	public void onDeath() {
		this.forRemoval = true;
	}

}
