package game.screen;

import game.Game;
import game.utils.FileSaver;
import game.utils.SpriteSheet;

import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

public class ScreenHighscore extends Screen
{
	
	public String[] names;
	public int[] scores;

	public ScreenHighscore(int width, int height, SpriteSheet sheet)
	{
		super(width, height, sheet);
	}
	
	@Override
	public void tick()
	{
		if(names == null && scores == null)
			getHighscores();
	}
	
	
	@Override
	public void render(Graphics g)
	{	
		this.drawBackgroundScreen();
		if(names == null && scores == null)
			game.getFontRenderer().drawString("LOADING", game.getWidth()/2-20,game.getHeight()/2, 1);
		game.getFontRenderer().drawString("Enter your username below to submit your highscore", 200, 80, 1);
		ScreenTools.drawButton(150, 100, 500, 400, " ", g, game);
		ScreenTools.drawButton(150, 520, 400, 40, " ", g, game);
		ScreenTools.drawButton(553, 521, 100, 40, "SUBMIT", g, game, new Color(255, 0, 0, 100), Color.red);
		
		game.getFontRenderer().drawString("Name                        Time taken to complete game", 185, 110, 1);
		
		for(int i = 0; i < scores.length; i++)
		{
			game.getFontRenderer().drawString(names[i], 160,140 + 32 * i, 2);
			game.getFontRenderer().drawString("-", 430,140 + 32 * i, 2);
			game.getFontRenderer().drawString(scores[i]+"", 500,140 + 32 * i, 2);
		}
		
		
	}
	
	public void getHighscores()
	{
		Game.log("Trying to get highscores...");
		try{
		String hsRaw = FileSaver.getURL("http://fightthetoast.co.uk/assets/scoreboard.php?type=DISPLAY");
		Game.log("Connection successful");
		String[] hsUnparsed = hsRaw.replace("\n","").split("<br>");
		names = new String[hsUnparsed.length];
		scores = new int[hsUnparsed.length];
		for(int i = 0; i < hsUnparsed.length; i++)
		{
			String[] score = hsUnparsed[i].split(" ");
			names[i] = score[0];
			scores[i] = Integer.parseInt(score[1]);
		}
		Game.log("Parsed "+names.length+" highscores");
		
		
		
		}catch(Exception e)
		{
			e.printStackTrace();
			names[0] = "Highscore loading failed";
		}
	}
	
	public boolean submitHighscore(String name, int score)
	{
		String scoreResponse = "";
		try
		{
			scoreResponse = FileSaver.getURL("http://fightthetoast.co.uk/assets/scoreboard.php?type=DISPLAY");
		} catch (Exception e)
		{
			return false;
		}
		return scoreResponse.contains("OK");
	}

}
