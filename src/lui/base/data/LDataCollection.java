package lui.base.data;

public interface LDataCollection<T> extends Cloneable {

	void insert(LPath parentPath, int index, LDataTree<T> node);
	void delete(LPath parentPath, int index);
	void move(LPath sourceParent, int sourceIndex, LPath destParent, int destIndex);
	void set(LDataCollection<T> data);
	LDataCollection<T> clone();
	LDataTree<T> toTree();
	LDataList<T> toList();

}
