package application;

import java.net.URL;
import java.util.ResourceBundle;

import controller.ChucNang;
import controller.Save;
import controller.QuanLiDonHang;
import controller.QuanLiSanPham;
import controller.ChucNang.CustomDoubleStringConverter;
import controller.ChucNang.CustomIntegerStringConverter;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.StringConverter;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import model.DonHang;
import model.SanPham;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * 
 * @author Project 6
 * 
 * Lớp điều khiển tableview.
 */
public class lienket implements Initializable{
	Save data = new Save();
	
	
	
	/*
	 * Tạo list rỗng chứa thông tin danh sách đơn hàng.
	 */
	private QuanLiDonHang dataDh = data.getDataDh();
	/*
	 * Tạo list rỗng chứa thông tin danh sách sản phẩm .
	 */
	private QuanLiSanPham dataSp = data.getDataSp();
	
	
	
    /*
     * Tạo dữ liệu cho tbDonHang.
     */
    private ObservableList<DonHang> donHangList = FXCollections.observableArrayList(dataDh.getDonHangList());
    
    /*
     * Tạo dữ liệu cho tbSanPham.
     */
    private ObservableList<SanPham> sanPhamList = FXCollections.observableArrayList(dataSp.getSanPhams());
	
	
	
    @FXML
    private TableColumn<DonHang, Integer> donHangIDcol;

    @FXML
    private TableColumn<DonHang, String> donHangNamecol;

    @FXML
    private TableColumn<DonHang, String> donHangNameSpcol;

    @FXML
    private TableColumn<DonHang, Double> donHangHoaDonCol;
    
    @FXML
    private TableColumn<DonHang, Double> donHangSoLuongCol;
        
    @FXML
    private TextField donHangID;

    @FXML
    private TextField donHangName;

    @FXML
    private TextField donHangNameSp;

    @FXML
    private TextField donHangHoaDon;

    @FXML
    private TextField donHangSoLuong;

   

    @FXML
    private TableColumn<SanPham, Integer> sanPhamIDcol;

    @FXML
    private TableColumn<SanPham, String> sanPhamNamecol;

    @FXML
    private TableColumn<SanPham, Double> sanPhamPricecol;

    @FXML
    private TextField sanPhamID;

    @FXML
    private TextField sanPhamName;

    @FXML
    private TextField sanPhamPrice;

    @FXML
    private TextField sanPhamPriceS;

 

    @FXML
    private TableView<DonHang> tbDonHang;

    @FXML
    private TableView<SanPham> tbSanPham;
    
    /**
     * 
     * 
     * @param sanPham
     * @return 0 nếu có sản phẩm , 1 nếu không có sản phẩm .
     */
    public int kiemTraSanPham(String sanPham) {
    	int check = 0;
		for (SanPham checkSanPham: sanPhamList) {
			if(checkSanPham.getName().equals(sanPham)) {
				check = 1;
				break;
			}
		}
		return check;
    }
    
    /**
     * 
     * @param checkSoNguyen
     * Thay đổi một ô được chọn trong cột donHangIDcol, kiểm tra ID đảm bảo không trùng, không rỗng và phải là số nguyên.
     */
    public void changeDonHangID (CellEditEvent checkSoNguyen) {
    	int index = tbDonHang.getSelectionModel().getSelectedIndex();
    	if(checkSoNguyen.getNewValue() != null) {
	    	int temp_ID = Integer.parseInt(checkSoNguyen.getNewValue().toString().trim());
	    	for (DonHang temp: donHangList) {
				if(temp.getId() == temp_ID && donHangIDcol.getCellData(index) != temp_ID || temp_ID == -1) {
					if(temp_ID == -1) {
						ChucNang.showErrorAlert("ID phải là số nguyên!");
					}else {
						ChucNang.showErrorAlert("ID đã được sử dụng!");
					}
					updateDonHang();
					return;
				}
	    	}
	    	DonHang sanPhamSlected = tbDonHang.getSelectionModel().getSelectedItem();
	    	sanPhamSlected.setId(temp_ID);
	    	dataDh.updateDonHangID(donHangList.indexOf(sanPhamSlected), temp_ID);
    	}else {
    		ChucNang.showErrorAlert("Không thể thay thế bằng chuỗi rỗng!");
    	}
    	updateDonHang();
    }
    
