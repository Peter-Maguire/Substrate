package game.screen;

import game.Game;
import game.utils.FileSaver;
import game.utils.SpriteSheet;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.HashMap;

public class ScreenOptions extends Screen {

	private String sound, music, debug, gatherStats, mpMapPrev, showTutorial,
			showConsole, advTilePlacement, fontRecolouring;
	private int volume = 10;

	public ScreenOptions(int width, int height, SpriteSheet sheet,
			HashMap<String, String> options) {
		super(width, height, sheet);

		addButton("toggleSound", new Rectangle(550, 194, 90, 30));
		addButton("toggleDebug", new Rectangle(550, 110, 90, 30));
		addButton("toggleMusic", new Rectangle(550, 152, 90, 30));
		addButton("toggleStats", new Rectangle(550, 287, 90, 30));
		addButton("toggleMapPrev", new Rectangle(550, 327, 90, 30));
		addButton("toggleTutorial", new Rectangle(550, 367, 90, 30));
		addButton("toggleConsole", new Rectangle(550, 407, 90, 30));
		addButton("toggleTilePlacement", new Rectangle(550, 447, 90, 30));
		addButton("toggleFontRecolouring", new Rectangle(550, 487, 90, 30));

		addButton("increaseSound", new Rectangle(702, 238, 25, 30));
		addButton("decreaseSound", new Rectangle(473, 238, 25, 30));

		addButton("launchStats", new Rectangle(642, 287, 25, 28));

		addButton("save", new Rectangle(292, 547, 95, 28));
		addButton("cancel", new Rectangle(392, 547, 120, 28));

	}

	@Override
	public void tick() {

	}

	@Override
	public void keyReleased(KeyEvent arg0) {

	}

	@Override()
	public void init(Game game) {
		super.init(game);

		HashMap<String, String> settings = game.settings.getSettings();
		sound = settings.get("Sound");
		music = settings.get("Music");
		debug = settings.get("Debug");
		volume = Integer.parseInt(settings.get("Volume"));
		gatherStats = settings.get("GatherStats");
		mpMapPrev = settings.get("MapPreviews");
		showTutorial = settings.get("HasDoneIntro");
		showConsole = settings.get("Cheats");
		advTilePlacement = settings.get("UseAdvancedTilePlacement");
		fontRecolouring = settings.get("UseFontRecolouring");
	}

	@Override
	public void postAction(String action) {
		if (action.equals("decreaseSound") && volume > 0) {
			volume -= 10;
			return;
		}
		if (action.equals("increaseSound") && volume < 100) {
			volume += 10;
			return;
		}
		if (action.equals("toggleSound")) {
			sound = sound.contains("ON") ? "OFF" : "ON";
			return;
		}
		if (action.equals("toggleMusic")) {
			music = music.contains("ON") ? "OFF" : "ON";
			return;
		}
		if (action.equals("toggleDebug")) {
			debug = debug.contains("ON") ? "OFF" : "ON";
			return;
		}

		if (action.equals("toggleConsole")) {
			showConsole = showConsole.contains("ON") ? "OFF" : "ON";
			return;
		}
		if (action.equals("toggleTutorial")) {
			showTutorial = showTutorial.contains("ON") ? "OFF" : "ON";
			return;
		}
		if (action.equals("toggleStats")) {
			gatherStats = gatherStats.contains("ON") ? "OFF" : "ON";
			return;
		}
		if (action.equals("toggleTilePlacement")) {
			advTilePlacement = advTilePlacement.contains("ON") ? "OFF" : "ON";
			return;
		}
		if (action.equals("toggleFontRecolouring")) {
			fontRecolouring = fontRecolouring.contains("ON") ? "OFF" : "ON";
			return;
		}
		if (action.equals("toggleMapPrev")) {
			mpMapPrev = mpMapPrev.contains("ON") ? "OFF" : "ON";
			return;
		}
		if (action.equals("launchStats")) {
		//	game.setScreen(); FIXME: Stat screen
			return;
		}
		if (action.equals("save")) {
			game.settings.setSetting("Sound", sound);
			game.settings.setSetting("Debug", debug);
			game.settings.setSetting("Music", music);
			game.settings.setSetting("Volume", volume + "");
			game.settings.setSetting("Sound", sound);
			game.settings.setSetting("GatherStats", gatherStats);
			game.settings.setSetting("MapPreviews", mpMapPrev);
			game.settings.setSetting("Cheats", showConsole);
			game.settings.setSetting("UseAdvancedTilePlacement",
					advTilePlacement);
			game.settings.setSetting("UseFontRecolouring", fontRecolouring);
			FileSaver.savePropertiesFile(game.settings.getSettings(),
					FileSaver.getCleanPath() + "\\settings.txt");
			game.setScreen(new ScreenMainMenu(w, h, sheet));

		}
		if (action.equals("cancel")) {
			game.setScreen(new ScreenMainMenu(w, h, sheet));
		}
	}

