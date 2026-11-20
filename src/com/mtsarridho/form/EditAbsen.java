package com.mtsarridho.form;

import com.mtsarridho.config.KoneksiDatabase;
import com.toedter.calendar.JCalendar;
import java.util.Date;
import java.text.SimpleDateFormat;
import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import com.mtsarridho.form.GuruItem;



/**
 *
 * @author yurisa
 */
public class EditAbsen extends javax.swing.JFrame {
    private FormDataAbsensi parentForm;
    int xx, xy;
    JDialog calendarDialog;
    JCalendar calendar;
    private int idKehadiran;
    

    /**
     * Creates new form Absen
     */
    
    public EditAbsen(FormDataAbsensi parent) {
        initComponents();
        this.parentForm = parent;
        
        jComboBox2.removeAllItems();
        jComboBox2.addItem("Hadir");
        jComboBox2.addItem("Tidak Hadir");
        
        loadGuruData();
      
        
        setLocationRelativeTo(null); 
        calendar = new JCalendar();

        calendarDialog = new JDialog(this, "Pilih Tanggal", true);
        calendarDialog.setUndecorated(true);
        calendarDialog.getContentPane().add(calendar);
        calendarDialog.pack();

        calendar.addPropertyChangeListener("calendar", evt -> {
            Date selectedDate = calendar.getDate();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            tTanggal.setText(sdf.format(selectedDate));
            calendarDialog.setVisible(false);
        });

        bttn.addActionListener(e -> {
            Point lokasi = bttn.getLocationOnScreen();
            calendarDialog.setLocation(lokasi.x, lokasi.y + bttn.getHeight());
            calendarDialog.setVisible(true);
        });
    }
    
    public int getIdKehadiran() {
        return idKehadiran;
    }

    public void setIdKehadiran(int idKehadiran) {
        this.idKehadiran = idKehadiran;
    }

    private void loadGuruData() {
        try {
            Connection conn = KoneksiDatabase.getKoneksi(); 
            if (conn != null) {
                try (Statement stmt = conn.createStatement();
                     ResultSet rs = stmt.executeQuery("SELECT nik, nama FROM tb_guru")) {
                    DefaultComboBoxModel<GuruItem> model = new DefaultComboBoxModel<>();
                    while (rs.next()) {
                        String nik = rs.getString("nik");
                        String nama = rs.getString("nama");
                        if (nik != null && !nik.trim().isEmpty() && nama != null && !nama.trim().isEmpty()) {
                            model.addElement(new GuruItem(nik.trim(), nama.trim()));
                        }
                    }
                    ((JComboBox) jComboBox1).setModel(model);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error loading teachers in EditAbsen: " + e.getMessage());
            JOptionPane.showMessageDialog(this, "Gagal memuat nama guru.", "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void setData(String namaGuru, String kehadiran, String tanggal) {
        try {
            // Find attendance record by teacher name and date
            Connection conn = KoneksiDatabase.getKoneksi();
            if (conn == null) {
                JOptionPane.showMessageDialog(this, "Koneksi database tidak tersedia.", "Database Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String sql = "SELECT id_kehadiran, nik, nama, kehadiran, waktu FROM tb_kehadiran_guru " +
                         "WHERE nama = ? AND waktu = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, namaGuru);
                stmt.setString(2, tanggal);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        // Store attendance record ID
                        this.idKehadiran = rs.getInt("id_kehadiran");
                        String nik = rs.getString("nik");
                        
                        // Set date
                        tTanggal.setText(tanggal != null ? tanggal.trim() : "");
                        
                        // Set attendance status in combo box
                        if (kehadiran != null) {
                            jComboBox2.setSelectedItem(kehadiran.trim());
                        }
                        
                        // Set selected teacher in combo box
                        for (int i = 0; i < jComboBox1.getItemCount(); i++) {
                            Object obj = jComboBox1.getItemAt(i);
                            if (obj instanceof GuruItem) {
                                GuruItem item = (GuruItem) obj;
                                if (item.getNama().equals(namaGuru) && item.getNik().equals(nik)) {
                                    jComboBox1.setSelectedIndex(i);
                                    break;
                                }
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Data kehadiran tidak ditemukan!", "Peringatan", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Error fetching attendance data in EditAbsen: " + e.getMessage());
            JOptionPane.showMessageDialog(this, "Error saat mengambil data dari database.", "Database Error", JOptionPane.ERROR_MESSAGE);
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

        jCalendar2 = new com.toedter.calendar.JCalendar();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        jTextField4 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        tTanggal = new javax.swing.JTextField();
        bttn = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        rSMaterialButtonRectangle1 = new rojerusan.RSMaterialButtonRectangle();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
        });
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextField1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jTextField1.setText("ABSENSI GURU");
        jTextField1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        getContentPane().add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(122, 0, 157, -1));

        jTextField2.setBackground(new java.awt.Color(25, 125, 192));
        jTextField2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jTextField2.setForeground(java.awt.Color.darkGray);
        jTextField2.setText("Kehadiran");
        jTextField2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jTextField2.setOpaque(false);
        getContentPane().add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 200, 110, -1));

        jComboBox1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        getContentPane().add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 140, 220, 30));

        jComboBox2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        getContentPane().add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 240, 220, 30));

