package lbase.action.collection;

import lbase.action.LAction;
import lbase.data.LPath;
import lbase.event.LEditEvent;
import lbase.gui.LCollection;

public class LEditAction<T> implements LAction {

	private LCollection<?, T> collection;
	private LPath path;
	private T oldData;
	private T newData;
	
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
