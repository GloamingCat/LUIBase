package lbase.event;

import lbase.data.LPath;

public class LEditEvent<T> {

	public LPath path;
	public T oldData;
	public T newData;
	
	public LEditEvent(LPath path, T oldData, T newData) {
		this.path = path;
		this.newData = newData;
		this.oldData = oldData;
	}
	
}
