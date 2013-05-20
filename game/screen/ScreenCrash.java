package game.screen;

import game.Game;
import game.SpriteSheet;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class ScreenCrash extends Screen{

	
	private String[] crashdump;
	public ScreenCrash(int width, int height, SpriteSheet sheet, String[] crashdump) {
		super(width, height, sheet);
		this.crashdump = crashdump;
	}
	
	
	@Override
	public void render(Graphics g)
	{
		
		game.getFontRenderer().drawString("Game Crash.",20, 50, 2, Color.red);
		game.getFontRenderer().drawString("See console for more details.",20, 140, 2, Color.red);
		g.setColor(Color.RED);
		for(int i = 0; i < crashdump.length; i++)
		{
			if(crashdump[i] == null )
			{
				crashdump[i] = "N/A";
			}
		
	
			game.getFontRenderer().drawString(crashdump[i], 20, 200+(10*i), 1);
		}
		
		game.getFontRenderer().drawString("Press any key to >ATTEMPT< to restart the game",20, 540, 2, Color.red);
		
		
	}
	
	@Override
	public void keyPressed(KeyEvent e)
	{
		Game.log("Attempting to recover from crash...");
		game.gameRunning = true;
		Game.log("Restarting game loop.");
		game.gameLoop();
		Game.log("Reinitializing game...");
		game.init();

	
	}

}
