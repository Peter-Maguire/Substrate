package game.console.command;

import java.io.IOException;

import javax.imageio.ImageIO;

import game.Font;
import game.Game;

public class CommandFontInit extends Command{

	public CommandFontInit(Game game) {
		super(game);

	}

	@Override
	public void executeCommand(String[] args) {
		Game.log("Reloading font");
		try {
			game.font = new Font(ImageIO.read(Game.class
					.getResource("/res/font.png")), game.g,
					game);
		} catch (IOException e1) {
			Game.log("Unable to load font.");
			e1.printStackTrace();
			throw new RuntimeException("Unable to load font sheet.");
		}
	}

}
