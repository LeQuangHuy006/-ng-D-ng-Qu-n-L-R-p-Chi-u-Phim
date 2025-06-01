package com.rapchieuphim.model;

import java.time.LocalDateTime;

public class SuatChieu {
    private int idSuatChieu;
    private String tenPhim;
    private LocalDateTime thoiGian;
    private int thoiLuong;
    private String theLoai;
    private int soLuong;

    public SuatChieu(int idSuatChieu, String tenPhim, LocalDateTime thoiGian, int thoiLuong, String theLoai, int soLuong) {
        this.idSuatChieu = idSuatChieu;
        this.tenPhim = tenPhim;
        this.thoiGian = thoiGian;
        this.thoiLuong = thoiLuong;
        this.theLoai = theLoai;
        this.soLuong = soLuong;
    }

    public int getIdSuatChieu() { return idSuatChieu; }
    public String getTenPhim() { return tenPhim; }
    public LocalDateTime getThoiGian() { return thoiGian; }
    public int getThoiLuong() { return thoiLuong; }
    public String getTheLoai() { return theLoai; }
    public int getSoLuong() { return soLuong; }
}