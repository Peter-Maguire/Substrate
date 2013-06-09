package game.screen;

import game.Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;

public class ScreenTools {

	public static void drawButton(int x, int y, int length, int height,
			String text, Graphics g, Game game) {
		g.setColor(new Color(0, 0, 0, 155));
		g.fillRect(x, y, length, height);
		g.setColor(Color.black);
		g.drawRect(x - 1, y - 1, length + 1, height + 1);
		game.getFontRenderer().drawString(text,
				x + (length / text.length()) / 2, y + 5, 2);
	}

	public static void drawButton(int x, int y, int length, int height,
			String text, Graphics g, Game game, Color foreground,
			Color background) {
		g.setColor(foreground);
		g.fillRect(x, y, length, height);
		g.setColor(background);
		g.drawRect(x - 1, y - 1, length + 1, height - 1);
		game.getFontRenderer().drawString(text,
				x + (length / text.length()) / 2, y + 5, 2);
	}

	public static void drawOnOffButton(int x, int y, int length, int height,
			String on, Graphics g, Game game) {
		ScreenTools.drawButton(x, y, length, height, on, g, game,
				new Color(on.contains("ON") ? 0 : 145, on.contains("ON") ? 145
						: 0, 0, 155), on.contains("ON") ? Color.green
						: Color.red);
	}

	public static void drawProgressBar(int x, int y, int length, int height,
			int amount, Graphics g, Game game, Color foreground,
			Color background) {
		g.setColor(foreground);
		g.fillRect(x, y, length, height);
		g.setColor(background);
		g.drawRect(x - 1, y - 1, length + 1, height - 1);
		g.fillRect(x + 1, y + 1, (length / 100 * amount) - 2, height - 4);

	}

	public static BufferedImage recolourImage(BufferedImage input, Color colour) {
		BufferedImage destination = input;
		WritableRaster destinationRaster = destination.getRaster();
		Raster sourceRaster = input.getRaster();

		int[] newColour = { colour.getRed(), colour.getGreen(),
				colour.getBlue(), colour.getAlpha() };

		for (int x = 0; x < sourceRaster.getWidth(); x++) {
			for (int y = 0; y < sourceRaster.getHeight(); y++) {
				int[] sourcePixel = { 0, 0, 0, 0 };
				sourceRaster.getPixel(x, y, sourcePixel);

				if (sourcePixel[3] != 0) {
					destinationRaster.setPixel(x, y, newColour);
				}
			}
		}

		return destination;

	}

}
