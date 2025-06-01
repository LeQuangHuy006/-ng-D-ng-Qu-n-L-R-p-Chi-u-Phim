package com.rapchieuphim.controller;

import com.rapchieuphim.dao.AccountDAO;
import com.rapchieuphim.model.Account;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import java.io.IOException;

public class LoginController {
    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Label lblMessage;

    private final AccountDAO accountDAO = new AccountDAO();

    @FXML
    public void loginUser() {
        String username = txtUsername.getText();
        String password = txtPassword.getText();
        Account acc = accountDAO.login(username, password, "user");
        if (acc != null) {
            lblMessage.setText("");
            openUserView();
        } else {
            lblMessage.setText("Sai tài khoản người dùng hoặc mật khẩu!");
        }
    }

    @FXML
    public void loginAdmin() {
        String username = txtUsername.getText();
        String password = txtPassword.getText();
        Account acc = accountDAO.login(username, password, "admin");
        if (acc != null) {
            lblMessage.setText("");
            openAdminView();
        } else {
            lblMessage.setText("Sai tài khoản quản lý hoặc mật khẩu!");
        }
    }

    @FXML
    public void showRegister() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/RegisterView.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Tạo tài khoản mới");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            lblMessage.setText("Không mở được cửa sổ đăng ký!");
        }
    }

    private void openUserView() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/UserView.fxml"));
            Stage stage = (Stage) txtUsername.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            lblMessage.setText("Không mở được giao diện người dùng!");
        }
    }

    private void openAdminView() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/AdminView.fxml"));
            Stage stage = (Stage) txtUsername.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace(); // In chi tiết lỗi
            lblMessage.setText("Không mở được giao diện quản lý!");
        }
    }
}