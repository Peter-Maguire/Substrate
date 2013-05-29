package game.screen;

import game.Game;
import game.SpriteSheet;
import game.entity.Entity;
import game.mapeditor.tools.Tool;
import game.mapeditor.tools.ToolPencil;
import game.mapeditor.tools.ToolReplace;
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
	private boolean isPlacingTile = true;
	
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
		toolRegistry.add(new ToolPencil("Pencil", 1));
		toolRegistry.add(new ToolReplace("Replacer", 0));
		addButton("selectTile", new Rectangle(10, 520, 64, 64));
		addButton("selectTool", new Rectangle(104, 520, 64, 64));
		
		int i = 0;
		for(Tool t : toolRegistry){
			addButton(""+i, new Rectangle(112+(42*i), 434, 32, 32));
			i++;
		}
		
		i = 0;
		int x = 0;
		int y = 0;
		for(Tile t : Tile.tiles){
			addButton(""+i, new Rectangle(14+(42*x),227+(42*y),32,32));
			i++;
			x++;
			if(45*x > 400)
			{
				y++;
				x = 0;
			}
		}

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
		if(openMenu == MENU_TILE)
		{
			drawMenuBox(10,320,400,200, g);
			int i = 0;
			int x = 0;
			int y = 0;
			for(Tile t : Tile.tiles){
				g.setColor(Color.BLACK);
				g.drawRect(13+(42*x), 225+(42*y), 33, 34);
				g.setColor(new Color(0,0,0,135));
				g.fillRect(13+(42*x), 225+(42*y), 33, 34);
				g.drawImage(game.sheetTiles.getImage(Tile.tiles[i].sprite),14+(42*x),227+(42*y),32,32,game);
				i++;
				x++;
				if(45*x > 400)
				{
					y++;
					x = 0;
				}
			}
			
			
		}
		if(openMenu == MENU_TOOL)
		{
			drawMenuBox(104,450,300,50, g);
			int i = 0;
			for(Tool t : toolRegistry){
				g.setColor(Color.BLACK);
				g.drawRect(112+(42*i), 434, 32, 32);
				g.setColor(new Color(0,0,0,135));
				g.fillRect(112+(42*i), 434, 32, 32);
				g.drawImage(game.sheetUI.getImage(t.getSprite()),112+(42*i),436,32,32,game);
				i++;
			}
		}
		

	}

	@Override
	public void postAction(String name)
	{
		isPlacingTile = false;
		if(name == "selectTile")
		{
			openMenu = openMenu == MENU_TILE ? MENU_NONE : MENU_TILE;
			return;
		}
		if(name == "selectTool")
		{
			openMenu = openMenu == MENU_TOOL ? MENU_NONE : MENU_TOOL;
			return;
		}
		if(openMenu == MENU_TOOL)
		{
			currentTool = toolRegistry.get(Integer.parseInt(name));
			openMenu = MENU_NONE;
			return;
		}
		if(openMenu == MENU_TILE)
		{
			currentTile = Tile.tiles[Integer.parseInt(name)];
			openMenu = MENU_NONE;
		}

	}

	
}
