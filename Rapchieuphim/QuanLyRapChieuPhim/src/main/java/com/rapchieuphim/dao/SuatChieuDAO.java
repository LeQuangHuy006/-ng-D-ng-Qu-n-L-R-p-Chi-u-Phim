package com.rapchieuphim.dao;

import com.rapchieuphim.model.SuatChieu;
import java.time.LocalDateTime;
import java.util.*;

public class SuatChieuDAO {
    private static final List<SuatChieu> list = Arrays.asList(
            new SuatChieu(1, "Nhà bà nữ", LocalDateTime.of(2023, 3, 9, 8, 0), 102, "Gia đình", 100),
            new SuatChieu(2, "Chị chị em em 2", LocalDateTime.of(2023, 3, 9, 9, 0), 95, "Tâm lý", 90),
            new SuatChieu(3, "Pororo: Cuộc phiêu lưu đến dinh thự rồng", LocalDateTime.of(2023, 3, 9, 9, 30), 80, "Hoạt hình", 70)
    );

    public List<SuatChieu> getAll() {
        return new ArrayList<>(list); // Trả về bản sao để tránh sửa đổi danh sách tĩnh
    }

    public SuatChieu getById(int id) {
        return list.stream()
                .filter(s -> s.getIdSuatChieu() == id) // Sửa lại để khớp với getIdSuatChieu()
                .findFirst()
                .orElse(null);
    }
}