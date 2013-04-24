package game.screen;

import game.SpriteSheet;

import java.awt.Color;
import java.awt.Graphics;

public class ScreenLoading extends Screen{

	
	private String[] crashdump;
	public ScreenLoading(int width, int height, SpriteSheet sheet, String[] crashdump) {
		super(width, height, sheet);
		this.crashdump = crashdump;
	}
	
	
	@Override
	public void render(Graphics g)
	{
		game.getFontRenderer().drawString("Game Crash. Restart the game to continue.",20, 50, 2);;
		game.getFontRenderer().drawString("See console for more details.",20, 140, 2);
		g.setColor(Color.RED);
		for(int i = 0; i < crashdump.length; i++)
		{
			if(crashdump[i] == null )
			{
				crashdump[i] = "null";
			}
		
	
			game.getFontRenderer().drawString(crashdump[i], 20, 200+(10*i), 1);
		}
		
	}

}
