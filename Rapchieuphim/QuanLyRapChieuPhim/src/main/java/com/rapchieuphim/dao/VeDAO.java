package com.rapchieuphim.dao;

import com.rapchieuphim.model.Ve;
import java.io.*;
import java.util.*;

public class VeDAO {
    private final String FILE = "ve.csv";

    public VeDAO() {
        // Khởi tạo tệp ve.csv nếu chưa tồn tại
        try {
            File file = new File(FILE);
            if (!file.exists()) {
                file.createNewFile(); // Tạo tệp trống
                // Thêm dữ liệu mẫu nếu cần
                try (PrintWriter pw = new PrintWriter(new FileWriter(FILE))) {
                    pw.println("1,1,1,1,50.0"); // idVe, idSuatChieu, idKhachHang, soGhe, giaVe
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Ve> getAll() {
        List<Ve> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] arr = line.split(",", -1);
                if (arr.length == 5) {
                    list.add(new Ve(
                            Integer.parseInt(arr[0]), // idVe
                            Integer.parseInt(arr[1]), // idSuatChieu
                            Integer.parseInt(arr[2]), // idKhachHang
                            Integer.parseInt(arr[3]), // soGhe
                            Double.parseDouble(arr[4]) // giaVe
                    ));
                }
            }
        } catch (IOException e) {
            e.printStackTrace(); // In lỗi để debug
        }
        return list;
    }

    public void saveAll(List<Ve> ves) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE))) {
            for (Ve v : ves) {
                pw.println(v.getIdVe() + "," + v.getIdSuatChieu() + "," + v.getIdKhachHang() + "," + v.getSoGhe() + "," + v.getGiaVe());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}