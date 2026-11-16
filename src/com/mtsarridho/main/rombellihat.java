
package com.mtsarridho.main;

import java.awt.Frame;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
    
public class rombellihat extends javax.swing.JPanel {

   
    public rombellihat() {
        initComponents();
    }
    
    public rombellihat(String namaRombel, String tingkat, String wali) {
       initComponents();
       
       namarombel.setText(namaRombel);
       walikelas.setText(tingkat);     
       tingkatkelas.setText(wali);      
       
           namarombel.setEditable(false);
            tingkatkelas.setEditable(false);
            walikelas.setEditable(false);
            
       loadSiswaRombel(namaRombel);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        tambah = new rojerusan.RSMaterialButtonRectangle();
        hapus = new rojerusan.RSMaterialButtonRectangle();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablerombel = new com.mtsarridho.pallate.JTableCustom();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        namarombel = new javax.swing.JTextField();
        tingkatkelas = new javax.swing.JTextField();
        walikelas = new javax.swing.JTextField();
        batal = new rojerusan.RSMaterialButtonRectangle();

        setLayout(new java.awt.BorderLayout());

        jPanel1.setPreferredSize(new java.awt.Dimension(1040, 731));

        tambah.setText("TAMBAH");
        tambah.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tambahMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tambahMouseEntered(evt);
            }
        });
        tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambahActionPerformed(evt);
            }
        });

        hapus.setBackground(new java.awt.Color(255, 0, 0));
        hapus.setText("HAPUS");
        hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapusActionPerformed(evt);
            }
        });

        tablerombel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Nama", "NISN", "Tingkat"
            }
        ));
        tablerombel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablerombelMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tablerombel);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("NAMA ROMBEL  :");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel2.setText("DAFTAR SISWA DALAM ROMBEL");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
        jLabel3.setText("TINGKAT KELAS     :");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
        jLabel4.setText("WALI KELAS           :");

        tingkatkelas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tingkatkelasActionPerformed(evt);
            }
        });

        walikelas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                walikelasActionPerformed(evt);
            }
        });

        batal.setBackground(new java.awt.Color(255, 153, 0));
        batal.setText("BATAL");
        batal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                batalMouseClicked(evt);
            }
        });
        batal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                batalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1063, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(tambah, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(hapus, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(batal, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(walikelas, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(tingkatkelas, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(namarombel, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(476, 476, 476)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addContainerGap(302, Short.MAX_VALUE)
                    .addComponent(jLabel2)
                    .addGap(228, 228, 228)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(107, 107, 107)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(namarombel, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tingkatkelas, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(walikelas)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(hapus, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tambah, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(batal, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(23, 23, 23)
                    .addComponent(jLabel2)
                    .addContainerGap(693, Short.MAX_VALUE)))
        );

        add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapusActionPerformed
    int selectedRow = tablerombel.getSelectedRow();
    
    if (selectedRow != -1) {
        DefaultTableModel model = (DefaultTableModel) tablerombel.getModel();
        String nisn = model.getValueAt(selectedRow, 1).toString(); 
        String namaRombel = namarombel.getText(); 

        int confirm = JOptionPane.showConfirmDialog(this, 
            "Apakah yakin ingin menghapus siswa dari rombel?",
            "Konfirmasi Hapus",
            JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            try {
                Connection conn = com.mtsarridho.config.KoneksiDatabase.getKoneksi();
                String sql = "DELETE FROM tb_rombel_siswa WHERE id_rombel = (SELECT id_rombel FROM tb_rombel WHERE nama_rombel = ?) AND nisn = ?";
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setString(1, namaRombel);
                pst.setString(2, nisn);

                int result = pst.executeUpdate();
                if (result > 0) {
                    JOptionPane.showMessageDialog(this, "Siswa berhasil dihapus dari rombel.");
                    model.removeRow(selectedRow); 
                } else {
                    JOptionPane.showMessageDialog(this, "Gagal menghapus siswa dari rombel!", "Error", JOptionPane.ERROR_MESSAGE);
                }

                pst.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error saat menghapus siswa: " + e.getMessage());
            }
        }
    } else {
        JOptionPane.showMessageDialog(this, "Pilih siswa yang ingin dihapus terlebih dahulu!");
    }
    }//GEN-LAST:event_hapusActionPerformed

    private void tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahActionPerformed

    }//GEN-LAST:event_tambahActionPerformed

    private void tambahMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tambahMouseClicked
            String namaRombel = namarombel.getText();
            String tingkatKelas = tingkatkelas.getText();
            String waliKelas = walikelas.getText();

            rombellihattambah panelTambah = new rombellihattambah(this, namaRombel, tingkatKelas, waliKelas);
            JDialog dialog = new JDialog((Frame) null, "Tambah Siswa ke Rombel", true);
            dialog.getContentPane().add(panelTambah);
            dialog.setSize(1100, 800);
            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);
    }//GEN-LAST:event_tambahMouseClicked

    private void tambahMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tambahMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tambahMouseEntered

    private void tablerombelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablerombelMouseClicked

    }//GEN-LAST:event_tablerombelMouseClicked

    private void walikelasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_walikelasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_walikelasActionPerformed

    private void tingkatkelasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tingkatkelasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tingkatkelasActionPerformed

    private void batalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_batalActionPerformed
 
    }//GEN-LAST:event_batalActionPerformed

    private void batalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_batalMouseClicked
        SwingUtilities.getWindowAncestor(this).dispose();
    }//GEN-LAST:event_batalMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojerusan.RSMaterialButtonRectangle batal;
    private rojerusan.RSMaterialButtonRectangle hapus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField namarombel;
    private com.mtsarridho.pallate.JTableCustom tablerombel;
    private rojerusan.RSMaterialButtonRectangle tambah;
    private javax.swing.JTextField tingkatkelas;
    private javax.swing.JTextField walikelas;
    // End of variables declaration//GEN-END:variables

    void loadSiswaRombel(String namaRombel) {
        DefaultTableModel model = (DefaultTableModel) tablerombel.getModel();
            model.setRowCount(0); 

            try {
                Connection conn = com.mtsarridho.config.KoneksiDatabase.getKoneksi();
                    String sql =    "SELECT s.nama, s.nisn, s.tingkat FROM tb_rombel_siswa rs " +
                                    "JOIN tb_siswa s ON rs.nisn = s.nisn " +
                                    "WHERE rs.id_rombel = (SELECT id_rombel FROM tb_rombel WHERE nama_rombel = ?)";

                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setString(1, namaRombel);
                ResultSet rs = pst.executeQuery();

                while (rs.next()) {
                    model.addRow(new Object[]{
                        rs.getString("nama"),
                        rs.getString("nisn"),
                        rs.getString("tingkat")
                    });
                }

                rs.close();
                pst.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Gagal load siswa rombel: " + e.getMessage());
            }
    }

    

    
}