package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import config.Koneksi;
import model.WorkOrder;

public class WorkOrderDAO {

    public WorkOrderDAO() {
        initTable();
    }

    /**
     * Memastikan tabel workOrder otomatis dibuat jika belum ada di database.
     */
    private void initTable() {
        Connection conn = Koneksi.getConnection();
        if (conn == null) {
            return;
        }

        String sqlTable = "CREATE TABLE IF NOT EXISTS `workOrder` ("
                + " `id_wo` VARCHAR(20) NOT NULL PRIMARY KEY,"
                + " `nama_workorder` VARCHAR(150) NOT NULL,"
                + " `worker` VARCHAR(100) NOT NULL,"
                + " `pelanggan` VARCHAR(100) NOT NULL,"
                + " `noWa` VARCHAR(20) NOT NULL,"
                + " `estimasi` VARCHAR(50) NOT NULL,"
                + " `qrCode` VARCHAR(255) NOT NULL,"
                + " `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,"
                + " `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP"
                + ")";

        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sqlTable);
        } catch (SQLException e) {
            System.err.println("[WorkOrderDAO] Init tabel notice: " + e.getMessage());
        }
    }

    /**
     * Mengambil seluruh data work order dari database db_alexander.
     */
    public List<WorkOrder> getAll() {
        List<WorkOrder> list = new ArrayList<>();
        Connection conn = Koneksi.getConnection();
        if (conn == null) {
            return list;
        }

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

    /**
     * Mengambil data Work Order spesifik berdasarkan id_wo.
     */
    public WorkOrder getById(String idWo) {
        Connection conn = Koneksi.getConnection();
        if (conn == null) {
            return null;
        }

        String sql = "SELECT * FROM workOrder WHERE id_wo = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, idWo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String nama = rs.getString("nama_workorder");
                    String worker = rs.getString("worker");
                    String pelanggan = rs.getString("pelanggan");
                    String noWa = getColumnSafely(rs, "noWa", "no_wa");
                    String estimasi = rs.getString("estimasi");
                    String qrCode = getColumnSafely(rs, "qrCode", "qr_code");
                    Timestamp createdAt = rs.getTimestamp("created_at");
                    Timestamp updatedAt = rs.getTimestamp("updated_at");

                    return new WorkOrder(idWo, nama, worker, pelanggan, noWa, estimasi, qrCode, createdAt, updatedAt);
                }
            }
        } catch (SQLException e1) {
            try (PreparedStatement ps2 = conn.prepareStatement("SELECT * FROM work_order WHERE id_wo = ?")) {
                ps2.setString(1, idWo);
                try (ResultSet rs2 = ps2.executeQuery()) {
                    if (rs2.next()) {
                        String nama = rs2.getString("nama_workorder");
                        String worker = rs2.getString("worker");
                        String pelanggan = rs2.getString("pelanggan");
                        String noWa = getColumnSafely(rs2, "noWa", "no_wa");
                        String estimasi = rs2.getString("estimasi");
                        String qrCode = getColumnSafely(rs2, "qrCode", "qr_code");
                        Timestamp createdAt = rs2.getTimestamp("created_at");
                        Timestamp updatedAt = rs2.getTimestamp("updated_at");

                        return new WorkOrder(idWo, nama, worker, pelanggan, noWa, estimasi, qrCode, createdAt, updatedAt);
                    }
                }
            } catch (SQLException e2) {
                System.err.println("[WorkOrderDAO] Gagal mengambil data by id: " + e2.getMessage());
            }
        }
        return null;
    }

    /**
     * Menyimpan data Work Order baru ke dalam database.
     */
    public boolean insert(WorkOrder wo) {
        Connection conn = Koneksi.getConnection();
        if (conn == null) {
            System.err.println("[WorkOrderDAO] Koneksi null saat insert!");
            return false;
        }

        String sql1 = "INSERT INTO workOrder (id_wo, nama_workorder, worker, pelanggan, noWa, estimasi, qrCode) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql1)) {
            ps.setString(1, wo.getIdWo());
            ps.setString(2, wo.getNamaWorkOrder());
            ps.setString(3, wo.getWorker());
            ps.setString(4, wo.getPelanggan());
            ps.setString(5, wo.getNoWa());
            ps.setString(6, wo.getEstimasi());
            ps.setString(7, wo.getQrCode());

            return ps.executeUpdate() > 0;
        } catch (SQLException e1) {
            // Fallback jika tabel menggunakan work_order dengan snake_case kolom
            String sql2 = "INSERT INTO work_order (id_wo, nama_workorder, worker, pelanggan, no_wa, estimasi, qr_code) "
                        + "VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement ps2 = conn.prepareStatement(sql2)) {
                ps2.setString(1, wo.getIdWo());
                ps2.setString(2, wo.getNamaWorkOrder());
                ps2.setString(3, wo.getWorker());
                ps2.setString(4, wo.getPelanggan());
                ps2.setString(5, wo.getNoWa());
                ps2.setString(6, wo.getEstimasi());
                ps2.setString(7, wo.getQrCode());

                return ps2.executeUpdate() > 0;
            } catch (SQLException e2) {
                System.err.println("[WorkOrderDAO] Gagal menyimpan data work order: " + e2.getMessage());
                return false;
            }
        }
    }

    /**
     * Memperbarui data Work Order yang ada berdasarkan id_wo.
     */
    public boolean update(WorkOrder wo) {
        Connection conn = Koneksi.getConnection();
        if (conn == null) {
            System.err.println("[WorkOrderDAO] Koneksi null saat update!");
            return false;
        }

        String sql1 = "UPDATE workOrder SET nama_workorder = ?, worker = ?, pelanggan = ?, noWa = ?, estimasi = ? "
                    + "WHERE id_wo = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql1)) {
            ps.setString(1, wo.getNamaWorkOrder());
            ps.setString(2, wo.getWorker());
            ps.setString(3, wo.getPelanggan());
            ps.setString(4, wo.getNoWa());
            ps.setString(5, wo.getEstimasi());
            ps.setString(6, wo.getIdWo());

            return ps.executeUpdate() > 0;
        } catch (SQLException e1) {
            // Fallback jika tabel bernama work_order dengan snake_case no_wa
            String sql2 = "UPDATE work_order SET nama_workorder = ?, worker = ?, pelanggan = ?, no_wa = ?, estimasi = ? "
                        + "WHERE id_wo = ?";
            try (PreparedStatement ps2 = conn.prepareStatement(sql2)) {
                ps2.setString(1, wo.getNamaWorkOrder());
                ps2.setString(2, wo.getWorker());
                ps2.setString(3, wo.getPelanggan());
                ps2.setString(4, wo.getNoWa());
                ps2.setString(5, wo.getEstimasi());
                ps2.setString(6, wo.getIdWo());

                return ps2.executeUpdate() > 0;
            } catch (SQLException e2) {
                System.err.println("[WorkOrderDAO] Gagal memperbarui data work order: " + e2.getMessage());
                return false;
            }
        }
    }

    /**
     * Menghapus data Work Order berdasarkan id_wo.
     */
    public boolean delete(String idWo) {
        Connection conn = Koneksi.getConnection();
        if (conn == null) {
            System.err.println("[WorkOrderDAO] Koneksi null saat delete!");
            return false;
        }

        String sql1 = "DELETE FROM workOrder WHERE id_wo = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql1)) {
            ps.setString(1, idWo);
            return ps.executeUpdate() > 0;
        } catch (SQLException e1) {
            // Fallback jika tabel menggunakan work_order
            String sql2 = "DELETE FROM work_order WHERE id_wo = ?";
            try (PreparedStatement ps2 = conn.prepareStatement(sql2)) {
                ps2.setString(1, idWo);
                return ps2.executeUpdate() > 0;
            } catch (SQLException e2) {
                System.err.println("[WorkOrderDAO] Gagal menghapus data work order: " + e2.getMessage());
                return false;
            }
        }
    }

    /**
     * Mengambil ID urutan berikutnya (contoh: WO-0000004).
     */
    public String generateNextId() {
        Connection conn = Koneksi.getConnection();
        if (conn == null) {
            return WorkOrder.formatId(1);
        }

        String sql1 = "SELECT id_wo FROM workOrder ORDER BY id_wo DESC LIMIT 1";

        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = null;
            try {
                rs = stmt.executeQuery(sql1);
            } catch (SQLException e) {
                rs = stmt.executeQuery("SELECT id_wo FROM work_order ORDER BY id_wo DESC LIMIT 1");
            }

            if (rs != null) {
                if (rs.next()) {
                    String lastId = rs.getString("id_wo");
                    rs.close();
                    if (lastId != null && lastId.startsWith("WO-")) {
                        String numPart = lastId.substring(3);
                        try {
                            int nextNumber = Integer.parseInt(numPart) + 1;
                            return WorkOrder.formatId(nextNumber);
                        } catch (NumberFormatException ignored) {
                        }
                    }
                } else {
                    rs.close();
                }
            }
        } catch (SQLException e) {
            System.err.println("[WorkOrderDAO] Gagal mengenerate ID baru: " + e.getMessage());
        }

        return WorkOrder.formatId(1);
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
