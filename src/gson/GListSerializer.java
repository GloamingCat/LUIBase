package gson;

import java.lang.reflect.Type;

import lui.base.data.LDataList;

public interface GListSerializer<T> {

	Type getDataType();
	LDataList<T> getList();
	
}
