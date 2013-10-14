package game.console.command;

import game.Game;
import game.screen.ScreenGame;

public class CommandAddTile extends Command {

	public CommandAddTile(Game game) {
		super(game);
	}

	@Override
	public void executeCommand(String[] args) {

		if (args.length < 4 || args.length > 4) {
			Game.log("Not enough arguments!");
			return;
		}
		if (game.currentScreen instanceof ScreenGame) {
			int tileType = Integer.parseInt(args[3]);
			int x = Integer.parseInt(args[1]);
			int y = Integer.parseInt(args[2]);
			ScreenGame gameScreen = (ScreenGame) game.currentScreen;

			gameScreen.setTileAt(x, y, tileType);

		} else {
			Game.log("You are not currently in a game!");
			return;
		}

	}

}
