package lui.base.action;

import lui.base.event.LControlEvent;
import lui.base.gui.LControl;

public class LControlAction<T> implements LAction {
	
	private final LControl<T> control;
	private final LControlEvent<T> event;
	
	public LControlAction(LControl<T> control, LControlEvent<T> event) {
		this.control = control;
		this.event = event;
	}
	
	public void apply() {
		control.setValue(event.newValue);
		control.notifyListeners(event);
	}
	
	private void swap() {
		T temp = event.oldValue;
		event.oldValue = event.newValue;
		event.newValue = temp;
	}
	
	@Override
	public void undo() {
		swap();
		apply();
	}

	@Override
	public void redo() {
		swap();
		apply();
	}

	@Override
	public String toString() {
		return "Control Action: " + "oldValue=" + event.oldValue + "; newValue=" + event.newValue + "; control=" + control;
	}

}
