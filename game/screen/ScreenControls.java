package game.screen;

import game.utils.SpriteSheet;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.Set;

public class ScreenControls extends Screen {

	int selectedMenu = 0;

	public ScreenControls(int width, int height, SpriteSheet sheet) {
		super(width, height, sheet);
	}

	@Override
	public void render(Graphics g) {
		this.drawBackgroundScreen();
		Set keymap = game.controls.keyMap.entrySet();
		for (int i = 0; i < keymap.size(); i++) {
			String control = KeyEvent.getKeyText(game.controls.keyMap
					.get(keymap.toArray()[i]));
		}

	}

	@Override
	public void keyReleased(KeyEvent arg0) {

	}

	@Override
	public void tick()
	{
	}

}
