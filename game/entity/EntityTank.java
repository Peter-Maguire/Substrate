package game.entity;

import java.awt.event.KeyEvent;

import game.Controls;
import game.screen.ScreenGame;

public class EntityTank extends Entity {

	public static final int NORTH = 0, EAST = 1, SOUTH = 2, WEST = 3;
	ScreenGame game;
	private int orientation = 0, health = 20;

	public EntityTank(ScreenGame game) {
		super(game);
		this.game = game;
		this.sprite = 45;

	}

	@Override
	public void tick() {
		
		if (getOrientation() == NORTH) {
			sprite = 45;
		}
		if (getOrientation() == SOUTH) {
			sprite = 44;

		}
		if (getOrientation() == EAST) {
			sprite = 47;

		}
		if (getOrientation() == WEST) {
			sprite = 46;
		}

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
	

}
