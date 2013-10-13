package game.triggers;

import game.Game;
import game.entity.EntityBullet;

public class TriggerBullet extends Trigger
{

	
	public TriggerBullet(int x, int y, Game game)
	{
		super(x, y, 18, game);	
		drawnInPlay = false;
		lx = 1;
		ly = 1;
	}
	
	public TriggerBullet()
	{
		lx = 1;
		ly = 1;
		sprite = 18;
		drawnInPlay = false;
	}
	
	
	@Override
	public void onTriggerRelease(Trigger trigger)
	{
	}

	@Override
	public void onTrigger(Trigger trigger)
	{
		sg.spawnEntity(new EntityBullet(sg, lx, ly, x, y));
		
		//if(sg.getTileAt(x, y))
	}

}
