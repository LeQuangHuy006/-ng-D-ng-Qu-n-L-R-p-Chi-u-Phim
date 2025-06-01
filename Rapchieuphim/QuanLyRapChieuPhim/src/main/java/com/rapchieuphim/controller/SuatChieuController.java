package com.rapchieuphim.controller;

import com.rapchieuphim.dao.PhimDAO;
import com.rapchieuphim.model.Phim;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.List;

public class SuatChieuController {
    @FXML private ComboBox<Phim> cbPhim;
    @FXML private TextField txtNgayGio;
    @FXML private TextField txtPhongChieu;

    private final PhimDAO phimDAO = new PhimDAO();

    @FXML
    public void initialize() {
        List<Phim> allPhim = phimDAO.getAllPhim();
        cbPhim.setItems(FXCollections.observableArrayList(allPhim));
    }

    @FXML
    public void handleThemSuatChieu() {
        Phim phim = cbPhim.getValue();
        String ngayGio = txtNgayGio.getText();
        String phongChieu = txtPhongChieu.getText();
        if (phim == null || ngayGio.isEmpty() || phongChieu.isEmpty()) {
            showAlert("Vui lòng nhập/chọn đầy đủ thông tin!");
            return;
        }
        // TODO: Thêm logic lưu suất chiếu vào CSDL ở đây
        showAlert("Đã thêm suất chiếu cho phim: " + phim.getTenPhim());
    }

    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, msg);
        alert.showAndWait();
    }
}