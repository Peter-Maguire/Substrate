package game.console.command;

import game.Game;

public abstract class Command {

	protected Game game;

	public Command(Game game) {
		this.game = game;
	}

	public abstract void executeCommand(String[] args);

}
