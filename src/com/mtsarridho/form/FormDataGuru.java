
package com.mtsarridho.form;

import java.sql.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import java.sql.Connection;
import com.mtsarridho.config.KoneksiDatabase;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;
import com.mtsarridho.form.FormEditDataGuru;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

public class FormDataGuru extends javax.swing.JPanel implements DataChangedListener {
    
    private boolean ascending = true; // Untuk melacak arah pengurutan
    private int lastSortedColumn = -1;
    
     @Override
    public void onDataChanged() {
        tampilkanDataGuru();
        
    }
    
    public FormDataGuru() {
        initComponents();
        tampilkanDataGuru();
        setupTableHeaderListener();
    }
   
    private void setupTableHeaderListener() {
        JTableHeader header = tableDataGuru.getTableHeader();
        header.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int columnIndex = tableDataGuru.columnAtPoint(e.getPoint());
                
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
                case 2: columnName = "nik"; break;
                case 3: columnName = "jenis_kelamin"; break;
                default: columnName = "nama"; break;
            }
            
            // ORDER BY untuk mengurutkan data
            String orderDirection = isAscending ? "ASC" : "DESC";
            String query = "SELECT * FROM tb_guru ORDER BY " + columnName + " " + orderDirection;
            
            ResultSet rs = stmt.executeQuery(query);
            
            // Perbarui model tabel dengan data terurut
            updateTableModel(rs);
            
            // Menampilkan indikator pengurutan pada kolom header
            String headerText = tableDataGuru.getColumnName(columnIndex);
            if (!headerText.contains(" ↑") && !headerText.contains(" ↓")) {
                for (int i = 1; i < tableDataGuru.getColumnCount(); i++) {
                    tableDataGuru.getColumnModel().getColumn(i).setHeaderValue(
                            tableDataGuru.getColumnName(i).replace(" ↑", "").replace(" ↓", ""));
                }
                
                String sortIndicator = isAscending ? " ↑" : " ↓";
                tableDataGuru.getColumnModel().getColumn(columnIndex).setHeaderValue(
                        headerText.replace(" ↑", "").replace(" ↓", "") + sortIndicator);
            } else {
                for (int i = 1; i < tableDataGuru.getColumnCount(); i++) {
                    if (i == columnIndex) {
                        String newHeader = tableDataGuru.getColumnName(i).replace(" ↑", "").replace(" ↓", "");
                        String sortIndicator = isAscending ? " ↑" : " ↓";
                        tableDataGuru.getColumnModel().getColumn(i).setHeaderValue(newHeader + sortIndicator);
                    } else {
                        tableDataGuru.getColumnModel().getColumn(i).setHeaderValue(
                                tableDataGuru.getColumnName(i).replace(" ↑", "").replace(" ↓", ""));
                    }
                }
            }
            
            tableDataGuru.getTableHeader().repaint();
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal mengurutkan data: " + e.getMessage());
        }
    }
    
    private void updateTableModel(ResultSet rs) throws SQLException {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("NO");
        model.addColumn("NAMA");
        model.addColumn("NIK/NUPTK");
        model.addColumn("JENIS KELAMIN");
        
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
            
            // Terapkan pengaturan tampilan
            applyTableFormatting();
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal menampilkan data guru\n" + e.getMessage());
        }
    }
    
    // Implementasi pencarian berdasarkan kata kunci
    private void cariData(String keyword) {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("NO");
        model.addColumn("NAMA");
        model.addColumn("NIK/NUPTK");
        model.addColumn("JENIS KELAMIN");

        try {
            Connection conn = KoneksiDatabase.getKoneksi();
            String query = "SELECT * FROM tb_guru WHERE " +
                          "nama LIKE ? OR " +
                          "nik LIKE ? OR " +
                          "jenis_kelamin LIKE ?";
            
            PreparedStatement ps = conn.prepareStatement(query);
            String searchPattern = "%" + keyword + "%";
            
            for (int i = 1; i <= 3; i++) {
                ps.setString(i, searchPattern);
            }
            
            ResultSet rs = ps.executeQuery();

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
        tableDataGuru = new com.mtsarridho.pallate.JTableCustom();
        jButton1 = new javax.swing.JButton();
        searchTxt = new javax.swing.JTextField();

        setLayout(new java.awt.BorderLayout());

        jPanel1.setPreferredSize(new java.awt.Dimension(1040, 731));

        tambah.setText("TAMBAH");
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
        jScrollPane2.setViewportView(tableDataGuru);
        if (tableDataGuru.getColumnModel().getColumnCount() > 0) {
            tableDataGuru.getColumnModel().getColumn(0).setMinWidth(1);
            tableDataGuru.getColumnModel().getColumn(0).setPreferredWidth(1);
        }

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mtsarridho/icon/search_6033394 (1).png"))); // NOI18N
        jButton1.setOpaque(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 173, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tambah, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(hapus, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(edit, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(searchTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 822, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editActionPerformed
      
    }//GEN-LAST:event_editActionPerformed

    private void hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapusActionPerformed
        int selectedRow = tableDataGuru.getSelectedRow();
        if (selectedRow != -1) {
            String nik = tableDataGuru.getValueAt(selectedRow, 2).toString(); // kolom NIK

            int confirm = JOptionPane.showConfirmDialog(this, "Yakin ingin menghapus data siswa dengan NISN " + nik + "?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    Connection conn = KoneksiDatabase.getKoneksi();
                    PreparedStatement ps = conn.prepareStatement("DELETE FROM tb_guru WHERE nik = ?");
                    ps.setString(1, nik);
                    ps.executeUpdate();

                    JOptionPane.showMessageDialog(this, "Data berhasil dihapus.");
                    tampilkanDataGuru();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(this, "Gagal menghapus data\n" + e.getMessage());
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Silakan pilih data yang akan dihapus.");
        }
    }//GEN-LAST:event_hapusActionPerformed

    private void tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahActionPerformed
        FormTambahDataGuru TG = new FormTambahDataGuru();
        TG.setDataChangedListener(this);
        TG.setVisible(true);
    }//GEN-LAST:event_tambahActionPerformed

    private void editMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editMouseClicked
        int selectedRow = tableDataGuru.getSelectedRow();
            if (selectedRow != -1) {
                String nik = tableDataGuru.getValueAt(selectedRow, 2).toString(); // Kolom NISK
                FormEditDataGuru ES = new FormEditDataGuru(nik); // Pastikan konstruktor ini ada
                ES.setDataChangedListener(this);
                ES.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Silakan pilih data siswa yang akan diedit.");
            }
        
    }//GEN-LAST:event_editMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String keyword = searchTxt.getText().trim();
        if (keyword.equals("") || keyword.equalsIgnoreCase("Search")) {
            tampilkanDataGuru();
        } else {
            cariData(keyword);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void searchTxtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchTxtMouseClicked
        searchTxt.setText("");
    }//GEN-LAST:event_searchTxtMouseClicked

    private void searchTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchTxtActionPerformed
        jButton1ActionPerformed(evt);
    }//GEN-LAST:event_searchTxtActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojerusan.RSMaterialButtonRectangle edit;
    private rojerusan.RSMaterialButtonRectangle hapus;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField searchTxt;
    private com.mtsarridho.pallate.JTableCustom tableDataGuru;
    private rojerusan.RSMaterialButtonRectangle tambah;
    // End of variables declaration//GEN-END:variables
}
