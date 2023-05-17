package controller;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.ObservableList;
import model.DonHang;
import model.SanPham;

/**
 * 
 * @author Project 6
 *
 * Lớp quản lí danh sách sản phẩm.
 */
public class QuanLiSanPham {
    private List<SanPham> sanPhamList;
    
    /**
     * Tạo danh sách sản phẩm rỗng.
     */
    public QuanLiSanPham() {
        this.sanPhamList = new ArrayList<>();
    }
	
    public List<SanPham> getSanPhams() {
		return sanPhamList;
	}

	public void setSanPhams(List<SanPham> sanPhamList) {
		this.sanPhamList = sanPhamList;
	}
	
	/**
	 * 
	 * @param sanPham
	 * Thêm sản phẩm.
	 */
	public void addSanPham(SanPham sanPham) {
        sanPhamList.add(sanPham);
    }

	/**
	 * 
	 * @param sanPham
	 * Xóa sản phẩm.
	 */
    public void removeSanPham(SanPham sanPham) {
        sanPhamList.remove(sanPham);
    }

    /**
     * 
     * @param index
     * @param ID
     * Chỉnh sửa ID của sản phẩm dựa theo index.
     */
    public void updateSanPhamID(int index, int ID) {
    	sanPhamList.get(index).setID(ID);
    }
    
    /**
     * 
     * @param index
     * @param name
     * Chỉnh sửa tên của sản phẩm dựa theo index.
     */
    public void updateSanPhamName(int index, String name) {
    	sanPhamList.get(index).setName(name);
    }
    
    /**
     * 
     * @param index
     * @param salaryCoefficient
     * Chỉnh sửa giá của sản phẩm dựa theo index.
     */
    public void updateSanPhamPrice(int index, double price) {
    	sanPhamList.get(index).setSanPhamPrice(price);
    }
}
