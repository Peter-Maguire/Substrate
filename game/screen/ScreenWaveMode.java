package game.screen;

import game.Game;
import game.Map;
import game.SpriteSheet;
import game.entity.EntitySoldier;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

public class ScreenWaveMode extends ScreenGame{

	public int wave = 1, lastwave = 0, timeleft = 0, maxtime = 122, waveammount = 5, wavehealth = 5, waveammo = 20, wavecooldown = 256,
			noticetimer = 0; 
	
	public ScreenWaveMode(int width, int height, SpriteSheet sheet, Map mapfile) {
		super(width, height, sheet, mapfile);
	}
	
	
	@Override
	public void render(Graphics g)
	{
		super.render(g);
		if(game.settings.getSetting("Debug") == "ON")
		{
			game.getFontRenderer().drawString("W:"+wave+" LW:"+lastwave+" TL:"+timeleft+" MT:"+maxtime+" WHP:"+wavehealth+" WA:"+waveammo+" WCD:"+wavecooldown, 529, 0, 1);
			game.getFontRenderer().drawString("NOTICE:"+noticetimer, 609, 10, 1);
		}
		
		if(noticetimer > 0)
		{
			g.fillRect(0,100,Game.WIDTH, 50);
			game.getFontRenderer().drawCenteredString("WAVE "+wave, 113, 3);
		}
		
		g.drawImage(game.sheetUI.getImage(20), 681,Game.HEIGHT-64,32,32,game);
		game.getFontRenderer().drawString(timeleft < 0 ? "HALT" : timeleft/60+" S", 715, Game.HEIGHT-52,1, timeleft < 600 ? Color.red : Color.white);
		
	}
	
	
	@Override
	public void tick()
	{
		super.tick();
		if(wave != lastwave)
		{
			noticetimer = 256; 
			lastwave = wave;
			initwave(wave);
		}
		
		if(noticetimer > 0)
		{
			noticetimer--;
		}
		if(timeleft > 0 || timeleft < 0)
		{
			if(timeleft > 0)
			{
				timeleft--;
			}
		}else
		{
			wave++;
		}
	}
	
	
	private void initwave(int wave)
	{
		maxtime-=2;
		wavehealth = wavehealth*maxtime;
		waveammo = wavehealth-wave;
		wavecooldown = 256-(wave*2);
		waveammount = wave*3;
		timeleft = maxtime*60;
		
		
	}
	
	@Override
	public void mousePressed(MouseEvent e)
	{
		spawnEntity(new EntitySoldier(this, e.getX(), e.getY()));
	}
	

}
