package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import config.Koneksi;
import model.Admin;
import view.AdminDashboard;
import view.LoginFrame;

public class LoginController {

    private final LoginFrame view;

    public LoginController(LoginFrame view) {
        this.view = view;
        this.view.addLoginListener(new LoginActionListener());
    }

    private class LoginActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = view.getUsername();
            String password = view.getPassword();

            if (username.isEmpty() || password.isEmpty()) {
                view.showWarningMessage(
                        "Username dan Password tidak boleh kosong!",
                        "Peringatan Validasi"
                );
                return;
            }

            String sql = "SELECT user_id, nama, username, password, role FROM users WHERE username = ? AND password = ?";
            Connection conn = Koneksi.getConnection();

            if (conn == null) {
                view.showErrorMessage(
                        "Gagal terhubung ke database!\nPastikan MySQL/XAMPP aktif dan database 'db_alexander' telah dibuat.",
                        "Koneksi Database Gagal"
                );
                return;
            }

            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, username);
                ps.setString(2, password);

                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        int userId = rs.getInt("user_id");
                        String nama = rs.getString("nama");

                        Admin admin = new Admin(userId, nama, username, password);

                        view.close();
                        AdminDashboard dashboard = new AdminDashboard(admin);
                        dashboard.setVisible(true);
                    } else {
                        view.showErrorMessage(
                                "Username atau Password salah!",
                                "Login Gagal"
                        );
                    }
                }
            } catch (SQLException ex) {
                view.showErrorMessage(
                        "Terjadi kesalahan database:\n" + ex.getMessage(),
                        "Database Error"
                );
            }
        }
    }
}
