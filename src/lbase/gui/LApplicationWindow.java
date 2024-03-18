package lbase.gui;

public interface LApplicationWindow extends LWindow {
	LWindow getWindow();
	void refreshEditButtons();
	void refreshClipboardButtons();
	LMenu getEditMenu();
}
