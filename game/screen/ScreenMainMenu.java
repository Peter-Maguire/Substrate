package game.screen;

import game.SpriteSheet;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class ScreenMainMenu extends Screen {


	private Graphics g;
	
	public ScreenMainMenu(int width, int height, SpriteSheet sheet) {
		super(width, height, sheet);
		addButton("Singleplayer", new Rectangle(290, 116, 232, 25));
		addButton("Map editor", new Rectangle(290, 148, 232, 25));
		addButton("Options", new Rectangle(290, 180, 232, 25));
		addButton("Exit", new Rectangle(290, 212, 232, 25));
	}

	@Override
	public void render(Graphics g) {

		this.drawBackgroundScreen();
	this.g = g;
		game.getFontRenderer().drawCenteredString(game.TITLE + "        ", 36, 3);

		ScreenTools.drawButton(290, 116, 232, 25, "Singleplayer", g, game);
		ScreenTools.drawButton(290, 148, 232, 25, "Map editor", g, game);
		ScreenTools.drawButton(290, 180, 232, 25, "Options", g, game);
		ScreenTools.drawButton(290, 212, 232, 25, "Exit", g, game);
		

	}

	@Override
	public void tick() {
		


		
	
	}
	
	@Override
	public void postAction(String action)
	{
		switch(action)
		{
		case "Singleplayer":
			game.setScreen(new ScreenIntro(w, h, sheet));
			break;
		case "Map editor":
			game.setScreen(new ScreenMapEditor(w,h,sheet));
			break;
		case "Options":
			game.setScreen(new ScreenOptions(w,h,sheet));
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
