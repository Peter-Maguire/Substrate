package game.triggers;

import game.Game;

public class TriggerTimer extends Trigger
{

	int timer = 0;
	
	public TriggerTimer(int x, int y, Game game) {
		super(x, y, 26, game);
		this.drawnInPlay = false;
	}
	
	public TriggerTimer() {
		this.sprite = 26;
		this.drawnInPlay = false;
	}

	@Override
	public void tick()
	{
		if(timer > 60)
		{
			timer = 0;
			trigger();
		}else
		{
			timer++;
		}
	}

	@Override
	public void onTriggerRelease(Trigger trigger)
	{
	}

	@Override
	public void onTrigger(Trigger trigger)
	{
		timer = 0;
	}
}
