package lui.base.gui;

import lui.base.LMenuInterface;
import lui.base.event.LControlEvent;
import lui.base.event.listener.LControlListener;

public interface LControl<T> extends LPastable {

	void setMenuInterface(LMenuInterface mi);
	LMenuInterface getMenuInterface();
	void forceModification(T newValue);
	void setValue(Object value);
	T getValue();
	void notifyListeners(LControlEvent<T> event);
	void addModifyListener(LControlListener<T> listener);
	void setEnabled(boolean value);

}