    /**
     * 
     * @param checkRong
     * Thay đổi một ô được chọn trong cột donHangNamecol, kiểm tra đầu vào là chuỗi rỗng thì báo lỗi.
     */
    public void changeDonHangName (CellEditEvent checkRong) {
    	if(!checkRong.getNewValue().toString().trim().equals("")) {
	    	DonHang donHangSlected = tbDonHang.getSelectionModel().getSelectedItem();
	    	donHangSlected.setDonHangName(checkRong.getNewValue().toString().trim());
	    	dataDh.updateDonHangName(donHangList.indexOf(donHangSlected), checkRong.getNewValue().toString().trim());
    	}else {
    		ChucNang.showErrorAlert("Không thể thay thế bằng chuỗi rỗng!");
    	}
    	updateDonHang();
    }
    
    /**
     * 
     * @param canhBao
     * Thay đổi một ô được chọn trong cột donHangNameSpcol, phát hiện sản phẩm đã được thiết lập chưa, thay đổi hóa đơn trong đơn hàng theo tên sản phẩm được thay đổi.
     */
    public void changeDonHangNameSp (CellEditEvent canhBao) {
    	DonHang donHangSlected = tbDonHang.getSelectionModel().getSelectedItem();
    	if(kiemTraSanPham(canhBao.getNewValue().toString().trim()) == 0 && !canhBao.getNewValue().toString().trim().equals("")) {
    		ChucNang.showWarningAlert("Cảnh báo hiện không có sản phẩm này!\n* Nếu bạn muốn sử dụng tên này vui lòng tạo sản phẩm này.\n* Xác nhận lại tại cột 'Sản Phẩm' để cập nhật lại số lượng cho sản phẩm này.");
    	}
    	donHangSlected.setSanPhamName(canhBao.getNewValue().toString().trim());
    	dataDh.updateDonHangSp(donHangList.indexOf(donHangSlected), canhBao.getNewValue().toString().trim(), dataSp);
    	updateDonHang();
    }
    
    /**
     * 
     * @param evenDonHang
     * Thay đổi một ô được chọn trong cột donHangHoaDoncol, sửa hóa đơn bằng không nếu nhập vào không phải số nguyên, thay đổi lại hóa đơn nếu sửa giá mới.
     */
    public void changeDonHangSoLuong (CellEditEvent evenDonHang) {
    	if(evenDonHang.getNewValue() != null) {
	    	DonHang donHangSlected = tbDonHang.getSelectionModel().getSelectedItem();
	    	donHangSlected.setSoLuong(Double.parseDouble(evenDonHang.getNewValue().toString().trim()));
	    	dataDh.updateDonHangPrice(donHangList.indexOf(donHangSlected), Double.parseDouble(evenDonHang.getNewValue().toString().trim()));
	    	dataDh.updateDonHangSp(donHangList.indexOf(donHangSlected), donHangList.get(donHangList.indexOf(donHangSlected)).getSanPhamName(), dataSp);
    	}else {
    		DonHang donHangSlected = tbDonHang.getSelectionModel().getSelectedItem();
	    	donHangSlected.setSoLuong(0.0);
	    	dataDh.updateDonHangPrice(donHangList.indexOf(donHangSlected), 0.0);
	    	dataDh.updateDonHangSp(donHangList.indexOf(donHangSlected), donHangList.get(donHangList.indexOf(donHangSlected)).getSanPhamName(), dataSp);
    	}
    	updateDonHang();
    }
    
    /**
     * 
     * @param event
     * Thay đổi một ô được chọn trong cột donHangHoaDoncol, mặc định hóa đơn là 0 nếu nhập vào không phải số nguyên.
     */
    public void changeDonHangHoaDon (CellEditEvent event) {
    	if(event.getNewValue() != null) {
	    	DonHang donHangSlected = tbDonHang.getSelectionModel().getSelectedItem();
	    	donHangSlected.setPrice(Double.parseDouble(event.getNewValue().toString().trim()));
	    	dataDh.updateDonHangPrice(donHangList.indexOf(donHangSlected), Double.parseDouble(event.getNewValue().toString().trim()));
    	}else {
    		DonHang donHangSlected = tbDonHang.getSelectionModel().getSelectedItem();
	    	donHangSlected.setPrice(0.0);
	    	dataDh.updateDonHangPrice(donHangList.indexOf(donHangSlected), 0.0);
    	}
    	updateDonHang();
    }
    
