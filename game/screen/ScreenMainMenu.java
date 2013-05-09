package game.screen;

import game.Controls;
import game.SpriteSheet;
import game.sound.SoundManager;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class ScreenMainMenu extends Screen {

	int selectedMenu = 1;
	private Graphics g;
	
	public ScreenMainMenu(int width, int height, SpriteSheet sheet) {
		super(width, height, sheet);

	}

	@Override
	public void render(Graphics g) {

		this.drawBackgroundScreen();
	this.g = g;
		game.getFontRenderer().drawCenteredString(game.TITLE + "    ", 36, 3);
		game.getFontRenderer().drawCenteredString("Main Menu", 70, 2);

		if (selectedMenu == 1) {
			game.getFontRenderer().drawCenteredString(">Singleplayer<", 8 * 15, 2);
		} else {
			game.getFontRenderer().drawCenteredString("Singleplayer", 8 * 15, 2);
		}

		if (selectedMenu == 2) {
			game.getFontRenderer().drawCenteredString(">:(<",
					(8 * 10) * 2, 2);
		} else {
			game.getFontRenderer().drawCenteredString("", (8 * 10) * 2,
					2);
		}
		if (selectedMenu == 3) {
			game.getFontRenderer().drawCenteredString(">Map Editor<",
					(8 * 12) * 2, 2);
		} else {
			game.getFontRenderer().drawCenteredString("Map Editor", (8 * 12) * 2,
					2);
		}
		if (selectedMenu == 4) {
			game.getFontRenderer().drawCenteredString(">Options<",
					(8 * 14) * 2, 2);
		} else {
			game.getFontRenderer().drawCenteredString("Options", (8 * 14) * 2,
					2);
		}
		if (selectedMenu == 5) {
			game.getFontRenderer().drawCenteredString(">Quit<",
					(8 * 16) * 2, 2);
		} else {
			game.getFontRenderer().drawCenteredString("Quit", (8 * 16) * 2,
					2);
		}


	}

	@Override
	public void tick() {
		


		
		if (selectedMenu <= 0) {
			selectedMenu = 5;
		}
		if (selectedMenu >= 6) {
			selectedMenu = 1;
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		boolean keyUp = arg0.getKeyCode() == game.controls.getKey(Controls.CONTROL_UP);
		boolean keyDown = arg0.getKeyCode() == game.controls.getKey(Controls.CONTROL_DOWN);
		boolean keyEnter = arg0.getKeyCode() == game.controls.getKey(Controls.CONTROL_ENTER);


		if (keyUp) {
			selectedMenu--;
			game.soundman.playSound("select.wav");
		}
		if (keyDown) {
			selectedMenu++;
			game.soundman.playSound("select.wav");
		}


		if (keyEnter) {
			switch (selectedMenu) {
			case 1:
				{
					game.setScreen(new ScreenIntro(w, h, sheet));
					return;
				}
				
			case 2:
				{
					try{
					//game.setScreen(new ScreenMultiplayer(w, h, sheet, g,"80.193.196.105", 25565));
					}catch(Exception e)
					{
						game.setScreen(new ScreenConnectionFailiure(w, h, sheet));
					}
						return;		
				}
			
				
			case 3: 
				{
					game.setScreen(new ScreenMapEditor(w, h, sheet));	
					break;
					
				}
				
			case 4:
			{
				game.setScreen(new ScreenOptions(w, h, sheet));
				return;
			}
			
			case 5:
			{
				game.gameRunning = false;
				return;
			}


			}
		}
	}

}
