package com.rapchieuphim.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import java.io.IOException;

public class UserController {
    @FXML
    private Button btnLogout;

    @FXML
    public void handleViewMovies() {
        // Mở giao diện xem phim nếu có
    }

    @FXML
    public void handleBookTicket() {
        // Mở giao diện đặt vé nếu có
    }

    @FXML
    public void handleLogout() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/LoginView.fxml"));
            Stage stage = (Stage) btnLogout.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}