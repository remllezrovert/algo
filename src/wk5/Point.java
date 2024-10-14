package wk5;

public class Point {

	public double x, y;

	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public final double distance(Point arg) {
		double diffX = arg.x - x;
		double diffY = arg.y - y;
		double dist = Math.sqrt(diffX * diffX + diffY * diffY);
		return dist;
	}

	public final String toString() {
		return String.format("(%d,%d)", x, y);
	}
}
