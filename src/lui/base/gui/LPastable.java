package lui.base.gui;

import lui.base.LVocab;

public interface LPastable {

	boolean canDecode(String str);
	void onPasteButton(LMenu object);
	void onCopyButton(LMenu object);

	default void setCopyEnabled(LMenu menu, boolean value) {
		menu.setMenuButton(value, LVocab.instance.COPY, "copy", (d) -> onCopyButton(menu), "Ctrl+&C");
	}

	default void setPasteEnabled(LMenu menu, boolean value) {
		menu.setMenuButton(value, LVocab.instance.PASTE, "paste", (d) -> onPasteButton(menu), "Ctrl+&V");
	}

}
