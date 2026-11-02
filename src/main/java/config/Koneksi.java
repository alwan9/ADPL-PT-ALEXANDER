package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Koneksi {

    private static final String HOST = "localhost";
    private static final String PORT = "3306";
    private static final String DATABASE = "db_alexander";
    private static final String USER = "root";
    private static final String PASSWORD = "Sapi17$";

    private static final String URL = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE + "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Jakarta";

    private static Connection connection = null;

    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("[Koneksi] Berhasil terhubung ke database: " + DATABASE);
            }
        } catch (ClassNotFoundException e) {
            System.err.println("[Koneksi] Error: Driver MySQL JDBC tidak ditemukan!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("[Koneksi] Error: Gagal terhubung ke database!");
            System.err.println("Pesan Error: " + e.getMessage());
            System.err.println("Pastikan MySQL/XAMPP sudah aktif dan database '" + DATABASE + "' sudah dibuat.");
        }
        return connection;
    }

    public static Connection getKoneksi() {
        return getConnection();
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
                System.out.println("[Koneksi] Koneksi ke database ditutup.");
            } catch (SQLException e) {
                System.err.println("[Koneksi] Gagal menutup koneksi: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Mencoba menghubungkan ke database MySQL (" + DATABASE + ") via JDBC...");
        Connection conn = getConnection();
        if (conn != null) {
            System.out.println(">> STATUS: KONEKSI BERHASIL! <<");
        } else {
            System.out.println(">> STATUS: KONEKSI GAGAL! <<");
        }
    }
}
