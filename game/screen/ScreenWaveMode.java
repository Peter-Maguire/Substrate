package game.screen;

import game.Game;
import game.Map;
import game.entity.EntitySoldier;
import game.pathfinding.grid.GridMap;
import game.tile.Tile;
import game.utils.FileSaver;
import game.utils.SpriteSheet;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.io.File;

public class ScreenWaveMode extends ScreenGame {

	public int wave = 1, lastwave = 0, 
			noticetimer = 0;
	public GridMap map;

	public ScreenWaveMode(int width, int height, SpriteSheet sheet, Map mapfile) {
		super(width, height, sheet, mapfile);

	}

	public void render(Graphics g) {
		super.render(g);
		if (game.settings.getSetting("Debug").equals("ON")) {

			game.getFontRenderer().drawString("NOTICE:" + noticetimer, 609, 10,
					1);
		}
		if (noticetimer > 0) {
			g.fillRect(0, 100, Game.WIDTH, 50);
			game.getFontRenderer().drawCenteredString("LEVEL " + wave, 113, 3);

		}
	}
	

	public void tick() {
		if (player == null)
		{
		System.out.println("PLAY NULL");
		}
		super.tick();
		if (wave != lastwave) {
			noticetimer = 256;
			lastwave = wave;
			initwave(wave);
		}

		if (noticetimer > 0) {
			noticetimer--;
		}
	}

	private void initwave(int nwave) {
		//MAP LOAD
		deinit();
		
		File f = new File(FileSaver.getCleanPath()+"\\maps\\Level_"+nwave+".smf");
		if(f.exists())
		{
			initMap(FileSaver.loadMapFile(FileSaver.getCleanPath() + "\\maps\\Level_"+nwave+".smf"));
		//	wave++;
		}else
		{
			System.out.println("Unable to load level "+nwave+" wave "+wave);
			Game.log("Game won.");
			 game.setScreen(new ScreenWin(w,h,sheet, 0));
		}

	}
	
	@Override
	public void deinit()
	{
		Game.log("Unloading entities...");
		entities.clear();
		Game.log("Unloading map...");

	}
	

	@Override
	public void mousePressed(MouseEvent e) {
		//spawnEntity(new EntitySoldier(this, e.getX(), e.getY(), map));
	}

}
