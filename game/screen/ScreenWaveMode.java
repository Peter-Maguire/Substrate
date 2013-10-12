package game.screen;

import game.Game;
import game.Map;
import game.entity.Player;
import game.utils.FileSaver;
import game.utils.SpriteSheet;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;

public class ScreenWaveMode extends ScreenGame {

	public int wave = 1, lastwave = 0, 
			noticetimer = 0, timetaken = 0;
	private ArrayList<Integer> levelTimes = new ArrayList<Integer>();


	public ScreenWaveMode(int width, int height, SpriteSheet sheet, Map mapfile) {
		super(width, height, sheet, mapfile);

	}


	
	@Override
	public void tick() {
	
		if (player == null)
		{
			System.out.println("Adding new player");
			player = new Player(this);
			player.setPos(px, py);
			entities.add(player);
		}
	
	
		player.tryMoveEntity(velx, vely);
	
		if (wave != lastwave) {
			noticetimer = 256;
			lastwave = wave;
			initwave(wave);
		}
		
		timetaken++;

		if (noticetimer > 0) {
			noticetimer--;
		}
	}
	
	@Override
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

	private void initwave(int nwave) {
		//MAP LOAD
		deinit();
		if(nwave == 1)
			timetaken = 0;
		else
			levelTimes.add(timetaken);
			
		File f = new File(FileSaver.getCleanPath()+"\\maps\\Level_"+nwave+".smf");
		if(f.exists())
		{
			initMap(FileSaver.loadMapFile(FileSaver.getCleanPath() + "\\maps\\Level_"+nwave+".smf"));
		//	wave++;
		}else
		{
			System.out.println("Unable to load level "+nwave+" wave "+wave);
			Game.log("Game won.");
			 game.setScreen(new ScreenWin(w,h,sheet, timetaken/60, levelTimes));
		}
		player = null;

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
