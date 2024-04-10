package lui.base.event.listener;

import lui.base.event.LDeleteEvent;
import lui.base.event.LEditEvent;
import lui.base.event.LInsertEvent;
import lui.base.event.LMoveEvent;

public class LCollectionListener<T> {

	public void onInsert(LInsertEvent<T> event) {}
	public void onDelete(LDeleteEvent<T> event) {}
	public void onMove(LMoveEvent<T> event) {}
	public void onEdit(LEditEvent<T> event) {}
	
}
