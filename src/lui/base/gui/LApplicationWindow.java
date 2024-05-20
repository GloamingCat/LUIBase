package lui.base.gui;

import lui.base.serialization.LFileManager;
import lui.base.serialization.LSerializer;

public interface LApplicationWindow extends LWindow {

	//////////////////////////////////////////////////
	//region UI

	void run();
	String getApplicationName();

	LWindow getWindow();
	void refreshEditButtons();
	void refreshClipboardButtons();
	LMenu getEditMenu();

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
		String path = LFileManager.appDataPath(getApplicationName()) + "lattest.txt";
		byte[] bytes = resultPath.getBytes();
		LFileManager.save(path, bytes);
		return newProject;
	}
	
	default LSerializer openProject() {
		if (!askSave()) {
			return null;
		}
		String resultFile = openLoadProjectDialog();
		if (resultFile == null)
			return null;
		LSerializer project = createProject(resultFile);
		if (project.load()) {
			String path = LFileManager.appDataPath(getApplicationName()) + "lattest.txt";
			byte[] bytes = resultFile.getBytes();
			LFileManager.save(path, bytes);
			onLoadSuccess(project, resultFile);
		} else {
			onLoadFail(resultFile);
			return null;
		}
		return project;
	}

	default String defaultProjectPath() {
		String lattest = LFileManager.appDataPath(getApplicationName()) + "lattest.txt";
		byte[] bytes = LFileManager.load(lattest);
		if (bytes != null && bytes.length > 0) {
			return new String(bytes);
		} else {
			return null;
		}
	}

	default boolean loadDefault(String path) {
		if (path == null) {
			path = defaultProjectPath();
		}
		LSerializer project = createProject(path);
		if (!project.load()) {
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
