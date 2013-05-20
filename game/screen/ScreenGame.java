package game.screen;

import game.Controls;
import game.Game;
import game.Map;
import game.MathHelper;
import game.SpriteSheet;
import game.entity.Entity;
import game.entity.EntitySign;
import game.entity.Player;
import game.tile.Tile;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;

public class ScreenGame extends Screen {

	
	public Player player = new Player(this);
	private ArrayList<Entity> entities = new ArrayList<Entity>();
	private HashMap<Rectangle, Tile> tiles = new HashMap<Rectangle, Tile>();

	int velx = 0, vely = 0, w, h;
	public int xScroll = 0;
	public int yScroll = 0;
	int mapSize = 0;



	public ScreenGame(int width, int height, SpriteSheet sheet, Graphics g, Map mapfile) {
		super(width, height, sheet);
		this.w = width;
		this.h = height;

		entities = mapfile.entities;
		tiles = mapfile.tiles;
		if(!mapfile.entities.contains(player))
		entities.add(player);
		

		
		

	}
	
	public void spawnEntity(Entity entity)
	{
		entities.add(entity);
	}


	@Override
	public void tick() {
		player.tryMoveEntity(velx, vely);

	}



	public Tile getTileAt(int x, int y) {
		Rectangle rec = new Rectangle(MathHelper.round(x, 16 * Game.SCALE),
				MathHelper.round(y, 16 * Game.SCALE), 16 * Game.SCALE,
				16 * Game.SCALE);
		return tiles.get(rec);
	}
	
	public void setTileAt(int x, int y, int tile)
	{
		Rectangle rec = new Rectangle(MathHelper.round(x, 16 * Game.SCALE),
				MathHelper.round(y, 16 * Game.SCALE), 16 * Game.SCALE,
				16 * Game.SCALE);
		
		tiles.put(rec, Tile.tiles[tile]);
	}
	public Entity getEntityInBox(Rectangle rec)
	{
		for(Entity ent : entities)
		{
			if(rec.contains(new Point(ent.x, ent.y)))
				{
				return ent;
				}
		}
		return null;
	}
	public ArrayList<Entity> getEntitiesInBox(Rectangle rec)
	{
		ArrayList<Entity>ents = new ArrayList<Entity>();
		for(Entity ent : entities)
		{
			if(rec.contains(new Rectangle(ent.x, ent.y, 32, 32)))
				{
					ents.add(ent);	
				}
		}
		return ents;
	}

	@Override
	public void render(final Graphics g) {

		
		
		for (int i = 0; i < tiles.keySet().size(); i++) {
			Rectangle rec = (Rectangle) tiles.keySet().toArray()[i];
			Tile tile = tiles.get(rec);	
			tile.tick();
			g.drawImage(game.sheetTiles.getImage(tile.sprite), rec.x - xScroll,
					rec.y - yScroll, rec.width, rec.height, game);

		}
		if (game.settings.getSetting("Debug") == "ON") {
			game.getFontRenderer().drawString("DX:" + velx + " DY:" + vely+" SX:"+xScroll+" SY:"+yScroll+" WX:" + Game.WIDTH + " WY:" + Game.HEIGHT, 260, 0, 1);
			game.getFontRenderer().drawString("X:" + player.x + " Y:" + player.y+" ROT:"+player.getOrientation()+" HEL:"+player.getHealth()+ " AMM:"+player.getAmmo()+ " CLD: "+player.ammocooldown, 260, 10, 1);

	
			
		}
		
		

		for(int i = 0; i < entities.size(); i++)
		{
			Entity ent = entities.get(i);
			if(ent.forRemoval)
				entities.remove(i);
			ent.tick();
			ent.render(g);

		}
		
		g.setColor(new Color(155, 155, 155, 142));
		g.fillRect(0, h-74, w, h);
		
		if(player.getHealth() > 0)
		{
			g.drawImage(sheet.getImage(69), 16, h-64, 16, 16, game);
			for(int i = 0; i < player.getHealth()-1; i++)
			{
				g.drawImage(sheet.getImage(70), 32+(16*i), h-64, 16, 16, game);
			}
			
				g.drawImage(sheet.getImage(71), 16+(16*player.getHealth()), h-64, 16, 16, game);
			
		}
		if(player.getAmmo() > 0)
		{
			
				
			
			g.drawImage(sheet.getImage(66), 16, h-32, 16, 16, game);
			for(int i = 0; i < player.getAmmo()-1; i++)
			{
				g.drawImage(sheet.getImage(67), 32+(16*i), h-32, 16, 16, game);
			}
			
				g.drawImage(sheet.getImage(68), 16+(16*player.getAmmo()), h-32, 16, 16, game);
				if(player.ammocooldown != 0)
				{
					g.drawImage(sheet.getImage(72),32+(16*player.getAmmo()), h-39,32,32, game); 
					game.getFontRenderer().drawString(""+player.ammocooldown/60, 32+(16*player.getAmmo()),h-32, 1);
				}

		}

	}

	@Override
	public void keyPressed(KeyEvent e) {
		player.keyPressed(e);
		if (e.getKeyCode() == game.controls.getKey(Controls.CONTROL_UP)) {
			vely = -1;
		}
		if (e.getKeyCode() == game.controls.getKey(Controls.CONTROL_DOWN)) {
			vely = 1;
		}
		if (e.getKeyCode() == game.controls.getKey(Controls.CONTROL_LEFT)) {
			velx = -1;
		}
		if (e.getKeyCode() == game.controls.getKey(Controls.CONTROL_RIGHT)) {
			velx = 1;
	
		}
		if (e.getKeyCode() == game.controls.getKey(Controls.CONTROL_ESCAPE)) {
			game.setScreen(new ScreenMainMenu(w, h, sheet));
	
		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		entities.add(new EntitySign(this, "This Sign", "is", "is a test", e.getX(), e.getY()));
		/*tiles.put(new Rectangle(MathHelper.round(e.getX(), 16 * Game.SCALE),
				MathHelper.round(e.getY(), 16 * Game.SCALE), 32, 32), e
				.getButton() == MouseEvent.BUTTON1 ? Tile.tiles[11] : Tile.tiles[1]); //TODO: FIX
*/	}

	@Override
	public void keyReleased(KeyEvent e) {

		player.keyReleased(e);
		if (e.getKeyCode() == game.controls.getKey(Controls.CONTROL_UP)) {
			vely = 0;
		}
		if (e.getKeyCode() == game.controls.getKey(Controls.CONTROL_DOWN)) {
			vely = 0;
		}
		if (e.getKeyCode() == game.controls.getKey(Controls.CONTROL_LEFT)) {
			velx = 0;
		}
		if (e.getKeyCode() == game.controls.getKey(Controls.CONTROL_RIGHT)) {
			velx = 0;
		}
	}

}
