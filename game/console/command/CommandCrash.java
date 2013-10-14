package game.console.command;

import game.Game;

public class CommandCrash extends Command {

	public CommandCrash(Game game) {
		super(game);
	}

	@Override
	public void executeCommand(String[] args) {
		Game.log("Crashing game by corrupting graphics instance");
		game.g = null;
	}

}
