package game.tile;

public class TileCarpet extends Tile
{


	public String tileName = "Carpet";
	public TileCarpet(int carpetType) {
		sprite = 48 + (16 * carpetType);
	}

}


