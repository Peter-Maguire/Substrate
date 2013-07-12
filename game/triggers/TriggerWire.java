package game.triggers;

import game.Game;

public class TriggerWire extends Trigger{

	
	protected Trigger lastTrigger;
	public TriggerWire(int x, int y, Game game) {
		super(x, y, 2, true, game);
	}
	
	@Override
	public void onTrigger(Trigger trigger)
	{
		sprite = 3;
		lastTrigger = trigger;
		triggerNextTo();
		
	}
	

	
	@Override
	public void triggerNextTo()
	{
		System.out.println("Triggering...");
		for(Trigger t : sg.triggers)
		{
			if(t.x-x <= 16  && t.y-y <= 16 && t != this)
			{
				System.out.println("Triggering trigger "+t+" at "+t.x+","+t.y);
				if(t != lastTrigger)
				{
					t.onTrigger(this);
				}
				
			}
		}
		
		System.out.println("Finished Triggering");
	}

}
