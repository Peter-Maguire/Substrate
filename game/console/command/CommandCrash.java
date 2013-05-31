package game.console.command;

import game.Game;
import game.screen.ScreenCrash;

public class CommandCrash extends Command{

	public CommandCrash(Game game) {
		super(game);
	}

	@Override
	public void executeCommand(String[] args) {
		Game.log("Crashing game by dividing by 0.");
		String[] crashDump = { "Crashed on screen: "+game.currentScreen.getClass().getName(), "Game version: "+Game.VERSION,
				"FPS: (Manual crash)", "TPS: (Manual Crash)",
				"Stack trace message: Manual crash","Settings: "+game.SETTINGS, "---SYSTEM STATS---", "Operating System: "+ System.getProperty("os.name")+"_"+System.getProperty("os.version"), "Java Version: "+System.getProperty("java.version") };
		game.setScreen(new ScreenCrash(Game.WIDTH, Game.HEIGHT, null, crashDump));
	}

}
