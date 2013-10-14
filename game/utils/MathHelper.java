package game.utils;

public class MathHelper {

	/**
	 * @param i The number to round
	 * @param v ...to the nearest...
	 * @return Rounded number
	 */
	public static int round(double i, int v) {
		return (int) (Math.round(i / v) * v);
	}

}
