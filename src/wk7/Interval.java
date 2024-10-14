package wk7;

public class Interval {

	public char name;
	public int start, end;

	public Interval(char n, int s, int e) {
		name = n;
		start = s;
		end = e;
	}

	public String toString() {
		return String.format("(%c, %d, %d)", name, start, end);
	}
}
