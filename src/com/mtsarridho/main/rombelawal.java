
package com.mtsarridho.main;

import com.mtsarridho.config.KoneksiDatabase;
import java.awt.Color;
import java.awt.Frame;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;


public class rombelawal extends javax.swing.JPanel {

   
    public rombelawal() {
        initComponents();
        tampilkantablerombel();
    }
    
    void tampilkantablerombel() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("NO");
        model.addColumn("Nama Rombel");
        model.addColumn("Wali Kelas"); 
        model.addColumn("Tingkat Kelas");
        model.addColumn("Tahun Ajaran");

        try {
            Connection conn = KoneksiDatabase.getKoneksi();
            Statement stmt = conn.createStatement();

            String sql = "SELECT r.nama_rombel, g.nama AS nama_guru, r.tingkat_kelas, r.tahun_ajaran " +
                         "FROM tb_rombel r " +
                         "JOIN tb_guru g ON r.wali_kelas = g.nik " +
                         "ORDER BY CAST(SUBSTRING(r.tingkat_kelas, 1, 1) AS UNSIGNED), SUBSTRING(r.tingkat_kelas, 2)";

            ResultSet rs = stmt.executeQuery(sql);
            int no = 1;
            while (rs.next()) {
                model.addRow(new Object[]{
                    no++,
                    rs.getString("nama_rombel"),
                    rs.getString("nama_guru"),     
                    rs.getString("tingkat_kelas"),
                    rs.getString("tahun_ajaran")
                });
            }

            tablerombel.setModel(model);

            // Styling tabel tetap
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
            centerRenderer.setBackground(new Color(173, 216, 230));
            centerRenderer.setForeground(Color.BLACK);
            centerRenderer.setOpaque(true);

            for (int i = 0; i < tablerombel.getColumnModel().getColumnCount(); i++) {
                tablerombel.getColumnModel().getColumn(i).setHeaderRenderer(centerRenderer);
            }

            TableColumnModel columnModel = tablerombel.getColumnModel();
            columnModel.getColumn(0).setMinWidth(40);
            columnModel.getColumn(0).setPreferredWidth(40);
            columnModel.getColumn(0).setMaxWidth(40);

            DefaultTableCellRenderer colRenderer = new DefaultTableCellRenderer();
            colRenderer.setHorizontalAlignment(SwingConstants.CENTER);
            for (int i = 0; i < tablerombel.getColumnCount(); i++) {
                tablerombel.getColumnModel().getColumn(i).setCellRenderer(colRenderer);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal menampilkan data rombel\n" + e.getMessage());
        }
    }


    
    public DefaultTableModel getTableModel() {
    return (DefaultTableModel) tablerombel.getModel();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        tambah = new rojerusan.RSMaterialButtonRectangle();
        edit = new rojerusan.RSMaterialButtonRectangle();
        hapus = new rojerusan.RSMaterialButtonRectangle();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablerombel = new com.mtsarridho.pallate.JTableCustom();
        lihat = new rojerusan.RSMaterialButtonRectangle();

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

        edit.setBackground(new java.awt.Color(255, 153, 0));
        edit.setText("EDIT");
        edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editActionPerformed(evt);
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
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Tingkat Kelas", "Nama Rombel", "Wali Kelas", "Tahun Ajaran"
            }
        ));
        tablerombel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablerombelMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tablerombel);

        lihat.setBackground(new java.awt.Color(0, 153, 153));
        lihat.setText("LIHAT");
        lihat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lihatMouseClicked(evt);
            }
        });
        lihat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lihatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(tambah, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(edit, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(hapus, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(lihat, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 260, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tambah, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hapus, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(edit, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lihat, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 808, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapusActionPerformed
    int selectedRow = tablerombel.getSelectedRow();
    
    if (selectedRow != -1) {
        DefaultTableModel model = (DefaultTableModel) tablerombel.getModel();
        String namaRombel = model.getValueAt(selectedRow, 1).toString();
        
        int confirm = JOptionPane.showConfirmDialog(this, 
            "Apakah yakin ingin menghapus rombel \"" + namaRombel + "\"?",
            "Konfirmasi Hapus",
            JOptionPane.YES_NO_OPTION
        );
        
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                java.sql.Connection conn = com.mtsarridho.config.KoneksiDatabase.getKoneksi();
                String sql = "DELETE FROM tb_rombel WHERE nama_rombel = ?";
                java.sql.PreparedStatement pst = conn.prepareStatement(sql);
                pst.setString(1, namaRombel);
                
                int result = pst.executeUpdate();
                if (result > 0) {
                    model.removeRow(selectedRow); 
                    JOptionPane.showMessageDialog(this, "Data berhasil dihapus.");
                } else {
                    JOptionPane.showMessageDialog(this, "Gagal menghapus data!", "Error", JOptionPane.ERROR_MESSAGE);
                }

                pst.close();
                // conn.close(); 

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error saat menghapus data: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    } else {
        JOptionPane.showMessageDialog(null, "PILIH DATA YANG INGIN DIHAPUS!");
    }
    }//GEN-LAST:event_hapusActionPerformed

    private void tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahActionPerformed
            rombeltambah tambahPanel = new rombeltambah(this);
            JDialog dialog = new JDialog((Frame) null, "TAMBAH", true);
            dialog.getContentPane().add(tambahPanel);
            dialog.setSize(800, 800); // ukuran sesuai kebutuhan
            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);
    }//GEN-LAST:event_tambahActionPerformed

    private void tambahMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tambahMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tambahMouseClicked

    private void tambahMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tambahMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tambahMouseEntered

    private void tablerombelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablerombelMouseClicked

    }//GEN-LAST:event_tablerombelMouseClicked

    private void editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editActionPerformed
        int selectedRow = tablerombel.getSelectedRow();

        if (selectedRow != -1) {
            DefaultTableModel model = (DefaultTableModel) tablerombel.getModel();

            String namaRombel = model.getValueAt(selectedRow, 1).toString(); 
            String waliKelas = model.getValueAt(selectedRow, 2).toString();  
            String tingkatKelas = model.getValueAt(selectedRow, 3).toString();
            String tahunAjaran = model.getValueAt(selectedRow, 4).toString();

            rombeledit editPanel = new rombeledit(model, selectedRow, namaRombel, waliKelas, tingkatKelas, tahunAjaran, new JDialog());
            JDialog dialog = new JDialog((Frame) null, "EDIT DATA ROMBEL", true);
            dialog.getContentPane().add(editPanel);
            dialog.setSize(800, 800); // Ukuran sesuai kebutuhan
            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "PILIH DATA YANG INGIN DIEDIT!");
        }
    }//GEN-LAST:event_editActionPerformed

    private void lihatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lihatActionPerformed

    }//GEN-LAST:event_lihatActionPerformed

    private void lihatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lihatMouseClicked
        int selectedRow = tablerombel.getSelectedRow();
        if (selectedRow != -1) {
            String namarombel = tablerombel.getValueAt(selectedRow, 1).toString();
            String tingkatkelas = tablerombel.getValueAt(selectedRow, 2).toString();
            String walikelas = tablerombel.getValueAt(selectedRow, 3).toString();

            rombellihat lihatPanel = new rombellihat(namarombel, tingkatkelas, walikelas);
            JDialog dialog = new JDialog();
            dialog.setContentPane(lihatPanel);
            dialog.pack();
            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "PILIH DATA YANG INGIN DILIHAT!");
        }

    }//GEN-LAST:event_lihatMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojerusan.RSMaterialButtonRectangle edit;
    private rojerusan.RSMaterialButtonRectangle hapus;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private rojerusan.RSMaterialButtonRectangle lihat;
    private com.mtsarridho.pallate.JTableCustom tablerombel;
    private rojerusan.RSMaterialButtonRectangle tambah;
    // End of variables declaration//GEN-END:variables

    

    
}