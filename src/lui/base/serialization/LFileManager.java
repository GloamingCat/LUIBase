package lui.base.serialization;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LFileManager {
	
	public static String appDataPath(String applicationName) {
		return System.getenv("APPDATA") + "\\" + applicationName + "\\";
	}
	
	public static String applicationPath() {
		try {
			String path = new File(".").getCanonicalPath();
			return path + "/";
		} catch(Exception e) {
			return "";
		}
	}
	
	public static String getDirectory(String filePath) {
		return new File(filePath).getParentFile().getAbsolutePath() + File.separator;
	}

	public static byte[] load(String path) {
		path = path.replace("\\", File.separator);
		path = path.replace("/", File.separator);
		try {
			File file = new File(path);
			FileInputStream reader = new FileInputStream(file);
			byte[] array = new byte[(int) file.length()];
			if (reader.read(array) <= 0)
				throw new IOException("No byte read.");
			reader.close();
			return array;
		} catch (Exception e) {
			System.err.println("Couldn't load: " + path);
			e.printStackTrace();
			return null;
		}
	}

	public static boolean makeDirs(File file) {
		if (!file.exists() && !file.getParentFile().exists()) {
            if (!file.getParentFile().mkdirs()) {
				System.err.println("Couldn't create folders for: " + file);
				return false;
			}
		}
		return true;
	}
	
	public static boolean save(String path, byte[] content) {
		path = path.replace("\\", File.separator);
		path = path.replace("/", File.separator);
		try {
			File file = new File(path);
			if (!makeDirs(file))
				throw new IOException("Couldn't create directory.");
			FileOutputStream writer = new FileOutputStream(file);
			writer.write(content);
			writer.close();
			return true;
		} catch(Exception e) {
			System.err.println("Couldn't save: " + path);
			e.printStackTrace();
			return false;
		}
	}
	
	public static void log(Exception e) {
	    Logger logger = Logger.getAnonymousLogger();
	    FileHandler fh;  
	    try {  

	        // This block configure the logger with handler and formatter  
	        fh = new FileHandler(applicationPath() + "/log.txt");  
	        logger.addHandler(fh);
	        SimpleFormatter formatter = new SimpleFormatter();  
	        fh.setFormatter(formatter);  

	        // the following statement is used to log any messages  
	        logger.log(Level.SEVERE, "an exception was thrown", e); 
	    } catch (Exception e0) {  
	        e.printStackTrace();  
	    }  
	}
	
}
