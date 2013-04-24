package game.console.command;

import game.Game;

public class CommandHelp extends Command{

	
	
	
	@Override
	public void executeCommand(String[] args) {
		
		Game.log("This is a test command!");
	}

	

}
