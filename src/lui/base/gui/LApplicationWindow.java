package lui.base.gui;

import lui.base.serialization.LFileManager;
import lui.base.serialization.LSerializer;
import org.ini4j.Ini;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public interface LApplicationWindow extends LWindow {

	//////////////////////////////////////////////////
	//region UI

	void run(String... args);
	String getApplicationName();
	Ini getPreferences();

	LWindow getWindow();
	void refreshEditButtons();
	void refreshClipboardButtons();
	LMenu getEditMenu();

	//endregion

	//////////////////////////////////////////////////
	//region Preferences

	default Ini loadPreferences() {
		File file = new File(LFileManager.appDataPath(getApplicationName()) + "prefs.ini");
        try {
			return new Ini(file);
        } catch (IOException e) {
            System.err.println("No .ini file found.");
			LFileManager.makeDirs(file);
			Ini ini = new Ini();
			ini.setFile(file);
			return ini;
        }
	}

	default void savePreferences() {
		try {
			getPreferences().store();
        } catch (IOException e) {
            System.err.println("Couldn't save .ini file: " + getPreferences().getFile());
            System.err.println("Preferences content: " + getPreferences().toString());
			e.printStackTrace();
        }
	}

	default String getLatestProject() {
		Ini.Section recent = (Ini.Section) getPreferences().get("file");
		if (recent == null)
			recent = getPreferences().add("file");
		return (String) recent.get("recent0", String.class);
	}

	default void setLatestProject(String projectPath) {
		Ini.Section section = (Ini.Section) getPreferences().get("file");
		ArrayList<String> files = new ArrayList<>();
		for (int i = 0; i < 9; i++) {
			String file = (String) section.get("recent" + i, String.class);
			if (file == null)
				break;
			files.add(file);
		}
		section.put("recent0", projectPath);
		int n = Math.min(files.size(), 9);
		for (int i = 0; i < n; i++) {
			section.put("recent" + (i+1), files.get(i));
		}
	}

	default void setPreference(String section, String key, Object value) {
		Ini.Section s = (Ini.Section) getPreferences().get(section);
		if (s == null)
			s = getPreferences().add(section);
		s.put(key, value);
	}

	default <T> T getPreference(String section, String key, Class<T> c) {
		Ini.Section s = (Ini.Section) getPreferences().get(section);
		if (s == null)
			return null;
		return (T) s.get(key, c);
	}

	//endregion

	//////////////////////////////////////////////////
	//region Project

	String getProjectExtension();
	
	LSerializer createProject(String path);

	default LSerializer newProject() {
		if (!askSave()) {
			return null;
		}
		String resultPath = openNewProjectDialog();
		if (resultPath == null)
			return null;
		LSerializer newProject = createProject(resultPath);
		if (newProject.isDataFolder(LFileManager.getDirectory(resultPath))) {
			if (!openNewConfirmDialog())
				return null;
		}
		newProject.initialize();
		newProject.save();
		setLatestProject(resultPath);
		savePreferences();
		return newProject;
	}

	// Returns null if canceled or failed.
	default LSerializer openProject() {
		if (!askSave())
			return null;
		String resultFile = openLoadProjectDialog();
		if (resultFile == null)
			return null;
		LSerializer project = createProject(resultFile);
		if (project.load()) {
			setLatestProject(resultFile);
			savePreferences();
			onLoadSuccess(project, resultFile);
		} else {
			onLoadFail(resultFile);
			return null;
		}
		return project;
	}

	default boolean loadDefault(String path) {
		if (path == null) {
			path = getLatestProject();
			if (path == null)
				return false;
		}
		LSerializer project = createProject(path);
		if (project == null) {
			return false;
		} else if (!project.load()) {
			onLoadFail(path);
			return false;
		} else {
			onLoadSuccess(project, path);
			return true;
		}
	}

	void onLoadSuccess(LSerializer project, String path);
	void onLoadFail(String path);

	//endregion

	//////////////////////////////////////////////////
	//region Dialogs

	String openNewProjectDialog();
	boolean openNewConfirmDialog();
	String openLoadProjectDialog();

	boolean askSave();

	//endregion

}
