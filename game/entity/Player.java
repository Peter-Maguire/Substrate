package game.entity;

import java.awt.event.KeyEvent;

import game.Controls;
import game.screen.Screen;
import game.screen.ScreenGame;
import game.screen.ScreenMultiplayer;

public class Player extends Entity {

	public static final int NORTH = 0, EAST = 1, SOUTH = 2, WEST = 3;
	Screen game;
	private int texAnim = 0, orientation = 0, health = 20, ammo = 5;
	public int ammocooldown = 256;

	public Player(Screen game) {
		super((ScreenGame) game);
		this.game =  game;
		this.sprite = 45;

	}

	@Override
	public void tick() {
		if(ammocooldown != 0)
		{
			ammocooldown--;
		}
		if(ammocooldown < 0)
		{
			ammocooldown = 256;
		}
		if(ammocooldown > 256)
		{
			ammocooldown = 0;
		}
		

	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == game.game.controls.getKey(Controls.CONTROL_UP)) {
			sprite = 45;
			setOrientation(NORTH);
			texAnim++;
		}
		if (e.getKeyCode() == game.game.controls.getKey(Controls.CONTROL_DOWN)) {
			sprite = 44;
			setOrientation(SOUTH);
			texAnim++;
		}
		if (e.getKeyCode() == game.game.controls.getKey(Controls.CONTROL_LEFT)) {
			sprite = 47;
			setOrientation(WEST);
			texAnim++;
		}
		if (e.getKeyCode() == game.game.controls.getKey(Controls.CONTROL_RIGHT)) {
			sprite = 46;
			setOrientation(EAST);
			texAnim++;
		}
		if (e.getKeyCode() == game.game.controls.getKey(Controls.CONTROL_FIRE)) {
			System.out.println(ammocooldown);
			if(ammo != 0 && ammocooldown == 0)
			{
				((ScreenGame) game).spawnEntity(new EntityBullet((ScreenGame) game, 0, getOrientation(), x, y));
				ammo--;
				ammocooldown = 256;
			}
			
			
		}
	}

	public void keyReleased(KeyEvent e) {

	}
	
	public int getOrientation()
	{
		return orientation;
	}
	
	public void setOrientation(int or)
	{
		orientation = or;
	}
	public int getHealth()
	{
		return health;
	}
	
	public void setHealth(int he)
	{
		health = he;
	}
	public int getAmmo()
	{
		return ammo;
	}
	
	public void setAmmo(int am)
	{
		this.ammo = am;
	}

}
