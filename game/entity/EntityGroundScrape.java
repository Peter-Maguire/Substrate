package game.entity;

import java.util.Random;

public class EntityGroundScrape extends Entity
{
	Random rand;
	public EntityGroundScrape(int x, int y)
	{
		this.x = x;
		this.y = y;
		rand = new Random();
		this.renderFirst = true;
		sprite = 11+rand.nextInt(2);
	}
}
