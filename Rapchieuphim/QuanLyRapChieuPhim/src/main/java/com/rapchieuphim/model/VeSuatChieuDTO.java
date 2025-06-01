package com.rapchieuphim.model;

public class VeSuatChieuDTO {
    private int idVe;
    private String tenPhim;
    private String thoiGian;
    private double giaVe;
    private int thoiLuong;
    private String theLoai;
    private int soLuong;

    public VeSuatChieuDTO(int idVe, String tenPhim, String thoiGian, double giaVe, int thoiLuong, String theLoai, int soLuong) {
        this.idVe = idVe;
        this.tenPhim = tenPhim;
        this.thoiGian = thoiGian;
        this.giaVe = giaVe;
        this.thoiLuong = thoiLuong;
        this.theLoai = theLoai;
        this.soLuong = soLuong;
    }

    public int getIdVe() { return idVe; }
    public String getTenPhim() { return tenPhim; }
    public String getThoiGian() { return thoiGian; }
    public double getGiaVe() { return giaVe; }
    public int getThoiLuong() { return thoiLuong; }
    public String getTheLoai() { return theLoai; }
    public int getSoLuong() { return soLuong; }
}