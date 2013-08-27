package game.tile;

public class TileGrass extends Tile {

	/**
	 * 
	 */
	private static final long serialVersionUID = 679389482939848986L;
	public String tileName = "Grass";
	
	public TileGrass() {
		this.sprite = 7;
	}

	boolean canPassThrough = true;

	@Override
	public void tick() {

	}

}
