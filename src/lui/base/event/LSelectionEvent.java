package lui.base.event;

import lui.base.data.LPath;

public class LSelectionEvent {

	public LPath path;
	public Object data;
	public int id;
	public boolean checked;
	
	public LSelectionEvent(LPath path, Object data, int id, boolean checked) {
		this.path = path;
		this.data = data;
		this.id = id;
		this.checked = checked;
	}
	
}
