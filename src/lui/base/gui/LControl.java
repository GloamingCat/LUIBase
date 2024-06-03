package lui.base.gui;

import lui.base.event.LControlEvent;

public interface LControl<T> extends LPastable {

	void forceModification(T newValue);
	void setValue(Object value);
	void notifyListeners(LControlEvent<T> event);

}
