package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.kordamp.ikonli.fontawesome5.FontAwesomeBrands;
import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid;
import org.kordamp.ikonli.swing.FontIcon;

/**
 * View dialog untuk Form Edit Work Order (Arsitektur MVC).
 */
public class EditWorkOrderDialog extends JDialog {

    private JTextField txtIdWo;
    private JTextField txtNama;
    private JTextField txtWorker;
    private JTextField txtPelanggan;
    private JTextField txtNoWa;
    private JTextField txtEstimasi;
    private JTextField txtQrCode;

    private JButton btnSimpan;
    private JButton btnBatal;

    public EditWorkOrderDialog(Frame parent, boolean modal) {
        super(parent, "Edit Work Order", modal);
        initComponents();
        setSize(500, 520);
        setLocationRelativeTo(parent);
        setResizable(false);
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.WHITE);

        // Header Dialog
        JPanel panelHeader = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 15));
        panelHeader.setBackground(new Color(44, 62, 80));

        JLabel lblTitle = new JLabel("Form Edit Data Work Order");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setIcon(FontIcon.of(FontAwesomeSolid.EDIT, 18, Color.WHITE));
        lblTitle.setIconTextGap(10);
        panelHeader.add(lblTitle);

        add(panelHeader, BorderLayout.NORTH);

        // Form Fields Container
        JPanel panelForm = new JPanel(new GridBagLayout());
        panelForm.setBackground(Color.WHITE);
        panelForm.setBorder(BorderFactory.createEmptyBorder(20, 25, 15, 25));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        txtIdWo = new JTextField();
        txtIdWo.setEditable(false);
        txtIdWo.setBackground(new Color(245, 246, 250));
        txtIdWo.setForeground(new Color(44, 62, 80));
        txtIdWo.setFont(new Font("Segoe UI", Font.BOLD, 12));

        txtNama = new JTextField();
        txtWorker = new JTextField();
        txtPelanggan = new JTextField();
        txtNoWa = new JTextField();
        txtEstimasi = new JTextField();

        txtQrCode = new JTextField();
        txtQrCode.setEditable(false);
        txtQrCode.setBackground(new Color(245, 246, 250));
        txtQrCode.setForeground(new Color(44, 62, 80));
        txtQrCode.setFont(new Font("Segoe UI", Font.BOLD, 12));

        // Form fields dengan ikon Ikonli
        addFormField(panelForm, gbc, 0, "ID Work Order:", txtIdWo, FontIcon.of(FontAwesomeSolid.ID_BADGE, 14, new Color(52, 73, 94)));
        addFormField(panelForm, gbc, 1, "Nama Work Order:", txtNama, FontIcon.of(FontAwesomeSolid.TOOLS, 14, new Color(52, 73, 94)));
        addFormField(panelForm, gbc, 2, "Worker / Teknisi:", txtWorker, FontIcon.of(FontAwesomeSolid.USER_COG, 14, new Color(52, 73, 94)));
        addFormField(panelForm, gbc, 3, "Nama Pelanggan:", txtPelanggan, FontIcon.of(FontAwesomeSolid.USER_TIE, 14, new Color(52, 73, 94)));
        addFormField(panelForm, gbc, 4, "No. WhatsApp:", txtNoWa, FontIcon.of(FontAwesomeBrands.WHATSAPP, 15, new Color(37, 211, 102)));
        addFormField(panelForm, gbc, 5, "Estimasi Waktu:", txtEstimasi, FontIcon.of(FontAwesomeSolid.CLOCK, 14, new Color(52, 73, 94)));
        addFormField(panelForm, gbc, 6, "Kode QR:", txtQrCode, FontIcon.of(FontAwesomeSolid.QRCODE, 14, new Color(52, 73, 94)));

        add(panelForm, BorderLayout.CENTER);

        // Buttons Footer
        JPanel panelButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT, 12, 14));
        panelButtons.setBackground(new Color(245, 246, 250));
        panelButtons.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(220, 224, 230)));

        btnBatal = createStyledButton("Batal", FontIcon.of(FontAwesomeSolid.TIMES, 13, Color.WHITE), new Color(149, 165, 166));
        btnSimpan = createStyledButton("Simpan Perubahan", FontIcon.of(FontAwesomeSolid.SAVE, 13, Color.WHITE), new Color(230, 126, 34));

        panelButtons.add(btnBatal);
        panelButtons.add(btnSimpan);
        add(panelButtons, BorderLayout.SOUTH);
    }

    private void addFormField(JPanel panel, GridBagConstraints gbc, int row, String label, JTextField field, Icon icon) {
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.weightx = 0.38;
        JLabel lbl = new JLabel(label);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 12));
        if (icon != null) {
            lbl.setIcon(icon);
            lbl.setIconTextGap(8);
        }
        panel.add(lbl, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.62;
        field.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        field.setPreferredSize(new Dimension(240, 32));
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 205, 210), 1),
                BorderFactory.createEmptyBorder(4, 8, 4, 8)
        ));
        panel.add(field, gbc);
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
    // Getters & Setters for MVC Controller
    // =========================================================================

    public void setData(String idWo, String nama, String worker, String pelanggan, String noWa, String estimasi, String qrCode) {
        txtIdWo.setText(idWo);
        txtNama.setText(nama);
        txtWorker.setText(worker);
        txtPelanggan.setText(pelanggan);
        txtNoWa.setText(noWa);
        txtEstimasi.setText(estimasi);
        txtQrCode.setText(qrCode);
    }

    public String getIdWo() {
        return txtIdWo.getText().trim();
    }

    public String getNamaWorkOrder() {
        return txtNama.getText().trim();
    }

    public String getWorker() {
        return txtWorker.getText().trim();
    }

    public String getPelanggan() {
        return txtPelanggan.getText().trim();
    }

    public String getNoWa() {
        return txtNoWa.getText().trim();
    }

    public String getEstimasi() {
        return txtEstimasi.getText().trim();
    }

    public String getQrCode() {
        return txtQrCode.getText().trim();
    }

    public void addSimpanListener(ActionListener listener) {
        btnSimpan.addActionListener(listener);
    }

    public void addBatalListener(ActionListener listener) {
        btnBatal.addActionListener(listener);
    }

    public void showWarning(String message) {
        JOptionPane.showMessageDialog(this, message, "Peringatan Validasi", JOptionPane.WARNING_MESSAGE);
    }

    public void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Kesalahan Sistem", JOptionPane.ERROR_MESSAGE);
    }

    public void showSuccess(String message) {
        JOptionPane.showMessageDialog(this, message, "Berhasil", JOptionPane.INFORMATION_MESSAGE);
    }

    public void closeDialog() {
        dispose();
    }
}

