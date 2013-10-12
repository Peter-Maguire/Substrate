package game.triggers;

import game.Game;
import game.screen.ScreenGame;
import game.tile.Tile;

public class TriggerDoorToggle extends Trigger
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public TriggerDoorToggle()
	{
		this.drawnInPlay = false;
		this.sprite = 2;
	}
	
	public TriggerDoorToggle(int x, int y, Game game)
	{
		super(x,y,2,game);
		this.drawnInPlay = false;
	}
	
	
	@Override
	public void onTrigger(Trigger trigger)
	{
		//Is it an open door?
		
		if(sg.getTileAt(lx, ly) == Tile.tiles[28] || sg.getTileAt(lx, ly) == Tile.tiles[30])
		{
			//Is it a vertical door?
			if(sg.getTileAt(lx, ly) == Tile.tiles[29])
				sg.setTileAtEnt(lx, ly, 30);
			else
				sg.setTileAtEnt(lx, ly, 29);
		}else //It must be a closed door
		{
			
			if(sg.getTileAt(lx, ly) == Tile.tiles[29])
				sg.setTileAtEnt(lx, ly, 30);
			else
				sg.setTileAtEnt(lx, ly, 29);
		}
		
		
	}

	@Override
	public void onTriggerRelease(Trigger trigger)
	{
	}
	

}
