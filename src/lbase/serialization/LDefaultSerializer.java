package lbase.serialization;

import java.io.File;

public abstract class LDefaultSerializer implements LSerializer {

	protected String path;
	
	public LDefaultSerializer(String path) {
		this.path = path;
	}
	
	public void setPath(String path) {
		this.path = path;
	}
	
	@Override
	public boolean isDataFolder(String path) {
		File file = new File(this.path);
		File folder = new File(path);
		for (File entry : folder.listFiles()) {
			if (entry.isFile() && entry.equals(file)) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public boolean save() {
		try {
			byte[] bytes = serialize();
			LFileManager.save(path, bytes);
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean load() {
		try {
			byte[] bytes = LFileManager.load(path);
			if (bytes == null)
				return false;
			deserialize(bytes);
			return true;
		} catch(Exception e) {
			System.err.println("Deserialization error: " + path);
			e.printStackTrace();
			return false;
		}
	}
	
	protected abstract byte[] serialize();
	protected abstract void deserialize(byte[] bytes);
	
}
