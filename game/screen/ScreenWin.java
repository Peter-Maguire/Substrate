package game.screen;

import game.Game;
import game.utils.SpriteSheet;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

public class ScreenWin extends Screen{

	
	private float timeTaken = 0.0F;
	private ArrayList<Integer> levelTimes;
	
	public ScreenWin(int width, int height, SpriteSheet sheet, int timeTaken, ArrayList<Integer> levelTimes) {
		super(width, height, sheet);
		System.out.println(timeTaken);
		this.timeTaken = timeTaken/60f;
		this.levelTimes = levelTimes;
		addButton("highscore", new Rectangle(550, 520, 200, 50));
	}
	
	
	@Override
	public void render(Graphics g)
	{
		g.drawImage(game.winScreen, 0, 0, Game.WIDTH, Game.HEIGHT, game);
		game.getFontRenderer().drawString("and it only took you\n     "+timeTaken+" minutes!", 475, 470, 2);
		
		ScreenTools.drawButton(550, 520, 200, 50, "Submit Time", g, game);
		
		for(int i = 0; i < levelTimes.size(); i++)
		{
			int j = levelTimes.get(i);
			game.getFontRenderer().drawString("Level "+i+":"+j/60+" seconds.", 475, 20+(32*i), 2);
			
		}
	}
	
	@Override
	public void postAction(String name)
	{
		if(name.equals("highscore"))
		{
			game.setScreen(new ScreenHighscore(w, h, sheet, timeTaken, levelTimes));
		}
	}


	@Override
	public void tick()
	{
	}

	
	
	
}
