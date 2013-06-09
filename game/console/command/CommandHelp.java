package game.console.command;

import game.Game;

public class CommandHelp extends Command {

	public CommandHelp(Game game) {
		super(game);
	}

	@Override
	public void executeCommand(String[] args) {

		Game.log("This is a test command!");
	}

}
