package game.console.command;

import game.Game;

public class CommandReinit extends Command {

	public CommandReinit(Game game) {
		super(game);
	}

	@Override
	public void executeCommand(String[] args) {
		Game.log("Attempting to reinitalize the current screen...");
		game.currentScreen.init(game);
		Game.log("Done!");

	}

}
