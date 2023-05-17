package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import controller.GetPath;
import controller.ChucNang;
import controller.IoFile;
import controller.Save;
import controller.QuanLiDonHang;
import controller.QuanLiSanPham;
import model.DonHang;
import model.SanPham;

/**
 * 
 * @author Project 6
 *
 * Lớp trình diễn cửa sổ.
 */
public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try{
            Parent root = FXMLLoader.load(this.getClass().getResource("BTL6.fxml"));
            Scene scene = new Scene(root);
            primaryStage.getIcons().add(new Image("file:///C:/Users/Admin/eclipse-workspace/QuanLyBanHang_OOP_JavaFx/icon/banhang.jpg"));
            primaryStage.setTitle("Quản Lý Bán Hàng");
            primaryStage.setScene(scene);
            primaryStage.show();
        }catch (Exception e){
            System.out.println(e);
        }
    }
}