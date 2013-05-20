package game.console.command;

import game.Game;
import game.screen.ScreenGame;
import game.tile.Tile;

public class CommandAddTile extends Command{

	public CommandAddTile(Game game) {
		super(game);
	}

	@Override
	public void executeCommand(String[] args) {
		
		if(args.length < 4 || args.length > 4)
		{
			Game.log("Not enough arguments!");
			return;
		}
		if(game.currentScreen instanceof ScreenGame)
		{
			String tileType = args[1];
			int x = Integer.parseInt(args[2]);
			int y = Integer.parseInt(args[3]);
			ScreenGame gameScreen = (ScreenGame)game.currentScreen;
			
			for(int i = 0; i < Tile.tiles.length; i++)
			{
				if(Tile.tiles[i] instanceof  tileType)
				{
					
				}
			}
			gameScreen.setTileAt(x, y, );
		}else
		{
			Game.log("You are not currently in a game!");
			return;
		}
		
	}

}
