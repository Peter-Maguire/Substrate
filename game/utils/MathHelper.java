package game.utils;

public class MathHelper {

	public static int round(double i, int v) {
		return (int) (Math.round(i / v) * v);
	}

}
