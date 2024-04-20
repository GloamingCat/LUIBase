package lui.base.action.collection;

import lui.base.action.LAction;
import lui.base.data.LPath;
import lui.base.event.LMoveEvent;
import lui.base.gui.LCollection;

public class LMoveAction<T> implements LAction {

	private final LCollection<T, ?> collection;
	private final LPath sourceParent;
	private final LPath destParent;
	private final int sourceIndex;
	private final int destIndex;
	
	public LMoveAction(LCollection<T, ?> collection, LPath sourceParent, int sourceIndex, LPath destParent, int destIndex) {
		this.collection = collection;
		this.sourceParent = sourceParent;
		this.sourceIndex = sourceIndex;
		this.destParent = destParent;
		this.destIndex = destIndex;
	}

	@Override
	public void undo() {
		LMoveEvent<T> e = collection.move(destParent, destIndex, sourceParent, sourceIndex);
		collection.notifyMoveListeners(e);
	}

	@Override
	public void redo() {
		LMoveEvent<T> e = collection.move(sourceParent, sourceIndex, destParent, destIndex);
		collection.notifyMoveListeners(e);
	}

}
