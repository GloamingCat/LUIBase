package lui.base.gui;

import lui.base.data.LDataCollection;
import lui.base.data.LDataTree;
import lui.base.data.LPath;
import lui.base.event.LDeleteEvent;
import lui.base.event.LEditEvent;
import lui.base.event.LInsertEvent;
import lui.base.event.LMoveEvent;
import lui.base.event.listener.LCollectionListener;

public interface LCollection<T, ST> {
	
	LDataCollection<T> getDataCollection();
	void setDataCollection(LDataCollection<T> collection);
	T toObject(LPath path);
	void refreshObject(LPath path);
	void refreshAll();
	LPath getSelectedPath();
	
}
