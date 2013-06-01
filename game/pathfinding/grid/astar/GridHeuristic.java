package game.pathfinding.grid.astar;

import game.pathfinding.grid.GridLocation;

public interface GridHeuristic {
	
	public double getDistance(int x, int y, GridLocation location);

}
