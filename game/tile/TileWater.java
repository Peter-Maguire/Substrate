package game.tile;

public class TileWater extends Tile {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1625043749319476216L;

	public String tileName = "Water";
	public TileWater() {
		sprite = 22;
		canPassThrough = false;
	}
	
	

}
