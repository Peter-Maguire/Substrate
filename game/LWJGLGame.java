package game;



import java.io.IOException;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;



public class LWJGLGame {

	

	private static int WIDTH = 800, HEIGHT = 600;
	
	
	
	public static void main(String[] args)
	{
		//gameLoop();
	}
	
	
	
	private void init() throws LWJGLException
	{
		System.out.println("Creating display...");
		Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
		Display.create();
		
		System.out.println("Loading Resources...");
		
		try {
			loadResources();
		} catch (IOException e) {
			System.err.println("Unable to load resources");
			e.printStackTrace();
		}
	}
	
	private void loadResources() throws IOException
	{
		
	}
}
