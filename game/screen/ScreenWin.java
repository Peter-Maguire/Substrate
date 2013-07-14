package game.screen;

import java.awt.Graphics;

import game.Game;
import game.utils.SpriteSheet;

public class ScreenWin extends Screen{

	
	private int timeTaken = 0;
	
	public ScreenWin(int width, int height, SpriteSheet sheet, int timeTaken) {
		super(width, height, sheet);
		this.timeTaken = timeTaken;
	}
	
	
	@Override
	public void render(Graphics g)
	{
		g.drawImage(game.winScreen, 0, 0, Game.WIDTH, Game.HEIGHT, game);
		game.getFontRenderer().drawString("and it only took you\n     "+timeTaken+" seconds!", 475, 470, 2);
	}

	
	
	
}
