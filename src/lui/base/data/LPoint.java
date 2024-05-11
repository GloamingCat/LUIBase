package lui.base.data;

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

	@Override
	public String toString() {
		return "(" + x + "," + y + ")";
	}

	@Override
	public boolean equals(Object other) {
		if (other instanceof LPoint t) {
            return t.x == x && t.y == y;
		} else return false;
	}

	@Override
	public LPoint clone() {
        try {
            LPoint clone = (LPoint) super.clone();
			clone.x = x;
			clone.y = y;
			return clone;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
	}
	
}