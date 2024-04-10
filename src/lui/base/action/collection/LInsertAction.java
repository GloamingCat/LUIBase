package lui.base.action.collection;

import lui.base.action.LAction;
import lui.base.data.LDataTree;
import lui.base.data.LPath;
import lui.base.event.LDeleteEvent;
import lui.base.event.LInsertEvent;
import lui.base.gui.LCollection;

public class LInsertAction<T> implements LAction {

	private LCollection<T, ?> collection;
	private LPath parent;
	private int index;
	private LDataTree<T> node;
	
	public LInsertAction(LCollection<T, ?> c, LPath parent, int index, LDataTree<T> node) {
		collection = c;
		this.parent = parent;
		this.index = index;
		this.node = node;
	}
	
	@Override
	public void undo() {
		LDeleteEvent<T> e = collection.delete(parent, index);
		collection.notifyDeleteListeners(e);
	}

	@Override
	public void redo() {
		LInsertEvent<T> e = collection.insert(parent, index, node);
		collection.notifyInsertListeners(e);
	}
	
}
