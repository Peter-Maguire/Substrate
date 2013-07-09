package game.screen;

import game.Game;
import game.Map;
import game.utils.FileSaver;
import game.utils.SpriteSheet;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class ScreenMainMenu extends Screen {

	Graphics g;

	public ScreenMainMenu(int width, int height, SpriteSheet sheet) {
		super(width, height, sheet);
		addButton("Singleplayer", new Rectangle(290, 116, 232, 25));
		addButton("Wave mode", new Rectangle(290, 148, 232, 25));
		addButton("Map editor", new Rectangle(290, 180, 232, 25));
		addButton("Options", new Rectangle(290, 212, 232, 25));
		addButton("Exit", new Rectangle(290, 244, 232, 25));
	}

	@Override
	public void render(Graphics g) {
		this.g = g;
		this.drawBackgroundScreen();
		// this.drawAnimatedBackground();
		game.getFontRenderer().drawCenteredString(Game.TITLE, 36, 3);
		ScreenTools.drawButton(290, 116, 232, 25, "Singleplayer", g, game,
				new Color(255, 255, 255, 155), Color.white);
		ScreenTools.drawButton(290, 148, 232, 25, "Wave mode", g, game,
				new Color(255, 255, 255, 155), Color.white);
		ScreenTools.drawButton(290, 180, 232, 25, "Map Editor", g, game,
				new Color(255, 255, 255, 155), Color.white);
		ScreenTools.drawButton(290, 212, 232, 25, "Options", g, game,
				new Color(255, 255, 255, 155), Color.white);
		ScreenTools.drawButton(290, 244, 232, 25, "Quit", g, game, new Color(
				255, 255, 255, 155), Color.white);

		game.getFontRenderer().drawString(
				String.format("%s version %s", Game.TITLE, Game.VERSION), 0,
				Game.HEIGHT, 1);

	}

	@Override
	public void tick() {
	}

	@Override
	public void postAction(String action) {
		switch (action) {
		case "Singleplayer":
			game.setScreen(new ScreenIntro(w, h, sheet));
			break;
		case "Wave mode":
			game.setScreen(new ScreenWaveMode(w, h, sheet, (Map) FileSaver
					.load(FileSaver.getCleanPath() + "//maps//WaveAttacks.smf")));
			break;
		case "Map editor":
			game.setScreen(new ScreenMapEditor(w, h, sheet));
			break;
		case "Options":
			game.setScreen(new ScreenOptions(w, h, sheet, game.SETTINGS));
			break;
		case "Exit":
			game.shutdown();
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
	}

}
