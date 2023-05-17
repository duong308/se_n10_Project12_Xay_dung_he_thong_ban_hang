package model;

import java.io.Serializable;

import controller.QuanLiSanPham;

/**
 * 
 * @author Project 6
 *
 * Lớp đơn hàng.
 */
public class DonHang implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private double price;
    private String sanPhamName;
    private double soLuong;

    public DonHang(int id, String name, double soLuong, String sanPhamName) {
        this.id = id;
        this.name = name;
        this.soLuong = Math.round(soLuong);
        this.sanPhamName = sanPhamName;
    }
    
    public DonHang() {};

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getSanPhamPrice() {
        return this.price / this.soLuong;
    }

    public double getPrice() {
    	return this.price;
    }
    
    public String getSanPhamName() {
        return sanPhamName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDonHangName(String name) {
        this.name = name;
    }
    
    /**
     * 
     * @param sanPham
     * @param sanPhams
     * Tính hóa đơn dựa trên giá và  số lượng.
     */
    public void setPrice(String sanPham, QuanLiSanPham sanPhams) {
    	double gia = 0.0;
    	for(SanPham temp: sanPhams.getSanPhams()) {
    		if(sanPham.equals(temp.getName())) {
    			gia = temp.getSanPhamPrice();
    		}
    	}
    	this.price = gia * this.soLuong;
    }
    
    public void setPrice(double price) {
        this.price = Math.round(price);
    }

    public void setSanPhamName(String sanPhamName) {
        this.sanPhamName = sanPhamName;
    }

	public double getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(double soLuong) {
		this.soLuong = Math.round(soLuong);
	}
}