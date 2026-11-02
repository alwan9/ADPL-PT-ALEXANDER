package view;

import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class LoginFrame extends javax.swing.JFrame {

    public LoginFrame() {
        initComponents();
        setLocationRelativeTo(null);

        txtUsername.addActionListener(e -> txtPassword.requestFocus());
        txtPassword.addActionListener(e -> btnLogin.doClick());
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelLeft = new javax.swing.JPanel();
        lblBrand = new javax.swing.JLabel();
        panelRight = new javax.swing.JPanel();
        lblTitle = new javax.swing.JLabel();
        panelForm = new javax.swing.JPanel();
        lblUsername = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        lblPassword = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        panelButton = new javax.swing.JPanel();
        btnLogin = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("PT Alexander - Login Administrator");

        panelLeft.setBackground(new java.awt.Color(44, 62, 80));
        panelLeft.setPreferredSize(new java.awt.Dimension(300, 420));
        panelLeft.setLayout(new java.awt.BorderLayout());

        lblBrand.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        lblBrand.setForeground(new java.awt.Color(255, 255, 255));
        lblBrand.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBrand.setText("PT ALEXANDER");
        panelLeft.add(lblBrand, java.awt.BorderLayout.CENTER);

        getContentPane().add(panelLeft, java.awt.BorderLayout.WEST);

        panelRight.setBackground(new java.awt.Color(255, 255, 255));
        panelRight.setBorder(javax.swing.BorderFactory.createEmptyBorder(35, 40, 35, 40));
        panelRight.setLayout(new java.awt.BorderLayout(0, 20));

        lblTitle.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(44, 62, 80));
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setText("LOGIN ADMINISTRATOR");
        panelRight.add(lblTitle, java.awt.BorderLayout.NORTH);

        panelForm.setBackground(new java.awt.Color(255, 255, 255));
        panelForm.setLayout(new java.awt.GridLayout(4, 1, 0, 8));

        lblUsername.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblUsername.setText("Username");
        panelForm.add(lblUsername);

        txtUsername.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        panelForm.add(txtUsername);

        lblPassword.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblPassword.setText("Password");
        panelForm.add(lblPassword);

        txtPassword.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        panelForm.add(txtPassword);

        panelRight.add(panelForm, java.awt.BorderLayout.CENTER);

        panelButton.setBackground(new java.awt.Color(255, 255, 255));
        panelButton.setLayout(new java.awt.GridLayout(1, 1));

        btnLogin.setBackground(new java.awt.Color(41, 128, 185));
        btnLogin.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLogin.setForeground(new java.awt.Color(255, 255, 255));
        btnLogin.setText("Login");
        btnLogin.setPreferredSize(new java.awt.Dimension(0, 40));
        panelButton.add(btnLogin);

        panelRight.add(panelButton, java.awt.BorderLayout.SOUTH);

        getContentPane().add(panelRight, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public String getUsername() {
        return txtUsername.getText().trim();
    }

    public String getPassword() {
        return String.valueOf(txtPassword.getPassword()).trim();
    }

    public void addLoginListener(ActionListener listener) {
        btnLogin.addActionListener(listener);
    }

    public void showErrorMessage(String message, String title) {
        JOptionPane.showMessageDialog(this, message, title, JOptionPane.ERROR_MESSAGE);
    }

    public void showWarningMessage(String message, String title) {
        JOptionPane.showMessageDialog(this, message, title, JOptionPane.WARNING_MESSAGE);
    }

    public void showInfoMessage(String message, String title) {
        JOptionPane.showMessageDialog(this, message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    public void close() {
        this.dispose();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogin;
    private javax.swing.JLabel lblBrand;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JPanel panelButton;
    private javax.swing.JPanel panelForm;
    private javax.swing.JPanel panelLeft;
    private javax.swing.JPanel panelRight;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
