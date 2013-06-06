package game.screen;

import game.Controls;
import game.Game;
import game.utils.SpriteSheet;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class ScreenIntro extends Screen{

	public Graphics g;
	private int page = 1, ttl = 4;
	public ScreenIntro(int width, int height, SpriteSheet sheet) {
		super(width, height, sheet);
	}
	
	@Override
	public void render(Graphics g)
	{
		if(game.settings.getSetting("HasDoneIntro") == "ON")
		{
			game.setScreen(new ScreenLoadMap(w, h, sheet));
		}else
		{
	
		this.g = g;
		if(page == 1){
		game.getFontRenderer().drawString("Welcome to "+Game.TITLE+"!", 10, 30, 2);
		game.getFontRenderer().drawString("The game is currently in very early stages.", 10, 50, 2);
		game.getFontRenderer().drawString("If the next screen hangs, you don't have any maps", 10, 70, 2);
		game.getFontRenderer().drawString("Space to fire, Arrow keys to move around.", 10, 90, 2);
		}
		
		if(page == 2){
		g.drawImage(sheet.getImage(9), 420, 25,32,32, game);
		game.getFontRenderer().drawString("There are small packages ( ) on the map.", 10, 30, 2);
		game.getFontRenderer().drawString("These are ammo boxes, they supply 5-10 ammo each.", 10, 50, 2);
		game.getFontRenderer().drawString("These packs supply you with tier 1 ammo.", 10, 70, 2);
		game.getFontRenderer().drawString("Tier 2 and 3 ammo is not yet implemented.", 10, 90, 2);
		}
		if(page == 3){
		g.drawImage(sheet.getImage(72), 696, 83,32,32, game);
		game.getFontRenderer().drawString("The red bar along the bottom is your health.", 10, 30, 2);
		game.getFontRenderer().drawString("The orange bar is your ammo. Be sure to make", 10, 50, 2);
		game.getFontRenderer().drawString("every shot count as you cool down after shots.", 10, 70, 2);
		game.getFontRenderer().drawString("You can see the time left on the ice cube ( )", 10, 90, 2);
		}
		if(page == 4){
		game.getFontRenderer().drawString("There are a number of bugs in this game!", 10, 30, 2);
		game.getFontRenderer().drawString("Collision detection is not yet working on the +x", 10, 50, 2);
		game.getFontRenderer().drawString("and -y directions! If you find any others, let me ", 10, 70, 2);
		game.getFontRenderer().drawString("know! NOTE: This game should run at 60 FPS.", 10, 90, 2);
		}
		
		
		
		game.getFontRenderer().drawString(page == ttl ? "Press Space to start!" : "Press Space to continue...", 10, 130, 3);
		game.getFontRenderer().drawString("Page "+page+" of "+ttl, 10, 160, 2);
		
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e)
	{
		if (e.getKeyCode() == game.controls.getKey(Controls.CONTROL_FIRE)) {
			if(page == ttl){
				game.settings.setSetting("HasDoneIntro", "ON");
				game.setScreen(new ScreenLoadMap(w, h, sheet));
				
			}else
			{
				page++;
				
			}
			
	
		}
	}

}
