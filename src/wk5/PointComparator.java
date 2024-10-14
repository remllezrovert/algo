package wk5;


import java.util.Comparator;

public class PointComparator implements Comparator<Point> {
	@Override
	public int compare(Point arg1, Point arg2) {
		return arg1.x <= arg2.x ? -1 : 1;
	}
}