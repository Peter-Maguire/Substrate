package game.tile;

import game.Game;
import game.screen.ScreenGame;

public class TileGrass extends Tile{


	
	/**
	 * 
	 */
	private static final long serialVersionUID = 679389482939848986L;

	public TileGrass(ScreenGame screenGame) {
		super(screenGame);
		sprite = 7;
	}

	boolean canPassThrough = true;
	
	@Override
	public void tick()
	{
		
		
	}
	
	}
