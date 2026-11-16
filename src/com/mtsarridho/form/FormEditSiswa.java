/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mtsarridho.form;

import com.mtsarridho.config.KoneksiDatabase;
import com.toedter.calendar.JCalendar;
import java.awt.Color;
import java.awt.Point;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author zaena
 */
public class FormEditSiswa extends javax.swing.JFrame {

    private final JCalendar calendar;
    private final JDialog calendarDialog;
    private Object tTanggal;
    int xx, xy;
    private String nisn;
    private DataChangedListener dataChangedListener;
    /**
     * Creates new form FormTambahSiswa
     */
    public void setDataChangedListener(DataChangedListener listener) {
        this.dataChangedListener = listener;
    }
    
    public FormEditSiswa(String nisn) {
        setUndecorated(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initComponents();
        this.nisn = nisn;
        tampilkanDataSiswa();

        setLocationRelativeTo(null); //tengah
        calendar = new JCalendar();

        calendarDialog = new JDialog(this, "Pilih Tanggal", true);
        calendarDialog.setUndecorated(true);
        calendarDialog.getContentPane().add(calendar);
        calendarDialog.pack();

        calendar.addPropertyChangeListener("calendar", evt -> {
            Date selectedDate = calendar.getDate();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            tTanggal1.setText(sdf.format(selectedDate));
            calendarDialog.setVisible(false);
        });

        bttn.addActionListener(e -> {
            Point lokasi = bttn.getLocationOnScreen();
            calendarDialog.setLocation(lokasi.x, lokasi.y + bttn.getHeight());
            calendarDialog.setVisible(true);
        });
    }
    
    private void tampilkanDataSiswa() {
        try {
            Connection conn = KoneksiDatabase.getKoneksi();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM tb_siswa WHERE nisn = ?");
            ps.setString(1, nisn);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                
                txtNama1.setText(rs.getString("nama"));
                txtNama2.setText(rs.getString("nisn"));
                txtNama3.setText(rs.getString("tempat_lahir"));
                tTanggal1.setText(rs.getString("tanggal_lahir"));
                
                rSComboMetro1.addItem("Laki-laki");
                rSComboMetro1.addItem("Perempuan");
                String genderSiswa = rs.getString("jenis_kelamin");
                if ("Laki-laki".equalsIgnoreCase(genderSiswa)) {
                    rSComboMetro1.setSelectedItem("Laki-laki");
                } else if ("Perempuan".equalsIgnoreCase(genderSiswa)) {
                    rSComboMetro1.setSelectedItem("Perempuan");
                }
                
                rSComboMetro2.addItem("KELAS 7");
                rSComboMetro2.addItem("KELAS 8");
                rSComboMetro2.addItem("KELAS 9");
                String kelas = rs.getString("tingkat");
                if ("KELAS 7".equalsIgnoreCase(kelas)) {
                    rSComboMetro2.setSelectedItem("KELAS 7");
                } else if ("KELAS 8".equalsIgnoreCase(kelas)) {
                    rSComboMetro2.setSelectedItem("KELAS 8");
                } else if ("KELAS 9".equalsIgnoreCase(kelas)) {
                    rSComboMetro2.setSelectedItem("KELAS 9");
                }     
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal mengambil data siswa\n" + e.getMessage());
        }
    }
    
    private void updateDataSiswa() {
        String nama = txtNama1.getText();
        String nisnBaru = txtNama2.getText(); // bisa berbeda dari nisn lama
        String jenisKelamin = rSComboMetro1.getSelectedItem().toString();
        String tempatLahir = txtNama3.getText();
        String tanggalLahir = tTanggal1.getText();
        String tingkat = rSComboMetro2.getSelectedItem().toString();

        try {
            Connection conn = KoneksiDatabase.getKoneksi();
            PreparedStatement ps = conn.prepareStatement(
                "UPDATE tb_siswa SET nama=?, nisn=?, jenis_kelamin=?, tempat_lahir=?, tanggal_lahir=?, tingkat=? WHERE nisn=?"
            );
            ps.setString(1, nama);
            ps.setString(2, nisnBaru);
            ps.setString(3, jenisKelamin);
            ps.setString(4, tempatLahir);
            ps.setString(5, tanggalLahir);
            ps.setString(6, tingkat);
            ps.setString(7, this.nisn); // pakai NISN lama sebagai acuan update

            int result = ps.executeUpdate();
            if (result > 0) {
                JOptionPane.showMessageDialog(this, "Data siswa berhasil diperbarui.");
                if (dataChangedListener != null) {
                    dataChangedListener.onDataChanged();
                }
                this.dispose(); // tutup form edit
            } else {
                JOptionPane.showMessageDialog(this, "Data siswa gagal diperbarui.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Kesalahan saat mengupdate data:\n" + e.getMessage());
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
        jLabel7 = new javax.swing.JLabel();
        txtNama1 = new javax.swing.JTextField();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel14 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtNama2 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        rSComboMetro1 = new rojerusan.RSComboMetro();
        jLabel16 = new javax.swing.JLabel();
        txtNama3 = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        tTanggal1 = new javax.swing.JTextField();
        bttn = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        rSComboMetro2 = new rojerusan.RSComboMetro();
        rSMaterialButtonRectangle1 = new rojerusan.RSMaterialButtonRectangle();
        rSMaterialButtonRectangle2 = new rojerusan.RSMaterialButtonRectangle();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jpanelGradient1.setColorEnd(new java.awt.Color(51, 255, 255));
        jpanelGradient1.setColorStart(new java.awt.Color(0, 102, 204));
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

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel7.setText("Nama :");

        txtNama1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNama1ActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel14.setText("EDIT SISWA");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel8.setText("NISN  :");

        txtNama2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNama2ActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel15.setText("Jenis Kelamin : ");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel16.setText("Tempat Lahir :");

        txtNama3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNama3ActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel17.setText("Tanggal Lahir : ");

        bttn.setText("...");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel18.setText("Tingkat Kelas :");

        rSMaterialButtonRectangle1.setBackground(new java.awt.Color(204, 0, 0));
        rSMaterialButtonRectangle1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mtsarridho/icon/siswa.png"))); // NOI18N
        rSMaterialButtonRectangle1.setText("BATAL");
        rSMaterialButtonRectangle1.setFont(new java.awt.Font("Roboto Medium", 1, 14)); // NOI18N
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

        rSMaterialButtonRectangle2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mtsarridho/icon/siswa.png"))); // NOI18N
        rSMaterialButtonRectangle2.setText("UPDATE");
        rSMaterialButtonRectangle2.setFont(new java.awt.Font("Roboto Medium", 1, 14)); // NOI18N
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
                    .addGroup(jpanelGradient1Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jSeparator6))
                .addContainerGap())
            .addGroup(jpanelGradient1Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jpanelGradient1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpanelGradient1Layout.createSequentialGroup()
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23)
                        .addComponent(rSComboMetro2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(30, Short.MAX_VALUE))
                    .addGroup(jpanelGradient1Layout.createSequentialGroup()
                        .addGroup(jpanelGradient1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpanelGradient1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(33, 33, 33)
                                .addComponent(txtNama1, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jpanelGradient1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jpanelGradient1Layout.createSequentialGroup()
                                    .addComponent(jLabel8)
                                    .addGap(33, 33, 33)
                                    .addComponent(txtNama2, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jpanelGradient1Layout.createSequentialGroup()
                                    .addGroup(jpanelGradient1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel15)
                                        .addComponent(jLabel16)
                                        .addComponent(jLabel17))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jpanelGradient1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jpanelGradient1Layout.createSequentialGroup()
                                            .addComponent(tTanggal1)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(bttn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(txtNama3)
                                        .addComponent(rSComboMetro1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpanelGradient1Layout.createSequentialGroup()
                                .addComponent(rSMaterialButtonRectangle2, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(rSMaterialButtonRectangle1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(46, 46, 46)))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(jpanelGradient1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jpanelGradient1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(130, 130, 130)
                    .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 292, Short.MAX_VALUE)))
        );
        jpanelGradient1Layout.setVerticalGroup(
            jpanelGradient1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanelGradient1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel14)
                .addGap(10, 10, 10)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jpanelGradient1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtNama1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(jpanelGradient1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtNama2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addGroup(jpanelGradient1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(rSComboMetro1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(jpanelGradient1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(txtNama3, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(jpanelGradient1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(bttn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tTanggal1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(jpanelGradient1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(rSComboMetro2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(jpanelGradient1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rSMaterialButtonRectangle1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rSMaterialButtonRectangle2, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(59, Short.MAX_VALUE))
            .addGroup(jpanelGradient1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jpanelGradient1Layout.createSequentialGroup()
                    .addGap(0, 126, Short.MAX_VALUE)
                    .addGroup(jpanelGradient1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(0, 497, Short.MAX_VALUE)))
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

    private void txtNama1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNama1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNama1ActionPerformed

    private void txtNama2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNama2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNama2ActionPerformed

    private void txtNama3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNama3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNama3ActionPerformed

    private void rSMaterialButtonRectangle1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonRectangle1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rSMaterialButtonRectangle1ActionPerformed

    private void rSMaterialButtonRectangle2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonRectangle2ActionPerformed
        updateDataSiswa();
    }//GEN-LAST:event_rSMaterialButtonRectangle2ActionPerformed

    private void rSMaterialButtonRectangle1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rSMaterialButtonRectangle1MouseClicked
        dispose();
    }//GEN-LAST:event_rSMaterialButtonRectangle1MouseClicked

    private void jpanelGradient1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpanelGradient1MousePressed
        xx = evt.getX();
        xy = evt.getY();
    }//GEN-LAST:event_jpanelGradient1MousePressed

    private void jpanelGradient1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpanelGradient1MouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xx, y - xy);
    }//GEN-LAST:event_jpanelGradient1MouseDragged

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
            java.util.logging.Logger.getLogger(FormEditSiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormEditSiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormEditSiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormEditSiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //</editor-fold>

        /* Create and display the form */
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bttn;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private com.mtsarridho.pallate.JpanelGradient jpanelGradient1;
    private rojerusan.RSComboMetro rSComboMetro1;
    private rojerusan.RSComboMetro rSComboMetro2;
    private rojerusan.RSMaterialButtonRectangle rSMaterialButtonRectangle1;
    private rojerusan.RSMaterialButtonRectangle rSMaterialButtonRectangle2;
    private javax.swing.JTextField tTanggal1;
    private javax.swing.JTextField txtNama1;
    private javax.swing.JTextField txtNama2;
    private javax.swing.JTextField txtNama3;
    // End of variables declaration//GEN-END:variables

    
}
