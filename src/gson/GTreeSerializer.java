package gson;

import java.lang.reflect.Type;

import lbase.data.LDataTree;

public interface GTreeSerializer<T> {

	public Type getDataType();
	public LDataTree<T> getTree();
	
}