	@Override
	public void render(Graphics g) {
		drawBackgroundScreen();

		game.getFontRenderer().drawString("Options", 320, 30, 2);

		game.getFontRenderer().drawString("Sound", 100, 200, 2);
		ScreenTools.drawOnOffButton(550, 194, 90, 30, sound, g, game);

		game.getFontRenderer().drawString("Debug", 100, 116, 2);
		ScreenTools.drawOnOffButton(550, 110, 90, 30, debug, g, game);

		game.getFontRenderer().drawString("Music", 100, 159, 2);
		ScreenTools.drawOnOffButton(550, 152, 90, 30, music, g, game);

		game.getFontRenderer().drawString("Volume", 100, 241, 2);
		ScreenTools.drawProgressBar(500, 238, 200, 32, volume, g, game,
				new Color(0, 0, 0, 155),
				sound.equals("ON") || music.equals("ON") ? Color.green : Color.gray);
		ScreenTools.drawButton(473, 238, 25, 30, "< ", g, game);
		ScreenTools.drawButton(702, 238, 25, 30, "> ", g, game);

		game.getFontRenderer().drawString("Gather stats", 100, 287, 2);
		game.getFontRenderer()
				.drawString("Should the game gather", 300, 287, 1);
		game.getFontRenderer().drawString("anonymous statistics?", 300, 297, 1);
		ScreenTools.drawOnOffButton(550, 287, 90, 30, gatherStats, g, game);
		ScreenTools.drawButton(642, 287, 25, 28, "? ", g, game);

		game.getFontRenderer().drawString("Map previews", 100, 327, 2);
		game.getFontRenderer().drawString("Multiplayer map previews", 300, 327,
				1);
		game.getFontRenderer().drawString("at the cost of RAM usage.", 300,
				337, 1);
		ScreenTools.drawOnOffButton(550, 327, 90, 30, mpMapPrev, g, game);

		game.getFontRenderer().drawString("Show tutorial", 100, 367, 2);
		game.getFontRenderer().drawString("Watch the tutorial again", 310, 367,
				1);
		game.getFontRenderer()
				.drawString("turns off after 1 use.", 310, 377, 1);
		ScreenTools.drawOnOffButton(550, 367, 90, 30, showTutorial, g, game);

		game.getFontRenderer().drawString("Show console", 100, 407, 2);
		game.getFontRenderer().drawString("Display developer console", 310,
				407, 1);
		game.getFontRenderer().drawString("for commands and things.", 310, 417,
				1);
		ScreenTools.drawOnOffButton(550, 407, 90, 30, showConsole, g, game);

		game.getFontRenderer().drawString("Adv Tile math", 100, 447, 2);
		game.getFontRenderer().drawString("Use advanced tile placement,", 310,
				447, 1);
		game.getFontRenderer().drawString("more accurate but slower.", 310,
				457, 1);
		ScreenTools
				.drawOnOffButton(550, 447, 90, 30, advTilePlacement, g, game);

		game.getFontRenderer().drawString("Font Colours", 100, 487, 2);
		game.getFontRenderer().drawString("Allow the use of colour fonts", 310,
				487, 1);
		game.getFontRenderer().drawString("turn this off to increase FPS.",
				310, 497, 1);
		ScreenTools.drawOnOffButton(550, 487, 90, 30, fontRecolouring, g, game);

		ScreenTools.drawButton(292, 547, 95, 28, "Save", g, game);
		ScreenTools.drawButton(392, 547, 120, 28, "Cancel", g, game);

	}

}
