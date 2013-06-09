package game.screen;

import game.Game;

import java.awt.Graphics;

public class Dialogue {

	private String title, message;
	private Game game;
	private int diaType, xLoc, yLoc;

	public static int DIALOGUE_OK = 0;
	public static int DIALOGUE_EXITGAME = 1;
	public static int DIALOGUE_YES_NO = 2;

	public Dialogue(String title, String message, Game game, int diaType) {
		this.title = title;
		this.message = message;
		this.game = game;
		this.diaType = diaType;
		this.xLoc = Game.WIDTH / 2;
		this.yLoc = Game.HEIGHT / 2;
	}

	public void render(Graphics g) {
		g.drawImage(game.sheet.getImage(24), Game.WIDTH / 2, Game.HEIGHT / 2,
				game);
	}
}
