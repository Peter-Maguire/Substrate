package game.screen;

import game.Game;
import game.utils.SpriteSheet;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Random;

public class Screen {

	int w, h;
	SpriteSheet sheet;
	Random rand = new Random();

	public Game game;

	boolean shouldDrawIGui = false;
	private HashMap<Rectangle, String> bttn = new HashMap<Rectangle, String>();
	int i = 0, j = 600;
	boolean isDecreasing = false;

	public Screen(int width, int height, SpriteSheet sheet) {
		this.sheet = sheet;
		this.w = width;
		this.h = height;
	}

	public void drawBackgroundScreen() {

		for (int x = 0; x < game.getWidth() / 32; x++) {
			for (int y = 0; y < game.getHeight() / 32; y++) {
				game.g.drawImage(game.sheetTiles.getImage(7), x * 40, y * 40,
						40, 40, game);
				game.g.setColor(new Color(x + y, y * j / 60, x * i / 60, 155));
				game.g.fillRect(x * 40, y * 40, 40, 40);

			}
		}

		if (isDecreasing) {
			i--;
			j++;
		} else {
			i++;
			j--;
		}

		if ((j <= 0 || i >= 600) && !isDecreasing) {
			isDecreasing = true;
		}
		if ((j >= 600 || i <= 0) && isDecreasing) {
			isDecreasing = false;
		}

	}

	public void drawAnimatedBackground() {
		game.g.setColor(Color.BLACK);
		game.g.drawRect(0, 0, Game.WIDTH, Game.HEIGHT);

	}

	public void addButton(String action, Rectangle bounds) {
		bttn.put(bounds, action);
	}

	public void removeButton(Rectangle bounds) {
		bttn.remove(bounds);
	}

	public HashMap<Rectangle, String> getButtons() {
		return bttn;
	}

	public void tick() {

	}

	public void render(Graphics g) {
	}

	public void init(Game game) {
		this.game = game;
	}

	public void mousePressed(MouseEvent e) {
		Point m = new Point(e.getX(), e.getY());
		for (int i = 0; i < bttn.keySet().size(); i++) {
			Rectangle rec = (Rectangle) bttn.keySet().toArray()[i];
			if (rec.contains(m)) {
				String action = bttn.get(rec);
				game.soundman.playSound("select.wav");
				postAction(action);
				break;

			}
		}
	}

	public void postAction(String action) {

	}

	public void mouseReleased(MouseEvent e) {

	}

	public void keyPressed(KeyEvent arg0) {
		if (arg0.getKeyCode() == KeyEvent.VK_ESCAPE)
			game.setScreen(new ScreenMainMenu(w, h, sheet));

	}

	public void keyReleased(KeyEvent arg0) {
	}

	public void focusGained(FocusEvent arg0) {

	}

	public void focusLost(FocusEvent arg0) {

	}

}
