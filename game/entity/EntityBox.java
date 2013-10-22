package game.entity;

import game.Game;
import game.screen.ScreenGame;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

public class EntityBox extends Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6538392496176575034L;
	private Random rand = new Random();;

	public EntityBox(ScreenGame game, int x, int y) {
		super(game);
		this.x = x;
		this.y = y;
		this.sprite = 8;
	}

	public EntityBox() {
		this.sprite = 8;
	}

	@Override
	public void tick() {

	}

	@Override
	public boolean tryMoveEntity(int x, int y) {

		ArrayList<Entity> eib = ((ScreenGame)game.currentScreen).getEntitiesInBox(new Rectangle(x == 1 ? this.x + - 9 : this.x - 24, y == 1 ?  this.y - 4 : this.y - 24, Game.SIZE, Game.SIZE));
		for (Entity e : eib) {
			if (!(e instanceof EntityBox)) {
				return false;
			}
		}
			super.tryMoveEntity(x, y);
				return false;
		
		
		
	}

	@Override
	public void onCollideWithPlayer(int x, int y, Player p) {
		tryMoveEntity(x, y);
	}

}
