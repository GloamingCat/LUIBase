package lui.base.data;

public class LPath {

	public int index;
	public LPath child;

	public LPath(int index) {
		this.index = index;
	}

	public LPath(LPath parent, int lastIndex) {
		if (parent == null) {
			this.index = lastIndex;
		} else {
			this.index = parent.index;
			this.child = new LPath(parent.child, lastIndex);
		}
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
		s.append(index);
		LPath path = child;
		while(path != null) {
			s.append(" ").append(path.index);
			path = path.child;
		}
		return s.toString();
	}
	
}
