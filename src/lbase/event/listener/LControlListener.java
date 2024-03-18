package lbase.event.listener;

import lbase.event.LControlEvent;

public interface LControlListener<T> {

	void onModify(LControlEvent<T> event);
	
}
