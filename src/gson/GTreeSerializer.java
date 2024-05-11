package gson;

import java.lang.reflect.Type;

import lui.base.data.LDataTree;

public interface GTreeSerializer<T> {

	Type getDataType();
	LDataTree<T> getTree();
	
}
