package lbase.serialization;

public interface LSerializer {

	void initialize();
	boolean save();
	boolean load();
	boolean isDataFolder(String path);
	
}
