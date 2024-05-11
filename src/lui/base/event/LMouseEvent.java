package lui.base.event;

public class LMouseEvent {
	
	public int button;
	public int x;
	public int y;
	public int type;

	public LMouseEvent(int button, int x, int y, int type) {
		this.button = button;
		this.x = x;
		this.y = y;
		this.type = type;
	}

	@Override
	public String toString() {
		return "Mouse event: Button=" + button + "; type=" + type +  "; x=" + x + "; y=" + y;
	}
	
}
