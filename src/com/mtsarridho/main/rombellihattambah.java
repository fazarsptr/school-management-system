
package com.mtsarridho.main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class rombellihattambah extends javax.swing.JPanel {

    public rombellihattambah() {
        initComponents();
    }
    
    private void konfigurasiTableRombel() {
    tablerombel.setColumnSelectionAllowed(false); 
    tablerombel.setRowSelectionAllowed(true); 
    tablerombel.clearSelection(); 
    }

    
   
        public rombellihattambah(rombellihat panelAsal, String namaRombel, String tingkatKelas, String waliKelas) {
            initComponents(); 
            this.panelAsal = panelAsal;
            konfigurasiTableRombel();
            tablerombel.setModel(new DefaultTableModel( 
                new Object [][] {},
                new String [] { "Pilih", "Nama", "NISN", "Tingkat" }
            ) {
                Class[] types = new Class [] {
                    Boolean.class, String.class, String.class, String.class
                };
                boolean[] canEdit = new boolean [] {
                    true, false, false, false
                };

                @Override
                public Class getColumnClass(int columnIndex) {
                    return types[columnIndex];
                }

                @Override
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    if (columnIndex == 0) { 
                        String nisn = getValueAt(rowIndex, 2).toString();
                        return !nisnTerpakai.contains(nisn); 
                    }
                    return false;
                }

            });
            
            tablerombel.setCellSelectionEnabled(false);
            tablerombel.setRowSelectionAllowed(true);
            tablerombel.setColumnSelectionAllowed(false);   
            
            namarombel.setText(namaRombel);
            tingkatkelas.setText(tingkatKelas);
            walikelas.setText(waliKelas);

            namarombel.setEditable(false);
            tingkatkelas.setEditable(false);
            walikelas.setEditable(false);

            tampilkanDataSiswa(tingkatKelas);
        }


    private java.util.Set<String> nisnTerpakai = new java.util.HashSet<>(); 

    private void tampilkanDataSiswa(String tingkatKelas) {
        DefaultTableModel model = (DefaultTableModel) tablerombel.getModel();
        model.setRowCount(0);  

        try {
            Connection conn = com.mtsarridho.config.KoneksiDatabase.getKoneksi();

            String sqlNisnTerpakai = "SELECT nisn FROM tb_rombel_siswa WHERE id_rombel = (SELECT id_rombel FROM tb_rombel WHERE nama_rombel = ?)";
            PreparedStatement pstNisn = conn.prepareStatement(sqlNisnTerpakai);
            pstNisn.setString(1, namarombel.getText());
            ResultSet rsNisn = pstNisn.executeQuery();

            nisnTerpakai.clear();
            while (rsNisn.next()) {
                nisnTerpakai.add(rsNisn.getString("nisn"));
            }

            rsNisn.close();
            pstNisn.close();

            String sql = "SELECT nisn, nama, tingkat FROM tb_siswa WHERE tingkat = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, tingkatKelas);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                String nisn = rs.getString("nisn");
                if (!nisnTerpakai.contains(nisn)) {
                    model.addRow(new Object[]{
                        false, 
                        rs.getString("nama"),
                        nisn,
                        rs.getString("tingkat")
                    });
                }
            }

            rs.close();
            pst.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal load data siswa: " + e.getMessage());
        }
    }

    private rombellihat panelAsal;
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        simpan = new rojerusan.RSMaterialButtonRectangle();
        batal = new rojerusan.RSMaterialButtonRectangle();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablerombel = new com.mtsarridho.pallate.JTableCustom();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        namarombel = new javax.swing.JTextField();
        tingkatkelas = new javax.swing.JTextField();
        walikelas = new javax.swing.JTextField();
        selesai = new rojerusan.RSMaterialButtonRectangle();

        setLayout(new java.awt.BorderLayout());

        jPanel1.setPreferredSize(new java.awt.Dimension(1040, 731));

        simpan.setBackground(new java.awt.Color(0, 51, 204));
        simpan.setText("SIMPAN");
        simpan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                simpanMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                simpanMouseEntered(evt);
            }
        });
        simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simpanActionPerformed(evt);
            }
        });

        batal.setBackground(new java.awt.Color(255, 0, 0));
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

        tablerombel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Pilih", "Nama", "NISN", "Tingkat"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tablerombel.setColumnSelectionAllowed(true);
        tablerombel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablerombelMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tablerombel);
        tablerombel.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("NAMA ROMBEL  :");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel2.setText("TAMBAH SISWA KEDALAM ROMBEL");

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

        selesai.setBackground(new java.awt.Color(0, 153, 51));
        selesai.setText("SELESAI");
        selesai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                selesaiMouseClicked(evt);
            }
        });
        selesai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selesaiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2)
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
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(simpan, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(batal, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(selesai, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addContainerGap(226, Short.MAX_VALUE)
                    .addComponent(jLabel2)
                    .addGap(228, 228, 228)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(96, 96, 96)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(namarombel, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tingkatkelas)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(walikelas)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(simpan, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(batal, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(selesai, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(23, 23, 23)
                    .addComponent(jLabel2)
                    .addContainerGap(709, Short.MAX_VALUE)))
        );

        add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void batalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_batalActionPerformed
 
    }//GEN-LAST:event_batalActionPerformed

    private void simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpanActionPerformed

    }//GEN-LAST:event_simpanActionPerformed

    private void simpanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_simpanMouseClicked
    String namaRombel = namarombel.getText();

        try {
            Connection conn = com.mtsarridho.config.KoneksiDatabase.getKoneksi();

            String getIdQuery = "SELECT id_rombel FROM tb_rombel WHERE nama_rombel = ?";
            PreparedStatement pstId = conn.prepareStatement(getIdQuery);
            pstId.setString(1, namaRombel);
            ResultSet rs = pstId.executeQuery();

            int idRombel = -1;
            if (rs.next()) {
                idRombel = rs.getInt("id_rombel");
            } else {
                JOptionPane.showMessageDialog(this, "ID rombel tidak ditemukan!");
                return;
            }

            rs.close();
            pstId.close();

            DefaultTableModel model = (DefaultTableModel) tablerombel.getModel();
            String insertSQL = "INSERT INTO tb_rombel_siswa (id_rombel, nisn) VALUES (?, ?)";
            PreparedStatement pstInsert = conn.prepareStatement(insertSQL);

            int rows = model.getRowCount();
            int count = 0;
            for (int i = 0; i < rows; i++) {
                Boolean selected = (Boolean) model.getValueAt(i, 0);
                String nisn = model.getValueAt(i, 2).toString();

                if (selected != null && selected && !nisnTerpakai.contains(nisn)) {
                    pstInsert.setInt(1, idRombel);
                    pstInsert.setString(2, nisn);
                    pstInsert.addBatch();
                    count++;
                }
            }

            if (count > 0) {
                pstInsert.executeBatch();
                JOptionPane.showMessageDialog(this, "Berhasil menambahkan " + count + " siswa ke rombel.");

                tampilkanDataSiswa(tingkatkelas.getText());

                if (panelAsal != null) {
                    panelAsal.loadSiswaRombel(namarombel.getText());
                    panelAsal.revalidate();
                    panelAsal.repaint();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Belum ada siswa yang dipilih.");
            }

            pstInsert.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal simpan siswa: " + e.getMessage());
        }
    }//GEN-LAST:event_simpanMouseClicked

    private void simpanMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_simpanMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_simpanMouseEntered

    private void tablerombelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablerombelMouseClicked

    }//GEN-LAST:event_tablerombelMouseClicked

    private void walikelasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_walikelasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_walikelasActionPerformed

    private void tingkatkelasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tingkatkelasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tingkatkelasActionPerformed

    private void selesaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selesaiActionPerformed

    }//GEN-LAST:event_selesaiActionPerformed

    private void batalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_batalMouseClicked
        SwingUtilities.getWindowAncestor(this).dispose();
    }//GEN-LAST:event_batalMouseClicked

    private void selesaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selesaiMouseClicked
        SwingUtilities.getWindowAncestor(this).dispose();
    }//GEN-LAST:event_selesaiMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojerusan.RSMaterialButtonRectangle batal;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField namarombel;
    private rojerusan.RSMaterialButtonRectangle selesai;
    private rojerusan.RSMaterialButtonRectangle simpan;
    private com.mtsarridho.pallate.JTableCustom tablerombel;
    private javax.swing.JTextField tingkatkelas;
    private javax.swing.JTextField walikelas;
    // End of variables declaration//GEN-END:variables

//    private void pencarian() {
//            String searchKeyword = search.getText();
//            tampilkanDataSiswa(searchKeyword); 
//            tbl.Model.setData(list);
//    }

    

    
}