    /**
     * 
     * @param event
     * Thay đổi một ô được chọn trong cột sanPhamIDcol, kiểm tra ID đảm bảo không trùng, không rỗng và phải là số nguyên.
     */
    public void changeSanPhamID (CellEditEvent event) {
    	int index = tbSanPham.getSelectionModel().getSelectedIndex();
    	if(event.getNewValue() != null) {
	    	int temp_ID = Integer.parseInt(event.getNewValue().toString().trim());
	    	for (SanPham temp: sanPhamList) {
				if(temp.getID() == temp_ID && sanPhamIDcol.getCellData(index) != temp_ID) {
					if(temp_ID == -1) {
						ChucNang.showErrorAlert("ID phải là số nguyên!");
					}else {
						ChucNang.showErrorAlert("ID đã được sử dụng!");
					}
					updateTableSanPham();
					return;
				}
	    	}
	    	SanPham sanPhamSlected = tbSanPham.getSelectionModel().getSelectedItem();
	    	sanPhamSlected.setID(temp_ID);
	    	dataSp.updateSanPhamID(index, temp_ID);
    	}else {
    		ChucNang.showErrorAlert("Không thể thay thế bằng chuỗi rỗng!");
    	}
    	updateTableSanPham();
    }
    
    /**
     * 
     * @param event
     * Thay đổi một ô được chọn trong cột sanPhamNamecol, kiểm tra ID đảm bảo không trùng, không rỗng.
     */
    public void changeSanPhamName (CellEditEvent event) {
    	int index = tbSanPham.getSelectionModel().getSelectedIndex();
    	if(!event.getNewValue().toString().trim().equals("")) {
    		for (SanPham temp: sanPhamList) {
    			String temp_Name = event.getNewValue().toString().trim();
				if(temp.getName().equals(temp_Name)&& !sanPhamNamecol.getCellData(index).equals(temp_Name)) {
					ChucNang.showErrorAlert("Đã có sản phẩm này!");
					updateTableSanPham();
					return;
				}
	    	}
	    	SanPham sanPhamSlected = tbSanPham.getSelectionModel().getSelectedItem();
	    	sanPhamSlected.setName(event.getNewValue().toString().trim());
	    	dataSp.updateSanPhamName(sanPhamList.indexOf(sanPhamSlected), event.getNewValue().toString().trim());
    	}else {
    		ChucNang.showErrorAlert("Không thể thay thế bằng chuỗi rỗng!");
    	}
    	updateTableSanPham();
    }
    
    /**
     * 
     * @param event
     * Thay đổi một ô được chọn trong cột sanPhamPricecol, mặc định hóa đơn bằng 0 nếu truyền vào không phải số nguyên.
     */
    public void changeSanPhamPrice (CellEditEvent event) {
    	if(event.getNewValue() != null) {
	    	SanPham sanPhamSlected = tbSanPham.getSelectionModel().getSelectedItem();
	    	sanPhamSlected.setSanPhamPrice(Double.parseDouble(event.getNewValue().toString().trim()));
	    	dataSp.updateSanPhamPrice(sanPhamList.indexOf(sanPhamSlected), Double.parseDouble(event.getNewValue().toString().trim()));
    	}else {
    		SanPham sanPhamSlected = tbSanPham.getSelectionModel().getSelectedItem();
	    	sanPhamSlected.setSanPhamPrice(0.0);
	    	dataSp.updateSanPhamPrice(sanPhamList.indexOf(sanPhamSlected), 0.0);
    	}
    	updateTableSanPham();
    }
    
    /**
     * 
     * @param event
     * Lưu dữ liệu đơn hàng vào đường dẫn chỉ định bằng nút "Save as...".
     */
    @FXML
    void saveDonHang(ActionEvent event) {
    	data.saveAsDataDonHang();
    }
    
