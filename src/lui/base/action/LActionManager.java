package lui.base.action;

import java.util.ArrayList;

public class LActionManager {

	private static final LActionManager instance = new LActionManager();
	
	private final ArrayList<LActionStack> stacks = new ArrayList<>();
	
	public static LActionManager getInstance() {
		return instance;
	}
	
	public void addStack(LActionStack stack) {
		stacks.add(stack);
	}
	
	public boolean hasChanges() {
		for(LActionStack stack : stacks) {
			if (stack.hasChanges())
				return true;
		}
		return false;
	}
	
	public void onSave() {
		for(LActionStack stack : stacks) {
			stack.onSave();
		}
	}
	
}
