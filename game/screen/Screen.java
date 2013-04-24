package game.screen;

import game.Game;
import game.SpriteSheet;

import java.awt.Graphics;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Random;

public class Screen {

	int w, h;
	SpriteSheet sheet;
	Random rand = new Random();

	public Game game;

	boolean shouldDrawIGui = false;

	public Screen(int width, int height, SpriteSheet sheet) {
		this.sheet = sheet;
		this.w = width;
		this.h = height;
	}

	public void drawBackgroundScreen() {
		for (int x = 0; x < game.getWidth() / 16; x++) {
			for (int y = 0; y < game.getHeight() / 16; y++) {

				game.g.drawImage(game.sheet.getImage(7), x * 16, y * 16, game);

			}
		}
	}

	public void tick() {

	}

	public void render(Graphics g) {

	}

	public void init(Game game) {
		this.game = game;
	}

	public void mousePressed(MouseEvent e) {

	}

	public void mouseReleased(MouseEvent e) {

	}

	public void keyPressed(KeyEvent arg0) {

	}

	public void keyReleased(KeyEvent arg0) {
	}
	

	public void focusGained(FocusEvent arg0) {


	}


	public void focusLost(FocusEvent arg0) {


	}

}
