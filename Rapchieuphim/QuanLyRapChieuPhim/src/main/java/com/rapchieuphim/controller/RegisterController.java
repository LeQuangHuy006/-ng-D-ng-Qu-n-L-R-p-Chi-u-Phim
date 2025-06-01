package com.rapchieuphim.controller;

import com.rapchieuphim.dao.AccountDAO;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class RegisterController {
    @FXML
    private TextField txtNewUsername;
    @FXML
    private PasswordField txtNewPassword, txtConfirmPassword;
    @FXML
    private Label lblRegisterMsg;

    private final AccountDAO accountDAO = new AccountDAO();

    @FXML
    public void register() {
        String user = txtNewUsername.getText().trim();
        String pass = txtNewPassword.getText();
        String confirm = txtConfirmPassword.getText();

        if (user.isEmpty() || pass.isEmpty() || confirm.isEmpty()) {
            lblRegisterMsg.setText("Vui lòng nhập đầy đủ thông tin!");
        } else if (!pass.equals(confirm)) {
            lblRegisterMsg.setText("Mật khẩu xác nhận không khớp!");
        } else if (accountDAO.findByUsername(user) != null) {
            lblRegisterMsg.setText("Tên đăng nhập đã tồn tại!");
        } else {
            boolean ok = accountDAO.registerUser(user, pass);
            if (ok) {
                lblRegisterMsg.setText("Đăng ký thành công!");
                txtNewUsername.clear();
                txtNewPassword.clear();
                txtConfirmPassword.clear();
            } else {
                lblRegisterMsg.setText("Đăng ký thất bại, thử lại sau!");
            }
        }
    }
}