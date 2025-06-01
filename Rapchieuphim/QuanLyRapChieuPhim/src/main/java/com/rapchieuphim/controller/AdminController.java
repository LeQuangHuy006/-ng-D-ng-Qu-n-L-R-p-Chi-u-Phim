package com.rapchieuphim.controller;

import com.rapchieuphim.dao.SuatChieuDAO;
import com.rapchieuphim.dao.VeDAO;
import com.rapchieuphim.model.SuatChieu;
import com.rapchieuphim.model.Ve;
import com.rapchieuphim.model.VeSuatChieuDTO;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.StringConverter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AdminController {
    @FXML private TableView<VeSuatChieuDTO> tableVe;
    @FXML private TableColumn<VeSuatChieuDTO, String> colMaVe;
    @FXML private TableColumn<VeSuatChieuDTO, String> colTenPhim;
    @FXML private TableColumn<VeSuatChieuDTO, String> colThoiGian;
    @FXML private TableColumn<VeSuatChieuDTO, String> colGiaVe;
    @FXML private TableColumn<VeSuatChieuDTO, String> colThoiLuong;
    @FXML private TableColumn<VeSuatChieuDTO, String> colTheLoai;
    @FXML private TableColumn<VeSuatChieuDTO, String> colSoLuong;

    @FXML private TextField txtMaVe;
    @FXML private ComboBox<SuatChieu> cbSuatChieu;
    @FXML private TextField txtIdKhachHang;
    @FXML private TextField txtSoGhe;
    @FXML private TextField txtGiaVe;
    @FXML private TextField txtTenPhim;
    @FXML private TextField txtThoiGian;
    @FXML private TextField txtThoiLuong;
    @FXML private TextField txtTheLoai;
    @FXML private TextField txtSoLuong;

    @FXML private DatePicker dateFrom;
    @FXML private DatePicker dateTo;
    @FXML private Button btnThem;
    @FXML private Button btnSua;
    @FXML private Button btnXoa;

    private final VeDAO veDAO = new VeDAO();
    private final SuatChieuDAO suatChieuDAO = new SuatChieuDAO();
    private final ObservableList<Ve> veList = FXCollections.observableArrayList();
    private final ObservableList<VeSuatChieuDTO> tableData = FXCollections.observableArrayList();
    private final ObservableList<SuatChieu> suatChieuList = FXCollections.observableArrayList();
    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    @FXML
    public void initialize() {
        colMaVe.setCellValueFactory(cell -> new SimpleStringProperty(String.valueOf(cell.getValue().getIdVe())));
        colTenPhim.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getTenPhim()));
        colThoiGian.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getThoiGian()));
        colGiaVe.setCellValueFactory(cell -> new SimpleStringProperty(String.valueOf(cell.getValue().getGiaVe())));
        colThoiLuong.setCellValueFactory(cell -> new SimpleStringProperty(String.valueOf(cell.getValue().getThoiLuong())));
        colTheLoai.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getTheLoai()));
        colSoLuong.setCellValueFactory(cell -> new SimpleStringProperty(String.valueOf(cell.getValue().getSoLuong())));

        veList.setAll(veDAO.getAll());
        updateTableData();
        tableVe.setItems(tableData);

        // Load suất chiếu vào ComboBox
        suatChieuList.setAll(suatChieuDAO.getAll());
        cbSuatChieu.setItems(suatChieuList);
        cbSuatChieu.setConverter(new StringConverter<SuatChieu>() {
            @Override
            public String toString(SuatChieu sc) {
                if (sc == null) return "";
                return sc.getTenPhim() + " - " + dtf.format(sc.getThoiGian());
            }
            @Override
            public SuatChieu fromString(String s) { return null; }
        });
        cbSuatChieu.valueProperty().addListener((obs, old, sel) -> {
            if (sel != null) {
                txtTenPhim.setText(sel.getTenPhim());
                txtThoiGian.setText(dtf.format(sel.getThoiGian()));
                txtThoiLuong.setText(String.valueOf(sel.getThoiLuong()));
                txtTheLoai.setText(sel.getTheLoai());
                txtSoLuong.setText(String.valueOf(sel.getSoLuong()));
            } else {
                txtTenPhim.clear();
                txtThoiGian.clear();
                txtThoiLuong.clear();
                txtTheLoai.clear();
                txtSoLuong.clear();
            }
        });

        tableVe.getSelectionModel().selectedItemProperty().addListener((obs, old, sel) -> {
            if (sel != null) {
                txtMaVe.setText(String.valueOf(sel.getIdVe()));
                // Tìm suất chiếu phù hợp để set vào ComboBox
                SuatChieu suat = suatChieuList.stream()
                        .filter(s -> s.getTenPhim().equals(sel.getTenPhim()) && dtf.format(s.getThoiGian()).equals(sel.getThoiGian()))
                        .findFirst().orElse(null);
                cbSuatChieu.setValue(suat);
                txtGiaVe.setText(String.valueOf(sel.getGiaVe()));
            }
        });

        dateFrom.valueProperty().addListener((obs, old, val) -> filterByDate());
        dateTo.valueProperty().addListener((obs, old, val) -> filterByDate());
    }

    private void updateTableData() {
        tableData.clear();
        for (Ve v : veList) {
            SuatChieu s = suatChieuDAO.getById(v.getIdSuatChieu());
            if (s != null) {
                tableData.add(new VeSuatChieuDTO(
                        v.getIdVe(),
                        s.getTenPhim(),
                        dtf.format(s.getThoiGian()),
                        v.getGiaVe(),
                        s.getThoiLuong(),
                        s.getTheLoai(),
                        s.getSoLuong()
                ));
            }
        }
    }

    private void filterByDate() {
        LocalDate from = dateFrom.getValue();
        LocalDate to = dateTo.getValue();
        tableData.clear();
        for (Ve v : veList) {
            SuatChieu s = suatChieuDAO.getById(v.getIdSuatChieu());
            if (s != null) {
                LocalDate date = s.getThoiGian().toLocalDate();
                boolean afterFrom = (from == null) || !date.isBefore(from);
                boolean beforeTo = (to == null) || !date.isAfter(to);
                if (afterFrom && beforeTo) {
                    tableData.add(new VeSuatChieuDTO(
                            v.getIdVe(),
                            s.getTenPhim(),
                            dtf.format(s.getThoiGian()),
                            v.getGiaVe(),
                            s.getThoiLuong(),
                            s.getTheLoai(),
                            s.getSoLuong()
                    ));
                }
            }
        }
    }

    @FXML
    public void handleThem() {
        try {
            int idVe = Integer.parseInt(txtMaVe.getText());
            SuatChieu suatChieu = cbSuatChieu.getValue();
            if (suatChieu == null) throw new Exception("Chưa chọn suất chiếu!");
            int idSuatChieu = suatChieu.getIdSuatChieu();
            int idKhachHang = Integer.parseInt(txtIdKhachHang.getText());
            int soGhe = Integer.parseInt(txtSoGhe.getText());
            double giaVe = Double.parseDouble(txtGiaVe.getText());
            Ve v = new Ve(idVe, idSuatChieu, idKhachHang, soGhe, giaVe);
            veList.add(v);
            veDAO.saveAll(veList);
            updateTableData();
            tableVe.refresh();
            clearForm();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleSua() {
        VeSuatChieuDTO sel = tableVe.getSelectionModel().getSelectedItem();
        if (sel != null) {
            try {
                int idVe = Integer.parseInt(txtMaVe.getText());
                SuatChieu suatChieu = cbSuatChieu.getValue();
                if (suatChieu == null) throw new Exception("Chưa chọn suất chiếu!");
                int idSuatChieu = suatChieu.getIdSuatChieu();
                int idKhachHang = Integer.parseInt(txtIdKhachHang.getText());
                int soGhe = Integer.parseInt(txtSoGhe.getText());
                double giaVe = Double.parseDouble(txtGiaVe.getText());
                for (int i = 0; i < veList.size(); i++) {
                    if (veList.get(i).getIdVe() == sel.getIdVe()) {
                        veList.set(i, new Ve(idVe, idSuatChieu, idKhachHang, soGhe, giaVe));
                        break;
                    }
                }
                veDAO.saveAll(veList);
                updateTableData();
                tableVe.refresh();
                clearForm();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void handleXoa() {
        VeSuatChieuDTO sel = tableVe.getSelectionModel().getSelectedItem();
        if (sel != null) {
            veList.removeIf(v -> v.getIdVe() == sel.getIdVe());
            veDAO.saveAll(veList);
            updateTableData();
            tableVe.refresh();
            clearForm();
        }
    }

    private void clearForm() {
        txtMaVe.clear();
        cbSuatChieu.setValue(null);
        txtIdKhachHang.clear();
        txtSoGhe.clear();
        txtGiaVe.clear();
        txtTenPhim.clear();
        txtThoiGian.clear();
        txtThoiLuong.clear();
        txtTheLoai.clear();
        txtSoLuong.clear();
    }
}