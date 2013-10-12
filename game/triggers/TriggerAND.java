package game.triggers;

import game.Game;

public class TriggerAND extends Trigger
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1414607939944571480L;
	private Trigger and1;
	private Trigger and2;

	public TriggerAND()
	{
		this.drawnInPlay = false;
		this.sprite = 8;
	}
	
	public TriggerAND(int x, int y, Game game)
	{
		super(x,y,8,game);
		this.drawnInPlay = false;
	}
	
	
	@Override
	public void onTriggerRelease(Trigger trigger)
	{
	}

	@Override
	public void onTrigger(Trigger trigger)
	{
		/*if(and1 == null && and2 == null) //In the case that this is the first time the trigger is running
		{
			trigger = and1;
			return;
		}
		
		if(and1 == trigger)//This is the same trigger again so we shouldn't bother
			return;
		else if(and2 == null)
			and2 = trigger; //This is the second trigger
		else if(trigger == and2)
			trigger(); //Both things have been triggered so it is time to go
			*/
		
			
		if(and1 == null)
			and1 = trigger;
		else
			and2 = trigger;
		
		if(and1 != null && and2 != null)
			trigger();
		

	}

}
