package com.rapchieuphim.controller;

import com.rapchieuphim.model.Phim;
import com.rapchieuphim.dao.PhimDAO;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class PhimController {
    @FXML private TableView<Phim> tablePhim;
    @FXML private TableColumn<Phim, String> colIdPhim;
    @FXML private TableColumn<Phim, String> colTenPhim;
    @FXML private TableColumn<Phim, String> colTheLoai;
    @FXML private TableColumn<Phim, String> colThoiLuong;
    @FXML private TableColumn<Phim, String> colMoTa;

    @FXML private TextField txtTenPhim;
    @FXML private TextField txtTheLoai;
    @FXML private TextField txtThoiLuong;
    @FXML private TextField txtMoTa;

    private final ObservableList<Phim> phimList = FXCollections.observableArrayList();
    private final PhimDAO phimDAO = new PhimDAO();

    @FXML
    public void initialize() {
        colIdPhim.setCellValueFactory(cell -> new SimpleStringProperty(String.valueOf(cell.getValue().getIdPhim())));
        colTenPhim.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getTenPhim()));
        colTheLoai.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getTheLoai()));
        colThoiLuong.setCellValueFactory(cell -> new SimpleStringProperty(String.valueOf(cell.getValue().getThoiLuong())));
        colMoTa.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getMoTa()));

        reloadPhim();
        tablePhim.setItems(phimList);

        tablePhim.getSelectionModel().selectedItemProperty().addListener((obs, old, sel) -> {
            if (sel != null) {
                txtTenPhim.setText(sel.getTenPhim());
                txtTheLoai.setText(sel.getTheLoai());
                txtThoiLuong.setText(String.valueOf(sel.getThoiLuong()));
                txtMoTa.setText(sel.getMoTa());
            }
        });
    }

    public void reloadPhim() {
        phimList.setAll(phimDAO.getAllPhim());
    }

    @FXML
    public void handleThem() {
        try {
            String tenPhim = txtTenPhim.getText();
            String theLoai = txtTheLoai.getText();
            String thoiLuongStr = txtThoiLuong.getText();
            String moTa = txtMoTa.getText();
            if (tenPhim.isEmpty() || theLoai.isEmpty() || thoiLuongStr.isEmpty()) {
                showAlert("Vui lòng nhập đủ thông tin tên phim, thể loại, thời lượng!");
                return;
            }
            int thoiLuong = Integer.parseInt(thoiLuongStr);
            Phim phim = new Phim(0, tenPhim, theLoai, thoiLuong, moTa);
            if (phimDAO.addPhim(phim)) {
                reloadPhim();
                clearForm();
            } else {
                showAlert("Thêm phim thất bại!");
            }
        } catch (Exception e) {
            showAlert("Lỗi: " + e.getMessage());
        }
    }

    @FXML
    public void handleSua() {
        Phim sel = tablePhim.getSelectionModel().getSelectedItem();
        if (sel != null) {
            try {
                String tenPhim = txtTenPhim.getText();
                String theLoai = txtTheLoai.getText();
                String thoiLuongStr = txtThoiLuong.getText();
                String moTa = txtMoTa.getText();
                if (tenPhim.isEmpty() || theLoai.isEmpty() || thoiLuongStr.isEmpty()) {
                    showAlert("Vui lòng nhập đủ thông tin tên phim, thể loại, thời lượng!");
                    return;
                }
                int thoiLuong = Integer.parseInt(thoiLuongStr);
                Phim phim = new Phim(sel.getIdPhim(), tenPhim, theLoai, thoiLuong, moTa);
                if (phimDAO.updatePhim(phim)) {
                    reloadPhim();
                    clearForm();
                } else {
                    showAlert("Cập nhật phim thất bại!");
                }
            } catch (Exception e) {
                showAlert("Lỗi: " + e.getMessage());
            }
        }
    }

    @FXML
    public void handleXoa() {
        Phim sel = tablePhim.getSelectionModel().getSelectedItem();
        if (sel != null) {
            if (phimDAO.deletePhim(sel.getIdPhim())) {
                reloadPhim();
                clearForm();
            } else {
                showAlert("Xóa phim thất bại!");
            }
        }
    }

    private void clearForm() {
        txtTenPhim.clear();
        txtTheLoai.clear();
        txtThoiLuong.clear();
        txtMoTa.clear();
    }

    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR, msg);
        alert.showAndWait();
    }
}