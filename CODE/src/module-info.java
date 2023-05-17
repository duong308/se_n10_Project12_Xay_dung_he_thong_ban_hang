module QuanLyBanHang_OOP_JavaFx {
	requires javafx.controls;
	requires javafx.base;
	requires javafx.fxml;
	requires javafx.graphics;
	
	opens application to javafx.graphics, javafx.fxml;
	opens model to javafx.base;
	
}



