package game.pathfinding.core.astar;

import game.pathfinding.core.Location;

public interface SortedLocationList {

	public void add(Location location);

	public Location getNext();

	public boolean hasNext();

}
