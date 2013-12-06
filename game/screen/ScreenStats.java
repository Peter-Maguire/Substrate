package game.screen;

import java.awt.Graphics;

import game.Game;
import game.utils.SpriteSheet;

public class ScreenStats extends Screen
{

	public ScreenStats(int width, int height, SpriteSheet sheet)
	{
		super(width, height, sheet);
	}

	@Override
	public void tick()
	{
	}

	@Override
	public void render(Graphics g)
	{
		drawBackgroundScreen();
		game.getFontRenderer().drawCenteredString("Stats", 50, 3);
		game.getFontRenderer().drawCenteredString(Game.TITLE+" collects information to help with bug fixing. Here is what we collect:", 100, 1);
		ScreenTools.drawButton(50, 150, 700, 400, " \n\nJava version: "+System.getProperty("java.version")+"\n\nOS: "+System.getProperty("os.name")+"_"+System.getProperty("os.version")+"\n\nGame Version: "+Game.VERSION+"\n\nWe also record your IP address \nto avoid duplicate entries", g, game);
		
	}
	
	

}
