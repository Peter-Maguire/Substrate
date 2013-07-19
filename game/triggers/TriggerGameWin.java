package game.triggers;

import game.screen.ScreenWaveMode;
import game.screen.ScreenWin;

public class TriggerGameWin extends Trigger{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3909920295335515190L;
	
	
	
	
	
	@Override
	public void onTrigger(Trigger t)
	{
		if(sg instanceof ScreenWaveMode)
		{
			ScreenWaveMode swm = (ScreenWaveMode)sg;
			swm.wave++;
		}else
		{
			game.setScreen(new ScreenWin(800,600,null,-9001));
		}
	}
}
