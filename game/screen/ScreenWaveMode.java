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

	public int wave = 1, lastwave = 0, timeleft = 0, maxtime = 122,
			waveammount = 5, wavehealth = 5, waveammo = 20, wavecooldown = 256,
			noticetimer = 0;
	public GridMap map;

	public ScreenWaveMode(int width, int height, SpriteSheet sheet, Map mapfile) {
		super(width, height, sheet, mapfile);

	}

	@Override
	public void render(Graphics g) {
		super.render(g);
		if (game.settings.getSetting("Debug") == "ON") {
			game.getFontRenderer().drawString(
					"W:" + wave + " LW:" + lastwave + " TL:" + timeleft
							+ " MT:" + maxtime + " WHP:" + wavehealth + " WA:"
							+ waveammo + " WCD:" + wavecooldown, 529, 0, 1);
			game.getFontRenderer().drawString("NOTICE:" + noticetimer, 609, 10,
					1);
		}

		if (noticetimer > 0) {
			g.fillRect(0, 100, Game.WIDTH, 50);
			game.getFontRenderer().drawCenteredString("LEVEL " + wave, 113, 3);
			if(new File("//maps//Level_"+wave+".smf").exists())
			{
				initMap((Map) FileSaver
						.load(FileSaver.getCleanPath() + "//maps//Level_"+wave+".smf"));
				wave++;
			}else
			{
				System.out.println("You win!");
				//TODO: GAME WIN game.setScreen(new ScreenGameWin(w,h,sheet));
			}
		
		
		}

		g.drawImage(game.sheetUI.getImage(20), 681, Game.HEIGHT - 64, 32, 32,
				game);
		game.getFontRenderer().drawString(
				timeleft < 0 ? "HALT" : timeleft / 60 + " S", 715,
				Game.HEIGHT - 52, 1, timeleft < 600 ? Color.red : Color.white);

	}

	@Override
	public void tick() {
		if (map == null) {
			Game.log("Initializing path finder...");
			map = new GridMap(Game.WIDTH, (Game.HEIGHT - 64));
			System.out.println("Map size: " + w / 32 + "," + h / 32);
			for (int i = 0; i < tiles.keySet().size(); i++) {
				Rectangle rec = (Rectangle) tiles.keySet().toArray()[i];
				Tile tile = tiles.get(rec);
				System.out.println("Entry " + i + ": Tile " + tile.toString()
						+ " Position: " + rec.x / 32 + "," + rec.y / 32
						+ " Passable: " + tile.isPassable());
				map.set(rec.x, rec.y, tile.isPassable() ? 1 : -1);
			}
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
		if (timeleft > 0) {

			timeleft--;

		} else {
			wave++;
		}
	}

	private void initwave(int wave) {
		maxtime--;
		wavehealth = wavehealth * maxtime;
		waveammo = wavehealth - wave;
		wavecooldown = 256 - (wave * 2);
		waveammount = wave * 3;
		timeleft = maxtime * 60;

	}
	
	@Override
	public void deinit()
	{
		Game.log("Unloading entities...");
		entities.clear();
		Game.log("Unloading map...");
		tiles.clear();
	}
	

	@Override
	public void mousePressed(MouseEvent e) {
		spawnEntity(new EntitySoldier(this, e.getX(), e.getY(), map));
	}

}
