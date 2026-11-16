/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mtsarridho.main;

import com.mtsarridho.config.KoneksiDatabase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author fazar
 */
public class rombeltambah extends javax.swing.JPanel {
    private JDialog parentDialog;
    /**
     * Creates new form Rombel
     * @param parentDialog
     */
    
    
    
    public rombeltambah(JDialog parentDialog) {
        this.parentDialog = parentDialog;
        initComponents();
        setupForm();
        
        tingkatkelas.addItem("KELAS 7");
        tingkatkelas.addItem("KELAS 8");
        tingkatkelas.addItem("KELAS 9");
        loadGuruKeComboBox();        
    }   

    private void loadGuruKeComboBox() {
        try {
        Connection conn = KoneksiDatabase.getKoneksi(); 
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT nik, nama FROM tb_guru");
        DefaultComboBoxModel<GuruItem> model = new DefaultComboBoxModel<>();
            model.addElement(new GuruItem("", "--- PILIH ---")); // Tambahkan pilihan kosong di awal
            while (rs.next()) {
                String nik = rs.getString("nik");
                String nama = rs.getString("nama");
                model.addElement(new GuruItem(nik, nama));
            }
        walikelas.setModel(model);
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Gagal memuat data guru\n" + e.getMessage());
    }
    }
    
    public class GuruItem {
    private String nik;
    private String nama;

    public GuruItem(String nik, String nama) {
        this.nik = nik;
        this.nama = nama;
    }

    public String getNik() {
        return nik;
    }

    public String getNama() {
        return nama;
    }

    @Override
    public String toString() {
        return nama;
    }
    }
    
