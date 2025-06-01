package com.rapchieuphim.dao;

import com.rapchieuphim.model.Phim;
import com.rapchieuphim.utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PhimDAO {
    public List<Phim> getAllPhim() {
        List<Phim> list = new ArrayList<>();
        String sql = "SELECT * FROM phim";
        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Phim phim = new Phim(
                        rs.getInt("id_phim"),
                        rs.getString("ten_phim"),
                        rs.getString("the_loai"),
                        rs.getInt("thoi_luong"),
                        rs.getString("mo_ta")
                );
                list.add(phim);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean addPhim(Phim phim) {
        String sql = "INSERT INTO phim (ten_phim, the_loai, thoi_luong, mo_ta) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, phim.getTenPhim());
            ps.setString(2, phim.getTheLoai());
            ps.setInt(3, phim.getThoiLuong());
            ps.setString(4, phim.getMoTa());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updatePhim(Phim phim) {
        String sql = "UPDATE phim SET ten_phim=?, the_loai=?, thoi_luong=?, mo_ta=? WHERE id_phim=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, phim.getTenPhim());
            ps.setString(2, phim.getTheLoai());
            ps.setInt(3, phim.getThoiLuong());
            ps.setString(4, phim.getMoTa());
            ps.setInt(5, phim.getIdPhim());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deletePhim(int idPhim) {
        String sql = "DELETE FROM phim WHERE id_phim=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idPhim);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}