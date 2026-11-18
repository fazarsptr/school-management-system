/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mtsarridho.form;

import com.mtsarridho.config.KoneksiDatabase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author zaena
 */
public class FormEditDataGuru extends javax.swing.JFrame {
    int xx, xy;
    private DataChangedListener dataChangedListener;
    private String nik;
    /**
     * Creates new form FormTambahDataGuru
     */
    
    public void setDataChangedListener(DataChangedListener listener) {
        this.dataChangedListener = listener;
    }
    
    private void clearFields() {
        txtNama.setText("");
        txtNik.setText("");
        comboBoxLP.setSelectedIndex(0);
        
    }
    
    public FormEditDataGuru(String nik) {
        initComponents();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.nik = nik;
        tampilkanDataGuru();
            
                 
    }
    
    private void tampilkanDataGuru() {
        try {
            Connection conn = KoneksiDatabase.getKoneksi();
            try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM tb_guru WHERE nik = ?")) {
                ps.setString(1, nik);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        txtNama.setText(rs.getString("nama"));
                        txtNik.setText(rs.getString("nik"));
                        
                        comboBoxLP.addItem("Laki-laki");
                        comboBoxLP.addItem("Perempuan");
                        String genderGuru = rs.getString("jenis_kelamin");
                        if ("Laki-laki".equalsIgnoreCase(genderGuru)) {
                            comboBoxLP.setSelectedItem("Laki-laki");
                        } else if ("Perempuan".equalsIgnoreCase(genderGuru)) {
                            comboBoxLP.setSelectedItem("Perempuan");
                        }
                    }
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal mengambil data guru\n" + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Checks if newNik already exists in tb_guru belonging to another teacher record.
     */
    private boolean isNikExistsForOther(Connection conn, String newNik, String currentNik) throws SQLException {
        String sql = "SELECT 1 FROM tb_guru WHERE nik = ? AND nik != ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, newNik);
            ps.setString(2, currentNik);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }

    private void updateDataGuru() {
        // Retrieve and trim values from form fields
        String nama = txtNama.getText() != null ? txtNama.getText().trim() : "";
        String nikBaru = txtNik.getText() != null ? txtNik.getText().trim() : ""; // Can be different from current NIK
        String jenisKelamin = comboBoxLP.getSelectedItem() != null ? comboBoxLP.getSelectedItem().toString().trim() : "";
        
        // 1. Prevent empty or whitespace-only inputs
        if (nama.isEmpty() || nikBaru.isEmpty() || jenisKelamin.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Semua field harus diisi!", "Validasi Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 2. Validate field lengths against database schema
        if (nama.length() > 100) {
            JOptionPane.showMessageDialog(this, "Nama guru tidak boleh melebihi 100 karakter!", "Validasi Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (nikBaru.length() > 20) {
            JOptionPane.showMessageDialog(this, "NIK/NUPTK tidak boleh melebihi 20 karakter!", "Validasi Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 3. Validate gender selection
        if (!"Laki-laki".equalsIgnoreCase(jenisKelamin) && !"Perempuan".equalsIgnoreCase(jenisKelamin)) {
            JOptionPane.showMessageDialog(this, "Pilih jenis kelamin yang valid (Laki-laki atau Perempuan)!", "Validasi Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            Connection conn = KoneksiDatabase.getKoneksi();

            // 4. Check for duplicate NIK/NUPTK, excluding current teacher's original record
            if (!nikBaru.equals(this.nik) && isNikExistsForOther(conn, nikBaru, this.nik)) {
                JOptionPane.showMessageDialog(this, "NIK/NUPTK baru sudah terdaftar untuk guru lain!", "Validasi Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String sql = "UPDATE tb_guru SET nik=?, nama=?, jenis_kelamin=? WHERE nik=?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, nikBaru);
                ps.setString(2, nama);
                ps.setString(3, jenisKelamin);
                ps.setString(4, this.nik); // old NIK as condition
                
                int result = ps.executeUpdate();
                if (result > 0) {
                    this.nik = nikBaru; // Update internal reference to the new NIK
                    JOptionPane.showMessageDialog(this, "Data guru berhasil diperbarui.", "Sukses", JOptionPane.INFORMATION_MESSAGE);
                    if (dataChangedListener != null) {
                        dataChangedListener.onDataChanged();
                    }
                    this.dispose(); // Close edit form
                } else {
                    JOptionPane.showMessageDialog(this, "Data guru gagal diperbarui.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (SQLException e) {
            if (e.getErrorCode() == 1062 || (e.getMessage() != null && e.getMessage().toLowerCase().contains("duplicate"))) {
                JOptionPane.showMessageDialog(this, "NIK/NUPTK baru sudah terdaftar untuk guru lain!", "Database Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Kesalahan saat mengupdate data:\n" + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpanelGradient1 = new com.mtsarridho.pallate.JpanelGradient();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtNama = new javax.swing.JTextField();
        txtNik = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        comboBoxLP = new rojerusan.RSComboMetro();
        rSMaterialButtonRectangle1 = new rojerusan.RSMaterialButtonRectangle();
        rSMaterialButtonRectangle2 = new rojerusan.RSMaterialButtonRectangle();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jpanelGradient1.setColorEnd(new java.awt.Color(0, 51, 204));
        jpanelGradient1.setColorStart(new java.awt.Color(51, 255, 255));
        jpanelGradient1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jpanelGradient1MouseDragged(evt);
            }
        });
        jpanelGradient1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jpanelGradient1MousePressed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 0, 153));
        jLabel1.setText("Edit Guru");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 0, 153));
        jLabel2.setText("Nama :");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 0, 153));
        jLabel4.setText("NIK :");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(51, 0, 153));
        jLabel5.setText("Jenis Kelamin :");

        txtNama.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtNama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNamaActionPerformed(evt);
            }
        });