    /**
     * 
     * @param event
     * Lưu dữ liệu sản phẩm  vào đường dẫn chỉ định bằng nút "Save as...".
     */
    @FXML
    void saveSanPham(ActionEvent event) {
    	data.saveAsDataSanPham();
    }
    
   
    
    
    
    /**
     * Update tbDonHang khi có thay đổi.
     */
    public void updateDonHang() {
    	donHangIDcol.setCellValueFactory(new PropertyValueFactory<DonHang, Integer>("id"));
		donHangNamecol.setCellValueFactory(new PropertyValueFactory<DonHang, String>("name"));
		donHangNameSpcol.setCellValueFactory(new PropertyValueFactory<DonHang, String>("sanPhamName"));
		donHangHoaDonCol.setCellValueFactory(new PropertyValueFactory<DonHang, Double>("price"));
		donHangSoLuongCol.setCellValueFactory(new PropertyValueFactory<DonHang, Double>("soLuong"));
		tbDonHang.setItems(donHangList);
		tbDonHang.setEditable(true);
		donHangIDcol.setCellFactory(TextFieldTableCell.forTableColumn(new ChucNang.CustomIntegerStringConverter()));
		donHangNamecol.setCellFactory(TextFieldTableCell.forTableColumn());
		donHangNameSpcol.setCellFactory(TextFieldTableCell.forTableColumn());
		try {
			donHangHoaDonCol.setCellFactory(TextFieldTableCell.forTableColumn(new ChucNang.CustomDoubleStringConverter()));
		}catch(Exception e) {
			ChucNang.showErrorAlert("Hóa đơn phải là số nguyên!");
			return;
		}
		try {
			donHangSoLuongCol.setCellFactory(TextFieldTableCell.forTableColumn(new ChucNang.CustomDoubleStringConverter()));
		}catch(Exception e) {
			ChucNang.showErrorAlert("Hóa Đơn phải là số nguyên!");
			return;
		}
		
    }
    
    /**
     * Update tbSanPham khi có thay đổi.
     */
    public void updateTableSanPham() {
    	sanPhamIDcol.setCellValueFactory(new PropertyValueFactory<SanPham, Integer>("ID"));
		sanPhamNamecol.setCellValueFactory(new PropertyValueFactory<SanPham, String>("name"));
		sanPhamPricecol.setCellValueFactory(new PropertyValueFactory<SanPham, Double>("sanPhamPrice"));
		tbSanPham.setItems(sanPhamList);
		tbSanPham.setEditable(true);
		sanPhamIDcol.setCellFactory(TextFieldTableCell.forTableColumn(new ChucNang.CustomIntegerStringConverter()));
		sanPhamNamecol.setCellFactory(TextFieldTableCell.forTableColumn());
		try {
			sanPhamPricecol.setCellFactory(TextFieldTableCell.forTableColumn(new ChucNang.CustomDoubleStringConverter()));
		}catch(Exception e) {
			ChucNang.showErrorAlert("Hóa đơn phải là số nguyên!");
			return;
		}
		
    }
	
