package com.rapchieuphim.model;

public class KhachHang {
    private int idKhach;
    private String tenKhach;
    private String sdt;
    private String email;

    public KhachHang(int idKhach, String tenKhach, String sdt, String email) {
        this.idKhach = idKhach;
        this.tenKhach = tenKhach;
        this.sdt = sdt;
        this.email = email;
    }

    // Getters and Setters
    public int getIdKhach() { return idKhach; }
    public void setIdKhach(int idKhach) { this.idKhach = idKhach; }

    public String getTenKhach() { return tenKhach; }
    public void setTenKhach(String tenKhach) { this.tenKhach = tenKhach; }

    public String getSdt() { return sdt; }
    public void setSdt(String sdt) { this.sdt = sdt; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}