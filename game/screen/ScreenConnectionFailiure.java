package game.screen;

import game.SpriteSheet;

import java.awt.Graphics;

public class ScreenConnectionFailiure extends Screen{

	public ScreenConnectionFailiure(int width, int height, SpriteSheet sheet) {
		super(width, height, sheet);

	}
	
	public void render(Graphics g)
	{
		game.getFontRenderer().drawCenteredString("Could not connect to server.", 90, 2);
	}
	

}
