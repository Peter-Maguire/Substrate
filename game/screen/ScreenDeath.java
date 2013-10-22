package game.screen;

import game.utils.FileSaver;
import game.utils.SpriteSheet;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class ScreenDeath extends Screen
{

	private int animStage;
	public ScreenDeath(int width, int height, SpriteSheet sheet)
	{
		super(width, height, sheet);
		
		addButton("respawn", new Rectangle((width/2)-100, (height/2)+100, 200, 50));
	}
	
	
	@Override
	public void render(Graphics g)
	{
		super.drawBackgroundScreen();
		g.setColor(new Color(255,0,0,155));
		g.fillRect(0, 0, game.getWidth(), game.getHeight());
		game.getFontRenderer().drawCenteredString("YOU DIED!", game.getHeight()/2, 3);
		ScreenTools.drawButton((game.getWidth()/2)-100, (game.getHeight()/2)+100, 200, 50, "Start again", g, game);
		g.drawImage(game.sheetUI.getImage(animStage < 50 ? 12 : 13), (game.getWidth()/2)-60, 100, 128, 128, game);
	}
	
	public void tick()
	{
		animStage++;
		if(animStage == 100)
		{
			animStage = 0;
		}
		
	}
	
	
	@Override
	public void postAction(String action)
	{
		if(action.equals("respawn"))
			game.setScreen(new ScreenWaveMode(w, h, sheet, FileSaver.loadMapFile("/maps/Level_1.smf")));
		
	}

}
