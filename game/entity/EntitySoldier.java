package game.entity;

import game.pathfinding.grid.GridLocation;
import game.pathfinding.grid.GridMap;
import game.pathfinding.grid.GridPath;
import game.pathfinding.grid.GridPathfinding;
import game.screen.ScreenGame;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class EntitySoldier extends Entity {

	private static final long serialVersionUID = 6404228773270715706L;

	private static final int IDLE_LEFT = 16, IDLE_RIGHT = 17,
			IDLE_LEFT_HATLESS = 18, IDLE_RIGHT_HATLESS = 19, AIM_LEFT = 32,
			AIM_RIGHT = 33, AIM_LEFT_HATLESS = 34, AIM_RIGHT_HATLESS = 35;

	private Random rand = new Random();

	private GridPath path;
	private GridPathfinding pathFinder = new GridPathfinding();
	private GridMap pfGrid;

	private int lastPlayerX = 0, lastPlayerY = 0;
	public int speed = 1;
	private ScreenGame screen;


	public EntitySoldier(ScreenGame game, int x, int y, GridMap map) {
		this.screen = game;
		this.pfGrid = map;
		this.game = game.game;
		sprite = 16;
		this.x = x;
		this.y = y;
		lastPlayerX = screen.player.x;
		lastPlayerY = screen.player.y;
		/*path = pathFinder.getPath(new GridLocation(this.x, this.y, false),
				new GridLocation(screen.player.x, screen.player.y, true),
				pfGrid);
		System.out.println(path);*/
	}

	@Override
	public void tick() {
		super.tick();
		/*if (lastPlayerX != screen.player.x && lastPlayerY != screen.player.y) {
			path = pathFinder.getPath(new GridLocation(this.x, this.y, false),
					new GridLocation(screen.player.x, screen.player.y, true),
					pfGrid);
			lastPlayerX = screen.player.x;
			lastPlayerY = screen.player.y;
		}*/

	 pathTo(screen.player.x, screen.player.y);
	}

	private void idle() {
		int walkDirection = rand.nextInt(50);

		if (walkDirection == 0)
			return;

		switch (walkDirection) {
		case 1:
			tryMoveEntity(speed, 0);
			break;
		case 2:
			tryMoveEntity(-speed, 0);
			break;
		case 3:
			tryMoveEntity(0, speed);
			break;
		case 4:
			tryMoveEntity(0, -speed);
			break;

		}
	}

	private void pathTo(int tx, int ty) {
		//If current X is more than target X
		if(this.x > tx)
		{
			tryMoveEntity(-speed,0);
		}
		//If current X is less than target X
		if(this.x < tx)
		{
			tryMoveEntity(speed,0);
		}
		//If current Y is more than target Y
		if(this.y > ty)
		{
			tryMoveEntity(0,-speed);
		}
		if(this.y < ty)
		{
			tryMoveEntity(0,speed);
		}
	}

	@Override
	public void render(Graphics g) {
		super.render(g);
		g.setColor(Color.blue);
		if (path == null) {
			g.drawString("No path", x, y);
		} else {
			int lastX = x, lastY = y;
			ArrayList<GridLocation> paths = path.getList();
			for (GridLocation l : paths) {
				lastX = l.getX();
				lastY = l.getY();
				g.drawLine(lastX, lastY, l.getX(), l.getY());

			}
		}

	}

}
