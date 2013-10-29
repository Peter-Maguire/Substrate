package game.tile;

public class TileCarpet extends Tile
{


	public TileCarpet(int carpetType) {
		sprite = 48 + (16 * carpetType);
		tileName = "Carpet";
	}

}


