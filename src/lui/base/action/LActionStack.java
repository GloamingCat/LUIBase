package lui.base.action;

import java.util.ArrayList;

import lui.base.gui.LApplicationWindow;
import lui.base.gui.LView;

public class LActionStack {

	private static class Node {
		public LAction action;
		public LState state;
		public Node(LAction action, LState state) {
			this.action = action;
			this.state = state;
		}
	}
	
	private int savedAction = 0;
	private int lastAction = 0;
	private ArrayList<Node> actions = new ArrayList<>();
	private LView rootEditor;
	private LApplicationWindow shell;
	
	public LActionStack(LView rootEditor) {
		this.rootEditor = rootEditor;
		LActionManager.getInstance().addStack(this);
		if (rootEditor.getWindow() instanceof LApplicationWindow)
			shell = (LApplicationWindow) rootEditor.getWindow();
	}
	
	public LView getRootView() {
		return rootEditor;
	}
	
	//////////////////////////////////////////////////
	//region Actions
	
	public void newAction(LAction action) {
		while (lastAction < actions.size()) {
			actions.remove(lastAction);
			savedAction = -1;
		}
		actions.add(new Node(action, rootEditor.getState()));
		lastAction++;
		if (shell != null)
			shell.refreshEditButtons();
	}
	
	public void undo() {
		if (lastAction > 0) {
			lastAction--;
			actions.get(lastAction).state.reset();
			actions.get(lastAction).action.undo();
			if (shell != null)
				shell.refreshEditButtons();
		}
	}
	
	public void redo() {
		if (lastAction < actions.size()) {
			actions.get(lastAction).state.reset();
			actions.get(lastAction).action.redo();
			lastAction++;
			if (rootEditor.getWindow() instanceof LApplicationWindow) {
				LApplicationWindow shell = (LApplicationWindow) rootEditor.getWindow();
				shell.refreshEditButtons();
			}
		}
	}
	
	public boolean canUndo() {
		return lastAction > 0;
	}
	
	public boolean canRedo() {
		return lastAction < actions.size();
	}
	
	public void onSave() {
		savedAction = lastAction;
	}
	
	public boolean hasChanges() {
		return savedAction != lastAction;
	}
	
	public void clear() {
		savedAction = 0;
		lastAction = 0;
		actions.clear();
	}
	
	//endregion
	
}
