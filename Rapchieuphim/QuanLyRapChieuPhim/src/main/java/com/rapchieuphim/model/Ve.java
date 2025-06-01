package com.rapchieuphim.model;

public class Ve {
    private int idVe;
    private int idSuatChieu;
    private int idKhachHang;
    private int soGhe;
    private double giaVe;

    public Ve(int idVe, int idSuatChieu, int idKhachHang, int soGhe, double giaVe) {
        this.idVe = idVe;
        this.idSuatChieu = idSuatChieu;
        this.idKhachHang = idKhachHang;
        this.soGhe = soGhe;
        this.giaVe = giaVe;
    }

    public int getIdVe() { return idVe; }
    public int getIdSuatChieu() { return idSuatChieu; }
    public int getIdKhachHang() { return idKhachHang; }
    public int getSoGhe() { return soGhe; }
    public double getGiaVe() { return giaVe; }
}