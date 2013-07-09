package game.opengl;

import org.lwjgl.opengl.GL11;

public class GLQuad {

	
	int x, y, sx, sy;
	
	public GLQuad(int x, int y, int sx, int sy)
	{
		this.x = x;
		this.y = y;
		this.sx = sx;
		this.sy = sy;
	}
	
	
	public void render()
	{
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT); 

		// set the color of the quad (R,G,B,A)
		GL11.glColor3f(0.5f,0.5f,1.0f);
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glVertex2f(x,y);
		GL11.glVertex2f(x+sx, y);
		GL11.glVertex2f(x+sx,y+sy);
		GL11.glVertex2f(x,y+sy);
		GL11.glEnd();
	}
}
