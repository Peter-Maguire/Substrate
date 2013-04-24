package game.screen;

import game.Controls;
import game.SpriteSheet;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.Map;
import java.util.Set;

public class ScreenControls extends Screen{

	int selectedMenu = 0;
	public ScreenControls(int width, int height, SpriteSheet sheet) {
		super(width, height, sheet);
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	public void render(Graphics g)
	{
		this.drawBackgroundScreen();
		Set keymap =  game.controls.keyMap.entrySet();
		for(int i = 0; i < keymap.size(); i++)
		{
			String control = KeyEvent.getKeyText(game.controls.keyMap.get(keymap.toArray()[i]));
		}
	
		
		
	}
	
	@Override
	public void keyReleased(KeyEvent arg0) {

		boolean keyUp = arg0.getKeyCode() == game.controls.getKey(Controls.CONTROL_UP);
		boolean keyDown = arg0.getKeyCode() ==game.controls.getKey(Controls.CONTROL_DOWN);
		boolean keyLeft = arg0.getKeyCode() == game.controls.getKey(Controls.CONTROL_LEFT);
		boolean keyRight = arg0.getKeyCode() == game.controls.getKey(Controls.CONTROL_RIGHT);
		boolean keyEnter = arg0.getKeyCode() == game.controls.getKey(Controls.CONTROL_ENTER);

		if (keyUp) {
			
			game.soundman.playSound("select.wav");
		}
		if (keyDown) {
			
			game.soundman.playSound("select.wav");
		}
		
		

		if (keyEnter) {
			switch (selectedMenu) {
			case 1: {
				
				return;
			}

			case 2: {
				
				return;
			}

			case 3: {
				
				return;
			}

			case 5: {
			  
				return;
			}

			case 6: {
				game.setScreen(new ScreenOptions(w, h, sheet));
				return;
			}

			}
		}

	}

}
