package lbase.gui;

import java.util.function.Consumer;

public interface LMenu {
	void setMenuButton(boolean value, String name, String key, Consumer<Object> action, String shortcut);

	default void addMenuButton(String buttonName, String buttonKey, Consumer<Object> action) {
		setMenuButton(true, buttonName, buttonKey, action);
	}
	
	default void addMenuButton(String buttonName, String buttonKey, Consumer<Object> action, String acc) {
		setMenuButton(true, buttonName, buttonKey, action, acc);
	}
	
	default void setMenuButton(boolean value, String buttonName, String buttonKey, Consumer<Object> action) {
		setMenuButton(value, buttonName, buttonKey, action, null);
	}

}
