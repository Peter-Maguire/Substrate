package game.triggers;

import java.awt.Rectangle;

import game.Game;

public class TriggerPlate extends Trigger{

	public TriggerPlate(int x, int y, Game game) {
		super(x, y, 0, true, game);
	}
	
	@Override
	public void tick()
	{
	if(sg.getEntityInBox(new Rectangle(x,y,32,32)) != null)
	{
		triggerNextTo();
		sprite = 1; 
	}else
	{
		sprite = 0;
	}
	}

}
