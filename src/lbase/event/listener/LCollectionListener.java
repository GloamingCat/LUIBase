package lbase.event.listener;

import lbase.event.LDeleteEvent;
import lbase.event.LEditEvent;
import lbase.event.LInsertEvent;
import lbase.event.LMoveEvent;

public class LCollectionListener<T> {

	public void onInsert(LInsertEvent<T> event) {}
	public void onDelete(LDeleteEvent<T> event) {}
	public void onMove(LMoveEvent<T> event) {}
	public void onEdit(LEditEvent<T> event) {}
	
}
