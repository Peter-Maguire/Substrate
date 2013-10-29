package game.tile;

public class TileDispenser extends Tile
{

	public TileDispenser(int rotation, boolean active)
	{
		this.sprite = active ? 41 + rotation : 25 + rotation;
		tileName = "Dispenser";
	}
}
