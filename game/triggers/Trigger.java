package game.triggers;

import game.Game;
import game.screen.ScreenGame;

import java.awt.Graphics;
import java.io.Serializable;

public class Trigger implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1219419139996693191L;
	private boolean shown = false, shouldBeShown = false;
	public int x = 0,y = 0, sprite = 0;
	public int lx = 0;
	public int ly = 0;
	public Game game;
	public ScreenGame sg;
	public Trigger linkedTrigger;
	
	
	public Trigger(int x, int y, int sprite,boolean shown, Game game)
	{
		this.x = x;
		this.y = y;
		this.shown = shown;
		this.shouldBeShown = shown;
		this.sprite = sprite;
		this.game = game;
		sg = (ScreenGame)game.currentScreen;
	}
	
	public Trigger()
	{
		
	}
	

	
	
	
	public void link(Trigger t)
	{
		linkedTrigger = t;
	}

	public void tick()
	{}
	
	public void triggerNextTo()
	{
		for(Trigger t : sg.triggers)
		{
			if(t.x-x <= 32  && t.y-y <= 32)
			{
				t.onTrigger(this);
			}
		}
	}
	
	public void render(Graphics g)
	{
		
		
	}
	
	public void trigger(int x, int y)
	{
		for(Trigger t : sg.triggers)
		{
			if( t.x == x && t.y == y)
			{
				t.onTrigger(this);
			}
		}
	}
	
	public void trigger()
	{
		for(Trigger t : sg.triggers)
		{
			if( t.x == lx && t.y == ly)
			{
				t.onTrigger(this);
			}
		}
	}
	
	public void onTrigger(Trigger trigger)
	{}
}
