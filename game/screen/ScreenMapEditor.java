package game.screen;

import game.Game;
import game.SpriteSheet;
import game.entity.Entity;
import game.mapeditor.tools.Tool;
import game.mapeditor.tools.ToolPencil;
import game.tile.Tile;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashMap;

public class ScreenMapEditor extends Screen {

	
	
	private static final int MENU_NONE = 0;
	private static final int MENU_TILE = 1;
	private static final int MENU_TOOL = 2;
	
	private ArrayList<Tool> toolRegistry = new ArrayList<Tool>();
	private HashMap<Rectangle, Tile> tiles = new HashMap<Rectangle, Tile>();
	private ArrayList<Entity> entities = new ArrayList<Entity>();
	private int openMenu = 0;
	
	private Tool currentTool = null;
	private Tile currentTile = null;
	
	
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
		toolRegistry.clear();
		toolRegistry.add(new ToolPencil("Pencil", 1));
		addButton("selectTile", new Rectangle(10, 520, 64, 64));
		addButton("selectTool", new Rectangle(104, 520, 64, 64));
		
		currentTool = toolRegistry.get(0);
		currentTile = Tile.tiles[1];
	}
	
	private void drawTileSelection(int x, int y, int texpos,String text, Graphics g)
	{
		g.drawImage(game.sheetTiles.getImage(texpos), x, y,64,64, game);
		g.drawImage(sheet.getImage(14), x, y, 32, 32, game);
		g.drawImage(sheet.getImage(15), x+32, y, 32, 32, game);
		g.drawImage(sheet.getImage(30), x, y+32, 32, 32, game);
		g.drawImage(sheet.getImage(31), x+32, y+32, 32, 32, game);
		game.getFontRenderer().drawString(text, x+15, y+65, 1);
		
	}
	private void drawToolSelection(int x, int y, int texpos,String text, Graphics g)
	{
		g.drawImage(game.sheetUI.getImage(texpos), x, y,64,64, game);
		g.drawImage(sheet.getImage(14), x, y, 32, 32, game);
		g.drawImage(sheet.getImage(15), x+32, y, 32, 32, game);
		g.drawImage(sheet.getImage(30), x, y+32, 32, 32, game);
		g.drawImage(sheet.getImage(31), x+32, y+32, 32, 32, game);
		game.getFontRenderer().drawString(text, x+15, y+65, 1);
		
	}
	
	private void drawMenuBox(int x, int y, int width, int height, Graphics g){
		g.setColor(Color.white);
		g.drawRect(x, y+height/2, 64, height/2);
		g.drawRect(x, y-height/2, width, height);
		
		g.setColor(new Color(255,255,255,155));
		g.fillRect(x, y+height/2, 64, height/2);
		g.fillRect(x, y-height/2, width, height);
	}
	
	@Override
	public void render(Graphics g)
	{
		g.setColor(new Color(255,255,255,155));
		g.fillRect(0, 500, Game.WIDTH, 100);
		drawTileSelection(10, 520, currentTile.sprite,"Tile", g);
		drawToolSelection(104, 520, currentTool.getSprite(),"Tool", g);
		drawMenuBox(10,320,400,200, g);

	}
	
	
	
	
	@Override
	public void postAction(String name)
	{
		if(name == "selectTile")openMenu = MENU_TILE;
		
		if(name == "selectTool")openMenu = MENU_TOOL;
	}

	
}
