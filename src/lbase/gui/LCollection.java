package lbase.gui;

import lbase.data.LDataCollection;
import lbase.data.LDataTree;
import lbase.data.LPath;
import lbase.event.LDeleteEvent;
import lbase.event.LEditEvent;
import lbase.event.LInsertEvent;
import lbase.event.LMoveEvent;
import lbase.event.listener.LCollectionListener;

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
