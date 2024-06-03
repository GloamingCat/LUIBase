package lui.base.action.collection;

import lui.base.action.LAction;
import lui.base.data.LDataTree;
import lui.base.data.LPath;
import lui.base.event.LDeleteEvent;
import lui.base.event.LInsertEvent;
import lui.base.gui.LEditableCollection;

public class LDeleteAction<T> implements LAction {

	private final LEditableCollection<T, ?> collection;
	private final LPath parentPath;
	private final int index;
	private final LDataTree<T> node;
	
	public LDeleteAction(LEditableCollection<T, ?> c, LPath parentPath, int index, LDataTree<T> node) {
		collection = c;
		this.parentPath = parentPath;
		this.index = index;
		this.node = node;
	}
	
	@Override
	public void undo() {
		LInsertEvent<T> e = collection.insert(parentPath, index, node);
		collection.notifyInsertListeners(e);
	}

	@Override
	public void redo() {
		LDeleteEvent<T> e = collection.delete(parentPath, index);
		collection.notifyDeleteListeners(e);
	}

	@Override
	public String toString() {
		return "Delete " + new LPath(parentPath, + index) + "; collection=" + collection;
	}

}
