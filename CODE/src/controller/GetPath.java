package controller;

import java.io.File;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * 
 * @author Project 6
 *
 * Lớp lấy đường dẫn phục vụ lưu và tải.
 */
public class GetPath {
	private Stage primaryStage;
	private String path;
	
	public GetPath(String path) {
			super();
			this.path = path;
	}
	
	public GetPath() {}

	public String getPath() {
		return path;
	}
	
	public void setPath(String path) {
		this.path = path;
	}
	
	
	/**
	 * 
	 * @return
	 * Đường dẫn phục vụ lưu dữ liệu.
	 * @throws Exception
	 * Ném ra ngoại lê
	 */
	public String getPathToSave() throws Exception{
		FileChooser FC = new FileChooser();
		FC.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Add all", "*"));
		FC.setTitle("Save your file");
		File file = FC.showSaveDialog(this.primaryStage);
		return this.path = file.getAbsolutePath();
	}


}
