package controller;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Project 6
 *
 * @param <T>
 * 
 * Lớp thực hiện lưu và tải dữ liệu.
 */
public class IoFile<T extends Serializable> {
    private String fileName;
    
    /**
     * Tạo đối tượng FileDataAccessObject
     */
    public IoFile() {}

    public IoFile(String fileName) {
		super();
		this.fileName = fileName;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
     * 
     * @param data
     * Lưu dữ liệu từ file
     */
    public void save(List<T> data) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            out.writeObject(data);
        } catch (Exception e) {
            ChucNang.showErrorAlert("Bạn chưa tải file hoặc không thể lưu vào đường dẫn này!");
        }
    }



}