package game.screen;

import game.Game;
import game.MathHelper;
import game.SpriteSheet;
import game.tile.WireProvider;

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
	private HashMap<Rectangle, String>bttn = new HashMap<Rectangle, String>();

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
	
	public void addButton(String action, Rectangle bounds)
	{
		bttn.put(bounds, action);
	}
	
	public HashMap<Rectangle, String>getButtons()
	{
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
				postAction(action);
				break;
		
				
			}
		}
	}

	
	public void postAction(String action)
	{
		
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
