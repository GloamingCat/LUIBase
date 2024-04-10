package lui.base.gui;

import lui.base.serialization.LFileManager;
import lui.base.serialization.LSerializer;

public interface LApplicationWindow extends LWindow {
	void run();
	String getApplicationName();
	String getProjectExtension();
	LWindow getWindow();
	void refreshEditButtons();
	void refreshClipboardButtons();
	LMenu getEditMenu();
	
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
		} else {
			openLoadErrorDialog(resultFile);
			return null;
		}
		return project;
	}
	
	default LSerializer loadDefault(String path) {
		if (path == null) {
			String lattest = LFileManager.appDataPath(getApplicationName()) + "lattest.txt";
			byte[] bytes = LFileManager.load(lattest);
			if (bytes != null && bytes.length > 0) {
				path = new String(bytes);
			} else {
				return null;
			}
		}
		LSerializer project = createProject(path);
		if (!project.load()) {
			onLoadFail(path);
			return null;
		} else {
			onLoadSuccess(project);
			return project;
		}
	}

	String openNewProjectDialog();
	boolean openNewConfirmDialog();
	String openLoadProjectDialog();
	void openLoadErrorDialog(String file);
	
	void onLoadSuccess(LSerializer project);
	void onLoadFail(String path);
	boolean askSave();

}
