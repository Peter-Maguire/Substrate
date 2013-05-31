package game;

import game.screen.ScreenTools;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.HashMap;

public class Font {

	String font = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
				  "0123456789!?/\\\"()#><|{}%" +
				  "+-.,:";
	
	private BufferedImage fontsheet;
	private BufferedImage[] chars;
	private Graphics g;
	private Game game;
	private HashMap<String, BufferedImage>chars2 = new HashMap<String, BufferedImage>();
	
	
	public Font(BufferedImage fontsheet, Graphics g, Game game)
	{
		this.game = game;
		this.g = g;
		this.setFontsheet(fontsheet);
		
		chars = new BufferedImage[fontsheet.getHeight() * fontsheet.getWidth()];
		
		int rows = fontsheet.getWidth() / 8; 
		int cols = fontsheet.getHeight() /8;

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				int loc = (i * cols) + j;
				chars[loc] = fontsheet.getSubimage(j * 8,
						i * 8, 8, 8);
				if(loc+1 <= font.length())
				{
					chars2.put(String.valueOf(font.charAt(loc)), fontsheet.getSubimage(j * 8,i * 8, 8, 8));
				}

			}
		}

	}
	
	public void updateDrawGraphics(Graphics g)
	{
		this.g = g;
	}
	
	public void drawCenteredString(String text, int y, int size)
	{
		drawCenteredString(text, y, size, Color.white);
	}
	public void drawCenteredString(String text, int y, int size, Color colour)
	{
		drawString(text,(Game.WIDTH/2)-(text.length()*(8*size)/2), y, size, colour);
	}
	public void drawString(String text, int x, int y, int size)
	{
		drawString(text,x,y,size,Color.white);
	}
	
	public void drawString(String text, int x, int y, int size, Color colour)
	{
		text = text.toUpperCase();
		for(int i = 0; i < text.length(); i++)
		{
			if(chars2.get(String.valueOf(text.charAt(i))) != null)
				g.drawImage(game.settings.getSetting("UseFontRecolouring") == "ON" ? ScreenTools.recolourImage(chars2.get(String.valueOf(text.charAt(i))),colour) : chars2.get(String.valueOf(text.charAt(i))), x+(i*size*8), y, 8*size, 8*size, game);
		}
	}


	public BufferedImage getFontsheet() {
		return fontsheet;
	}


	public void setFontsheet(BufferedImage fontsheet) {
		this.fontsheet = fontsheet;
	}
	
	
}
