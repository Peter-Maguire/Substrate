package game.screen;

import game.Game;
import game.SpriteSheet;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.HashMap;

public class ScreenOptions extends Screen {


	private String sound = "OFF", music = "ON", debug = "OFF";
	private int volume = 10;
	public ScreenOptions(int width, int height, SpriteSheet sheet, HashMap<String, String> options) {
		super(width, height, sheet);



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
		
	}

	@Override
	public void render(Graphics g) {
		drawBackgroundScreen();
		ScreenTools.drawOnOffButton(550, 110, 90, 30, music, g, game);
		ScreenTools.drawOnOffButton(550, 152, 90, 30, debug, g, game);
		ScreenTools.drawOnOffButton(550, 194, 90, 30, sound, g, game);	
		ScreenTools.drawProgressBar(500, 238, 200, 32, volume, g, game, new Color(0,0,0,155), Color.green);
		
		
		
	}

		

}
