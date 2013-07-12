package game.triggers;

import game.Game;
import game.screen.ScreenGame;

import java.awt.Graphics;

public class Trigger {

	
	private boolean shown = false, shouldBeShown = false;
	public int x = 0,y = 0, sprite = 0;
	protected Game game;
	protected ScreenGame sg;
	protected Trigger linkedTrigger;
	
	
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
	
	public void trigger(Trigger trigger)
	{
		trigger.onTrigger(this);
	}
	
	public void trigger()
	{
		linkedTrigger.onTrigger(this);
	}
	
	public void onTrigger(Trigger trigger)
	{}
}
