package lui.base.gui;

import lui.base.event.LControlEvent;

public interface LControl<T> {

	void modify(T newValue);
	void setValue(Object value);
	
	void notifyListeners(LControlEvent<T> event);

}

