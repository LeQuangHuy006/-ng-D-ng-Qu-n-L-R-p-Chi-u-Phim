package com.rapchieuphim.model;

public class PhongChieu {
    private int idPhong;
    private String tenPhong;
    private int sucChua;
    private String loaiPhong;

    public PhongChieu(int idPhong, String tenPhong, int sucChua, String loaiPhong) {
        this.idPhong = idPhong;
        this.tenPhong = tenPhong;
        this.sucChua = sucChua;
        this.loaiPhong = loaiPhong;
    }

    // Getters and Setters
    public int getIdPhong() { return idPhong; }
    public void setIdPhong(int idPhong) { this.idPhong = idPhong; }

    public String getTenPhong() { return tenPhong; }
    public void setTenPhong(String tenPhong) { this.tenPhong = tenPhong; }

    public int getSucChua() { return sucChua; }
    public void setSucChua(int sucChua) { this.sucChua = sucChua; }

    public String getLoaiPhong() { return loaiPhong; }
    public void setLoaiPhong(String loaiPhong) { this.loaiPhong = loaiPhong; }
}