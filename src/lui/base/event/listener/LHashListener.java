package lui.base.event.listener;

import lui.base.event.LHashEditEvent;
import lui.base.event.LHashKeyEvent;

public interface LHashListener<T> {

	void onInsert(LHashKeyEvent<T> event);
	void onDelete(LHashKeyEvent<T> event);
	void onEdit(LHashEditEvent<T> event);
	
}
