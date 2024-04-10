package lui.base;

import lui.base.action.LActionStack;
import lui.base.gui.LApplicationWindow;
import lui.base.gui.LView;
import lui.base.gui.LPastable;

public class LMenuInterface {

	private LPastable focusEditor;
	private LPastable focusWidget;
	private LApplicationWindow shell;
	public final LActionStack actionStack;
	
	public LMenuInterface(LView root) {
		actionStack = new LActionStack(root);
		if (root.getWindow() instanceof LApplicationWindow)
			shell = (LApplicationWindow) root.getWindow();
	}
	
	public void copy() {
		if (focusWidget != null)
			focusWidget.onCopyButton(shell == null ? null : shell.getEditMenu());
		else if (focusEditor != null)
			focusEditor.onCopyButton(shell == null ? null : shell.getEditMenu());
	}
	
	public void paste() {
		if (focusWidget != null)
			focusWidget.onPasteButton(shell == null ? null : shell.getEditMenu());
		else if (focusEditor != null)
			focusEditor.onPasteButton(shell == null ? null : shell.getEditMenu());
	}
	
	public boolean canCopy() {
		return focusWidget != null || focusEditor != null;
	}
	
	public boolean canPaste(Object obj) {
		if (obj == null)
			return false;
		if (focusWidget != null) {
			if (!focusWidget.canDecode((String) obj))
				return false;
		}
		if (focusEditor == null)
			return false;
		return focusEditor.canDecode((String) obj);
	}
	
	public void setFocusEditor(LPastable editor) {
		focusEditor = editor;
		focusWidget = null;
		if (shell != null) {
			shell.refreshClipboardButtons();
		}
	}
	
	public void setFocusWidget(LPastable widget) {
		focusWidget = widget;
		if (shell != null) {
			shell.refreshClipboardButtons();
		}
	}
	
}
