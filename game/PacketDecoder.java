package game;

import game.entity.Entity;
import game.entity.EntityAmmo;
import game.entity.EntityBullet;
import game.screen.ScreenGame;
import game.tile.Tile;
import game.tile.TileGrass;
import game.tile.TilePaving;
import game.tile.TileRubble;
import game.tile.TileWall;
import game.tile.TileWater;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashMap;

public class PacketDecoder implements Runnable {

	public static final int ENTITY_AMMO = 1, ENTITY_BULLET = 2,
			ENTITY_EXPLOSION = 3, ENTITY_TANK = 4, TILE_GRASS = 1, TILE_PAVING = 2, TILE_RUBBLE = 3, TILE_WALL = 4, TILE_WATER = 5;

	@Override
	public void run() {
		new PacketDecoder();

	}

	public PacketDecoder() {

	}

	public ArrayList<Entity> decodeEntityPacket(ArrayList<ArrayList<Integer>> input, ScreenGame game)
	{
		System.out.println("Decoding entity packet...");
		ArrayList<Entity> out = new ArrayList<Entity>();
	
		for(ArrayList<Integer> arr : input)
		{
			int ID = arr.get(0), x = arr.get(1), y = arr.get(2), extraParam = arr.get(3);
			switch(ID)
			{
			case ENTITY_AMMO:
				out.add(new EntityAmmo(game, x, y));
				break;
			case ENTITY_BULLET:
				out.add(new EntityBullet(game, 0, x, y, extraParam));
				break;
			case ENTITY_EXPLOSION:
				out.add(new EntityAmmo(game, x, y));
				break;
			case ENTITY_TANK:
				out.add(new EntityAmmo(game, x, y));
				break;
			}
		
		}
		
		return out;
	}
	
	public HashMap<Rectangle, Tile> decodeMapPacket(HashMap<Rectangle, Integer> sentTiles, ScreenGame game)
	{
		System.out.println("Decoding map packet...");
		HashMap<Rectangle, Tile> tiles = new HashMap<Rectangle, Tile>();
		for (int i = 0; i < sentTiles.keySet().size(); i++) {
			Rectangle rec = (Rectangle) sentTiles.keySet().toArray()[i];
			int tileID = sentTiles.get(rec);
			switch(tileID)
			{
			case TILE_GRASS:
				tiles.put(rec, new TileGrass(game));
				break;
			case TILE_PAVING:
				tiles.put(rec, new TilePaving(game));
				break;
			case TILE_RUBBLE:
				tiles.put(rec, new TileRubble(game));
				break;
			case TILE_WALL:
				tiles.put(rec, new TileWall(game, TileWall.WALL_VERTICAL));
				break;
			case TILE_WATER:
				tiles.put(rec, new TileWater(game));
				break;
			}		
		}
		return tiles;
	}
}
