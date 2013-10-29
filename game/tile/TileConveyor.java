package game.tile;

import java.awt.Graphics;

public class TileConveyor extends Tile
{

	public static final int TILE_CON_TOP = 58;
	private int animStage = 0, type;
	private boolean active;
	
	public TileConveyor(int type, boolean active)
	{
		this.type = type;
		this.active = active;
		sprite = type;
		tileName = "Conveyor Belt "+type+(active ? " active" : " inactive");
	}
	
	public void tick()
	{
		if(active){
			animStage++;
			if(animStage == 100)
				animStage = 0;
			
			sprite = type+animStage/50;
		}
	}
	
	

}
