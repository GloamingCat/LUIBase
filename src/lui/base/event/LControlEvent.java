package lui.base.event;

public class LControlEvent<T> {
	
	public T oldValue;
	public T newValue;
	public int detail = 0;
	
	public LControlEvent(T oldValue, T newValue) {
		this.oldValue = oldValue;
		this.newValue = newValue;
	}

	@Override
	public String toString() {
		return "oldValue=" + oldValue + "; newValue=" + newValue;
	}
	
}