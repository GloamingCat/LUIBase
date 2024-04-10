package gson;

import java.lang.reflect.Type;

import lui.base.data.LDataList;

public interface GListSerializer<T> {

	public Type getDataType();
	public LDataList<T> getList();
	
}
