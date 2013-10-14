package game.triggers;

import game.Game;
import game.entity.EntityBullet;
import game.tile.Tile;

public class TriggerBullet extends Trigger
{

	int animStage = 0;
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
	public void tick()
	{
		super.tick();
		int tileID = Tile.getTileID(sg.getTileAt(x, y));
		if(tileID == 32 || tileID == 34 || tileID == 36 || tileID == 38)
		{
			 animStage++;
			 if(animStage >= 120)
			 {
				 sg.setTileAtEnt(x, y, tileID-1);
				 animStage = 0;
			 }
		}
				
	}
	
	
	@Override
	public void onTriggerRelease(Trigger trigger)
	{
	}

	@Override
	public void onTrigger(Trigger trigger)
	{
		sg.spawnEntity(new EntityBullet(sg, lx, ly, x, y));
		int tileID = Tile.getTileID(sg.getTileAt(x, y));
		if(tileID == 31 || tileID == 33 || tileID == 35 || tileID == 37)
			sg.setTileAtEnt(x,y, tileID+1);
		
	}

}
