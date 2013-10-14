package game.entity;

import game.screen.ScreenGame;

public class EntityAmmo extends Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2659727171922463550L;
	private ScreenGame ngame;

	public EntityAmmo(ScreenGame game, int x, int y) {
		super(game);
		this.ngame = game;
		this.x = x;
		this.y = y;
		sprite = 10;
	}

	public EntityAmmo() {
		sprite = 10;
	}

	@Override
	public void tick() {

	}

	@Override
	public void onCollideWithPlayer(int x, int y, Player p) {
		p.setAmmo(p.getAmmo() + 5);
		this.forRemoval = true;
	}

}
