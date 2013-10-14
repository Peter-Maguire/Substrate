package game;

import game.screen.ScreenTools;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.RasterFormatException;
import java.util.HashMap;

public class Font {

	String font = 
	"ABCDEFGHIJKLMNOPQRSTUVWXYZ"
  + "0123456789!?/\\\"()#><|{}%"
  + "+-.,:";

	/*String font = 
			" !\"#$%&'()*+,-./0123456789:;<=>?"
		  + "@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_"
		  + "'abcdefghijklmnopqrstuvwxyz{|}~~";*/

	private BufferedImage fontsheet;
	private BufferedImage[] chars;
	private Graphics g;
	private Game game;
	private int charWidth = 8, charHeight = 8;
	private HashMap<String, BufferedImage> chars2 = new HashMap<String, BufferedImage>();

	public Font(BufferedImage fontsheet, Graphics g, Game game) {
		this.game = game;
		this.g = g;
		this.setFontsheet(fontsheet);

		chars = new BufferedImage[fontsheet.getHeight() * fontsheet.getWidth()];

		int rows = fontsheet.getWidth() / charWidth;
		int cols = fontsheet.getHeight() / charHeight;

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				int loc = (i * cols) +  j;
				//chars[loc] = fontsheet.getSubimage(j * 8, i * 8, 6, 8);
				if (loc + 1 <= font.length()) {
					try{
						
					chars2.put(String.valueOf(font.charAt(loc)),
							fontsheet.getSubimage(j * charWidth, i * charHeight, charWidth, charHeight));
					}catch(RasterFormatException e)
					{
						System.err.println("RASTA FORMAT EXCEPTION");
					}
				}

			}
		}

	}

	public void updateDrawGraphics(Graphics g) {
		this.g = g;
	}

	public void drawCenteredString(String text, int y, int size) {
		drawCenteredString(text, y, size, Color.white);
	}

	public void drawCenteredString(String text, int y, int size, Color colour) {
		drawString(text, (Game.WIDTH / 2) - (text.length() * (charHeight * size) / 2),
				y, size, colour);
	}

	public void drawString(String text, int x, int y, int size) {
		drawString(text, x, y, size, Color.white);
	}

	public void drawString(String text, int x, int y, int size, Color colour) {
		text = text.toUpperCase();
		int cy = y, ci = 0;
		for (int i = 0; i < text.length(); i++) {
			if (String.valueOf(text.charAt(i)).contains("\n")) {
				ci = -1;
				cy += charHeight * size+3;
			}
			if (chars2.get(String.valueOf(text.charAt(i))) != null)
				g.drawImage(
						game.settings.getSetting("UseFontRecolouring").equals("ON") ? ScreenTools
								.recolourImage(chars2.get(String.valueOf(text
										.charAt(i))), colour) : chars2
								.get(String.valueOf(text.charAt(i))), x
								+ (ci * size * charHeight), cy, charWidth * size, charHeight * size, game);
			ci++;
		}
	}

	public BufferedImage getFontsheet() {
		return fontsheet;
	}

	public void setFontsheet(BufferedImage fontsheet) {
		this.fontsheet = fontsheet;
	}

}
