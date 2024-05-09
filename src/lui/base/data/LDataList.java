package lui.base.data;

import java.io.Serial;
import java.util.ArrayList;

public class LDataList<T> extends ArrayList<T> implements LDataCollection<T> {

	@Serial
	private static final long serialVersionUID = -6494822823659233992L;

	public LDataList() {
		super();
	}
	
	public LDataList(int i) {
		super(i);
	}

	public LDataList(LDataList<T> copy) {
		super(copy);
	}
	
	@Override
	public void set(LDataCollection<T> data) {
		LDataTree<T> tree = data.toTree();
		clear();
		for (LDataTree<T> node : tree.children)
			add(node.data);
	}
	
	@Override
	public LDataList<T> clone() {
		LDataList<T> list = new LDataList<T>();
		for (T element : this) {
			list.add(element);
		}
		return list;
	}
	
	@Override
	public void insert(LPath parentPath, int index, LDataTree<T> node) {
		if (index == -1)  {
			add(node.data);
		} else {
			add(index, node.data);
		}
	}

	@Override
	public void delete(LPath parentPath, int index) {
		if (index == -1) {
			index = size() - 1;
		}
		remove(index);
	}

	@Override
	public void move(LPath sourceParent, int sourceIndex, LPath destParent,
			int destIndex) {
		T data;
		if (sourceIndex == -1) {
			data = remove(size() - 1);
		} else {
			data = remove(sourceIndex);
		}
		if (destIndex == -1) {
			add(data);
		} else {
			add(destIndex, data);
		}
	}

	public LDataTree<T> toTree() {
		LDataTree<T> root = new LDataTree<T>();
		int i = 0;
		for(T element : this) {
			new LDataTree<T>(i++, element, root);
		}
		return root;
	}
	
	public LDataTree<Object> toObjectTree() {
		LDataTree<Object> root = new LDataTree<Object>();
		for(T element : this) {
			new LDataTree<>(element, root);
		}
		return root;
	}

}
