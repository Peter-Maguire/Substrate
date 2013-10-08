package game.screen;

import game.Game;
import game.utils.SpriteSheet;

import java.awt.Graphics;
import java.awt.Rectangle;

public class ScreenWin extends Screen{

	
	private int timeTaken = 0;
	
	public ScreenWin(int width, int height, SpriteSheet sheet, int timeTaken) {
		super(width, height, sheet);
		this.timeTaken = timeTaken;
		addButton("highscore", new Rectangle(550, 520, 200, 50));
	}
	
	
	@Override
	public void render(Graphics g)
	{
		g.drawImage(game.winScreen, 0, 0, Game.WIDTH, Game.HEIGHT, game);
		game.getFontRenderer().drawString("and it only took you\n     "+timeTaken+" seconds!", 475, 470, 2);
		
		ScreenTools.drawButton(550, 520, 200, 50, "Submit Time", g, game);
	}
	
	@Override
	public void postAction(String name)
	{
		if(name == "highscore")
		{
			game.setScreen(new ScreenHighscore(w, h, sheet, timeTaken));
		}
	}

	
	
	
}
