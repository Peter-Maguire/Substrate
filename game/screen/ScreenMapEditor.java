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
import game.tile.TileGrass;
import game.tile.TilePaving;
import game.tile.TileRubble;
import game.tile.TileWall;
import game.tile.TileWater;
import game.tile.TileWire;
import game.tile.WireProvider;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;

public class ScreenMapEditor extends Screen {

	private HashMap<Rectangle, String> buttons = new HashMap<Rectangle, String>();

	private HashMap<String, Tile> tileList = new HashMap<String, Tile>();
	private HashMap<String, Entity> entityList = new HashMap<String, Entity>();

	private ArrayList<Entity> entities = new ArrayList<Entity>();
	private HashMap<Rectangle, Tile> tiles = new HashMap<Rectangle, Tile>();
	private HashMap<String, Rectangle> specialPoints = new HashMap<String, Rectangle>();
	
	private String name = "Test map 1", desc = "Test map desc", version = "v9001";

	private Tile currentTile = null;

	public ScreenMapEditor(int width, int height, SpriteSheet sheet) {
		super(width, height, sheet);
		init();

	}

	public void init() {
		Game.log("Initializing...");
		tileList.put("Remover", null);


		entityList.put("Ammo Box", new EntityAmmo(null, 0, 0));
		entityList.put("Player", new Player(null));
		entityList.put("Bullet t1", new EntityBullet(null, 2, 1, 0, 0));
		entityList.put("Bullet t2", new EntityBullet(null, 3, 2, 0, 0));
		entityList.put("Bullet t3", new EntityBullet(null, 4, 3, 0, 0));
		

		int tileCnt = 0, y = 0, x = 0;

		for (int i = 0; i < tileList.keySet().size(); i++) {
			String name = (String) tileList.keySet().toArray()[i];
			Object o = tileList.get(name);
			Tile t = (Tile) o;

			x++;
			tileCnt++;
			if (tileCnt > 5) {
				tileCnt = 0;
				x = 1;
				y++;
			}

			buttons.put(new Rectangle(607 + Game.SIZE * x / 2,
					96 + (y * Game.SIZE) / 2, 32, 32), name);

		}
		
		buttons.put(new Rectangle(607 + Game.SIZE * 2 / 2, 112
				+ tileList.size() * 32 / 2 + (1 * Game.SIZE) / 2, 32, 32), "SAVE");
		
		Game.log("Done!");
	}

	public void render(Graphics g) {
		drawMap(g);
		drawUI(g);
	}

	@Override
	public void mouseReleased(MouseEvent e) {

		Point m = new Point(e.getX(), e.getY());

		for (int i = 0; i < buttons.keySet().size(); i++) {

			Rectangle rec = (Rectangle) buttons.keySet().toArray()[i];
			if (rec.contains(m)) {
				String action = buttons.get(rec);
				postAction(action);
				break;
			}
		}

		if (e.getButton() == 1) {
			tiles.put(
					new Rectangle(MathHelper.round(e.getX(), 16 * Game.SCALE),
							MathHelper.round(e.getY(), 16 * Game.SCALE), 32, 32),
					currentTile instanceof WireProvider ? currentTile : currentTile);
		}

	}

	public void postAction(String action) {
		if(action == "SAVE")
		{
			Game.log("Creating map object...");
			Map map = new Map(name, desc, version, tiles, entities);
			Game.log("Saving...");
			FileSaver.save(map, name+".smf");
			Game.log("Done!");
		}else
		{
			currentTile = tileList.get(action);
		}
		
		
	}

	public void drawUI(Graphics g) {
		g.setColor(new Color(0, 0, 0, 155));
		g.fillRect(w - 164, 0, w, h);
		game.getFontRenderer().drawString("Map Editor", w - 164, 10, 2);
		
		drawTileUI(g);
		
		drawEntityUI(g);
		
		drawMenuUI(g);

		

	}
	

	public void drawTileUI(Graphics g) {
		game.getFontRenderer().drawString("Tiles", w - 164, 72, 2);

		int tileCnt = 0, y = 0, x = 0;

		for (int i = 0; i < tileList.keySet().size(); i++) {
			x++;
			tileCnt++;
			if (tileCnt > 5) {
				tileCnt = 0;
				x = 1;
				y++;
			}
			String name = (String) tileList.keySet().toArray()[i];
			Object o = tileList.get(name);
			Tile t = tileList.get(name);

			g.drawImage(sheet.getImage(t == null ? 79 : t.sprite), 607
					+ Game.SIZE * x / 2, 96 + (y * Game.SIZE) / 2, 32, 32, game);
			g.setColor(Color.RED);
			if (t == currentTile)
				g.drawRect(607 + Game.SIZE * x / 2, 96 + (y * Game.SIZE) / 2,
						31, 31);
		}
	}
	
	public void drawEntityUI(Graphics g)
	{
		game.getFontRenderer().drawString("Entities", w - 164,
				12 + tileList.size() * 32 / 2, 2);

		int entCnt = 0, ey = 0, ex = 0;

		for (int i = 0; i < entityList.keySet().size(); i++) {
			ex++;
			entCnt++;
			if (entCnt > 5) {
				entCnt = 0;
				ex = 1;
				ey++;
			}
			String name = (String) entityList.keySet().toArray()[i];
			Object o = entityList.get(name);
			Entity t = entityList.get(name);
			g.drawImage(sheet.getImage(t.sprite), 607 + Game.SIZE * ex / 2, 32
					+ tileList.size() * 32 / 2 + (ey * Game.SIZE) / 2, 32, 32,
					game);
		}
	}
	
	public void drawMenuUI(Graphics g)
	{
		game.getFontRenderer().drawString("Menu", w - 164,
				(12 + tileList.size() * 32 / 2) + (12 + entityList.size() * 32 / 2), 2);
		
		g.drawImage(sheet.getImage(116), 607 + Game.SIZE * 1 / 2, 112
				+ tileList.size() * 32 / 2 + (1 * Game.SIZE) / 2, 32, 32,
				game);
		
		g.drawImage(sheet.getImage(117), 607 + Game.SIZE * 2 / 2, 112
				+ tileList.size() * 32 / 2 + (1 * Game.SIZE) / 2, 32, 32,
				game);
	}

	public void drawMap(Graphics g) {
		for (int x = 0; x < w; x += 16 * Game.SCALE) {
			for (int y = 0; y < h; y += 16 * Game.SCALE) {

				Rectangle rec = new Rectangle(x, y, 16 * Game.SCALE,
						16 * Game.SCALE);
				if (tiles.get(rec) == null) {
					g.drawImage(sheet.getImage(78), x, y, 32, 32, game);
				} else {
					g.drawImage(sheet.getImage(tiles.get(rec).sprite), x, y,
							32, 32, game);
				}

			}
		}
	}

}
