package game.screen;

import game.Game;

import java.awt.Color;
import java.awt.Graphics;

public class ScreenTools {

	public static void drawButton(int x, int y, int length, int height, String text, Graphics g, Game game)
	{
		g.setColor(new Color(0,0,0,155));
		g.fillRect(x, y, length, height);
		g.setColor(Color.black);
		g.drawRect(x-1, y-1, length+1, height+1);
		game.getFontRenderer().drawString(text, x+(length/text.length()), y+5, 2);
	}
	
	public static void drawButton(int x, int y, int length, int height, String text, Graphics g, Game game, Color foreground, Color background)
	{
		g.setColor(foreground);
		g.fillRect(x, y, length, height);
		g.setColor(background);
		g.drawRect(x-1, y-1, length+1, height-1);
		game.getFontRenderer().drawString(text, x+(length/text.length()), y+5, 2);
	}
}
