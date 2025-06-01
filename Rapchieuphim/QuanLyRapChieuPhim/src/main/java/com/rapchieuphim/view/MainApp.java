package com.rapchieuphim.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/fxml/LoginView.fxml")));
        primaryStage.setScene(scene);
        primaryStage.setTitle("Quản lý rạp chiếu phim - Đăng nhập");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}