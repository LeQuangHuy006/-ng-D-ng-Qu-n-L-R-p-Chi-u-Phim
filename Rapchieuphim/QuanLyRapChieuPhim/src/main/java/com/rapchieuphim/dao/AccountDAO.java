package com.rapchieuphim.dao;

import com.rapchieuphim.model.Account;
import java.io.*;
import java.util.*;

public class AccountDAO {
    private final String FILE_PATH = "accounts.txt";

    public AccountDAO() {
        // Khởi tạo file và tài khoản admin mặc định nếu chưa có
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                FileWriter fw = new FileWriter(file);
                fw.write("admin,12345,admin\n"); // username,password,role (sửa mật khẩu admin ở đây)
                fw.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Đăng ký tài khoản mới (chỉ dành cho user)
    public boolean registerUser(String username, String password) {
        if (findByUsername(username) != null) return false; // Đã có tài khoản
        try (FileWriter fw = new FileWriter(FILE_PATH, true)) {
            fw.write(username + "," + password + ",user\n");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Kiểm tra đăng nhập
    public Account login(String username, String password, String role) {
        List<Account> accounts = getAllAccounts();
        for (Account acc : accounts) {
            if (acc.getUsername().equals(username) &&
                    acc.getPassword().equals(password) &&
                    acc.getRole().equals(role)) {
                return acc;
            }
        }
        return null;
    }

    // Tìm tài khoản theo username
    public Account findByUsername(String username) {
        List<Account> accounts = getAllAccounts();
        for (Account acc : accounts) {
            if (acc.getUsername().equals(username)) return acc;
        }
        return null;
    }

    // Lấy tất cả account
    public List<Account> getAllAccounts() {
        List<Account> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] arr = line.split(",");
                if (arr.length == 3)
                    list.add(new Account(arr[0], arr[1], arr[2]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}