    private void setupForm() {
    tingkatkelas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {
    "--- PILIH ---", "KELAS 7", "KELAS 8", "KELAS 9"
    }));
    loadGuruKeComboBox();
    }

    
    private rombelawal parent;
    public rombeltambah(rombelawal parent) {
    this.parent = parent;
    initComponents();
    setupForm();
    }

    private DefaultTableModel tableModel;
    public rombeltambah(DefaultTableModel model) {
        this.tableModel = model;
        initComponents();
        setupForm();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jRadioButton1 = new javax.swing.JRadioButton();
        tambahrombel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        simpan = new rojerusan.RSMaterialButtonRectangle();
        batal = new rojerusan.RSMaterialButtonRectangle();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        walikelas = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        tahunajaran = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        tingkatkelas = new javax.swing.JComboBox<>();
        namarombel = new javax.swing.JComboBox<>();
        selesai = new rojerusan.RSMaterialButtonRectangle();

        jRadioButton1.setText("jRadioButton1");

        setLayout(new java.awt.CardLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jLabel2.setText("Tambah Rombel");

        simpan.setText("SIMPAN");
        simpan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                simpanMouseClicked(evt);
            }
        });
        simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simpanActionPerformed(evt);
            }
        });

        batal.setBackground(new java.awt.Color(255, 0, 0));
        batal.setText("BATAL");
        batal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                batalActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        jLabel3.setText("Nama Rombel");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        jLabel4.setText("Wali Kelas");

        walikelas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                walikelasActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        jLabel5.setText("Tingkat Kelas");

        tahunajaran.setText("ketik");
        tahunajaran.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tahunajaranMouseClicked(evt);
            }
        });
        tahunajaran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tahunajaranActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        jLabel6.setText("Tahun Ajaran");

        tingkatkelas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tingkatkelasActionPerformed(evt);
            }
        });

        namarombel.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--- PILIH ---" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(walikelas, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tingkatkelas, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(namarombel, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tahunajaran)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(jLabel6))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tingkatkelas, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(namarombel, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(walikelas, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tahunajaran, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        selesai.setBackground(new java.awt.Color(0, 204, 0));
        selesai.setText("SELESAI");
        selesai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selesaiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout tambahrombelLayout = new javax.swing.GroupLayout(tambahrombel);
        tambahrombel.setLayout(tambahrombelLayout);
        tambahrombelLayout.setHorizontalGroup(
            tambahrombelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tambahrombelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tambahrombelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tambahrombelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(selesai, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(tambahrombelLayout.createSequentialGroup()
                        .addGroup(tambahrombelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(tambahrombelLayout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(simpan, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(batal, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 691, Short.MAX_VALUE)))
                .addContainerGap())
        );
        tambahrombelLayout.setVerticalGroup(
            tambahrombelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tambahrombelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(tambahrombelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(simpan, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(batal, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(selesai, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        add(tambahrombel, "card2");
    }// </editor-fold>//GEN-END:initComponents

    private void selesaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selesaiActionPerformed
        SwingUtilities.getWindowAncestor(this).dispose();
    }//GEN-LAST:event_selesaiActionPerformed

    private void tingkatkelasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tingkatkelasActionPerformed
            String tingkat = tingkatkelas.getSelectedItem().toString();
            updateNamaRombelOptions(tingkat);
    }//GEN-LAST:event_tingkatkelasActionPerformed

    private void batalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_batalActionPerformed
        SwingUtilities.getWindowAncestor(this).dispose();
    }//GEN-LAST:event_batalActionPerformed

    private void simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpanActionPerformed

    }//GEN-LAST:event_simpanActionPerformed

    private void simpanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_simpanMouseClicked
    String tkelas = tingkatkelas.getSelectedItem().toString();
    String rombel = namarombel.getSelectedItem().toString();
    String ajaran = tahunajaran.getText();
    
    if (tkelas.equals("--- PILIH ---")) {
    JOptionPane.showMessageDialog(this, "Pilih tingkat kelas!", "Validasi", JOptionPane.WARNING_MESSAGE);
    return;
    }
    
    if (tingkatkelas.getSelectedIndex() == 0 || walikelas.getSelectedIndex() == -1) {
    JOptionPane.showMessageDialog(this, "Pilih tingkat kelas dan wali kelas!", "Validasi", JOptionPane.WARNING_MESSAGE);
    return;
    }

    GuruItem selectedGuru = (GuruItem) walikelas.getSelectedItem();
    if (rombel.isEmpty() || ajaran.isEmpty() || 
        selectedGuru == null || tkelas.equals("--- PILIH ---")) {
        JOptionPane.showMessageDialog(this, "Semua field harus diisi!", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    String wkelas = selectedGuru.getNik(); 

    try {
        Connection conn = KoneksiDatabase.getKoneksi();
        String sql = "INSERT INTO tb_rombel (tingkat_kelas, nama_rombel, wali_kelas, tahun_ajaran) VALUES (?, ?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, tkelas);
        pstmt.setString(2, rombel);
        pstmt.setString(3, wkelas);
        pstmt.setString(4, ajaran);

        int result = pstmt.executeUpdate();

        if (result > 0) {
            JOptionPane.showMessageDialog(this, "Data Rombel berhasil disimpan!", "Sukses", JOptionPane.INFORMATION_MESSAGE);

        } else {
            JOptionPane.showMessageDialog(this, "Gagal menyimpan data rombel!", "Error", JOptionPane.ERROR_MESSAGE);
        }

        // Kosongkan inputan setelah penyimpanan
        tingkatkelas.setSelectedIndex(0);
        namarombel.setSelectedIndex(0);
        tahunajaran.setText("");
        walikelas.setSelectedIndex(0);

        if (parent != null) {
                parent.tampilkantablerombel();
            }
        
        pstmt.close();
//        conn.close();

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error database: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
    }


    }//GEN-LAST:event_simpanMouseClicked

    private void walikelasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_walikelasActionPerformed
        GuruItem selectedGuru = (GuruItem) walikelas.getSelectedItem();
        if (selectedGuru != null) {
            String nikWaliKelas = selectedGuru.getNik();
            System.out.println("Guru dipilih: " + selectedGuru.getNama() + " (NIK: " + nikWaliKelas + ")");
        }
    }//GEN-LAST:event_walikelasActionPerformed

    private void tahunajaranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tahunajaranActionPerformed
        tahunajaran.setText("");
    }//GEN-LAST:event_tahunajaranActionPerformed

    private void tahunajaranMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tahunajaranMouseClicked
        tahunajaran.setText("");
    }//GEN-LAST:event_tahunajaranMouseClicked

    private void updateNamaRombelOptions(String tingkat) {
    namarombel.removeAllItems();
    namarombel.addItem("--- PILIH ---");

    if (tingkat.equals("KELAS 7")) {
        namarombel.addItem("KELAS 7A");
        namarombel.addItem("KELAS 7B");
        namarombel.addItem("KELAS 7C");
        namarombel.addItem("KELAS 7D");
    } else if (tingkat.equals("KELAS 8")) {
        namarombel.addItem("KELAS 8A");
        namarombel.addItem("KELAS 8B");
        namarombel.addItem("KELAS 8C");
        namarombel.addItem("KELAS 8D");
    } else if (tingkat.equals("KELAS 9")) {
        namarombel.addItem("KELAS 9A");
        namarombel.addItem("KELAS 9B");
        namarombel.addItem("KELAS 9C");
        namarombel.addItem("KELAS 9D");
    }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojerusan.RSMaterialButtonRectangle batal;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JComboBox<String> namarombel;
    private rojerusan.RSMaterialButtonRectangle selesai;
    private rojerusan.RSMaterialButtonRectangle simpan;
    private javax.swing.JTextField tahunajaran;
    private javax.swing.JPanel tambahrombel;
    private javax.swing.JComboBox<String> tingkatkelas;
    private javax.swing.JComboBox<GuruItem> walikelas;
    // End of variables declaration//GEN-END:variables

    private void clearFields() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}