package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import dao.WorkOrderDAO;
import model.WorkOrder;
import view.AdminDashboard;
import view.TambahWorkOrderDialog;

/**
 * Controller untuk pengelolaan Work Order (Arsitektur MVC).
 * Menghubungkan View (AdminDashboard & TambahWorkOrderDialog) dengan Model (WorkOrder & WorkOrderDAO).
 */
public class WorkOrderController {

    private final AdminDashboard dashboardView;
    private final WorkOrderDAO workOrderDAO;

    public WorkOrderController(AdminDashboard dashboardView) {
        this(dashboardView, new WorkOrderDAO());
    }

    public WorkOrderController(AdminDashboard dashboardView, WorkOrderDAO workOrderDAO) {
        this.dashboardView = dashboardView;
        this.workOrderDAO = workOrderDAO;

        initListeners();
    }

    private void initListeners() {
        dashboardView.addTambahListener(new BukaTambahDialogListener());
        dashboardView.addRefreshListener(e -> refreshData());
    }

    public void refreshData() {
        dashboardView.loadWorkOrderData();
    }

    /**
     * Listener saat tombol "Tambah Work Order" di Dashboard diklik.
     */
    private class BukaTambahDialogListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String nextId = workOrderDAO.generateNextId();
            String nextQr = WorkOrder.generateQrCode(nextId);

            TambahWorkOrderDialog dialog = new TambahWorkOrderDialog(dashboardView, true);
            dialog.setGeneratedData(nextId, nextQr);

            dialog.addSimpanListener(new SimpanWorkOrderListener(dialog));
            dialog.addBatalListener(ev -> dialog.closeDialog());

            dialog.setVisible(true);
        }
    }

    /**
     * Listener saat tombol "Simpan" di dialog tambah diklik.
     */
    private class SimpanWorkOrderListener implements ActionListener {
        private final TambahWorkOrderDialog dialog;

        public SimpanWorkOrderListener(TambahWorkOrderDialog dialog) {
            this.dialog = dialog;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String idWo = dialog.getIdWo();
            String nama = dialog.getNamaWorkOrder();
            String worker = dialog.getWorker();
            String pelanggan = dialog.getPelanggan();
            String noWa = dialog.getNoWa();
            String estimasi = dialog.getEstimasi();
            String qrCode = dialog.getQrCode();

            // Validasi Input
            if (nama.isEmpty() || worker.isEmpty() || pelanggan.isEmpty() || noWa.isEmpty() || estimasi.isEmpty()) {
                dialog.showWarning("Semua kolom wajib diisi!");
                return;
            }

            if (!noWa.matches("^[0-9+]{6,20}$")) {
                dialog.showWarning("Nomor WhatsApp harus berupa angka yang valid (contoh: 08123456789)!");
                return;
            }

            // Validasi koneksi database
            if (config.Koneksi.getConnection() == null) {
                dialog.showError("Koneksi database tidak aktif!\nPastikan MySQL/XAMPP aktif dan konfigurasi database di Koneksi.java sudah sesuai.");
                return;
            }

            // Buat Object Model WorkOrder
            WorkOrder newWo = new WorkOrder(idWo, nama, worker, pelanggan, noWa, estimasi, qrCode);

            // Simpan ke Database melalui DAO
            boolean success = workOrderDAO.insert(newWo);

            if (success) {
                dialog.showSuccess("Work Order (" + idWo + ") berhasil disimpan!");
                dialog.closeDialog();
                dashboardView.loadWorkOrderData();
            } else {
                dialog.showError("Gagal menyimpan data Work Order ke database!\nSilakan periksa kembali data yang dimasukkan.");
            }
        }
    }
}

