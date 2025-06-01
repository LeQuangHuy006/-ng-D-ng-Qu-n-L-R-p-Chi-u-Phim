package com.rapchieuphim.controller;

import com.rapchieuphim.model.Phim;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class PhimManagerController {
    @FXML private TableView<Phim> tablePhim;
    @FXML private TableColumn<Phim, String> colIdPhim;
    @FXML private TableColumn<Phim, String> colTenPhim;
    @FXML private TableColumn<Phim, String> colTheLoai;
    @FXML private TableColumn<Phim, String> colThoiLuong;
    @FXML private TableColumn<Phim, String> colMoTa;

    @FXML private TextField txtIdPhim;
    @FXML private TextField txtTenPhim;
    @FXML private TextField txtTheLoai;
    @FXML private TextField txtThoiLuong;
    @FXML private TextField txtMoTa;

    private final ObservableList<Phim> phimList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        colIdPhim.setCellValueFactory(cell -> new SimpleStringProperty(String.valueOf(cell.getValue().getIdPhim())));
        colTenPhim.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getTenPhim()));
        colTheLoai.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getTheLoai()));
        colThoiLuong.setCellValueFactory(cell -> new SimpleStringProperty(String.valueOf(cell.getValue().getThoiLuong())));
        colMoTa.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getMoTa()));

        tablePhim.setItems(phimList);

        tablePhim.getSelectionModel().selectedItemProperty().addListener((obs, old, sel) -> {
            if (sel != null) {
                txtIdPhim.setText(String.valueOf(sel.getIdPhim()));
                txtTenPhim.setText(sel.getTenPhim());
                txtTheLoai.setText(sel.getTheLoai());
                txtThoiLuong.setText(String.valueOf(sel.getThoiLuong()));
                txtMoTa.setText(sel.getMoTa());
            }
        });
    }

    @FXML
    public void handleThem() {
        try {
            if (txtIdPhim.getText().isEmpty() || txtTenPhim.getText().isEmpty() ||
                    txtTheLoai.getText().isEmpty() || txtThoiLuong.getText().isEmpty()) {
                showAlert("Vui lòng nhập đầy đủ thông tin!");
                return;
            }
            int idPhim = Integer.parseInt(txtIdPhim.getText());
            String tenPhim = txtTenPhim.getText();
            String theLoai = txtTheLoai.getText();
            int thoiLuong = Integer.parseInt(txtThoiLuong.getText());
            String moTa = txtMoTa.getText();
            Phim phim = new Phim(idPhim, tenPhim, theLoai, thoiLuong, moTa);
            phimList.add(phim);
            clearForm();
        } catch (Exception e) {
            showAlert("Lỗi dữ liệu: " + e.getMessage());
        }
    }

    @FXML
    public void handleSua() {
        Phim sel = tablePhim.getSelectionModel().getSelectedItem();
        if (sel != null) {
            try {
                int idPhim = Integer.parseInt(txtIdPhim.getText());
                String tenPhim = txtTenPhim.getText();
                String theLoai = txtTheLoai.getText();
                int thoiLuong = Integer.parseInt(txtThoiLuong.getText());
                String moTa = txtMoTa.getText();
                sel.setIdPhim(idPhim);
                sel.setTenPhim(tenPhim);
                sel.setTheLoai(theLoai);
                sel.setThoiLuong(thoiLuong);
                sel.setMoTa(moTa);
                tablePhim.refresh();
                clearForm();
            } catch (Exception e) {
                showAlert("Lỗi dữ liệu: " + e.getMessage());
            }
        }
    }

    @FXML
    public void handleXoa() {
        Phim sel = tablePhim.getSelectionModel().getSelectedItem();
        if (sel != null) {
            phimList.remove(sel);
            clearForm();
        }
    }

    private void clearForm() {
        txtIdPhim.clear();
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