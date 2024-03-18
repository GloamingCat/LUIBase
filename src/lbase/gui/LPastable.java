package lbase.gui;

public interface LPastable {
	boolean canDecode(String str);
	void onPasteButton(LMenu object);
	void onCopyButton(LMenu object);
}
