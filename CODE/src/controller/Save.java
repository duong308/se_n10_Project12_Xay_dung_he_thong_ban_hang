package controller;

import java.io.File;
import java.util.List;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.DonHang;
import model.SanPham;

/**
 * 
 * @author Project 6
 *
 * Lớp điều khiển lưu và tải dữ liệu.
 */
public class Save {
	
	Stage primaryStage;
	/*
	 * Tạo list rỗng chứa thông tin danh sách nhân viên.
	 */
	private QuanLiDonHang dataDh = new QuanLiDonHang();

	/*
	 * Tạo list rỗng chứa thông tin danh sách chức vụ.
	 */
	private QuanLiSanPham dataSp = new QuanLiSanPham();
	
	/*
	 * Tạo đối tượng phục vụ lưu và tải dữ liệu.
	 */
	private IoFile<DonHang> donHangPath = new IoFile<DonHang>();
	private IoFile<SanPham> sanPhamPath = new IoFile<SanPham>();

	public Save() {}
	
	public QuanLiDonHang getDataDh() {
		return dataDh;
	}
	public void setDataDh(QuanLiDonHang dataDh) {
		this.dataDh = dataDh;
	}
	public QuanLiSanPham getDataSp() {
		return dataSp;
	}
	public void setDataSp(QuanLiSanPham dataSp) {
		this.dataSp = dataSp;
	}
	
	/**
	 * Lưu dữ liệu sản phẩm vào file chỉ định, báo lỗi nếu không lưu được.
	 */
	public void saveAsDataSanPham() {
		GetPath path = new GetPath();
		try {
			IoFile<SanPham> temp = new IoFile<SanPham>(path.getPathToSave());
			temp.save(dataSp.getSanPhams());
		} catch (Exception e) {
			if (path.getPath() != null) {
				ChucNang.showErrorAlert("Không thể lưu file vào đường dẫn này!");
			}
		}
	}
	
	
	/**
	 * Lưu dữ liệu đơn hàng vào file chỉ định, báo lỗi nếu không lưu được.
	 */
	public void saveAsDataDonHang() {
		GetPath path = new GetPath();
		try {
			IoFile<DonHang> temp = new IoFile<DonHang>(path.getPathToSave());
			temp.save(dataDh.getDonHangList());
		} catch (Exception e) {
			if (path.getPath() != null) {
				ChucNang.showErrorAlert("Không thể lưu file vào đường dẫn này!");
			}
		}
	}
}