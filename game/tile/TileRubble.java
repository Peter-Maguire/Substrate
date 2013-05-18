package game.tile;

public class TileRubble extends Tile{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5853358076920516483L;
	public static final int RUBBLE_VERTICAL_TOP = 3;
	public static final int RUBBLE_VERTICAL_MIDDLE = 19;
	public static final int RUBBLE_VERTICAL_BOTTOM = 35;
	public static final int RUBBLE_TOP_CORNER_RIGHT = 20;
	public static final int RUBBLE_TOP_CORNER_LEFT = 21;
	public static final int RUBBLE_BOTTOM_CORNER_RIGHT = 36;
	public static final int RUBBLE_BOTTOM_CORNER_LEFT = 37;

	public TileRubble(int sprite) {
		this.sprite = sprite;
		canPassThrough = true;
	}

}
