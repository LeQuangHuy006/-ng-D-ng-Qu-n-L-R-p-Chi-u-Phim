package com.rapchieuphim.model;

public class Phim {
    private int idPhim;
    private String tenPhim;
    private String theLoai;
    private int thoiLuong;
    private String moTa;

    public Phim(int idPhim, String tenPhim, String theLoai, int thoiLuong, String moTa) {
        this.idPhim = idPhim;
        this.tenPhim = tenPhim;
        this.theLoai = theLoai;
        this.thoiLuong = thoiLuong;
        this.moTa = moTa;
    }

    public int getIdPhim() { return idPhim; }
    public void setIdPhim(int idPhim) { this.idPhim = idPhim; }
    public String getTenPhim() { return tenPhim; }
    public void setTenPhim(String tenPhim) { this.tenPhim = tenPhim; }
    public String getTheLoai() { return theLoai; }
    public void setTheLoai(String theLoai) { this.theLoai = theLoai; }
    public int getThoiLuong() { return thoiLuong; }
    public void setThoiLuong(int thoiLuong) { this.thoiLuong = thoiLuong; }
    public String getMoTa() { return moTa; }
    public void setMoTa(String moTa) { this.moTa = moTa; }

    @Override
    public String toString() {
        return tenPhim;
    }
}