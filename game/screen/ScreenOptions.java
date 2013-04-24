package game.screen;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import game.Controls;
import game.FileSaver;
import game.Game;
import game.Options;
import game.SpriteSheet;
import game.sound.SoundManager;

public class ScreenOptions extends Screen {

	private int selectedMenu = 0;
	private String volstr = "";
	private int vol;
	private String sound, volume, debug, music;
	
	public ScreenOptions(int width, int height, SpriteSheet sheet) {
		super(width, height, sheet);

	}

	@Override
	public void tick() {

		sound = game.settings.getSetting("Sound");
		volume = game.settings.getSetting("Volume");
		debug = game.settings.getSetting("Debug");
		music = game.settings.getSetting("Music");
		vol = Integer.parseInt(game.settings.getSetting("Volume"));

		volstr = "";
		for (int i = 0; i < vol / 10; i++) {
			volstr = volstr + "|";
		}

		if (selectedMenu <= 0) {
			selectedMenu = 6;
		}
		if (selectedMenu >= 7) {
			selectedMenu = 1;
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {

		boolean keyUp = arg0.getKeyCode() == game.controls.getKey(Controls.CONTROL_UP);
		boolean keyDown = arg0.getKeyCode() ==game.controls.getKey(Controls.CONTROL_DOWN);
		boolean keyLeft = arg0.getKeyCode() == game.controls.getKey(Controls.CONTROL_LEFT);
		boolean keyRight = arg0.getKeyCode() == game.controls.getKey(Controls.CONTROL_RIGHT);
		boolean keyEnter = arg0.getKeyCode() == game.controls.getKey(Controls.CONTROL_ENTER);

		if (keyUp) {
			selectedMenu--;
			game.soundman.playSound("select.wav");
		}
		if (keyDown) {
			selectedMenu++;
			game.soundman.playSound("select.wav");
		}
		if (keyRight && selectedMenu == 4) {
			if (vol == 160) {
				game.soundman.playSound("blocked.wav");
				return;
			} else {
				game.soundman.playSound("select.wav");
				game.settings.setSetting("Volume", Integer.toString(vol += 10));
			}

		}

		if (keyLeft && selectedMenu == 4) {
			if (vol == 0) {
				game.soundman.playSound("blocked.wav");
				return;
			} else {
				game.soundman.playSound("select.wav");
				game.settings.setSetting("Volume", Integer.toString(vol -= 10));
			}

		}

		if (keyEnter) {
			switch (selectedMenu) {
			case 1: {
				game.settings.setSetting("Sound", sound == "ON" ? "OFF" : "ON");
				return;
			}

			case 2: {
				game.settings.setSetting("Debug", debug == "ON" ? "OFF" : "ON");
				return;
			}

			case 3: {
				game.settings.setSetting("Music", music == "ON" ? "OFF" : "ON");
				return;
			}

			case 5: {
				game.setScreen(new ScreenControls(w, h, sheet));
				return;
			}

			case 6: {
				game.getFontRenderer().drawString("Saving...", 90, 90, 2);
				FileSaver.save(game.settings.getSettings(), "settings.dat");
				game.setScreen(new ScreenMainMenu(w, h, sheet));
				return;
			}

			}
		}

	}

	@Override()
	public void init(Game game) {
		super.init(game);
		
	}

	@Override
	public void render(Graphics g) {

		this.drawBackgroundScreen();
		game.getFontRenderer().drawCenteredString("Options", 10, 2);

		if (selectedMenu == 1) {
			game.getFontRenderer().drawString(">Sound<              " + sound,
					40, 60, 2);
		} else {
			game.getFontRenderer().drawString("Sound                " + sound,
					40, 60, 2);
		}

		if (selectedMenu == 2) {
			game.getFontRenderer().drawString(">Debug<              " + debug,
					40, 96, 2);
		} else {
			game.getFontRenderer().drawString("Debug                " + debug,
					40, 96, 2);
		}
		if (selectedMenu == 3) {
			game.getFontRenderer().drawString(">Music<              " + music,
					40, 136, 2);
		} else {
			game.getFontRenderer().drawString("Music                " + music,
					40, 136, 2);
		}

		if (selectedMenu == 4) {
			game.getFontRenderer().drawString(
					">Volume<             {" + volstr + "} " + vol + "%", 40,
					176, 2);
		} else {
			game.getFontRenderer().drawString(
					"Volume               {" + volstr + "} " + vol + "%", 40,
					176, 2);
		}

		if (selectedMenu == 5) {
			game.getFontRenderer().drawString(">Controls<", 40, 216, 2);
		} else {
			game.getFontRenderer().drawString("Controls", 40, 216, 2);
		}

		if (selectedMenu == 6) {
			game.getFontRenderer().drawCenteredString(">Exit<",
					game.getHeight() - 26, 2);
		} else {
			game.getFontRenderer().drawCenteredString("Exit",
					game.getHeight() - 26, 2);
		}

	}

}
