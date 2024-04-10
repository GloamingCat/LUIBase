package lui.base.event;

import lui.base.data.LDataTree;
import lui.base.data.LPath;

public class LMoveEvent<T> {

	public LPath sourceParent;
	public LPath destParent;
	public int sourceIndex;
	public int destIndex;
	public LDataTree<T> sourceNode;
	
	public LMoveEvent(LPath sourceParent, int sourceIndex, LPath destParent, int destIndex, LDataTree<T> sourceNode) {
		this.sourceParent = sourceParent;
		this.destParent = destParent;
		this.sourceIndex = sourceIndex;
		this.destIndex = destIndex;
		this.sourceNode = sourceNode;
	}
	
}
