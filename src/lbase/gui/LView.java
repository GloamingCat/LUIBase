package lbase.gui;

import lbase.action.LState;

public interface LView {
	LWindow getWindow();
	LState getState();
}
