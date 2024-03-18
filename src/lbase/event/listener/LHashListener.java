package lbase.event.listener;

import lbase.event.LHashEditEvent;
import lbase.event.LHashKeyEvent;

public interface LHashListener<T> {

	void onInsert(LHashKeyEvent<T> event);
	void onDelete(LHashKeyEvent<T> event);
	void onEdit(LHashEditEvent<T> event);
	
}
