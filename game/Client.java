package game;

import game.tile.Tile;

import java.awt.Rectangle;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;

public class Client {

	
	private static final byte PACKET_GET_MAP = 0, PACKET_GET_ENTITIES = 1;
	private int port;
	private String ip;
	private Socket cs;
	private OutputStream os;
	private InputStream is;

	public Client(String ip, int port, Game game) {
		this.port = port;
		this.ip = ip;
		try {
			System.out.println("Connecting to "+ip+":"+port);
			cs = new Socket(ip, port);
			os = cs.getOutputStream();
			is = cs.getInputStream();
			cs.setKeepAlive(true);

		} catch (Exception e) {
			// game.setScreen(new ScreenConnectionFailed(game.WIDTH,
			// game.HEIGHT, game, e.getMessage()));
			e.printStackTrace();
		}
		System.out.println("Connection successful!");

	

	}
	
	public HashMap<Rectangle, Integer> getMap()
	{
		try {
			os.write(PACKET_GET_MAP);
			ObjectInputStream ois = new ObjectInputStream(is);
			return (HashMap<Rectangle, Integer>) ois.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<ArrayList<Integer>> getEntities()
	{
		try {
			os.write(PACKET_GET_ENTITIES);
			ObjectInputStream ois = new ObjectInputStream(is);
			return (ArrayList<ArrayList<Integer>>) ois.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void sendVelocity(int velx, int vely) {

	}

	public void sendHeartbeat() {
	}

}
