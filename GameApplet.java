import game.Game;

import java.applet.Applet;
import java.awt.BorderLayout;

public class GameApplet extends Applet {

	private static final long serialVersionUID = 1L;
	Game game;

	@Override
	public void init() {
		game = new Game();
		setVisible(true);
		setFocusable(true);
		add(game, BorderLayout.CENTER);
		setVisible(true);
		game.start();

	}

	@Override
	public void start() {
		game.start();
	}

	@Override
	public void stop() {
		game.stop();
	}

}
