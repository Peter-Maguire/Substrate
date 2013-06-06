package game.screen;

import game.Game;
import game.utils.SpriteSheet;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class ScreenCrash extends Screen{

	
	private String crashdump;
	public ScreenCrash(int width, int height, SpriteSheet sheet, String crashdump) {
		super(width, height, sheet);
		this.crashdump = crashdump;
	}
	
	
	@Override
	public void render(Graphics g)
	{
		Game.log("Game crash - Report the information below at http://github.com/UnacceptableUse/Susbtrate/issues");
		Game.log(crashdump);
		game.getFontRenderer().drawString("Game Crash.",20, 20, 2, Color.red);
		game.getFontRenderer().drawString("See console for more details.",20, 45, 2, Color.red);
		game.getFontRenderer().drawString(crashdump, 20, 65, 1);
		
		
	}
	
	@Override
	public void keyPressed(KeyEvent e)
	{
	
	
	}

}