        txtNik.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N

        jLabel8.setForeground(new java.awt.Color(51, 0, 153));
        jLabel8.setText("Edit data guru");

        rSMaterialButtonRectangle1.setBackground(new java.awt.Color(0, 0, 153));
        rSMaterialButtonRectangle1.setText("SIMPAN");
        rSMaterialButtonRectangle1.setFont(new java.awt.Font("Roboto Medium", 1, 18)); // NOI18N
        rSMaterialButtonRectangle1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rSMaterialButtonRectangle1MouseClicked(evt);
            }
        });
        rSMaterialButtonRectangle1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonRectangle1ActionPerformed(evt);
            }
        });

        rSMaterialButtonRectangle2.setBackground(new java.awt.Color(204, 0, 0));
        rSMaterialButtonRectangle2.setText("BATAL");
        rSMaterialButtonRectangle2.setFont(new java.awt.Font("Roboto Medium", 1, 18)); // NOI18N
        rSMaterialButtonRectangle2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rSMaterialButtonRectangle2MouseClicked(evt);
            }
        });
        rSMaterialButtonRectangle2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonRectangle2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpanelGradient1Layout = new javax.swing.GroupLayout(jpanelGradient1);
        jpanelGradient1.setLayout(jpanelGradient1Layout);
        jpanelGradient1Layout.setHorizontalGroup(
            jpanelGradient1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanelGradient1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpanelGradient1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addComponent(jSeparator2)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpanelGradient1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(rSMaterialButtonRectangle1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rSMaterialButtonRectangle2, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtNik)
                    .addComponent(txtNama, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jpanelGradient1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(comboBoxLP, javax.swing.GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE))
                    .addGroup(jpanelGradient1Layout.createSequentialGroup()
                        .addGroup(jpanelGradient1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel8)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jpanelGradient1Layout.setVerticalGroup(
            jpanelGradient1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanelGradient1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNik, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addGroup(jpanelGradient1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(comboBoxLP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addGroup(jpanelGradient1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rSMaterialButtonRectangle2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rSMaterialButtonRectangle1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(113, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpanelGradient1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpanelGradient1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNamaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNamaActionPerformed

    private void rSMaterialButtonRectangle1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonRectangle1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rSMaterialButtonRectangle1ActionPerformed

    private void rSMaterialButtonRectangle2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonRectangle2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rSMaterialButtonRectangle2ActionPerformed

    private void jpanelGradient1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpanelGradient1MousePressed
        xx = evt.getX();
        xy = evt.getY();
    }//GEN-LAST:event_jpanelGradient1MousePressed

    private void jpanelGradient1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpanelGradient1MouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xx, y - xy);
    }//GEN-LAST:event_jpanelGradient1MouseDragged

    private void rSMaterialButtonRectangle1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rSMaterialButtonRectangle1MouseClicked
        updateDataGuru();
    }//GEN-LAST:event_rSMaterialButtonRectangle1MouseClicked

    private void rSMaterialButtonRectangle2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rSMaterialButtonRectangle2MouseClicked
        dispose();
    }//GEN-LAST:event_rSMaterialButtonRectangle2MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormEditDataGuru.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormEditDataGuru.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormEditDataGuru.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormEditDataGuru.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //</editor-fold>

        /* Create and display the form */
       
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojerusan.RSComboMetro comboBoxLP;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private com.mtsarridho.pallate.JpanelGradient jpanelGradient1;
    private rojerusan.RSMaterialButtonRectangle rSMaterialButtonRectangle1;
    private rojerusan.RSMaterialButtonRectangle rSMaterialButtonRectangle2;
    private javax.swing.JTextField txtNama;
    private javax.swing.JTextField txtNik;
    // End of variables declaration//GEN-END:variables
}
