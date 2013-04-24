package game.screen;

import game.Client;
import game.Controls;
import game.Game;
import game.MathHelper;
import game.PacketDecoder;
import game.SpriteSheet;
import game.entity.Entity;
import game.entity.EntityAmmo;
import game.entity.Player;
import game.thread.ThreadGetData;
import game.tile.Tile;
import game.tile.TileGrass;
import game.tile.TilePaving;
import game.tile.TileRubble;
import game.tile.TileWall;
import game.tile.TileWater;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class ScreenMultiplayer extends ScreenGame {

	private Client client;
	private PacketDecoder pd;
	private Player player;
	public ArrayList<ArrayList<Integer>> sentEntities = new ArrayList<ArrayList<Integer>>();
	private ArrayList<Entity> entities = new ArrayList<Entity>();
	private HashMap<Rectangle, Tile> tiles = new HashMap<Rectangle, Tile>();
	public HashMap<Rectangle, Integer> sentTiles;
	public boolean loading = false;

	int velx = 0, vely = 0, w, h, mapSize = 0, xScroll = 0, yScroll = 0,
			counter = 0, loadingTank = 0;

	public ScreenMultiplayer(int width, int height, SpriteSheet sheet,
			Graphics g, String ip, int port) {
		super(width, height, sheet, g);

		player = new Player(this);
		pd = new PacketDecoder();
		client = new Client(ip, port, game);

		Thread thread = new Thread(new ThreadGetData(this, client));
		try{
		thread.run();
		}catch(Exception e)
		{
			game.setScreen(new ScreenConnectionFailiure(w, h, sheet));
		}
		tiles = pd.decodeMapPacket(sentTiles, this);
		//entities = pd.decodeEntityPacket(sentEntities, this);
		
		System.out.println("Tiles: " + tiles);
		
		
	

	}

	public void spawnEntity(Entity entity) {
		// entities.add(entity);
	}

	@Override
	public void tick() {
		if(tiles == null || entities == null)
		{
			
		}else
		{
			loading = false;
		}
		client.sendVelocity(velx, vely);
		counter++;
		if (counter == 60) {
			client.sendHeartbeat();
			counter = 0;
		}

	}

	public Tile getTileAt(int x, int y) {
		Rectangle rec = new Rectangle(MathHelper.round(x, 16 * Game.SCALE),
				MathHelper.round(y, 16 * Game.SCALE), 16 * game.SCALE,
				16 * game.SCALE);
		return tiles.get(rec);
	}

	public Entity getEntityInBox(Rectangle rec) {
		for (Entity ent : entities) {
			if (rec.contains(new Point(ent.x, ent.y))) {
				return ent;
			}
		}
		return null;
	}

	public void loadingScreen(Graphics g) {
		game.getFontRenderer().drawCenteredString("Loading...      ", 430, 3);
		loadingTank++;
		g.drawImage(game.sheet.getImage(46), loadingTank, 380, 32, 32, game);
		if (loadingTank > game.WIDTH + 64)
			loadingTank = -64;
	}

	public void render(final Graphics g) {

		if (loading)
			loadingScreen(g);

		for (int i = 0; i < tiles.keySet().size(); i++) {
			Rectangle rec = (Rectangle) tiles.keySet().toArray()[i];
			Tile tile = tiles.get(rec);
			tile.tick();
			// g.drawRect(rec.x, rec.y, rec.width, rec.height);
			g.drawImage(game.sheet.getImage(tile.sprite), rec.x - xScroll,
					rec.y - yScroll, rec.width, rec.height, game);

		}
		if (game.settings.getSetting("Debug") == "ON") {
			game.getFontRenderer().drawString(
					"DX:" + velx + " DY:" + vely + " SX:" + xScroll + " SY:"
							+ yScroll + " WX:" + Game.WIDTH + " WY:"
							+ Game.HEIGHT, 260, 0, 1);
			game.getFontRenderer().drawString(
					"X:" + player.x + " Y:" + player.y + " ROT:"
							+ player.getOrientation() + " HEL:"
							+ player.getHealth() + " AMM:" + player.getAmmo()
							+ " CLD: " + player.ammocooldown, 260, 10, 1);

		}

		for (int i = 0; i < entities.size(); i++) {
			Entity ent = entities.get(i);
			if (ent.forRemoval)
				entities.remove(i);
			ent.tick();
			ent.render(g);

		}

		g.setColor(new Color(0, 0, 0, 142));
		g.fillRect(0, h - 74, w, h);

		if (player.getHealth() > 0) {
			g.drawImage(sheet.getImage(69), 16, h - 64, 16, 16, game);
			for (int i = 0; i < player.getHealth() - 1; i++) {
				g.drawImage(sheet.getImage(70), 32 + (16 * i), h - 64, 16, 16,
						game);
			}

			g.drawImage(sheet.getImage(71), 16 + (16 * player.getHealth()),
					h - 64, 16, 16, game);

		}
		if (player.getAmmo() > 0) {

			g.drawImage(sheet.getImage(66), 16, h - 32, 16, 16, game);
			for (int i = 0; i < player.getAmmo() - 1; i++) {
				g.drawImage(sheet.getImage(67), 32 + (16 * i), h - 32, 16, 16,
						game);
			}

			g.drawImage(sheet.getImage(68), 16 + (16 * player.getAmmo()),
					h - 32, 16, 16, game);
			if (player.ammocooldown != 0) {
				g.drawImage(sheet.getImage(72), 32 + (16 * player.getAmmo()),
						h - 39, 32, 32, game);
				game.getFontRenderer().drawString(
						"" + player.ammocooldown / 60,
						32 + (16 * player.getAmmo()), h - 32, 1);
			}

		}

	}

}
