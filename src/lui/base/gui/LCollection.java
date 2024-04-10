package lui.base.gui;

import lui.base.data.LDataCollection;
import lui.base.data.LDataTree;
import lui.base.data.LPath;
import lui.base.event.LDeleteEvent;
import lui.base.event.LEditEvent;
import lui.base.event.LInsertEvent;
import lui.base.event.LMoveEvent;
import lui.base.event.listener.LCollectionListener;

public interface LCollection<T, ST> extends LPastable {
	
	public abstract LDataCollection<T> getDataCollection();
	public abstract void setDataCollection(LDataCollection<T> collection);
	
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

	void addMoveListener(LCollectionListener<T> listener);
	void addEditListener(LCollectionListener<ST> listener);
	void addInsertListener(LCollectionListener<T> listener);
	void addDeleteListener(LCollectionListener<T> listener);
	
	//-------------------------------------------------------------------------------------
	// Listener Notify
	//-------------------------------------------------------------------------------------

	void notifyMoveListeners(LMoveEvent<T> event);
	void notifyEditListeners(LEditEvent<ST> event);
	void notifyInsertListeners(LInsertEvent<T> event);
	void notifyDeleteListeners(LDeleteEvent<T> event);
	
	//-------------------------------------------------------------------------------------
	// Object
	//-------------------------------------------------------------------------------------
	
	T toObject(LPath path);
	void refreshObject(LPath path);
	void refreshAll();
	LPath getSelectedPath();
	
}
