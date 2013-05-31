package game.screen;

import game.FileSaver;
import game.Game;
import game.Map;
import game.MathHelper;
import game.SpriteSheet;
import game.entity.Entity;
import game.entity.EntityAmmo;
import game.entity.EntityBox;
import game.entity.EntityExplosion;
import game.entity.EntitySign;
import game.entity.Player;
import game.mapeditor.tools.Tool;
import game.mapeditor.tools.ToolBox;
import game.mapeditor.tools.ToolPencil;
import game.mapeditor.tools.ToolReplace;
import game.tile.Tile;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ScreenMapEditor extends Screen{

	

	private static final int MENU_NONE = 0;
	private static final int MENU_TILE = 1;
	private static final int MENU_TOOL = 2;
	private static final int MENU_ENTITY = 3;
	
	private ArrayList<Tool> toolRegistry = new ArrayList<Tool>();
	private ArrayList<Entity> entityRegistry = new ArrayList<Entity>();
	public HashMap<Rectangle, Tile> tiles = new HashMap<Rectangle, Tile>();
	private ArrayList<Entity> entities = new ArrayList<Entity>();
	private int openMenu = 0, mapVersion = 1;
	private boolean isPlacingTile = true, showGrid = true, isEntityMode = false;
	
	private Tool currentTool = null;
	public Tile currentTile = null; 
	private Entity currentEntity = null;
	
	
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
		
		for(int x = 0; x < Game.WIDTH/32; x++)
		{
			for(int y = 0; y < (Game.HEIGHT-64)/32; y++)
			{
				tiles.put(new Rectangle(x*32, y*32, 32, 32), Tile.tiles[0]);
			}
		}
		toolRegistry.add(new ToolPencil("Pencil", 1));
		toolRegistry.add(new ToolReplace("Replacer", 0));
		toolRegistry.add(new ToolBox("Rectangle", 6));
		entityRegistry.add(new EntityBox());
		entityRegistry.add(new EntityAmmo());
		entityRegistry.add(new EntityExplosion());
		entityRegistry.add(new EntitySign());
		entityRegistry.add(new Player());
		
		addButton("selectTile", new Rectangle(10,520,64,64));
		addButton("selectTool", new Rectangle(104,520,64,64));
		addButton("selectEntity", new Rectangle(194,520,64,64));
		addButton("toggleGrid", new Rectangle(350,579,32,32));
		addButton("toggleMode", new Rectangle(385,520,33,32));
		
		addButton("save", new Rectangle(350,515,32,32));
		addButton("open", new Rectangle(350,547,32,32));
		
		
	
		
	
		
		

		currentTool = toolRegistry.get(0);
		currentTile = Tile.tiles[1];
		currentEntity = entityRegistry.get(0);
		
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
	private void drawEntitySelection(int x, int y, int texpos,String text, Graphics g)
	{
		g.drawImage(currentEntity instanceof EntityExplosion ? game.sheetExplosions.getImage(texpos) : game.sheetEntities.getImage(texpos), x, y,64,64, game);
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
	
	private void drawMap(Graphics g)
	{
		for (int i = 0; i < tiles.keySet().size(); i++) {
			Rectangle rec = (Rectangle) tiles.keySet().toArray()[i];
			Tile tile = tiles.get(rec);	
			tile.tick();
			g.drawImage(game.sheetTiles.getImage(tile.sprite), rec.x,
					rec.y, rec.width, rec.height, game);
			g.setColor(Color.white);
			if(showGrid)g.drawRect(rec.x, rec.y,32,32);
		}
		for(Entity e : entities)
		{
			e.render(g);
		}
	}
	
	private void drawUI(Graphics g)
	{
		g.setColor(new Color(255,255,255,155));
		g.fillRect(0, 514, Game.WIDTH, 96);
		drawTileSelection(10, 520, currentTile.sprite,"Tile", g);
		drawToolSelection(104, 520, currentTool.getSprite(),"Tool", g);
		drawEntitySelection(194, 520, currentEntity.sprite,"Entity", g);
		g.drawImage(game.sheetUI.getImage(2),350,515,32,32,game);
		g.drawImage(game.sheetUI.getImage(3),350,547,32,32,game);
		g.drawImage(game.sheetUI.getImage(showGrid ? 5 : 4),350,579,32,32,game);
		g.setColor(Color.BLACK);
		g.drawRect(385, 520, 32, 32);
		g.setColor(new Color(0,0,0,135));
		g.fillRect(385, 520, 33, 32);
		game.getFontRenderer().drawString(isEntityMode ? "ENT" : "TILE", 385, 527, 1);
		game.getFontRenderer().drawString("MODE", 385, 537, 1);
		if(openMenu == MENU_TILE)
		{
			drawMenuBox(10,320,400,200, g);
			int x = 0;
			int y = 0;
			for(Tile t : Tile.tiles){
				g.setColor(Color.BLACK);
				g.drawRect(13+(42*x), 225+(42*y), 33, 34);
				g.setColor(new Color(0,0,0,135));
				g.fillRect(13+(42*x), 225+(42*y), 33, 34);
				g.drawImage(game.sheetTiles.getImage(t.sprite),14+(42*x),227+(42*y),32,32,game);
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
		
		if(openMenu == MENU_ENTITY)
		{
			drawMenuBox(194,450,300,50, g);
			int i = 0;
			for(Entity e : entityRegistry){
				g.setColor(Color.BLACK);
				g.drawRect(212+(42*i), 434, 32, 32);
				g.setColor(new Color(0,0,0,135));
				g.fillRect(212+(42*i), 434, 32, 32);
				g.drawImage(e instanceof EntityExplosion ? game.sheetExplosions.getImage(e.sprite) : game.sheetEntities.getImage(e.sprite),212+(42*i),436,32,32,game);
				i++;
			}
		}
	}
	
	@Override
	public void render(Graphics g)
	{
		drawMap(g);
		drawUI(g);
	}
	
	public Tile getTileAt(int x, int y) {
		Rectangle rec = new Rectangle(MathHelper.round(x, 16 * Game.SCALE),
				MathHelper.round(y, 16 * Game.SCALE), 16 * Game.SCALE,
				16 * Game.SCALE);
		return tiles.get(rec);
	}
	
	public void setTileAt(int x, int y, Tile tile)
	{
		if(game.settings.getSetting("UseAdvancedTilePlacement") == "ON")
		{
			for (int i = 0; i < tiles.keySet().size(); i++) {
				Rectangle rec = (Rectangle) tiles.keySet().toArray()[i];
				if(rec.contains(x,y))
					{
					 tiles.put(rec, tile);
					 return;
					}
			}
			
			
		}else{
		Rectangle rec = new Rectangle(MathHelper.round(x, 16 * Game.SCALE),
				MathHelper.round(y, 16 * Game.SCALE), 16 * Game.SCALE,
				16 * Game.SCALE);
		
		tiles.put(rec, tile);
		}
	}
	
	@Override
	public void mousePressed(MouseEvent arg0) {
		isPlacingTile = true;
		super.mousePressed(arg0);
		
		if(isPlacingTile && openMenu == MENU_NONE && !isEntityMode)
		{
			currentTool.onToolUsed(arg0.getX(), arg0.getY(), this);
		}else if(isPlacingTile && isEntityMode && openMenu == MENU_NONE)
		{
			try {
				Entity newent = currentEntity.getClass().newInstance();
				newent.x = arg0.getX();
				newent.y = arg0.getY();
				newent.game = game;
				entities.add(newent);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
		}
		
	}

	@Override
	public void postAction(String name)
	{
		isPlacingTile = false;
		if(name == "save")
		{
			saveMap();
			return;
		}
		if(name == "open")
		{
			openFile();
			return;
		}
		if(name == "selectTile")
		{
			if(openMenu == MENU_TILE)
			{
				openMenu = MENU_NONE;
				int x = 0;
				int y = 0;
				for(Tile t : Tile.tiles){
					removeButton(new Rectangle(14+(42*x),227+(42*y),32,32));
					x++;
					if(45*x > 400)
					{
						y++;
						x = 0;
					}
				}
				return;
			}else
			{
				openMenu = MENU_TILE;
				int i = 0;
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

				return;
			}
			
			
		
		}
		if(name == "selectTool")
		{
			if(openMenu == MENU_TOOL)
			{
				openMenu = MENU_NONE;
				int i = 0;
				for(Tool t : toolRegistry){
					removeButton(new Rectangle(112+(42*i), 434, 32, 32));
					i++;
				}
			}else
			{
				openMenu = MENU_TOOL;
				int i = 0;
				for(Tool t : toolRegistry){
					addButton(""+i, new Rectangle(112+(42*i), 434, 32, 32));
					i++;
				}
			}
			
			
			return;
		}
		if(name == "selectEntity")
		{
			if(openMenu == MENU_ENTITY)
			{
				openMenu = MENU_NONE;
				int i = 0;
				for(Entity e : entityRegistry){
					removeButton(new Rectangle(212+(42*i), 434, 32, 32));
					i++;
				}
			}else
			{
				openMenu = MENU_ENTITY;
				int i = 0;
				for(Entity e : entityRegistry){
					addButton(i+"",new Rectangle(212+(42*i), 434, 32, 32));
					i++;
				}
			}
			
			
			return;
		}
		if(name == "toggleGrid")
		{
			showGrid = !showGrid;
			return;
		}
		if(name == "toggleMode")
		{
			isEntityMode = !isEntityMode;
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
		if(openMenu == MENU_ENTITY)
		{
			currentEntity = entityRegistry.get(Integer.parseInt(name));
			openMenu = MENU_NONE;
		}
		


	}
	
	private void openFile()
	{
		JFileChooser fileChooser = new JFileChooser(new File(FileSaver.getCleanPath()+"/maps/"));
		fileChooser.setAcceptAllFileFilterUsed(false);
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
		        "Substrate Map File", "smf");
		fileChooser.setFileFilter(filter);
	
		if (fileChooser.showOpenDialog(new JFrame("Open")) == JFileChooser.APPROVE_OPTION) {
		  File file = fileChooser.getSelectedFile();
		  Map loadedMap = (Map)FileSaver.load(file.getAbsolutePath());
		  this.tiles = loadedMap.tiles;
		  try {
			this.entities = FileSaver.serialToEntity(loadedMap.entities, game);
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException e) {
			Game.log("Sad trumpet noise");
			e.printStackTrace();
		}
		  this.mapVersion = Integer.parseInt(loadedMap.version);
		}
	
	}
	
	private void saveMap()
	{
		JFileChooser fileChooser = new JFileChooser(new File(FileSaver.getCleanPath()+"/maps/"));
		fileChooser.setAcceptAllFileFilterUsed(false);
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
		        "Substrate Map File", "smf");
		fileChooser.setFileFilter(filter);
		if (fileChooser.showSaveDialog(new JFrame("Save")) == JFileChooser.APPROVE_OPTION) {
		  File file = fileChooser.getSelectedFile();
	
		  
		  Map savedMap = new Map(file.getName().replace("_"," ").replace(".smf",""), "NYI", (mapVersion+1)+"",tiles, FileSaver.entityToSerial(entities));

		  FileSaver.save(savedMap, file.getAbsolutePath().contains(".smf") == false ? file.getAbsolutePath()+".smf" : file.getAbsolutePath());
		 
		}
	}

	
}
