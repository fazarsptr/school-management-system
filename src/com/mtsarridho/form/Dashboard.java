/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mtsarridho.form;

import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.mtsarridho.config.KoneksiDatabase;
import java.awt.Color;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
/**
 *
 * @author zaena
 */
public class Dashboard extends javax.swing.JPanel {

    /**
     * Creates new form Dashboard
     */
    public Dashboard() {
        initComponents();
        loadJumlahData();
        tampilkantablerombel();
        tampilkanDataGuru();
        tampilkanDataSiswa();
    }
    
    private void tampilkanDataSiswa() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("NO");
        model.addColumn("NAMA");
        model.addColumn("NISN");
        model.addColumn("KELAS");

        try {
            Connection conn = KoneksiDatabase.getKoneksi();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM tb_siswa");

            int no = 1;
            while (rs.next()) {
                model.addRow(new Object[]{
                    no++,
                    rs.getString("nama"),
                    rs.getString("nisn"),
                    rs.getString("tingkat")
                });
            }

            tableDataSiswa1.setModel(model);
            
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
            centerRenderer.setBackground(new Color(173, 216, 230)); // Light Blue (RGB)
            centerRenderer.setForeground(Color.BLACK); // Warna teks
            centerRenderer.setOpaque(true);

            for (int i = 0; i < tableDataSiswa1.getColumnModel().getColumnCount(); i++) {
                tableDataSiswa1.getColumnModel().getColumn(i).setHeaderRenderer(centerRenderer);
            }

            TableColumnModel columnModel = tableDataSiswa1.getColumnModel();
            // Atur lebar kolom
            columnModel.getColumn(0).setMinWidth(40);
            columnModel.getColumn(0).setMaxWidth(40);
            columnModel.getColumn(0).setPreferredWidth(40); // Kolom NO kecil

            // Menetapkan renderer ke setiap kolom
            DefaultTableCellRenderer colRenderer = new DefaultTableCellRenderer();
            colRenderer.setHorizontalAlignment(SwingConstants.CENTER);

            // Atur semua kolom agar isi cell-nya di tengah
            for (int i = 0; i < tableDataSiswa1.getColumnCount(); i++) {
                tableDataSiswa1.getColumnModel().getColumn(i).setCellRenderer(colRenderer);
            }
            
            // Terapkan pengaturan tampilan
            //applyTableFormatting();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal menampilkan data siswa\n" + e.getMessage());
        }
    }
    
    private void tampilkanDataGuru() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("NO");
        model.addColumn("NAMA");
        model.addColumn("NIK/NUPTK");
        model.addColumn("JENIS KELAMIN");

        try {
            Connection conn = KoneksiDatabase.getKoneksi();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM tb_guru");

            int no = 1;
            while (rs.next()) {
                model.addRow(new Object[]{
                    no++,
                    rs.getString("nama"),
                    rs.getString("nik"),
                    rs.getString("jenis_kelamin")
                });
            }
            
            tableDataGuru.setModel(model);   
            
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
            centerRenderer.setBackground(new Color(173, 216, 230)); // Light Blue (RGB)
            centerRenderer.setForeground(Color.BLACK); // Warna teks
            centerRenderer.setOpaque(true);

            for (int i = 0; i < tableDataGuru.getColumnModel().getColumnCount(); i++) {
                tableDataGuru.getColumnModel().getColumn(i).setHeaderRenderer(centerRenderer);
            }

            TableColumnModel columnModel = tableDataGuru.getColumnModel();
            // Atur lebar kolom
            columnModel.getColumn(0).setMinWidth(40);
            columnModel.getColumn(0).setMaxWidth(40);
            columnModel.getColumn(0).setPreferredWidth(40); // Kolom NO kecil

            // Menetapkan renderer ke setiap kolom
            DefaultTableCellRenderer colRenderer = new DefaultTableCellRenderer();
            colRenderer.setHorizontalAlignment(SwingConstants.CENTER);

            // Atur semua kolom agar isi cell-nya di tengah
            for (int i = 0; i < tableDataGuru.getColumnCount(); i++) {
                tableDataGuru.getColumnModel().getColumn(i).setCellRenderer(colRenderer);
            }
            // Terapkan pengaturan tampilan
            //applyTableFormatting();
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal menampilkan data guru\n" + e.getMessage());
        }
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
    
    private int getJumlahData(String namaTabel) {
        int jumlah = 0;
        try {
            Connection conn = KoneksiDatabase.getKoneksi();
            String sql = "SELECT COUNT(*) FROM " + namaTabel;
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                jumlah = rs.getInt(1);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jumlah;
    }
    
    private void loadJumlahData() {
        int jumlahSiswa = getJumlahData("tb_siswa");    // ganti dengan nama tabel siswa kamu
        int jumlahGuru = getJumlahData("tb_guru");      // ganti dengan nama tabel guru kamu
        int jumlahRombel = getJumlahData("tb_rombel");  // ganti dengan nama tabel rombel kamu

        jLabel2.setText(String.valueOf(jumlahSiswa));
        jLabel4.setText(String.valueOf(jumlahGuru));
        jLabel6.setText(String.valueOf(jumlahRombel));
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
        cardSiswa = new com.mtsarridho.pallate.RoundedGradientPanel();
        jLabel1 = new javax.swing.JLabel();
        logo = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cardGuru = new com.mtsarridho.pallate.RoundedGradientPanel();
        jLabel3 = new javax.swing.JLabel();
        logo1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cardRombel = new com.mtsarridho.pallate.RoundedGradientPanel();
        jLabel5 = new javax.swing.JLabel();
        logo2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableDataSiswa1 = new com.mtsarridho.pallate.JTableCustom();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableDataGuru = new com.mtsarridho.pallate.JTableCustom();
        jScrollPane4 = new javax.swing.JScrollPane();
        tablerombel = new com.mtsarridho.pallate.JTableCustom();

        setLayout(new java.awt.BorderLayout());

        jpanelGradient1.setBackground(new java.awt.Color(255, 255, 255));
        jpanelGradient1.setColorStart(new java.awt.Color(255, 255, 255));

        cardSiswa.setColorEnd(new java.awt.Color(153, 255, 255));
        cardSiswa.setColorStart(new java.awt.Color(51, 51, 255));
        cardSiswa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cardSiswaMouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("JUMLAH SISWA");

        logo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mtsarridho/icon/student (1).png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 70)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("0");

        javax.swing.GroupLayout cardSiswaLayout = new javax.swing.GroupLayout(cardSiswa);
        cardSiswa.setLayout(cardSiswaLayout);
        cardSiswaLayout.setHorizontalGroup(
            cardSiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardSiswaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(cardSiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(cardSiswaLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addContainerGap(98, Short.MAX_VALUE))
                    .addGroup(cardSiswaLayout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(logo, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        cardSiswaLayout.setVerticalGroup(
            cardSiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardSiswaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(cardSiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(logo, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        cardGuru.setColorEnd(new java.awt.Color(255, 204, 255));
        cardGuru.setColorStart(new java.awt.Color(153, 0, 204));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("JUMLAH GURU");

        logo1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        logo1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mtsarridho/icon/teacher (1).png"))); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 70)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("0");

        javax.swing.GroupLayout cardGuruLayout = new javax.swing.GroupLayout(cardGuru);
        cardGuru.setLayout(cardGuruLayout);
        cardGuruLayout.setHorizontalGroup(
            cardGuruLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardGuruLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(cardGuruLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(cardGuruLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(cardGuruLayout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(logo1, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        cardGuruLayout.setVerticalGroup(
            cardGuruLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardGuruLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(cardGuruLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(logo1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        cardRombel.setColorEnd(new java.awt.Color(255, 153, 153));
        cardRombel.setColorStart(new java.awt.Color(255, 0, 51));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("JUMLAH ROMBEL");

        logo2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        logo2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mtsarridho/icon/classroom (1).png"))); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 70)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("0");

        javax.swing.GroupLayout cardRombelLayout = new javax.swing.GroupLayout(cardRombel);
        cardRombel.setLayout(cardRombelLayout);
        cardRombelLayout.setHorizontalGroup(
            cardRombelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardRombelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(cardRombelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(cardRombelLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(cardRombelLayout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(logo2, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        cardRombelLayout.setVerticalGroup(
            cardRombelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardRombelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(cardRombelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(logo2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        tableDataSiswa1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "NO", "NAMA", "NISN", "KELAS"
            }
        ));
        jScrollPane2.setViewportView(tableDataSiswa1);

        tableDataGuru.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "NO", "NAMA", "NIK", "JENIS KELAMIN"
            }
        ));
        jScrollPane3.setViewportView(tableDataGuru);

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
        jScrollPane4.setViewportView(tablerombel);

        javax.swing.GroupLayout jpanelGradient1Layout = new javax.swing.GroupLayout(jpanelGradient1);
        jpanelGradient1.setLayout(jpanelGradient1Layout);
        jpanelGradient1Layout.setHorizontalGroup(
            jpanelGradient1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanelGradient1Layout.createSequentialGroup()
                .addGroup(jpanelGradient1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpanelGradient1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(cardSiswa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(cardGuru, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(cardRombel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpanelGradient1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 461, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3))
                    .addGroup(jpanelGradient1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane4)))
                .addContainerGap())
        );
        jpanelGradient1Layout.setVerticalGroup(
            jpanelGradient1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanelGradient1Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(jpanelGradient1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cardRombel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cardGuru, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cardSiswa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(55, 55, 55)
                .addGroup(jpanelGradient1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        add(jpanelGradient1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void cardSiswaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cardSiswaMouseClicked
        
    }//GEN-LAST:event_cardSiswaMouseClicked

    private void tablerombelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablerombelMouseClicked

    }//GEN-LAST:event_tablerombelMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.mtsarridho.pallate.RoundedGradientPanel cardGuru;
    private com.mtsarridho.pallate.RoundedGradientPanel cardRombel;
    private com.mtsarridho.pallate.RoundedGradientPanel cardSiswa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private com.mtsarridho.pallate.JpanelGradient jpanelGradient1;
    private javax.swing.JLabel logo;
    private javax.swing.JLabel logo1;
    private javax.swing.JLabel logo2;
    private com.mtsarridho.pallate.JTableCustom tableDataGuru;
    private com.mtsarridho.pallate.JTableCustom tableDataSiswa1;
    private com.mtsarridho.pallate.JTableCustom tablerombel;
    // End of variables declaration//GEN-END:variables
}
