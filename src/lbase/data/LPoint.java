package lbase.data;

public class LPoint {
	public int x = 0;
	public int y = 0;
	
	public LPoint() {}
	
	public LPoint(LPoint i) {
		x = i.x;
		y = i.y;
	}
	public LPoint(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int first() {
		return x;
	}
	
	public int second() {
		return y;
	}
	public String toString() {
		return "(" + x + "," + y + ")";
	}
	
	public boolean equals(Object other) {
		if (other instanceof LPoint) {
			LPoint t = (LPoint) other;
			return t.x == x && t.y == y;// && t.z == z;
		} else return false;
	}
	
	public LPoint clone() {
		return new LPoint(x, y);
	}
	
}