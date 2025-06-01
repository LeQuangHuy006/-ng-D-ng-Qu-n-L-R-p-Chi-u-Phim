package com.rapchieuphim.model;

import java.time.LocalDateTime;

public class LichChieu {
    private int id;
    private Phim phim;
    private LocalDateTime thoiGianChieu;
    private String phongChieu;

    public LichChieu(int id, Phim phim, LocalDateTime thoiGianChieu, String phongChieu) {
        this.id = id;
        this.phim = phim;
        this.thoiGianChieu = thoiGianChieu;
        this.phongChieu = phongChieu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Phim getPhim() {
        return phim;
    }

    public void setPhim(Phim phim) {
        this.phim = phim;
    }

    public LocalDateTime getThoiGianChieu() {
        return thoiGianChieu;
    }

    public void setThoiGianChieu(LocalDateTime thoiGianChieu) {
        this.thoiGianChieu = thoiGianChieu;
    }

    public String getPhongChieu() {
        return phongChieu;
    }

    public void setPhongChieu(String phongChieu) {
        this.phongChieu = phongChieu;
    }

    @Override
    public String toString() {
        return phim.getTenPhim() + " - " + thoiGianChieu + " - Phòng: " + phongChieu;
    }
}