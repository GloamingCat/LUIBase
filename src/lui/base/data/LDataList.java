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

	public void setSize(int n) {
		while (size() < n)
			add(null);
		while (size() > n)
			removeLast();
	}
	
	@Override
	public void set(LDataCollection<T> data) {
		LDataTree<T> tree = data.toTree();
		clear();
		for (LDataTree<T> node : tree.children)
			add(node.data);
	}

	@SuppressWarnings("unchecked")
	@Override
	public LDataList<T> clone() {
		return (LDataList<T>) super.clone();
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

	@Override
	public LDataList<T> toList() {
		return this;
	}

	public LDataTree<T> toTree() {
		LDataTree<T> root = new LDataTree<>();
		int i = 0;
		for(T element : this) {
			new LDataTree<>(i++, element, root);
		}
		return root;
	}
	
	public LDataTree<Object> toObjectTree() {
		LDataTree<Object> root = new LDataTree<>();
		for(T element : this) {
			new LDataTree<>(element, root);
		}
		return root;
	}

}
