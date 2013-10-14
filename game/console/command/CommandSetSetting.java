package game.console.command;

import game.Game;

public class CommandSetSetting extends Command {

	public CommandSetSetting(Game game) {
		super(game);
	}

	@Override
	public void executeCommand(String[] args) {
		if (args.length < 3 || args.length > 3) {
			Game.log("Not enough arguments!");
			return;
		}
		game.settings.setSetting(args[1], args[2]);
	}

}
