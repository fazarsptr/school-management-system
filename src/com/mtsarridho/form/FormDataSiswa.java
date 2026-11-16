
package com.mtsarridho.form;

import java.sql.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import java.sql.Connection;
import com.mtsarridho.config.KoneksiDatabase;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;



public class FormDataSiswa extends javax.swing.JPanel implements DataChangedListener {
    
    private boolean ascending = true; // Untuk melacak arah pengurutan
    private int lastSortedColumn = -1; // Untuk melacak kolom terakhir yang diurutkan
    
    @Override
    public void onDataChanged() {
        tampilkanDataSiswa();
    }
    
    public FormDataSiswa() {
        initComponents();
        tampilkanDataSiswa();
        setupTableHeaderListener();
    }
    
    private void setupTableHeaderListener() {
        JTableHeader header = tableDataSiswa1.getTableHeader();
        header.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int columnIndex = tableDataSiswa1.columnAtPoint(e.getPoint());
                
                // Jika mengklik kolom yang sama, ubah arah pengurutan
                if (columnIndex == lastSortedColumn) {
                    ascending = !ascending;
                } else {
                    // Jika kolom baru, mulai dengan ascending
                    ascending = true;
                }
                
                lastSortedColumn = columnIndex;
                
                // Jangan urutkan kolom nomor (index 0)
                if (columnIndex > 0) {
                    sortTable(columnIndex, ascending);
                }
            }
        });
    }
    
    private void sortTable(int columnIndex, boolean isAscending) {
        try {
            Connection conn = KoneksiDatabase.getKoneksi();
            Statement stmt = conn.createStatement();
            
            // Menentukan nama kolom dari database berdasarkan indeks kolom pada tabel
            String columnName;
            switch (columnIndex) {
                case 1: columnName = "nama"; break;
                case 2: columnName = "jenis_kelamin"; break;
                case 3: columnName = "nisn"; break;
                case 4: columnName = "tempat_lahir"; break;
                case 5: columnName = "tanggal_lahir"; break;
                case 6: columnName = "tingkat"; break;
                default: columnName = "nama"; break;
            }
            
            // ORDER BY untuk mengurutkan data
            String orderDirection = isAscending ? "ASC" : "DESC";
            String query = "SELECT * FROM tb_siswa ORDER BY " + columnName + " " + orderDirection;
            
            ResultSet rs = stmt.executeQuery(query);
            
            // Perbarui model tabel dengan data terurut
            updateTableModel(rs);
            
            // Menampilkan indikator pengurutan pada kolom header
            String headerText = tableDataSiswa1.getColumnName(columnIndex);
            if (!headerText.contains(" ↑") && !headerText.contains(" ↓")) {
                for (int i = 1; i < tableDataSiswa1.getColumnCount(); i++) {
                    tableDataSiswa1.getColumnModel().getColumn(i).setHeaderValue(
                            tableDataSiswa1.getColumnName(i).replace(" ↑", "").replace(" ↓", ""));
                }
                
                String sortIndicator = isAscending ? " ↑" : " ↓";
                tableDataSiswa1.getColumnModel().getColumn(columnIndex).setHeaderValue(
                        headerText.replace(" ↑", "").replace(" ↓", "") + sortIndicator);
            } else {
                for (int i = 1; i < tableDataSiswa1.getColumnCount(); i++) {
                    if (i == columnIndex) {
                        String newHeader = tableDataSiswa1.getColumnName(i).replace(" ↑", "").replace(" ↓", "");
                        String sortIndicator = isAscending ? " ↑" : " ↓";
                        tableDataSiswa1.getColumnModel().getColumn(i).setHeaderValue(newHeader + sortIndicator);
                    } else {
                        tableDataSiswa1.getColumnModel().getColumn(i).setHeaderValue(
                                tableDataSiswa1.getColumnName(i).replace(" ↑", "").replace(" ↓", ""));
                    }
                }
            }
            
            tableDataSiswa1.getTableHeader().repaint();
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal mengurutkan data: " + e.getMessage());
        }
    }
    
    private void updateTableModel(ResultSet rs) throws SQLException {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("NO");
        model.addColumn("NAMA");
        model.addColumn("JENIS KELAMIN");
        model.addColumn("NISN");
        model.addColumn("TEMPAT LAHIR");
        model.addColumn("TANGGAL LAHIR");
        model.addColumn("KELAS");
        
        int no = 1;
        while (rs.next()) {
            model.addRow(new Object[]{
                no++,
                rs.getString("nama"),
                rs.getString("jenis_kelamin"),
                rs.getString("nisn"),
                rs.getString("tempat_lahir"),
                rs.getDate("tanggal_lahir"),
                rs.getString("tingkat")
            });
        }
        
        tableDataSiswa1.setModel(model);
        
        // Terapkan kembali pengaturan renderer untuk header dan cell
        applyTableFormatting();
    }
    
    private void applyTableFormatting() {
        // Custom renderer untuk header agar background biru muda dan teks rata tengah
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
    }
    
    private void tampilkanDataSiswa() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("NO");
        model.addColumn("NAMA");
        model.addColumn("JENIS KELAMIN");
        model.addColumn("NISN");
        model.addColumn("TEMPAT LAHIR");
        model.addColumn("TANGGAL LAHIR");
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
                    rs.getString("jenis_kelamin"),
                    rs.getString("nisn"),
                    rs.getString("tempat_lahir"),
                    rs.getDate("tanggal_lahir"),
                    rs.getString("tingkat")
                });
            }

            tableDataSiswa1.setModel(model);
            
            // Terapkan pengaturan tampilan
            applyTableFormatting();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal menampilkan data siswa\n" + e.getMessage());
        }
    }
    
    // Implementasi pencarian berdasarkan kata kunci
    private void cariData(String keyword) {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("NO");
        model.addColumn("NAMA");
        model.addColumn("JENIS KELAMIN");
        model.addColumn("NISN");
        model.addColumn("TEMPAT LAHIR");
        model.addColumn("TANGGAL LAHIR");
        model.addColumn("KELAS");

        try {
            Connection conn = KoneksiDatabase.getKoneksi();
            String query = "SELECT * FROM tb_siswa WHERE " +
                           "nama LIKE ? OR " +
                           "jenis_kelamin LIKE ? OR " +
                           "nisn LIKE ? OR " +
                           "tempat_lahir LIKE ? OR " +
                           "tingkat LIKE ?";
            
            PreparedStatement ps = conn.prepareStatement(query);
            String searchPattern = "%" + keyword + "%";
            
            for (int i = 1; i <= 5; i++) {
                ps.setString(i, searchPattern);
            }
            
            ResultSet rs = ps.executeQuery();

            int no = 1;
            while (rs.next()) {
                model.addRow(new Object[]{
                    no++,
                    rs.getString("nama"),
                    rs.getString("jenis_kelamin"),
                    rs.getString("nisn"),
                    rs.getString("tempat_lahir"),
                    rs.getDate("tanggal_lahir"),
                    rs.getString("tingkat")
                });
            }

            tableDataSiswa1.setModel(model);
            applyTableFormatting();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal mencari data\n" + e.getMessage());
        }
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        tambah = new rojerusan.RSMaterialButtonRectangle();
        edit = new rojerusan.RSMaterialButtonRectangle();
        hapus = new rojerusan.RSMaterialButtonRectangle();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableDataSiswa1 = new com.mtsarridho.pallate.JTableCustom();
        searchTxt = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        jPanel1.setPreferredSize(new java.awt.Dimension(1040, 731));

        tambah.setText("TAMBAH");
        tambah.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tambahMouseClicked(evt);
            }
        });
        tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambahActionPerformed(evt);
            }
        });

        edit.setBackground(new java.awt.Color(255, 153, 0));
        edit.setText("EDIT");
        edit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editMouseClicked(evt);
            }
        });
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

        tableDataSiswa1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "NO", "NAMA", "JENIS KELAMIN", "NISN", "TEMPAT LAHIR", "TANGGAL LAHIR", "KELAS"
            }
        ));
        jScrollPane2.setViewportView(tableDataSiswa1);

        searchTxt.setForeground(new java.awt.Color(51, 51, 51));
        searchTxt.setText("Search");
        searchTxt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                searchTxtMouseClicked(evt);
            }
        });
        searchTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchTxtActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mtsarridho/icon/search_6033394 (1).png"))); // NOI18N
        jButton1.setOpaque(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tambah, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(edit, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(hapus, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 164, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(searchTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tambah, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(hapus, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(edit, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(2, 2, 2))
                    .addComponent(searchTxt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 821, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_editActionPerformed

    private void hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapusActionPerformed
        int selectedRow = tableDataSiswa1.getSelectedRow();
        if (selectedRow != -1) {
            String nisn = tableDataSiswa1.getValueAt(selectedRow, 3).toString(); // kolom NISN

            int confirm = JOptionPane.showConfirmDialog(this, "Yakin ingin menghapus data siswa dengan NISN " + nisn + "?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    Connection conn = KoneksiDatabase.getKoneksi();
                    PreparedStatement ps = conn.prepareStatement("DELETE FROM tb_siswa WHERE nisn = ?");
                    ps.setString(1, nisn);
                    ps.executeUpdate();

                    JOptionPane.showMessageDialog(this, "Data berhasil dihapus.");
                    tampilkanDataSiswa();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(this, "Gagal menghapus data\n" + e.getMessage());
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Silakan pilih data yang akan dihapus.");
        }        
    }//GEN-LAST:event_hapusActionPerformed

    private void tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tambahActionPerformed

    private void tambahMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tambahMouseClicked
        FormTambahSiswa TS = new FormTambahSiswa();
        TS.setDataChangedListener(this); // 'this' adalah FormDataSiswa yang mengimplementasi DataChangedListener
        TS.setVisible(true);
    }//GEN-LAST:event_tambahMouseClicked

    private void editMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editMouseClicked
        int selectedRow = tableDataSiswa1.getSelectedRow();
            if (selectedRow != -1) {
                String nisn = tableDataSiswa1.getValueAt(selectedRow, 3).toString(); // Kolom NISN
                FormEditSiswa ES = new FormEditSiswa(nisn); // Pastikan konstruktor ini ada
                ES.setDataChangedListener(this);
                ES.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Silakan pilih data siswa yang akan diedit.");
            }
    }//GEN-LAST:event_editMouseClicked

    private void searchTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchTxtActionPerformed
        jButton1ActionPerformed(evt);
    }//GEN-LAST:event_searchTxtActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String keyword = searchTxt.getText().trim();
        if (keyword.equals("") || keyword.equalsIgnoreCase("Search")) {
            tampilkanDataSiswa();
        } else {
            cariData(keyword);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void searchTxtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchTxtMouseClicked
        searchTxt.setText("");
    }//GEN-LAST:event_searchTxtMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojerusan.RSMaterialButtonRectangle edit;
    private rojerusan.RSMaterialButtonRectangle hapus;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField searchTxt;
    private com.mtsarridho.pallate.JTableCustom tableDataSiswa1;
    private rojerusan.RSMaterialButtonRectangle tambah;
    // End of variables declaration//GEN-END:variables

    
    
}
