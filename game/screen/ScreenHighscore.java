package game.screen;

import game.utils.SpriteSheet;

import java.awt.Color;
import java.awt.Graphics;

public class ScreenHighscore extends Screen
{
	
	public String[] names;
	public int[] scores;

	public ScreenHighscore(int width, int height, SpriteSheet sheet)
	{
		super(width, height, sheet);
	}
	
	
	@Override
	public void render(Graphics g)
	{	
		this.drawBackgroundScreen();
		game.getFontRenderer().drawString("Enter your username below to submit your highscore", 200, 80, 1);
		ScreenTools.drawButton(150, 100, 500, 400, " ", g, game);
		ScreenTools.drawButton(150, 520, 400, 40, " ", g, game);
		ScreenTools.drawButton(553, 521, 100, 40, "SUBMIT", g, game, new Color(255, 0, 0, 100), Color.red);
	}
	
	public void getHighscores()
	{
		
	}

}
