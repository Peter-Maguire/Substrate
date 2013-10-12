package game.triggers;

import game.Game;
import game.screen.ScreenWaveMode;
import game.screen.ScreenWin;

public class TriggerGameWin extends Trigger{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3909920295335515190L;
	
	public TriggerGameWin()
	{
		this.sprite = 4;
		this.drawnInPlay = false;
	}
	
	public TriggerGameWin(int x, int y, Game game)
	{
		super(x,y,4,game);
		this.drawnInPlay = false;
	}
	
	@Override
	public void onTrigger(Trigger t)
	{
		if(sg instanceof ScreenWaveMode)
		{
			ScreenWaveMode swm = (ScreenWaveMode)sg;
			swm.wave++;
		}//TODO: This needs to work with other things
	}

	@Override
	public void onTriggerRelease(Trigger trigger)
	{
	}
}
