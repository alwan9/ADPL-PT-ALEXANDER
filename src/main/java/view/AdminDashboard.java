package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;

import org.kordamp.ikonli.fontawesome5.FontAwesomeBrands;
import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid;
import org.kordamp.ikonli.swing.FontIcon;

import controller.LoginController;
import dao.WorkOrderDAO;
import model.Admin;
import model.WorkOrder;

public class AdminDashboard extends javax.swing.JFrame {

    private Admin admin;
    private final WorkOrderDAO workOrderDAO = new WorkOrderDAO();

    // Work Order View UI components
    private JTable tableWorkOrder;
    private DefaultTableModel tableModel;
    private TableRowSorter<DefaultTableModel> rowSorter;
    private JTextField txtSearch;
    private JLabel lblTotalData;
    private JButton btnRefresh;
    private JButton btnTambah;

    public AdminDashboard() {
        this(new Admin(1, "Administrator", "admin", "admin123"));
    }

    public AdminDashboard(Admin admin) {
        this.admin = admin;
        initComponents();
        setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);

        applyIkonliIcons();

        if (admin != null) {
            lblUser.setText("Halo, " + admin.getNama() + " (" + admin.getRole() + ")");
        }

        setupWorkOrderUI();
        new controller.WorkOrderController(this, workOrderDAO);
        loadWorkOrderData();
    }

    private void applyIkonliIcons() {
        // Ikonli pada Header
        lblBrand.setIcon(FontIcon.of(FontAwesomeSolid.BUILDING, 20, new Color(52, 152, 219)));
        lblBrand.setIconTextGap(10);

        lblUser.setIcon(FontIcon.of(FontAwesomeSolid.USER_CIRCLE, 16, new Color(236, 240, 241)));
        lblUser.setIconTextGap(8);

        btnLogout.setIcon(FontIcon.of(FontAwesomeSolid.SIGN_OUT_ALT, 14, Color.WHITE));
        btnLogout.setIconTextGap(8);
        btnLogout.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelHeader = new javax.swing.JPanel();
        lblBrand = new javax.swing.JLabel();
        panelUser = new javax.swing.JPanel();
        lblUser = new javax.swing.JLabel();
        btnLogout = new javax.swing.JButton();
        panelContent = new javax.swing.JPanel();
        cardPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("PT Alexander - Admin Dashboard");

        panelHeader.setBackground(new java.awt.Color(44, 62, 80));
        panelHeader.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 25, 15, 25));
        panelHeader.setPreferredSize(new java.awt.Dimension(0, 70));
        panelHeader.setLayout(new java.awt.BorderLayout());

        lblBrand.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblBrand.setForeground(new java.awt.Color(255, 255, 255));
        lblBrand.setText("PT ALEXANDER  |  Admin Dashboard");
        panelHeader.add(lblBrand, java.awt.BorderLayout.WEST);

        panelUser.setOpaque(false);
        panelUser.setLayout(new java.awt.BorderLayout());

        lblUser.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblUser.setForeground(new java.awt.Color(236, 240, 241));
        lblUser.setText("Halo, Administrator (Admin)");
        panelUser.add(lblUser, java.awt.BorderLayout.WEST);

        btnLogout.setBackground(new java.awt.Color(231, 76, 60));
        btnLogout.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnLogout.setForeground(new java.awt.Color(255, 255, 255));
        btnLogout.setText("Logout");
        btnLogout.setBorder(javax.swing.BorderFactory.createEmptyBorder(8, 18, 8, 18));
        btnLogout.setFocusPainted(false);
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });
        panelUser.add(btnLogout, java.awt.BorderLayout.EAST);

        panelHeader.add(panelUser, java.awt.BorderLayout.EAST);

        getContentPane().add(panelHeader, java.awt.BorderLayout.NORTH);

        panelContent.setBackground(new java.awt.Color(245, 246, 250));
        panelContent.setBorder(javax.swing.BorderFactory.createEmptyBorder(30, 35, 30, 35));
        panelContent.setLayout(new java.awt.BorderLayout());

        cardPanel.setBackground(new java.awt.Color(255, 255, 255));
        cardPanel.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        cardPanel.setLayout(new java.awt.BorderLayout());
        panelContent.add(cardPanel, java.awt.BorderLayout.CENTER);

        getContentPane().add(panelContent, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnLogoutActionPerformed
        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Apakah Anda yakin ingin logout?",
                "Konfirmasi Logout",
                JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            dispose();
            LoginFrame loginView = new LoginFrame();
            new LoginController(loginView);
            loginView.setVisible(true);
        }
    }// GEN-LAST:event_btnLogoutActionPerformed

    // =========================================================================
    // Work Order View Setup & Data Display (Focus on View)
    // =========================================================================

    private void setupWorkOrderUI() {
        cardPanel.removeAll();
        cardPanel.setLayout(new BorderLayout(0, 15));
        cardPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 224, 230), 1),
                BorderFactory.createEmptyBorder(20, 25, 20, 25)));

        // 1. Top Panel (Title & Search / Refresh Tools)
        JPanel topPanel = new JPanel(new BorderLayout(15, 0));
        topPanel.setOpaque(false);

        // Title Section with Ikonli icon
        JPanel titlePanel = new JPanel();
        titlePanel.setOpaque(false);
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));

        JLabel lblTitle = new JLabel("Daftar Work Order");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitle.setForeground(new Color(44, 62, 80));
        lblTitle.setIcon(FontIcon.of(FontAwesomeSolid.CLIPBOARD_LIST, 22, new Color(44, 62, 80)));
        lblTitle.setIconTextGap(10);

        JLabel lblSubtitle = new JLabel("Menampilkan data pesanan kerja (Work Order) dan status QR Code");
        lblSubtitle.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblSubtitle.setForeground(new Color(127, 140, 141));

        titlePanel.add(lblTitle);
        titlePanel.add(Box.createVerticalStrut(4));
        titlePanel.add(lblSubtitle);
        topPanel.add(titlePanel, BorderLayout.WEST);

        // Action / Search Toolbar
        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        actionPanel.setOpaque(false);

        JLabel lblSearch = new JLabel("Pencarian:");
        lblSearch.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblSearch.setIcon(FontIcon.of(FontAwesomeSolid.SEARCH, 13, new Color(127, 140, 141)));
        lblSearch.setIconTextGap(6);

        txtSearch = new JTextField(15);
        txtSearch.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtSearch.setPreferredSize(new Dimension(180, 32));
        txtSearch.setToolTipText("Ketik kata kunci untuk memfilter tabel...");
        txtSearch.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filterTable();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filterTable();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                filterTable();
            }
        });

        // Refresh Button dengan Ikonli
        btnRefresh = createStyledButton("Refresh", FontIcon.of(FontAwesomeSolid.SYNC_ALT, 13, Color.WHITE),
                new Color(52, 152, 219));

        // Tambah Work Order Button dengan Ikonli
        btnTambah = createStyledButton("Tambah Work Order", FontIcon.of(FontAwesomeSolid.PLUS_CIRCLE, 13, Color.WHITE),
                new Color(39, 174, 96));

        actionPanel.add(lblSearch);
        actionPanel.add(txtSearch);
        actionPanel.add(btnRefresh);
        actionPanel.add(btnTambah);
        topPanel.add(actionPanel, BorderLayout.EAST);

        cardPanel.add(topPanel, BorderLayout.NORTH);

        // 2. Center Table
        String[] columnNames = {
                "ID WO",
                "Nama Work Order",
                "Worker",
                "Pelanggan",
                "No. WhatsApp",
                "Estimasi",
                "QR Code",
                "Dibuat Pada",
                "Diperbarui Pada"
        };

        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // View only, no inline editing
            }
        };

        tableWorkOrder = new JTable(tableModel);
        rowSorter = new TableRowSorter<>(tableModel);
        tableWorkOrder.setRowSorter(rowSorter);

        tableWorkOrder.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tableWorkOrder.setRowHeight(34);
        tableWorkOrder.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableWorkOrder.setGridColor(new Color(236, 240, 241));
        tableWorkOrder.setShowGrid(true);
        tableWorkOrder.setFillsViewportHeight(true);

        // Table Header Styling
        JTableHeader header = tableWorkOrder.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 13));
        header.setBackground(new Color(44, 62, 80));
        header.setForeground(Color.WHITE);
        header.setPreferredSize(new Dimension(0, 40));
        header.setReorderingAllowed(false);

        // Cell Alignment Renderer
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        tableWorkOrder.getColumnModel().getColumn(0).setCellRenderer(centerRenderer); // ID WO
        tableWorkOrder.getColumnModel().getColumn(4).setCellRenderer(centerRenderer); // No. WA
        tableWorkOrder.getColumnModel().getColumn(5).setCellRenderer(centerRenderer); // Estimasi
        tableWorkOrder.getColumnModel().getColumn(6).setCellRenderer(centerRenderer); // QR Code
        tableWorkOrder.getColumnModel().getColumn(7).setCellRenderer(centerRenderer); // Created
        tableWorkOrder.getColumnModel().getColumn(8).setCellRenderer(centerRenderer); // Updated

        // Column Widths
        tableWorkOrder.getColumnModel().getColumn(0).setPreferredWidth(100);
        tableWorkOrder.getColumnModel().getColumn(1).setPreferredWidth(210);
        tableWorkOrder.getColumnModel().getColumn(2).setPreferredWidth(120);
        tableWorkOrder.getColumnModel().getColumn(3).setPreferredWidth(140);
        tableWorkOrder.getColumnModel().getColumn(4).setPreferredWidth(110);
        tableWorkOrder.getColumnModel().getColumn(5).setPreferredWidth(90);
        tableWorkOrder.getColumnModel().getColumn(6).setPreferredWidth(120);
        tableWorkOrder.getColumnModel().getColumn(7).setPreferredWidth(140);
        tableWorkOrder.getColumnModel().getColumn(8).setPreferredWidth(140);

        // Double-click to view details
        tableWorkOrder.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && tableWorkOrder.getSelectedRow() != -1) {
                    showDetailWorkOrderDialog();
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(tableWorkOrder);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(220, 224, 230), 1));
        cardPanel.add(scrollPane, BorderLayout.CENTER);

        // 3. Bottom Panel (Status & Detail Viewer Button)
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setOpaque(false);

        lblTotalData = new JLabel("Memuat data...");
        lblTotalData.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblTotalData.setForeground(new Color(127, 140, 141));
        bottomPanel.add(lblTotalData, BorderLayout.WEST);

        cardPanel.add(bottomPanel, BorderLayout.SOUTH);
        cardPanel.revalidate();
        cardPanel.repaint();
    }

    public void loadWorkOrderData() {
        tableModel.setRowCount(0);
        List<WorkOrder> list = workOrderDAO.getAll();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        for (WorkOrder wo : list) {
            String createdStr = wo.getCreatedAt() != null ? sdf.format(wo.getCreatedAt()) : "-";
            String updatedStr = wo.getUpdatedAt() != null ? sdf.format(wo.getUpdatedAt()) : "-";

            tableModel.addRow(new Object[] {
                    wo.getIdWo(),
                    wo.getNamaWorkOrder(),
                    wo.getWorker(),
                    wo.getPelanggan(),
                    wo.getNoWa(),
                    wo.getEstimasi(),
                    wo.getQrCode(),
                    createdStr,
                    updatedStr
            });
        }

        lblTotalData.setText("Total: " + list.size() + " Work Order");
    }

    public void addTambahListener(ActionListener listener) {
        if (btnTambah != null) {
            btnTambah.addActionListener(listener);
        }
    }

    public void addRefreshListener(ActionListener listener) {
        if (btnRefresh != null) {
            btnRefresh.addActionListener(listener);
        }
    }

    private void filterTable() {
        String keyword = txtSearch.getText().trim();
        if (keyword.isEmpty()) {
            rowSorter.setRowFilter(null);
        } else {
            rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + keyword));
        }
        lblTotalData.setText("Menampilkan: " + tableWorkOrder.getRowCount() + " Work Order");
    }

    private void showDetailWorkOrderDialog() {
        int selectedRow = tableWorkOrder.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(
                    this,
                    "Silakan pilih baris Work Order pada tabel terlebih dahulu!",
                    "Peringatan",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        int modelRow = tableWorkOrder.convertRowIndexToModel(selectedRow);
        String idWo = String.valueOf(tableModel.getValueAt(modelRow, 0));
        String nama = String.valueOf(tableModel.getValueAt(modelRow, 1));
        String worker = String.valueOf(tableModel.getValueAt(modelRow, 2));
        String pelanggan = String.valueOf(tableModel.getValueAt(modelRow, 3));
        String noWa = String.valueOf(tableModel.getValueAt(modelRow, 4));
        String estimasi = String.valueOf(tableModel.getValueAt(modelRow, 5));
        String qrCode = String.valueOf(tableModel.getValueAt(modelRow, 6));
        String created = String.valueOf(tableModel.getValueAt(modelRow, 7));
        String updated = String.valueOf(tableModel.getValueAt(modelRow, 8));

        JDialog dialog = new JDialog(this, "Detail Work Order - " + idWo, true);
        dialog.setSize(520, 560);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(new BorderLayout());

        JPanel contentPanel = new JPanel(new BorderLayout(15, 15));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 25, 15, 25));
        contentPanel.setBackground(Color.WHITE);

        // Visual QR Badge Preview
        JPanel qrPreview = new QRCodeBadgePanel(idWo, qrCode);
        qrPreview.setPreferredSize(new Dimension(170, 170));

        JPanel qrWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER));
        qrWrapper.setOpaque(false);
        qrWrapper.add(qrPreview);
        contentPanel.add(qrWrapper, BorderLayout.NORTH);

        // Information Grid with Ikonli Icons
        JPanel infoGrid = new JPanel(new GridBagLayout());
        infoGrid.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 6, 5, 6);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        addInfoRowWithIcon(infoGrid, gbc, 0, "ID Work Order:", idWo,
                FontIcon.of(FontAwesomeSolid.ID_BADGE, 14, new Color(52, 73, 94)));
        addInfoRowWithIcon(infoGrid, gbc, 1, "Nama WO:", nama,
                FontIcon.of(FontAwesomeSolid.TOOLS, 14, new Color(52, 73, 94)));
        addInfoRowWithIcon(infoGrid, gbc, 2, "Worker / Teknisi:", worker,
                FontIcon.of(FontAwesomeSolid.USER_COG, 14, new Color(52, 73, 94)));
        addInfoRowWithIcon(infoGrid, gbc, 3, "Pelanggan:", pelanggan,
                FontIcon.of(FontAwesomeSolid.USER_TIE, 14, new Color(52, 73, 94)));
        addInfoRowWithIcon(infoGrid, gbc, 4, "No. WhatsApp:", noWa,
                FontIcon.of(FontAwesomeBrands.WHATSAPP, 15, new Color(37, 211, 102)));
        addInfoRowWithIcon(infoGrid, gbc, 5, "Estimasi Selesai:", estimasi,
                FontIcon.of(FontAwesomeSolid.CLOCK, 14, new Color(52, 73, 94)));
        addInfoRowWithIcon(infoGrid, gbc, 6, "Kode QR:", qrCode,
                FontIcon.of(FontAwesomeSolid.QRCODE, 14, new Color(52, 73, 94)));
        addInfoRowWithIcon(infoGrid, gbc, 7, "Dibuat Pada:", created,
                FontIcon.of(FontAwesomeSolid.CALENDAR_PLUS, 14, new Color(52, 73, 94)));
        addInfoRowWithIcon(infoGrid, gbc, 8, "Diperbarui Pada:", updated,
                FontIcon.of(FontAwesomeSolid.CALENDAR_CHECK, 14, new Color(52, 73, 94)));

        contentPanel.add(infoGrid, BorderLayout.CENTER);
        dialog.add(contentPanel, BorderLayout.CENTER);

        // Bottom Button Panel
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 12));
        bottomPanel.setBackground(new Color(245, 246, 250));

        JButton btnTutup = createStyledButton("Tutup", FontIcon.of(FontAwesomeSolid.TIMES, 13, Color.WHITE),
                new Color(44, 62, 80));
        btnTutup.addActionListener(e -> dialog.dispose());
        bottomPanel.add(btnTutup);
        dialog.add(bottomPanel, BorderLayout.SOUTH);

        dialog.setVisible(true);
    }

    private void addInfoRowWithIcon(JPanel panel, GridBagConstraints gbc, int row, String label, String value,
            Icon icon) {
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.weightx = 0.4;
        JLabel lbl = new JLabel(label);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 12));
        if (icon != null) {
            lbl.setIcon(icon);
            lbl.setIconTextGap(8);
        }
        panel.add(lbl, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.6;
        JLabel val = new JLabel(value != null && !value.isEmpty() ? value : "-");
        val.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        val.setForeground(new Color(44, 62, 80));
        panel.add(val, gbc);
    }

    private JButton createStyledButton(String text, Icon icon, Color bg) {
        JButton btn = new JButton(text);
        if (icon != null) {
            btn.setIcon(icon);
            btn.setIconTextGap(8);
        }
        btn.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btn.setBackground(bg);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }

    // =========================================================================
    // Visual QR Code Badge Panel
    // =========================================================================
    private static class QRCodeBadgePanel extends JPanel {
        private final String idWo;
        private final String qrData;

        public QRCodeBadgePanel(String idWo, String qrData) {
            this.idWo = idWo;
            this.qrData = qrData;
            setBackground(Color.WHITE);
            setBorder(BorderFactory.createLineBorder(new Color(200, 205, 210), 1));
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int width = getWidth();
            int height = getHeight();

            g2.setColor(Color.WHITE);
            g2.fillRect(0, 0, width, height);

            // Draw QR-style Matrix Pattern
            int margin = 14;
            int matrixSize = 21; // 21x21 modules standard for QR Version 1
            int area = Math.min(width - (margin * 2), height - (margin * 2) - 18);
            int cellSize = Math.max(3, area / matrixSize);
            int startX = (width - (matrixSize * cellSize)) / 2;
            int startY = margin;

            g2.setColor(new Color(30, 39, 46));

            // Hash seed to draw deterministic pattern based on qrData
            int hash = (qrData != null) ? Math.abs(qrData.hashCode()) : 1234567;

            for (int r = 0; r < matrixSize; r++) {
                for (int c = 0; c < matrixSize; c++) {
                    boolean isPositionPattern = (r < 7 && c < 7) ||
                            (r < 7 && c >= matrixSize - 7) ||
                            (r >= matrixSize - 7 && c < 7);

                    if (isPositionPattern) {
                        continue;
                    }

                    int cellBit = ((hash >> ((r * 7 + c) % 24)) & 1) ^ ((r + c) % 2);
                    if (cellBit == 1) {
                        g2.fillRect(startX + (c * cellSize), startY + (r * cellSize), cellSize - 1, cellSize - 1);
                    }
                }
            }

            // Draw 3 Standard Corner Position Markers
            drawPositionMarker(g2, startX, startY, cellSize);
            drawPositionMarker(g2, startX + ((matrixSize - 7) * cellSize), startY, cellSize);
            drawPositionMarker(g2, startX, startY + ((matrixSize - 7) * cellSize), cellSize);

            // Text Label at bottom
            g2.setColor(new Color(44, 62, 80));
            g2.setFont(new Font("Segoe UI", Font.BOLD, 10));
            String label = idWo != null ? idWo : qrData;
            int strWidth = g2.getFontMetrics().stringWidth(label);
            g2.drawString(label, (width - strWidth) / 2, height - 6);

            g2.dispose();
        }

        private void drawPositionMarker(Graphics2D g2, int x, int y, int cell) {
            // Outer 7x7 square
            g2.fillRect(x, y, 7 * cell, 7 * cell);
            // Inner 5x5 white square
            g2.setColor(Color.WHITE);
            g2.fillRect(x + cell, y + cell, 5 * cell, 5 * cell);
            // Center 3x3 black square
            g2.setColor(new Color(30, 39, 46));
            g2.fillRect(x + (2 * cell), y + (2 * cell), 3 * cell, 3 * cell);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogout;
    private javax.swing.JPanel cardPanel;
    private javax.swing.JLabel lblBrand;
    private javax.swing.JLabel lblUser;
    private javax.swing.JPanel panelContent;
    private javax.swing.JPanel panelHeader;
    private javax.swing.JPanel panelUser;
    // End of variables declaration//GEN-END:variables
}
