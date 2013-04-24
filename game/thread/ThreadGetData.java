package game.thread;

import game.Client;
import game.screen.ScreenGame;
import game.screen.ScreenMultiplayer;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashMap;

public class ThreadGetData implements Runnable {

	private ScreenMultiplayer game;

	private Client client;

	public ThreadGetData(ScreenGame game, Client client) {
		this.game = (ScreenMultiplayer) game;
		this.client = client;

	}

	public void start() {
	
		game.loading = true;
		System.out.println("Getting tiles from server...");
		game.sentTiles = grabMap();
		System.out.println("Done!");

		System.out.println("Getting entities from server...");
		game.sentEntities = grabEntities();
		System.out.println("Done!");
		//game.loading = false;


	}

	public ArrayList<ArrayList<Integer>> grabEntities() {
		return client.getEntities();
	}

	public HashMap<Rectangle, Integer> grabMap() {
		return client.getMap();
	}

	@Override
	public void run() {
		start();
		
	}

}
