package model;

import java.io.Serializable;

/**
 * 
 * @author Project 6
 *
 * Lớp sản phẩm.
 */
public class SanPham implements Serializable {
    private static final long serialVersionUID = 1L;
    private int ID;
    private String name;
    private double sanPhamPrice;

    public SanPham(int ID, String name, double sanPhamPrice) {
    	this.ID = ID;
        this.name = name;
        this.sanPhamPrice = sanPhamPrice;
    }

    public SanPham() {};

	public String getName() {
        return name;
    }

    public double getSanPhamPrice() {
        return sanPhamPrice;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSanPhamPrice(double sanPhamPrice) {
        this.sanPhamPrice = sanPhamPrice;
    }

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}
}