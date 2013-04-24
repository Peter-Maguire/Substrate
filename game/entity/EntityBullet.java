package game.entity;

import game.screen.ScreenGame;
import game.tile.Tile;
import game.tile.TileRubble;
import game.tile.TileWall;

public class EntityBullet extends Entity{

	
	
	
	private ScreenGame game;
	private int type = 0, rotation = 0, speed = 6;
	public EntityBullet(ScreenGame game, int type, int rotation, int x, int y) {
		super(game);
		this.game = game;
		this.type = type;
		this.rotation = rotation;
		this.x = x;
		this.y = y;
		
		sprite = 48 + rotation * (type+1);
		
	}
	
	@Override
	public void tick()
	{
		if(rotation == Player.NORTH)
			this.y -=speed;
		
		if(rotation == Player.SOUTH)
			this.y +=speed;
		
		if(rotation == Player.EAST)
			this.x +=speed;
		if(rotation == Player.WEST)
			this.x -=speed;
		
		
		if(game.getTileAt(x, y) == null)
		{
			this.forRemoval = true;
		return;
		}
		Tile t = game.getTileAt(x, y);
		if(!t.isPassable())
		{
			if(t instanceof TileWall)
			{
				game.setTileAt(x, y, new TileRubble(game));
			}
			game.spawnEntity(new EntityExplosion(game, x-4, y-2, 2));
			this.forRemoval = true;
		}
	}

}
