package game.screen;

import game.FileSaver;
import game.Game;
import game.Map;
import game.MathHelper;
import game.SpriteSheet;
import game.entity.Entity;
import game.entity.EntityAmmo;
import game.entity.EntityBullet;
import game.entity.Player;
import game.tile.Tile;
import game.tile.WireProvider;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;

public class ScreenMapEditor extends Screen {

	
	
	HashMap<Rectangle, Tile> tiles = new HashMap<Rectangle, Tile>();
	ArrayList<Entity> entities = new ArrayList<Entity>();
	
	public ScreenMapEditor(int width, int height, SpriteSheet sheet) {
		super(width, height, sheet);
		init();
	}
	public ScreenMapEditor(int width, int height, SpriteSheet sheet, HashMap<Rectangle, Tile> tiles, ArrayList<Entity> entities) {
		super(width, height, sheet);
		this.tiles = tiles;
		this.entities = entities;
		init();
	}
	
	private void init()
	{
		
	}
	
	private void drawSelectionGrid(int x, int y, int texpos,String text, Graphics g)
	{
		g.drawImage(sheet.getImage(texpos), x, y,64,64, game);
		g.drawImage(sheet.getImage(14), x, y, 32, 32, game);
		g.drawImage(sheet.getImage(15), x+32, y, 32, 32, game);
		g.drawImage(sheet.getImage(30), x, y+32, 32, 32, game);
		g.drawImage(sheet.getImage(31), x+32, y+32, 32, 32, game);
		game.getFontRenderer().drawString(text, x+15, y+65, 1);
		
	}
	
	@Override
	public void render(Graphics g)
	{
		g.setColor(new Color(255,255,255,155));
		g.fillRect(0, 500, Game.WIDTH, 100);
		drawSelectionGrid(10, 520, 7,"Tile", g);
		drawSelectionGrid(104, 520, 28,"Tool", g);
		

	}

	
}