        jTextField4.setBackground(new java.awt.Color(25, 125, 192));
        jTextField4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jTextField4.setForeground(java.awt.Color.darkGray);
        jTextField4.setText("Tanggal");
        jTextField4.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jTextField4.setOpaque(false);
        getContentPane().add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(144, 300, 80, -1));

        jTextField3.setBackground(new java.awt.Color(25, 125, 192));
        jTextField3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jTextField3.setForeground(java.awt.Color.darkGray);
        jTextField3.setText("Nama Guru");
        jTextField3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jTextField3.setOpaque(false);
        getContentPane().add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 100, 120, 30));
        getContentPane().add(tTanggal, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 350, 160, 30));

        bttn.setText("...");
        getContentPane().add(bttn, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 350, 50, 30));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        rSMaterialButtonRectangle1.setBackground(new java.awt.Color(51, 51, 255));
        rSMaterialButtonRectangle1.setText("UPDATE");
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
        jPanel1.add(rSMaterialButtonRectangle1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 500, 130, 50));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mtsarridho/icon/delete.png"))); // NOI18N
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(376, 0, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mtsarridho/icon/BGP.png"))); // NOI18N
        jLabel2.setText("...");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 31, 400, 572));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 600));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        xx = evt.getX();
        xy = evt.getY();
    }//GEN-LAST:event_formMousePressed

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xx, y - xy);
    }//GEN-LAST:event_formMouseDragged

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        dispose();
    }//GEN-LAST:event_jLabel1MouseClicked

    private void rSMaterialButtonRectangle1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonRectangle1ActionPerformed
       
    }//GEN-LAST:event_rSMaterialButtonRectangle1ActionPerformed

    private void rSMaterialButtonRectangle1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rSMaterialButtonRectangle1MouseClicked
        // Ensure id_kehadiran is set and valid
        if (this.idKehadiran <= 0) {
            JOptionPane.showMessageDialog(this, "ID kehadiran tidak valid untuk pembaruan.", "Validasi Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Object selectedItem = jComboBox1.getSelectedItem();
        if (!(selectedItem instanceof GuruItem)) {
            JOptionPane.showMessageDialog(this, "Silakan pilih guru terlebih dahulu.", "Validasi Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        GuruItem selectedGuru = (GuruItem) selectedItem;
        String nikGuru = selectedGuru.getNik() != null ? selectedGuru.getNik().trim() : "";
        String namaGuru = selectedGuru.getNama() != null ? selectedGuru.getNama().trim() : "";

        if (nikGuru.isEmpty() || namaGuru.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Data guru yang dipilih tidak valid.", "Validasi Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Object statusItem = jComboBox2.getSelectedItem();
        String keterangan = statusItem != null ? statusItem.toString().trim() : "";
        if (keterangan.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Silakan pilih status kehadiran.", "Validasi Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String tanggal = tTanggal.getText() != null ? tTanggal.getText().trim() : "";
        if (tanggal.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tanggal kehadiran tidak boleh kosong.", "Validasi Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!tanggal.matches("^\\d{4}-\\d{2}-\\d{2}$")) {
            JOptionPane.showMessageDialog(this, "Format tanggal tidak valid. Gunakan format YYYY-MM-DD.", "Validasi Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd").withResolverStyle(ResolverStyle.STRICT);
            LocalDate parsedDate = LocalDate.parse(tanggal, formatter);
            int year = parsedDate.getYear();
            if (year < 1900 || year > 2100) {
                JOptionPane.showMessageDialog(this, "Tahun tanggal kehadiran harus antara 1900 dan 2100.", "Validasi Error", JOptionPane.WARNING_MESSAGE);
                return;
            }
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(this, "Tanggal yang dimasukkan tidak valid kalender.", "Validasi Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            Connection conn = KoneksiDatabase.getKoneksi();
            if (conn == null) {
                JOptionPane.showMessageDialog(this, "Koneksi database tidak tersedia.", "Database Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Check if another attendance record already exists for the teacher on this date (excluding current record)
            String cekSql = "SELECT COUNT(*) FROM tb_kehadiran_guru WHERE nik = ? AND waktu = ? AND id_kehadiran != ?";
            try (PreparedStatement cekStmt = conn.prepareStatement(cekSql)) {
                cekStmt.setString(1, nikGuru);
                cekStmt.setString(2, tanggal);
                cekStmt.setInt(3, this.idKehadiran);
                try (ResultSet rs = cekStmt.executeQuery()) {
                    if (rs.next() && rs.getInt(1) > 0) {
                        JOptionPane.showMessageDialog(this, "Guru ini sudah memiliki catatan kehadiran pada tanggal tersebut.", "Validasi Error", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                }
            }

            // Update attendance record using id_kehadiran
            String sql = "UPDATE tb_kehadiran_guru SET nik=?, nama=?, kehadiran=?, waktu=? WHERE id_kehadiran=?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, nikGuru);
                stmt.setString(2, namaGuru);
                stmt.setString(3, keterangan);
                stmt.setString(4, tanggal);
                stmt.setInt(5, this.idKehadiran);

                int result = stmt.executeUpdate();
                if (result > 0) {
                    JOptionPane.showMessageDialog(this, "Data berhasil diupdate!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
                    if (parentForm != null) {
                        parentForm.refreshTable(); // Refresh table in parent form
                    }
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Data gagal diupdate!", "Peringatan", JOptionPane.WARNING_MESSAGE);
                }
            }

        } catch (SQLException e) {
            System.err.println("Database error updating attendance: " + e.getMessage());
            JOptionPane.showMessageDialog(this, "Gagal memperbarui data kehadiran di database.", "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_rSMaterialButtonRectangle1MouseClicked

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
            java.util.logging.Logger.getLogger(EditAbsen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditAbsen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditAbsen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditAbsen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //</editor-fold>

        //</editor-fold>

        //</editor-fold>

        /* Create and display the form */
        
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bttn;
    private com.toedter.calendar.JCalendar jCalendar2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private rojerusan.RSMaterialButtonRectangle rSMaterialButtonRectangle1;
    private javax.swing.JTextField tTanggal;
    // End of variables declaration//GEN-END:variables
}