	/**
	 * 
	 * @param event
	 * Thêm thông tin một đơn hàng mới bằng nút "Add".
	 */
	@FXML
	public void Add (ActionEvent event) {
		if(!donHangID.getText().trim().equals("") && !donHangName.getText().trim().equals("")) {
			try {
				Integer.parseInt(donHangID.getText().trim());
			}catch(Exception e) {
				ChucNang.showErrorAlert("ID phải là số nguyên!");
				return;
			}
			for (DonHang temp: donHangList) {
				if(temp.getId() == Integer.parseInt(donHangID.getText().trim())) {
					ChucNang.showErrorAlert("ID đã được sử dụng!");
					return;
				}
			}
			DonHang newDonHang = new DonHang();
			newDonHang.setId(Integer.parseInt(donHangID.getText().trim()));
			newDonHang.setDonHangName(donHangName.getText().trim());
			if(kiemTraSanPham(donHangName.getText().trim()) == 0 && donHangNameSp.getText().trim().equals("")) {
				ChucNang.showWarningAlert("Cảnh báo hiện không có sản phẩm này!\n* Nếu bạn muốn sử dụng tên này vui lòng tạo sản phẩm này.\n* Xác nhận lại tại cột 'Tên Sản Phẩm' để cập nhật lại hóa đơn cho khách hàng này.");
			}
			newDonHang.setSanPhamName(donHangNameSp.getText().trim());
			try {
				if(!donHangSoLuong.getText().trim().equals("")) {
					newDonHang.setSoLuong(Integer.parseInt(donHangSoLuong.getText().trim()));
				}
			}catch(Exception e) {
				ChucNang.showErrorAlert("Số lượng phải là số nguyên phải là số nguyên!");
				newDonHang.setSoLuong(0.0);
			}
			newDonHang.setPrice(donHangNameSp.getText().trim(), dataSp);
			donHangList.add(newDonHang);
			dataDh.addDonHang(newDonHang);
			donHangName.setText("");
			donHangID.setText("");
			donHangNameSp.setText("");
			donHangSoLuong.setText("");
			updateDonHang();
			return;
		}else {
			ChucNang.showErrorAlert("Thiếu thông tin!");
		}
		
	}
	
	/**
	 * 
	 * @param event
	 * Thêm thông tin một sản phẩm mới bằng nút "Add".
	 */
	@FXML
	public void AddSp (ActionEvent event) {
		if(!sanPhamID.getText().trim().equals("") && !sanPhamName.getText().trim().equals("") && !sanPhamPrice.getText().trim().equals("")) {
			try {
				Integer.parseInt(sanPhamID.getText().trim());
			}catch(Exception e) {
				ChucNang.showErrorAlert("ID phải là số nguyên!");
				return;
			}
			for (SanPham temp: sanPhamList) {
				if(temp.getID() == Integer.parseInt(sanPhamID.getText().trim()) || temp.getName().equals(sanPhamName.getText().trim())) {
					if(temp.getID() == Integer.parseInt(sanPhamID.getText().trim())) {
						ChucNang.showErrorAlert("ID đã được sử dụng!");
					}else {
						ChucNang.showErrorAlert("Đã có sản phẩm này!");
					} 
					
					return;
				}
			}
			SanPham newSanPham = new SanPham();
			newSanPham.setID(Integer.parseInt(sanPhamID.getText().trim()));
			newSanPham.setName(sanPhamName.getText().trim());
			try {
				newSanPham.setSanPhamPrice(Double.parseDouble(sanPhamPrice.getText().trim()));
			}catch(Exception e) {
				ChucNang.showErrorAlert("Giá của sản phẩm là số nguyên!");
				newSanPham.setSanPhamPrice(0.0);
			}
			sanPhamList.add(newSanPham);
			dataSp.addSanPham(newSanPham);
			sanPhamName.setText("");
			sanPhamID.setText("");
			sanPhamPrice.setText("");
			updateTableSanPham();
		}else {
			ChucNang.showErrorAlert("Thiếu thông tin!");
		}
		
	}
	
	/**
	 * 
	 * @param event
	 * Xóa một đơn hàng được chỉ định bằng nút "Remove".
	 */
	@FXML
	public void Remove (ActionEvent event) {
		DonHang selected = tbDonHang.getSelectionModel().getSelectedItem();
		if (selected == null) {
			ChucNang.showErrorAlert("Chọn đơn hàng trước khi xóa!");
		}
		donHangList.remove(selected);
		dataDh.removeDonHang(selected);
	
	}
	
	/**
	 * 
	 * @param event
	 * Xóa một sản phẩm được chỉ định bằng nút "Remove".
	 */
	@FXML
	public void RemoveSanPham (ActionEvent event) {
		SanPham selected = tbSanPham.getSelectionModel().getSelectedItem();
		if (selected == null) {
			ChucNang.showErrorAlert("Chọn sản phẩm trước khi xóa!");
		}
		sanPhamList.remove(selected);
		dataSp.removeSanPham(selected);
		
	}
	
	
	/**
	 * Override hàm initialize của giao diện Initialize thuộc fxml
	 * + Hiển thị tbDonHang
	 * + Hiển thị tbSanPham
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		updateDonHang();
		updateTableSanPham();
	}
}
