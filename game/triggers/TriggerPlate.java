package game.triggers;

import java.awt.Rectangle;
import java.io.Serializable;

import game.Game;

public class TriggerPlate extends Trigger implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7572380975319558520L;
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
			System.out.println("Triggering target "+lx+" ,"+ly);
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
