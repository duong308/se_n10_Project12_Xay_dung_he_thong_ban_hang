package controller;

import java.util.ArrayList;
import java.util.List;

import controller.QuanLiSanPham;
import model.DonHang;

/**
 * 
 * @author Project 6
 *
 * Lớp quản lí danh sách Đơn Hàng.
 */
public class QuanLiDonHang {
    private List<DonHang> donHangList;

    /**
     * Tạo danh sách đơn hàng rỗng.
     */
    public QuanLiDonHang() {
        this.donHangList = new ArrayList<>();
    }

    public List<DonHang> getDonHangList() {
		return donHangList;
	}

	public void setDonHangList(List<DonHang> donHangList) throws Exception {
		this.donHangList = donHangList;
	}
	
	/**
	 * 
	 * @param donHang
	 * Thêm đơn hàng
	 */
    public void addDonHang(DonHang donHang) {
        donHangList.add(donHang);
    }

    /**
     * 
     * @param donHang
     * Xóa đơn hàng.
     */
    public void removeDonHang(DonHang donHang) {
        donHangList.remove(donHang);
    }

    /**
     * 
     * @param index
     * @param ID
     * Chỉnh sửa ID của đơn hàng theo index.
     */
    public void updateDonHangID(int index, int ID) {
    	donHangList.get(index).setId(ID);
    }
    
    /**
     * 
     * @param index
     * @param name
     * Chỉnh sửa tên của đơn hàng dựa theo index.
     */
    public void updateDonHangName(int index, String name) {
    	donHangList.get(index).setDonHangName(name);
    }
    
    /**
     * 
     * @param index
     * @param sanPham
     * @param sanPhams
     * Chỉnh sửa tên sản phẩm đồng thời chỉnh hóa đơn của đơn  hàng theo sản phẩm dựa theo index.
     */
    public void updateDonHangSp(int index, String sanPham, QuanLiSanPham sanPhams) {
    	donHangList.get(index).setSanPhamName(sanPham);
    	donHangList.get(index).setPrice(sanPham, sanPhams);
    }
    
    /**
     * 
     * @param sanPham
     * @param sanPhams
     * Chỉnh sửa hóa đơn của sản phẩm theo tên sản phẩm dựa theo index.
     */
    public void updateDonHangPrice(String sanPham, QuanLiSanPham sanPhams) {
    	for (DonHang temp: this.donHangList) {
    		temp.setPrice(sanPham, sanPhams);
    	}
    }
    
    /**
     * 
     * @param index
     * @param salary
     * Chỉnh sửa hóa đơn của sản phẩm dựa theo index.
     */
    public void updateDonHangPrice(int index, double price) {
    	donHangList.get(index).setPrice(price);
    }
    
    /**
     * 
     * @param index
     * @param price
     * Chỉnh sửa giá và hóa đơn của đơn hàng dựa theo index.
     */
    public void updateSoLuongDonHang(int index, double price) {
    	donHangList.get(index).setSoLuong(price);
    }
}
