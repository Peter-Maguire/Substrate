package game.screen;

import game.SpriteSheet;

import java.awt.Graphics;

public class ScreenLoading extends Screen{

	public ScreenLoading(int width, int height, SpriteSheet sheet) {
		super(width, height, sheet);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void render(Graphics g)
	{
		game.getFontRenderer().drawString("Loading...", 100, 100, 3);
	}
	
	

}
