package game.entity;

import game.screen.ScreenGame;
import game.screen.ScreenTools;

import java.awt.Color;
import java.awt.Graphics;

public class EntitySign extends Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7792148283644707446L;
	private String line1, line2, line3;
	private boolean showGUI = false;
	private int lastPlayerX, lastPlayerY;

	public EntitySign(ScreenGame game, int x, int y) {
		super(game);
		this.sprite = 9;
		this.x = x;
		this.y = y;
	}

	public EntitySign(ScreenGame game, String line1, String line2,
			String line3, int x, int y) {
		super(game);
		this.sprite = 9;
		this.line1 = line1;
		this.line2 = line2;
		this.line3 = line3;
		this.x = x;
		this.y = y;
	}

	public EntitySign() {
		this.sprite = 9;
	}

	@Override
	public void render(Graphics g) {
		super.render(g);
		if (showGUI) {
			ScreenTools.drawButton(200, 100, 400, 150, " ", g, game, new Color(
					107, 69, 46), new Color(117, 79, 56));
			game.getFontRenderer().drawString(line1, 250, 120, 2);
			game.getFontRenderer().drawString(line2, 250, 155, 2);
			game.getFontRenderer().drawString(line3, 250, 187, 2);
			if (((ScreenGame) game.currentScreen).player.x != lastPlayerX
					|| ((ScreenGame) game.currentScreen).player.y != lastPlayerY) {
				showGUI = false;
			}
		}
	}

	@Override
	public void tick() {
	}

	@Override
	public void onCollideWithPlayer(int x, int y, Player p) {
		showGUI = true;
		lastPlayerX = ((ScreenGame) game.currentScreen).player.x;
		lastPlayerY = ((ScreenGame) game.currentScreen).player.y;
	}

}
