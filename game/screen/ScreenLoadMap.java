package game.screen;

import game.FileSaver;
import game.Game;
import game.Map;
import game.SpriteSheet;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class ScreenLoadMap extends Screen{

	
	private String mapName = "-", mapDesc = "-", mapVersion = "-";
	private ArrayList<Map> maps = new ArrayList<Map>();
	private HashMap<Rectangle, Integer>buttons = new HashMap<Rectangle, Integer>();
	private int selectedMap = 0;
	public ScreenLoadMap(int width, int height, SpriteSheet sheet) {
		super(width, height, sheet);
		
	}
	
	@Override
	public void render(Graphics g)
	{
		drawBackgroundScreen();
		game.getFontRenderer().drawString("Select map to load", 32, 32, 2);
		g.setColor(new Color(0,0,0,155));
		g.fillRect(32, 52, 300, Game.HEIGHT-128);
		g.setColor(Color.black);
		g.drawRect(31, 51, 301, Game.HEIGHT-127);
		
		g.setColor(new Color(0,0,0,155));
		g.fillRect(422, 52, 320, Game.HEIGHT/2);
		g.setColor(Color.black);
		g.drawRect(421, 51, 321, Game.HEIGHT/2);
		
		game.getFontRenderer().drawString(mapName, 422, 355, 2);
		game.getFontRenderer().drawString(mapVersion, 712, 355, 1);
		game.getFontRenderer().drawString(mapDesc, 422, 375, 2);
		if(maps.size() == 0)
		getMaps();
		
		int i = 1;
		for(Map m : maps)
		{	
			g.setColor(new Color(255,255,255,155));
			g.fillRect(41, 4+(60*i), 280, 49);
			g.setColor(selectedMap == i ? Color.orange : Color.white);
			g.drawRect(40, 3+(60*i), 281,50);
			game.getFontRenderer().drawString(m.name, 40, 3+(60*i), 2);
			i++;
			
		}
		mapName = maps.get(selectedMap).name;
		mapDesc = maps.get(selectedMap).desc;
		mapVersion = maps.get(selectedMap).version;

	}
	
	private void getMaps()
	{
		String[] files = new File(FileSaver.getCleanPath()+"\\maps\\").list();
		int i = 0;
		for(String s : files)
		{
		
			File tf = new File(s);
			System.out.println(tf.getName());
			if(tf.getName().contains(".smf"))
			{
				i++;
				System.err.println("Added map: "+s);
				maps.add((Map)FileSaver.load(FileSaver.getCleanPath()+"\\maps\\"+s));
				buttons.put( new Rectangle(41, 4+(60*i), 280, 49), i);
			}
		}
	}
	
	@Override
	public void mousePressed(MouseEvent e)
	{
		Point m = new Point(e.getX(), e.getY());

		for (int i = 0; i < buttons.keySet().size(); i++) {

			Rectangle rec = (Rectangle) buttons.keySet().toArray()[i];
			if (rec.contains(m)) {
				selectedMap = buttons.get(rec);
				break;
			
			}
		}
	}

}
