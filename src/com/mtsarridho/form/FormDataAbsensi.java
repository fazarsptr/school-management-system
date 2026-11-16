
package com.mtsarridho.form;

import java.sql.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import java.sql.Connection;
import com.mtsarridho.config.KoneksiDatabase;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;

public class FormDataAbsensi extends javax.swing.JPanel implements DataChangedListener {
    
private java.util.List<Integer> listIdKehadiran = new java.util.ArrayList<>();
private TableRowSorter<DefaultTableModel> sorter;
private boolean ascending = true;   

    @Override
    public void onDataChanged() {
        tampilkanDataAbsensiGuru();
        
    }

    public FormDataAbsensi() {
        initComponents();
        tampilkanDataAbsensiGuru();
        setupTableSorter();
    }
    
    private void setupTableSorter() {
        DefaultTableModel model = (DefaultTableModel) tableDataAbsen.getModel();
        sorter = new TableRowSorter<>(model);
        tableDataAbsen.setRowSorter(sorter);
        
        // Menambahkan MouseListener pada header tabel
        tableDataAbsen.getTableHeader().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int columnIndex = tableDataAbsen.columnAtPoint(e.getPoint());
                
                // Kita tidak perlu sort untuk kolom "NO" (indeks 0)
                if (columnIndex == 0) {
                    return;
                }
                
                SortOrder currentOrder = ascending ? SortOrder.ASCENDING : SortOrder.DESCENDING;
                
                List<RowSorter.SortKey> sortKeys = new ArrayList<>();
                sortKeys.add(new RowSorter.SortKey(columnIndex, currentOrder));
                
                sorter.setSortKeys(sortKeys);
                sorter.sort();
                
                // Toggle arah sorting untuk klik berikutnya
                ascending = !ascending;
            }
        });
    }
    
    public void refreshTable() {
        tampilkanDataAbsensiGuru();
        setupTableSorter(); // Perlu setup ulang karena model tabel berubah
    }
    
    public void tampilkanDataAbsensiGuru() {
        DefaultTableModel model = new DefaultTableModel();
        
        model.addColumn("NO");
        model.addColumn("NAMA GURU");
        model.addColumn("KEHADIRAN");
        model.addColumn("TANGGAL");
        
        listIdKehadiran.clear();
        
        try {
            Connection conn = KoneksiDatabase.getKoneksi();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM tb_kehadiran_guru");

            int no = 1;
            while (rs.next()) {
                listIdKehadiran.add(rs.getInt("id_kehadiran"));
                model.addRow(new Object[]{
                    no++,
                    rs.getString("nama"),
                    rs.getString("kehadiran"),
                    rs.getDate("waktu")
                });
            }
            
            tableDataAbsen.setModel(model);
            
            // Custom renderer untuk header agar background biru muda dan teks rata tengah
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
            centerRenderer.setBackground(new Color(173, 216, 230)); // Light Blue (RGB)
            centerRenderer.setForeground(Color.BLACK); // Warna teks
            centerRenderer.setOpaque(true);
            
            for (int i = 0; i < tableDataAbsen.getColumnModel().getColumnCount(); i++) {
                tableDataAbsen.getColumnModel().getColumn(i).setHeaderRenderer(centerRenderer);
            }
            
            TableColumnModel columnModel = tableDataAbsen.getColumnModel();
            // Atur lebar kolom
            columnModel.getColumn(0).setMinWidth(40);
            columnModel.getColumn(0).setMaxWidth(40);
            columnModel.getColumn(0).setPreferredWidth(40); // Kolom NO kecil
            
            // Menetapkan renderer ke setiap kolom
            DefaultTableCellRenderer colRenderer = new DefaultTableCellRenderer();
            colRenderer.setHorizontalAlignment(SwingConstants.CENTER);

            // Atur semua kolom agar isi cell-nya di tengah
            for (int i = 0; i < tableDataAbsen.getColumnCount(); i++) {
                tableDataAbsen.getColumnModel().getColumn(i).setCellRenderer(colRenderer);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal menampilkan data guru\n" + e.getMessage());
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
        tableDataAbsen = new com.mtsarridho.pallate.JTableCustom();

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

        hapus.setBackground(new java.awt.Color(255, 0, 0));
        hapus.setText("HAPUS");
        hapus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                hapusMouseClicked(evt);
            }
        });
        hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapusActionPerformed(evt);
            }
        });

        tableDataAbsen.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "NO", "NAMA GURU", "KEHADIRAN", "TANGGAL"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tableDataAbsen);
        if (tableDataAbsen.getColumnModel().getColumnCount() > 0) {
            tableDataAbsen.getColumnModel().getColumn(0).setMinWidth(1);
            tableDataAbsen.getColumnModel().getColumn(0).setPreferredWidth(1);
        }

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
                        .addGap(0, 448, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tambah, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hapus, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(edit, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 808, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hapusActionPerformed

    private void tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tambahActionPerformed

    private void tambahMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tambahMouseClicked
       TambahAbsen absenForm = new TambahAbsen(this);
       absenForm.setDataChangedListener(this);
       absenForm.setVisible(true);
    }//GEN-LAST:event_tambahMouseClicked

    private void editMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editMouseClicked
        int selectedRow = tableDataAbsen.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Silakan pilih baris yang akan diedit terlebih dahulu.");
            return;
        }

        // Ambil data dari tabel
        String namaGuru = tableDataAbsen.getValueAt(selectedRow, 1).toString();
        String kehadiran = tableDataAbsen.getValueAt(selectedRow, 2).toString();
        String tanggal = tableDataAbsen.getValueAt(selectedRow, 3).toString();

        // Panggil form edit dan kirim data
        EditAbsen editAbsen = new EditAbsen(this);
        editAbsen.setData(namaGuru, kehadiran, tanggal);
        editAbsen.setVisible(true);
    }//GEN-LAST:event_editMouseClicked

    private void hapusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hapusMouseClicked
        int selectedRow = tableDataAbsen.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Silakan pilih data yang ingin dihapus terlebih dahulu.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "Apakah Anda yakin ingin menghapus data ini?", "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        int idKehadiran = listIdKehadiran.get(selectedRow); // Ambil dari list ID

        try {
            Connection conn = KoneksiDatabase.getKoneksi();
            String sql = "DELETE FROM tb_kehadiran_guru WHERE id_kehadiran = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idKehadiran);

            int result = stmt.executeUpdate();
            if (result > 0) {
                JOptionPane.showMessageDialog(this, "Data berhasil dihapus.");
                refreshTable();
            } else {
                JOptionPane.showMessageDialog(this, "Data gagal dihapus.");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Terjadi kesalahan saat menghapus data:\n" + e.getMessage());
        }
    }//GEN-LAST:event_hapusMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojerusan.RSMaterialButtonRectangle edit;
    private rojerusan.RSMaterialButtonRectangle hapus;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private com.mtsarridho.pallate.JTableCustom tableDataAbsen;
    private rojerusan.RSMaterialButtonRectangle tambah;
    // End of variables declaration//GEN-END:variables

    
}
