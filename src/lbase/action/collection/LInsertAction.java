package lbase.action.collection;

import lbase.action.LAction;
import lbase.data.LDataTree;
import lbase.data.LPath;
import lbase.event.LDeleteEvent;
import lbase.event.LInsertEvent;
import lbase.gui.LCollection;

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
