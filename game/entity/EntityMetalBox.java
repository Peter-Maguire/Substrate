package game.entity;

import game.screen.ScreenGame;

public class EntityMetalBox extends EntityBox
{

	
	public EntityMetalBox(ScreenGame game, int x, int y) {
		super(game, x, y);
		this.sprite = 24;
	}

	public EntityMetalBox() {
		this.sprite = 24;
	}
}
