package game.pathfinding.grid;

import game.pathfinding.core.Location;
import game.pathfinding.core.Map;
import game.pathfinding.core.Pathfinding;
import game.pathfinding.grid.astar.GridAstar;
import game.pathfinding.grid.astar.GridHeuristic;
import game.pathfinding.grid.astar.GridHeuristicManathan;

public class GridPathfinding implements Pathfinding {

	GridAstar astar;
	GridHeuristic heuristic;

	public GridPathfinding() {
		heuristic = new GridHeuristicManathan();
	}

	@Override
	public GridPath getPath(Location s, Location e, Map m) {
		GridLocation start = (GridLocation) s;
		GridLocation end = (GridLocation) e;
		GridMap map = (GridMap) m;

		astar = new GridAstar(start, end, map, heuristic);

		return astar.getPath();
	}

}
