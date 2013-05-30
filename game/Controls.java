package game;

import java.awt.event.KeyEvent;
import java.io.Serializable;
import java.util.HashMap;

public class Controls{

	
	public HashMap<Integer, Integer> keyMap = new HashMap<Integer, Integer>();
	public static int CONTROL_UP = 0;
	public static int CONTROL_DOWN = 1;
	public static int CONTROL_LEFT = 2;
	public static int CONTROL_RIGHT = 3;
	public static int CONTROL_ENTER = 4;
	public static int CONTROL_FIRE = 5;
	public static int CONTROL_ESCAPE = 6;
	
	
	
	
	
	public Controls()
	{
		bind(KeyEvent.VK_UP, CONTROL_UP);
		bind(KeyEvent.VK_DOWN, CONTROL_DOWN);
		bind(KeyEvent.VK_LEFT, CONTROL_LEFT);
		bind(KeyEvent.VK_RIGHT, CONTROL_RIGHT);
		
		bind(KeyEvent.VK_ENTER, CONTROL_ENTER);
		bind(KeyEvent.VK_SPACE, CONTROL_FIRE);
		bind(KeyEvent.VK_ESCAPE, CONTROL_ESCAPE);
		

		
	}
	
	
	
	
	public HashMap<Integer, Integer> getKeyMap() {
		return keyMap;
	}
	
	public int getKey(int key)
	{
		return keyMap.get(key);
	}




	public void setKeyMap(HashMap<Integer, Integer> keyMap) {
		this.keyMap = keyMap;
	}




	public void bind(int key, int action)
	{
		keyMap.put(action, key);
	}
}
