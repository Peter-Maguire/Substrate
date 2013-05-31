package game.screen;

import game.Game;
import game.SpriteSheet;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class ScreenMainMenu extends Screen {


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
		//this.drawAnimatedBackground(); //DRAWING ANIMATED BACKGROUND BREAKS THE EVERYTHING
		game.getFontRenderer().drawCenteredString(Game.TITLE + "        ", 36, 3);
		ScreenTools.drawButton(290, 116, 232, 25, "Singleplayer", g, game, new Color(255,255,255,155), Color.white);
		ScreenTools.drawButton(290, 148, 232, 25, "Map editor", g, game, new Color(255,255,255,155), Color.white);
		ScreenTools.drawButton(290, 180, 232, 25, "Options", g, game, new Color(255,255,255,155), Color.white);
		ScreenTools.drawButton(290, 212, 232, 25, "Quit", g, game, new Color(255,255,255,155), Color.white);
		
		game.getFontRenderer().drawString(Game.TITLE+" version "+Game.VERSION,0, Game.HEIGHT, 1);
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
			game.setScreen(new ScreenOptions(w,h,sheet, game.SETTINGS));
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
