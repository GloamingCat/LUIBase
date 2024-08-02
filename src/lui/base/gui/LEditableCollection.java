package lui.base.gui;

import java.util.ArrayList;

import lui.base.LMenuInterface;
import lui.base.LVocab;
import lui.base.action.collection.LDeleteAction;
import lui.base.action.collection.LEditAction;
import lui.base.action.collection.LInsertAction;
import lui.base.action.collection.LMoveAction;
import lui.base.data.LDataCollection;
import lui.base.data.LDataTree;
import lui.base.data.LPath;
import lui.base.event.LDeleteEvent;
import lui.base.event.LEditEvent;
import lui.base.event.LInsertEvent;
import lui.base.event.LMoveEvent;
import lui.base.event.listener.LCollectionListener;

public interface LEditableCollection<T, ST> extends lui.base.gui.LCollection<T, ST>, LPastable {
	
	LDataCollection<T> getDataCollection();
	void setDataCollection(LDataCollection<T> collection);

	LMenuInterface getMenuInterface();
	void setMenuInterface(LMenuInterface mi);

	ArrayList<LCollectionListener<T>> getInsertListeners();
	ArrayList<LCollectionListener<T>> getMoveListeners();
	ArrayList<LCollectionListener<T>> getDeleteListeners();
	ArrayList<LCollectionListener<ST>> getEditListeners();
	
	//-------------------------------------------------------------------------------------
	// Actions
	//-------------------------------------------------------------------------------------
	
	default LEditEvent<ST> newEditAction(LPath path) {
		LEditEvent<ST> event = edit(path);
		if (event != null)
			newEditAction(event);
		return event;
	}

	default void newEditAction(LEditEvent<ST> event) {
		if (getMenuInterface() != null) {
			LEditAction<ST> action = new LEditAction<>(this, event.path, event.oldData, event.newData);
			getMenuInterface().actionStack.newAction(action);
		}
		notifyEditListeners(event);
	}

	default LInsertEvent<T> newInsertAction(LPath path, LDataTree<T> node) {
		if (path == null)
			return newInsertAction(null, -1, node);
		LPath parentPath = path.removeLast();
		int index = path.lastChild().index;
		return newInsertAction(parentPath, index, node);
	}
	
	default LInsertEvent<T> newInsertAction(LPath parentPath, int i, LDataTree<T> node) {
		LInsertEvent<T> event = insert(parentPath, i, node);
		if (event != null)
			newInsertAction(event);
		return event;
	}

	default void newInsertAction(LInsertEvent<T> event) {
		if (getMenuInterface() != null) {
			LInsertAction<T> action = new LInsertAction<>(this, event.parentPath, event.index, event.node);
			getMenuInterface().actionStack.newAction(action);
		}
		notifyInsertListeners(event);
	}

	default LDeleteEvent<T> newDeleteAction(LPath parentPath, int i) {
		LDeleteEvent<T> event = delete(parentPath, i);
		if (event != null)
			newDeleteAction(event);
		return event;
	}

	default void newDeleteAction(LDeleteEvent<T> event) {
		if (getMenuInterface() != null) {
			LDeleteAction<T> action = new LDeleteAction<>(this, event.parentPath, event.index, event.node);
			getMenuInterface().actionStack.newAction(action);
		}
		notifyDeleteListeners(event);
	}

	default LMoveEvent<T> newMoveAction(LPath sourceParent, int sourceIndex, LPath destParent, int destIndex) {
		LMoveEvent<T> event = move(sourceParent, sourceIndex, destParent, destIndex);
		if (event != null)
			newMoveAction(event);
		return event;
	}

	default void newMoveAction(LMoveEvent<T> event) {
		if (getMenuInterface() != null) {
			LMoveAction<T> action = new LMoveAction<>(this,
					event.sourceParent, event.sourceIndex,
					event.destParent, event.destIndex);
			getMenuInterface().actionStack.newAction(action);
		}
		notifyMoveListeners(event);
	}

	//-------------------------------------------------------------------------------------
	// Menu buttons
	//-------------------------------------------------------------------------------------
	
	default void setEditEnabled(LMenu menu, boolean value) {
		menu.setMenuButton(value, LVocab.instance.EDIT, "edit", (d) -> onEditButton(menu), "Space");
	}
	
	default void setInsertNewEnabled(LMenu menu, boolean value) {
		menu.setMenuButton(value, LVocab.instance.INSERTNEW, "new", (d) -> onInsertNewButton(menu), "Ctrl+&N");
	}
	
	default void setDuplicateEnabled(LMenu menu, boolean value) {
		menu.setMenuButton(value, LVocab.instance.DUPLICATE, "duplicate", (d) -> onDuplicateButton(menu), "Ctrl+&D");
	}
	
	default void setDeleteEnabled(LMenu menu, boolean value) {
		menu.setMenuButton(value, LVocab.instance.DELETE, "delete", (d) -> onDeleteButton(menu), "Del");
	}
	
	//-------------------------------------------------------------------------------------
	// Menu handlers
	//-------------------------------------------------------------------------------------
	
	void onEditButton(LMenu menu);
	void onInsertNewButton(LMenu menu);
	void onDuplicateButton(LMenu menu);
	void onDeleteButton(LMenu menu);
	
	//-------------------------------------------------------------------------------------
	// Modify
	//-------------------------------------------------------------------------------------
	
	LMoveEvent<T> move(LPath sourceParent, int sourceIndex, LPath destParent, int destIndex);
	LInsertEvent<T> insert(LPath parentPath, int index, LDataTree<T> node);
	LDeleteEvent<T> delete(LPath parentPath, int index);
	LEditEvent<ST> edit(LPath path);
	
	//-------------------------------------------------------------------------------------
	// Listeners
	//-------------------------------------------------------------------------------------

	default void addMoveListener(LCollectionListener<T> listener) {
		getMoveListeners().add(listener);
	}

	default void addEditListener(LCollectionListener<ST> listener) {
		getEditListeners().add(listener);
	}

	default void addInsertListener(LCollectionListener<T> listener) {
		getInsertListeners().add(listener);
	}

	default void addDeleteListener(LCollectionListener<T> listener) {
		getDeleteListeners().add(listener);
	}
	
	//-------------------------------------------------------------------------------------
	// Listener Notify
	//-------------------------------------------------------------------------------------

	default void notifyMoveListeners(LMoveEvent<T> event) {
		for(LCollectionListener<T> listener : getMoveListeners()) {
			listener.onMove(event);
		}
	}
	
	default void notifyEditListeners(LEditEvent<ST> event) {
		for(LCollectionListener<ST> listener : getEditListeners()) {
			listener.onEdit(event);
		}
	}
	
	default void notifyInsertListeners(LInsertEvent<T> event) {
		for(LCollectionListener<T> listener : getInsertListeners()) {
			listener.onInsert(event);
		}
	}
	
	default void notifyDeleteListeners(LDeleteEvent<T> event) {
		for(LCollectionListener<T> listener : getDeleteListeners()) {
			listener.onDelete(event);
		}
	}

}
