package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import config.Koneksi;
import model.WorkOrder;

public class WorkOrderDAO {

    /**
     * Mengambil seluruh data work order dari database db_alexander.
     * Fokus untuk pembacaan tampilan (view).
     */
    public List<WorkOrder> getAll() {
        List<WorkOrder> list = new ArrayList<>();
        Connection conn = Koneksi.getConnection();
        if (conn == null) {
            return list;
        }

        // Coba query dari tabel workOrder terlebih dahulu
        String sql = "SELECT * FROM workOrder ORDER BY id_wo ASC";

        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = null;
            try {
                rs = stmt.executeQuery(sql);
            } catch (SQLException e) {
                // Fallback jika tabel bernilai work_order
                rs = stmt.executeQuery("SELECT * FROM work_order ORDER BY id_wo ASC");
            }

            if (rs != null) {
                while (rs.next()) {
                    String idWo = rs.getString("id_wo");
                    String nama = rs.getString("nama_workorder");
                    String worker = rs.getString("worker");
                    String pelanggan = rs.getString("pelanggan");
                    String noWa = getColumnSafely(rs, "noWa", "no_wa");
                    String estimasi = rs.getString("estimasi");
                    String qrCode = getColumnSafely(rs, "qrCode", "qr_code");
                    Timestamp createdAt = rs.getTimestamp("created_at");
                    Timestamp updatedAt = rs.getTimestamp("updated_at");

                    WorkOrder wo = new WorkOrder(idWo, nama, worker, pelanggan, noWa, estimasi, qrCode, createdAt, updatedAt);
                    list.add(wo);
                }
                rs.close();
            }
        } catch (SQLException e) {
            System.err.println("[WorkOrderDAO] Gagal mengambil data work order: " + e.getMessage());
        }

        return list;
    }

    private String getColumnSafely(ResultSet rs, String col1, String col2) {
        try {
            String val = rs.getString(col1);
            if (val != null) return val;
        } catch (SQLException ignored) {}

        try {
            return rs.getString(col2);
        } catch (SQLException ignored) {
            return "";
        }
    }
}
