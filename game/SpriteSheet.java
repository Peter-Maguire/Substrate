package game;

import java.awt.Image;
import java.awt.image.BufferedImage;

public class SpriteSheet {


	BufferedImage sheet;
	private BufferedImage[] imagecache;

	public SpriteSheet(BufferedImage sheet, int SPRITE_SIZE) {
		this.sheet = sheet;
		int rows = sheet.getWidth() / SPRITE_SIZE;
		int cols = sheet.getHeight() / SPRITE_SIZE;
		imagecache = new BufferedImage[SPRITE_SIZE * SPRITE_SIZE];

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				imagecache[(i * cols) + j] = sheet.getSubimage(j * SPRITE_SIZE,
						i * SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE);
			}
		}

	}

	public BufferedImage getImage(int texpos) {

		
		return imagecache[texpos];
	}

}
