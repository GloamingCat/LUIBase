package gson;

import java.lang.reflect.Type;

import lui.base.data.LDataTree;

public interface GTreeSerializer<T> {

	public Type getDataType();
	public LDataTree<T> getTree();
	
}
