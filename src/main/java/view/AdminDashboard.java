package view;

import javax.swing.JOptionPane;

import controller.LoginController;
import model.Admin;

public class AdminDashboard extends javax.swing.JFrame {

    private Admin admin;

    public AdminDashboard() {
        this(new Admin(1, "Administrator", "admin", "admin123"));
    }

    public AdminDashboard(Admin admin) {
        this.admin = admin;
        initComponents();
        setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);

        if (admin != null) {
            lblUser.setText("Halo, " + admin.getNama() + " (" + admin.getRole() + ")");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
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
        panelContent.setBorder(javax.swing.BorderFactory.createEmptyBorder(40, 40, 40, 40));
        panelContent.setLayout(new java.awt.BorderLayout());

        cardPanel.setBackground(new java.awt.Color(255, 255, 255));
        cardPanel.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        cardPanel.setLayout(new java.awt.BorderLayout());
        panelContent.add(cardPanel, java.awt.BorderLayout.CENTER);

        getContentPane().add(panelContent, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Apakah Anda yakin ingin logout?",
                "Konfirmasi Logout",
                JOptionPane.YES_NO_OPTION
        );
        if (confirm == JOptionPane.YES_OPTION) {
            dispose();
            LoginFrame loginView = new LoginFrame();
            new LoginController(loginView);
            loginView.setVisible(true);
        }
    }//GEN-LAST:event_btnLogoutActionPerformed

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
