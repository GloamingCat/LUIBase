package lui.base.event.listener;

import lui.base.event.LControlEvent;

public interface LControlListener<T> {

	void onModify(LControlEvent<T> event);
	
}
