package lui.base.data;

public class LPath {

	public int index;
	public LPath child;

	public LPath(int index) {
		this.index = index;
	}
	
	public LPath lastChild() {
		LPath p = this;
		while(p.child != null) {
			p = p.child;
		}
		return p;
	}
	
	public LPath addLast(int index) {
		LPath newPath = new LPath(this.index);
		LPath newLast = newPath;
		LPath last = this;
		while (last.child != null) {
			last = last.child;
			newLast.child = new LPath(last.index);
			newLast = newLast.child;
		}
		newLast.child = new LPath(index);
		return newPath;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj instanceof LPath path) {
            if (path.index != index)
				return false;
			if (child == null)
                return path.child == null;
			return child.equals(path.child);
		} else {
			return false;
		}
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		LPath path = this;
		while(path != null) {
			s.append(path.index).append(" ");
			path = path.child;
		}
		return s.toString();
	}
	
}
