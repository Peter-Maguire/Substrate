package game.tile;

import game.Game;
import game.screen.ScreenGame;

import java.awt.Graphics;
import java.io.Serializable;

public class Tile implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2215788549581724737L;
	boolean canPassThrough = true;
	public int sprite = 1;
	protected ScreenGame game;
	
	public Tile(ScreenGame game)
	{
		this.game = game;
	}
	
	public boolean isPassable()
	{
		return canPassThrough;
	}
	
	
	
	public void tick()
	{
		
	}
}
