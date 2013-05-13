package game.screen;

import game.Game;
import game.SpriteSheet;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Screen {

	int w, h;
	SpriteSheet sheet;
	Random rand = new Random();

	public Game game;
	

	boolean shouldDrawIGui = false;
	private HashMap<Rectangle, String>bttn = new HashMap<Rectangle, String>();
	private ArrayList<Integer[]> animlines = new ArrayList<Integer[]>();
	private int makeCooldown = 0;
	

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
	
	public void drawAnimatedBackground()
	{
		makeCooldown--;
		while(animlines.size() < 40 || makeCooldown == 0)
		{
			game.g.setColor(Color.black);
			game.g.fillRect(0,0,Game.WIDTH, Game.HEIGHT);
			
			Integer[] lines = {0, rand.nextInt(10), rand.nextInt(Game.HEIGHT)};
			animlines.add(lines);
			
		}
		for(int i = 0; i < animlines.size(); i++)
		{
			Integer[] lines = animlines.get(i);
			game.g.setColor(Color.white);
			game.g.fillRect((Game.WIDTH-lines[1])-lines[0], lines[2], lines[1], 2);
			lines[0]++;
			if((Game.WIDTH-lines[1])-lines[0] > Game.WIDTH)
			{
				animlines.remove(i);
			}else
			{
				animlines.set(i, lines);
			}
			
			
		}	
		
		
		if(makeCooldown <= 0)
		{
			makeCooldown = 60;
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
		if(arg0.getKeyCode() == KeyEvent.VK_ESCAPE)
			game.setScreen(new ScreenMainMenu(w, h, sheet));

	}

	public void keyReleased(KeyEvent arg0) {
	}
	

	public void focusGained(FocusEvent arg0) {


	}


	public void focusLost(FocusEvent arg0) {


	}

}
