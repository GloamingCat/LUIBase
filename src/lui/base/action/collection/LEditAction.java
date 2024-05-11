package lui.base.action.collection;

import lui.base.action.LAction;
import lui.base.data.LPath;
import lui.base.event.LEditEvent;
import lui.base.gui.LCollection;

public class LEditAction<T> implements LAction {

	private final LCollection<?, T> collection;
	private final LPath path;
	private final T oldData;
	private final T newData;
	
	public LEditAction(LCollection<?, T> collection, LPath path, T oldData, T newData) {
		this.collection = collection;
		this.path = path;
		this.oldData = oldData;
		this.newData = newData;
	}
	
	@Override
	public void undo() {
		LEditEvent<T> e = new LEditEvent<T>(path, newData, oldData);
		collection.notifyEditListeners(e);
		collection.refreshObject(path);
	}

	@Override
	public void redo() {
		LEditEvent<T> e = new LEditEvent<T>(path, oldData, newData);
		collection.notifyEditListeners(e);
		collection.refreshObject(path);
	}

}
