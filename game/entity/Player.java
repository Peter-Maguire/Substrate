package game.entity;

import game.Controls;
import game.Game;
import game.screen.ScreenGame;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Player extends Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2248392686500480941L;
	public static final int NORTH = 0, EAST = 1, SOUTH = 2, WEST = 3;
	private int orientation = 0, health = 20;
	private int ammo = 0;
	public int hurtTimer = 0;
	private ScreenGame game2;
	public int ammocooldown = 0;

	public Player(ScreenGame game) {
		super(game);
		this.game2 = game;
		this.sprite = 0;

	}

	public Player() {
		this.sprite = 0;
	}

	@Override
	public boolean tryMoveEntity(int x, int y) {

/*		if (x == 1) // Player is moving forwards
		{
			ArrayList<Entity> eib = game2
					.getEntitiesInBox(new Rectangle(this.x + x - 10, this.y + y
							- 20, Game.SIZE, Game.SIZE));
			if (eib.size() > 1) {
				for (Entity e : eib) {
					if (!(e instanceof Player)) {
						e.onCollideWithPlayer(x, 0, this);
						return false;
					}
				}
			}
		}
		
		
		if (x == -1) // Player is moving backwards
		{
			ArrayList<Entity> eib = game2
					.getEntitiesInBox(new Rectangle(this.x + x - 25, this.y + y
							- 20, Game.SIZE, Game.SIZE));
			if (eib.size() > 1) {
				for (Entity e : eib) {
					if (!(e instanceof Player)) {
						e.onCollideWithPlayer(x, 0, this);
						return false;
					}
				}
			}
		}
		if (y == 1) // Player is moving up
		{
			ArrayList<Entity> eib = game2
					.getEntitiesInBox(new Rectangle(this.x + x - 25, this.y + y
							- 5, Game.SIZE, Game.SIZE));
			if (eib.size() > 1) {
				for (Entity e : eib) {
					if (!(e instanceof Player)) {
						e.onCollideWithPlayer(0, y, this);
						return false;
					}
				}
			}
		}
		if (y == -1) // Player is moving down
		{
			ArrayList<Entity> eib = game2
					.getEntitiesInBox(new Rectangle(this.x + x - 25, this.y + y
							- 25, Game.SIZE, Game.SIZE));
			if (eib.size() > 1) {
				for (Entity e : eib) {
					if (!(e instanceof Player)) {
						e.onCollideWithPlayer(0, y, this);
						return false;
					}
				}
			}
		}*/
		
		ArrayList<Entity> eib = game2.getEntitiesInBox(new Rectangle(x == 1 ? this.x + - 9 : this.x - 24, y == 1 ?  this.y -4 : this.y - 24, Game.SIZE, Game.SIZE));
			for (Entity e : eib) {
				if (!(e instanceof Player)) {
					e.onCollideWithPlayer(x, 0, this);
					return false;
				}
			}

		
		super.tryMoveEntity(x, y);
		return false;
	}

	@Override
	public void tick() {
		if (ammocooldown != 0) {
			ammocooldown--;
		}
		if (ammocooldown < 0) {
			ammocooldown = 256;
		}
		if (ammocooldown > 256) {
			ammocooldown = 0;
		}
		if(hurtTimer > 0)
			hurtTimer--;

	}
	
	public void hurt(int amount)
	{
		if(hurtTimer != 0)return;
		hurtTimer = 10 * amount;
		health -= amount;
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == game.controls.getKey(Controls.CONTROL_UP)) {
			sprite = 1;
			setOrientation(NORTH);

		}
		if (e.getKeyCode() == game.controls.getKey(Controls.CONTROL_DOWN)) {
			sprite = 0;
			setOrientation(SOUTH);

		}
		if (e.getKeyCode() == game.controls.getKey(Controls.CONTROL_LEFT)) {
			sprite = 3;
			setOrientation(WEST);

		}
		if (e.getKeyCode() == game.controls.getKey(Controls.CONTROL_RIGHT)) {
			sprite = 2;
			setOrientation(EAST);

		}
		if (e.getKeyCode() == game.controls.getKey(Controls.CONTROL_FIRE)) {
			if (ammo != 0 && ammocooldown == 0) {
				game2.spawnEntity(new EntityBullet(
						game2, 0, getOrientation(), x, y));
				ammo--;
				ammocooldown = 256;
			}
		}
	}

	public void keyReleased(KeyEvent e) {

	}

	public int getOrientation() {
		return orientation;
	}

	public void setOrientation(int or) {
		orientation = or;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int he) {
		health = he;
	}

	public int getAmmo() {
		return ammo;
	}

	public void setAmmo(int am) {
		this.ammo = am;
	}

}
