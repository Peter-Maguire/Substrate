package game.triggers;

import java.awt.Rectangle;

import game.Game;

public class TriggerPlate extends Trigger{

	
	private boolean triggered = false;
	public TriggerPlate(int x, int y, Game game) {
		super(x, y, 0, true, game);
	}
	
	public TriggerPlate() {
		
	}

	@Override
	public void tick()
	{
	if(sg.getEntityInBox(new Rectangle(x,y,32,32)) != null)
	{
		if(!triggered)
		{
			trigger();
			triggered = true;
		}
	
		sprite = 1; 
	}else	{
		
		triggered = false;
		sprite = 0;
	}
	}

}
