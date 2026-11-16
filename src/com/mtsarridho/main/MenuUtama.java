
package com.mtsarridho.main;

import com.mtsarridho.form.Dashboard;
import com.mtsarridho.form.FormDataAbsensi;
import com.mtsarridho.form.FormDataGuru;
import com.mtsarridho.form.FormDataSiswa;
import com.mtsarridho.form.FormMengajarGuru;
import com.mtsarridho.form.laporanGuru;
import com.mtsarridho.form.laporanrombel;
import com.mtsarridho.form.lpGr;
import com.mtsarridho.form.lpMl;
import com.mtsarridho.form.lpSw;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JFrame;

public class MenuUtama extends javax.swing.JFrame {

    
    public MenuUtama() {
        
        setUndecorated(false);// Menghilangkan title bar
        initComponents();
        //setTitle("Main Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setExtendedState(JFrame.MAXIMIZED_BOTH); // ✅ Fullscreen mode
        setVisible(true);
        setLocationRelativeTo(null);
        pn_utama.setLayout(new BorderLayout());
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pn_kiri = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        pn_main = new javax.swing.JPanel();
        pn_line = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        dataSiswa = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        pn_main1 = new javax.swing.JPanel();
        pn_line1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        dataGuru = new javax.swing.JLabel();
        pn_main3 = new javax.swing.JPanel();
        pn_line3 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        kehadiran = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        pn_main4 = new javax.swing.JPanel();
        pn_line4 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        rombel = new javax.swing.JLabel();
        pn_main5 = new javax.swing.JPanel();
        pn_line5 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        mapel = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        pn_main6 = new javax.swing.JPanel();
        pn_line6 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        reportRombel = new javax.swing.JLabel();
        pn_main7 = new javax.swing.JPanel();
        pn_line7 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        reportMapel = new javax.swing.JLabel();
        pn_main9 = new javax.swing.JPanel();
        pn_line9 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        reportKehadiran = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        pn_main2 = new javax.swing.JPanel();
        pn_line2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        dataSiswa1 = new javax.swing.JLabel();
        pn_main10 = new javax.swing.JPanel();
        pn_line10 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        reportdataSiswa = new javax.swing.JLabel();
        pn_main12 = new javax.swing.JPanel();
        pn_line12 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        reportKehadiran2 = new javax.swing.JLabel();
        pn_kanan = new javax.swing.JPanel();
        jpanelGradient1 = new com.mtsarridho.pallate.JpanelGradient();
        pn_dasar = new javax.swing.JPanel();
        pn_utama = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
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

        pn_kiri.setBackground(new java.awt.Color(51, 153, 255));

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Serif", 1, 25)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mtsarridho/icon/logo (1).png"))); // NOI18N
        jLabel2.setText("MTs ARRIDHO");

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Serif", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("SISWA");

        pn_main.setBackground(new java.awt.Color(51, 153, 255));

        pn_line.setBackground(new java.awt.Color(51, 153, 255));

        javax.swing.GroupLayout pn_lineLayout = new javax.swing.GroupLayout(pn_line);
        pn_line.setLayout(pn_lineLayout);
        pn_lineLayout.setHorizontalGroup(
            pn_lineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        pn_lineLayout.setVerticalGroup(
            pn_lineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel3.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mtsarridho/icon/siswa.png"))); // NOI18N

        dataSiswa.setBackground(new java.awt.Color(255, 255, 255));
        dataSiswa.setFont(new java.awt.Font("Serif", 1, 16)); // NOI18N
        dataSiswa.setText("DATA SISWA");
        dataSiswa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dataSiswaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                dataSiswaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                dataSiswaMouseExited(evt);
            }
        });

        javax.swing.GroupLayout pn_mainLayout = new javax.swing.GroupLayout(pn_main);
        pn_main.setLayout(pn_mainLayout);
        pn_mainLayout.setHorizontalGroup(
            pn_mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_mainLayout.createSequentialGroup()
                .addComponent(pn_line, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(dataSiswa, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
                .addContainerGap())
        );
        pn_mainLayout.setVerticalGroup(
            pn_mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pn_line, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_mainLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(pn_mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                    .addComponent(dataSiswa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5))
        );

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Serif", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("GURU");

        pn_main1.setBackground(new java.awt.Color(51, 153, 255));

        pn_line1.setBackground(new java.awt.Color(51, 153, 255));

        javax.swing.GroupLayout pn_line1Layout = new javax.swing.GroupLayout(pn_line1);
        pn_line1.setLayout(pn_line1Layout);
        pn_line1Layout.setHorizontalGroup(
            pn_line1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        pn_line1Layout.setVerticalGroup(
            pn_line1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel6.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mtsarridho/icon/teacher.png"))); // NOI18N

        dataGuru.setBackground(new java.awt.Color(255, 255, 255));
        dataGuru.setFont(new java.awt.Font("Serif", 1, 16)); // NOI18N
        dataGuru.setText("DATA GURU");
        dataGuru.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dataGuruMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                dataGuruMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                dataGuruMouseExited(evt);
            }
        });

        javax.swing.GroupLayout pn_main1Layout = new javax.swing.GroupLayout(pn_main1);
        pn_main1.setLayout(pn_main1Layout);
        pn_main1Layout.setHorizontalGroup(
            pn_main1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_main1Layout.createSequentialGroup()
                .addComponent(pn_line1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(dataGuru, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
                .addContainerGap())
        );
        pn_main1Layout.setVerticalGroup(
            pn_main1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pn_line1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_main1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(pn_main1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                    .addComponent(dataGuru, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5))
        );

        pn_main3.setBackground(new java.awt.Color(51, 153, 255));

        pn_line3.setBackground(new java.awt.Color(51, 153, 255));

        javax.swing.GroupLayout pn_line3Layout = new javax.swing.GroupLayout(pn_line3);
        pn_line3.setLayout(pn_line3Layout);
        pn_line3Layout.setHorizontalGroup(
            pn_line3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        pn_line3Layout.setVerticalGroup(
            pn_line3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel8.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mtsarridho/icon/calendar (1).png"))); // NOI18N

        kehadiran.setBackground(new java.awt.Color(255, 255, 255));
        kehadiran.setFont(new java.awt.Font("Serif", 1, 16)); // NOI18N
        kehadiran.setText("KEHADIRAN");
        kehadiran.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                kehadiranMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                kehadiranMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                kehadiranMouseExited(evt);
            }
        });

        javax.swing.GroupLayout pn_main3Layout = new javax.swing.GroupLayout(pn_main3);
        pn_main3.setLayout(pn_main3Layout);
        pn_main3Layout.setHorizontalGroup(
            pn_main3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_main3Layout.createSequentialGroup()
                .addComponent(pn_line3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(kehadiran, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
                .addContainerGap())
        );
        pn_main3Layout.setVerticalGroup(
            pn_main3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pn_line3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_main3Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(pn_main3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                    .addComponent(kehadiran, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5))
        );

        jLabel10.setBackground(new java.awt.Color(255, 255, 255));
        jLabel10.setFont(new java.awt.Font("Serif", 1, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("KELAS");

        pn_main4.setBackground(new java.awt.Color(51, 153, 255));

        pn_line4.setBackground(new java.awt.Color(51, 153, 255));

        javax.swing.GroupLayout pn_line4Layout = new javax.swing.GroupLayout(pn_line4);
        pn_line4.setLayout(pn_line4Layout);
        pn_line4Layout.setHorizontalGroup(
            pn_line4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        pn_line4Layout.setVerticalGroup(
            pn_line4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel11.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mtsarridho/icon/kelas.png"))); // NOI18N

        rombel.setBackground(new java.awt.Color(255, 255, 255));
        rombel.setFont(new java.awt.Font("Serif", 1, 16)); // NOI18N
        rombel.setText("ROMBONGAN BELAJAR");
        rombel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rombelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                rombelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                rombelMouseExited(evt);
            }
        });

        javax.swing.GroupLayout pn_main4Layout = new javax.swing.GroupLayout(pn_main4);
        pn_main4.setLayout(pn_main4Layout);
        pn_main4Layout.setHorizontalGroup(
            pn_main4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_main4Layout.createSequentialGroup()
                .addComponent(pn_line4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(rombel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pn_main4Layout.setVerticalGroup(
            pn_main4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pn_line4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_main4Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(pn_main4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                    .addComponent(rombel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5))
        );

        pn_main5.setBackground(new java.awt.Color(51, 153, 255));

        pn_line5.setBackground(new java.awt.Color(51, 153, 255));

        javax.swing.GroupLayout pn_line5Layout = new javax.swing.GroupLayout(pn_line5);
        pn_line5.setLayout(pn_line5Layout);
        pn_line5Layout.setHorizontalGroup(
            pn_line5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        pn_line5Layout.setVerticalGroup(
            pn_line5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel13.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mtsarridho/icon/book (1).png"))); // NOI18N

        mapel.setBackground(new java.awt.Color(255, 255, 255));
        mapel.setFont(new java.awt.Font("Serif", 1, 16)); // NOI18N
        mapel.setText("MATA PELAJARAN");
        mapel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mapelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                mapelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                mapelMouseExited(evt);
            }
        });

        javax.swing.GroupLayout pn_main5Layout = new javax.swing.GroupLayout(pn_main5);
        pn_main5.setLayout(pn_main5Layout);
        pn_main5Layout.setHorizontalGroup(
            pn_main5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_main5Layout.createSequentialGroup()
                .addComponent(pn_line5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(mapel, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
                .addContainerGap())
        );
        pn_main5Layout.setVerticalGroup(
            pn_main5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pn_line5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_main5Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(pn_main5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                    .addComponent(mapel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5))
        );

        jLabel15.setBackground(new java.awt.Color(255, 255, 255));
        jLabel15.setFont(new java.awt.Font("Serif", 1, 24)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("LAPORAN");

        pn_main6.setBackground(new java.awt.Color(51, 153, 255));

        pn_line6.setBackground(new java.awt.Color(51, 153, 255));

        javax.swing.GroupLayout pn_line6Layout = new javax.swing.GroupLayout(pn_line6);
        pn_line6.setLayout(pn_line6Layout);
        pn_line6Layout.setHorizontalGroup(
            pn_line6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        pn_line6Layout.setVerticalGroup(
            pn_line6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel16.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mtsarridho/icon/kelas.png"))); // NOI18N

        reportRombel.setBackground(new java.awt.Color(255, 255, 255));
        reportRombel.setFont(new java.awt.Font("Serif", 1, 16)); // NOI18N
        reportRombel.setText("ROMBONGAN BELAJAR");
        reportRombel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                reportRombelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                reportRombelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                reportRombelMouseExited(evt);
            }
        });

        javax.swing.GroupLayout pn_main6Layout = new javax.swing.GroupLayout(pn_main6);
        pn_main6.setLayout(pn_main6Layout);
        pn_main6Layout.setHorizontalGroup(
            pn_main6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_main6Layout.createSequentialGroup()
                .addComponent(pn_line6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(reportRombel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pn_main6Layout.setVerticalGroup(
            pn_main6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pn_line6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_main6Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(pn_main6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                    .addComponent(reportRombel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5))
        );

        pn_main7.setBackground(new java.awt.Color(51, 153, 255));

        pn_line7.setBackground(new java.awt.Color(51, 153, 255));

        javax.swing.GroupLayout pn_line7Layout = new javax.swing.GroupLayout(pn_line7);
        pn_line7.setLayout(pn_line7Layout);
        pn_line7Layout.setHorizontalGroup(
            pn_line7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        pn_line7Layout.setVerticalGroup(
            pn_line7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel18.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mtsarridho/icon/book (1).png"))); // NOI18N

        reportMapel.setBackground(new java.awt.Color(255, 255, 255));
        reportMapel.setFont(new java.awt.Font("Serif", 1, 16)); // NOI18N
        reportMapel.setText("MATA PELAJARAN");
        reportMapel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                reportMapelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                reportMapelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                reportMapelMouseExited(evt);
            }
        });

        javax.swing.GroupLayout pn_main7Layout = new javax.swing.GroupLayout(pn_main7);
        pn_main7.setLayout(pn_main7Layout);
        pn_main7Layout.setHorizontalGroup(
            pn_main7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_main7Layout.createSequentialGroup()
                .addComponent(pn_line7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(reportMapel, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
                .addContainerGap())
        );
        pn_main7Layout.setVerticalGroup(
            pn_main7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pn_line7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_main7Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(pn_main7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                    .addComponent(reportMapel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5))
        );

        pn_main9.setBackground(new java.awt.Color(51, 153, 255));

        pn_line9.setBackground(new java.awt.Color(51, 153, 255));

        javax.swing.GroupLayout pn_line9Layout = new javax.swing.GroupLayout(pn_line9);
        pn_line9.setLayout(pn_line9Layout);
        pn_line9Layout.setHorizontalGroup(
            pn_line9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        pn_line9Layout.setVerticalGroup(
            pn_line9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel20.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mtsarridho/icon/calendar (1).png"))); // NOI18N

        reportKehadiran.setBackground(new java.awt.Color(255, 255, 255));
        reportKehadiran.setFont(new java.awt.Font("Serif", 1, 16)); // NOI18N
        reportKehadiran.setText("KEHADIRAN GURU");
        reportKehadiran.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                reportKehadiranMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                reportKehadiranMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                reportKehadiranMouseExited(evt);
            }
        });

        javax.swing.GroupLayout pn_main9Layout = new javax.swing.GroupLayout(pn_main9);
        pn_main9.setLayout(pn_main9Layout);
        pn_main9Layout.setHorizontalGroup(
            pn_main9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_main9Layout.createSequentialGroup()
                .addComponent(pn_line9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(reportKehadiran, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
                .addContainerGap())
        );
        pn_main9Layout.setVerticalGroup(
            pn_main9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pn_line9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_main9Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(pn_main9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                    .addComponent(reportKehadiran, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5))
        );

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Serif", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("DASHBOARD");

        pn_main2.setBackground(new java.awt.Color(51, 153, 255));

        pn_line2.setBackground(new java.awt.Color(51, 153, 255));

        javax.swing.GroupLayout pn_line2Layout = new javax.swing.GroupLayout(pn_line2);
        pn_line2.setLayout(pn_line2Layout);
        pn_line2Layout.setHorizontalGroup(
            pn_line2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        pn_line2Layout.setVerticalGroup(
            pn_line2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel7.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mtsarridho/icon/book (1).png"))); // NOI18N

        dataSiswa1.setBackground(new java.awt.Color(255, 255, 255));
        dataSiswa1.setFont(new java.awt.Font("Serif", 1, 16)); // NOI18N
        dataSiswa1.setText("DASHBOARD");
        dataSiswa1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dataSiswa1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                dataSiswa1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                dataSiswa1MouseExited(evt);
            }
        });

        javax.swing.GroupLayout pn_main2Layout = new javax.swing.GroupLayout(pn_main2);
        pn_main2.setLayout(pn_main2Layout);
        pn_main2Layout.setHorizontalGroup(
            pn_main2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_main2Layout.createSequentialGroup()
                .addComponent(pn_line2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(dataSiswa1, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
                .addContainerGap())
        );
        pn_main2Layout.setVerticalGroup(
            pn_main2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pn_line2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_main2Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(pn_main2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                    .addComponent(dataSiswa1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5))
        );

        pn_main10.setBackground(new java.awt.Color(51, 153, 255));

        pn_line10.setBackground(new java.awt.Color(51, 153, 255));

        javax.swing.GroupLayout pn_line10Layout = new javax.swing.GroupLayout(pn_line10);
        pn_line10.setLayout(pn_line10Layout);
        pn_line10Layout.setHorizontalGroup(
            pn_line10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        pn_line10Layout.setVerticalGroup(
            pn_line10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel21.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mtsarridho/icon/siswa.png"))); // NOI18N

        reportdataSiswa.setBackground(new java.awt.Color(255, 255, 255));
        reportdataSiswa.setFont(new java.awt.Font("Serif", 1, 16)); // NOI18N
        reportdataSiswa.setText("DATA SISWA");
        reportdataSiswa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                reportdataSiswaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                reportdataSiswaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                reportdataSiswaMouseExited(evt);
            }
        });

        javax.swing.GroupLayout pn_main10Layout = new javax.swing.GroupLayout(pn_main10);
        pn_main10.setLayout(pn_main10Layout);
        pn_main10Layout.setHorizontalGroup(
            pn_main10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_main10Layout.createSequentialGroup()
                .addComponent(pn_line10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(reportdataSiswa, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
                .addContainerGap())
        );
        pn_main10Layout.setVerticalGroup(
            pn_main10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pn_line10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_main10Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(pn_main10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                    .addComponent(reportdataSiswa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5))
        );

        pn_main12.setBackground(new java.awt.Color(51, 153, 255));

        pn_line12.setBackground(new java.awt.Color(51, 153, 255));

        javax.swing.GroupLayout pn_line12Layout = new javax.swing.GroupLayout(pn_line12);
        pn_line12.setLayout(pn_line12Layout);
        pn_line12Layout.setHorizontalGroup(
            pn_line12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        pn_line12Layout.setVerticalGroup(
            pn_line12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel22.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mtsarridho/icon/teacher.png"))); // NOI18N

        reportKehadiran2.setBackground(new java.awt.Color(255, 255, 255));
        reportKehadiran2.setFont(new java.awt.Font("Serif", 1, 16)); // NOI18N
        reportKehadiran2.setText("DATA GURU");
        reportKehadiran2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                reportKehadiran2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                reportKehadiran2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                reportKehadiran2MouseExited(evt);
            }
        });

        javax.swing.GroupLayout pn_main12Layout = new javax.swing.GroupLayout(pn_main12);
        pn_main12.setLayout(pn_main12Layout);
        pn_main12Layout.setHorizontalGroup(
            pn_main12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_main12Layout.createSequentialGroup()
                .addComponent(pn_line12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(reportKehadiran2, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
                .addContainerGap())
        );
        pn_main12Layout.setVerticalGroup(
            pn_main12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pn_line12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_main12Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(pn_main12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                    .addComponent(reportKehadiran2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5))
        );

        javax.swing.GroupLayout pn_kiriLayout = new javax.swing.GroupLayout(pn_kiri);
        pn_kiri.setLayout(pn_kiriLayout);
        pn_kiriLayout.setHorizontalGroup(
            pn_kiriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_kiriLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(pn_kiriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(pn_main2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pn_main9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pn_main7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(pn_main6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pn_main5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(pn_main4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pn_main3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(pn_main1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pn_main, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pn_main10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pn_main12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
        );
        pn_kiriLayout.setVerticalGroup(
            pn_kiriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_kiriLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(60, 60, 60)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pn_main2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pn_main, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pn_main1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pn_main3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pn_main4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pn_main5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pn_main6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pn_main7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pn_main9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pn_main10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pn_main12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        getContentPane().add(pn_kiri, java.awt.BorderLayout.LINE_START);

        pn_kanan.setBackground(new java.awt.Color(255, 255, 255));
        pn_kanan.setLayout(new java.awt.BorderLayout());

        jpanelGradient1.setColorEnd(new java.awt.Color(33, 28, 132));
        jpanelGradient1.setColorStart(new java.awt.Color(77, 85, 204));

        javax.swing.GroupLayout jpanelGradient1Layout = new javax.swing.GroupLayout(jpanelGradient1);
        jpanelGradient1.setLayout(jpanelGradient1Layout);
        jpanelGradient1Layout.setHorizontalGroup(
            jpanelGradient1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1040, Short.MAX_VALUE)
        );
        jpanelGradient1Layout.setVerticalGroup(
            jpanelGradient1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 71, Short.MAX_VALUE)
        );

        pn_kanan.add(jpanelGradient1, java.awt.BorderLayout.PAGE_START);

        pn_dasar.setBackground(new java.awt.Color(204, 204, 204));

        pn_utama.setBackground(new java.awt.Color(255, 255, 255));
        pn_utama.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                pn_utamaAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        pn_utama.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout pn_dasarLayout = new javax.swing.GroupLayout(pn_dasar);
        pn_dasar.setLayout(pn_dasarLayout);
        pn_dasarLayout.setHorizontalGroup(
            pn_dasarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_dasarLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(pn_utama, javax.swing.GroupLayout.DEFAULT_SIZE, 1000, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );
        pn_dasarLayout.setVerticalGroup(
            pn_dasarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_dasarLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(pn_utama, javax.swing.GroupLayout.DEFAULT_SIZE, 897, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );

        pn_kanan.add(pn_dasar, java.awt.BorderLayout.CENTER);

        getContentPane().add(pn_kanan, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void dataSiswaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dataSiswaMouseClicked
        pn_main.setBackground(new Color(119, 205, 255));
        pn_line.setBackground(new Color(13, 146, 244));
        
        FormDataSiswa formDataSiswa = new FormDataSiswa();
        pn_utama.setLayout(new BorderLayout());
        pn_utama.removeAll();
        pn_utama.add(formDataSiswa, BorderLayout.CENTER);
        pn_utama.repaint();
        pn_utama.revalidate();

    }//GEN-LAST:event_dataSiswaMouseClicked

    private void dataSiswaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dataSiswaMouseEntered
        pn_main.setBackground(new Color(250,250,250));
        pn_line.setBackground(new Color(0,102,153));
    }//GEN-LAST:event_dataSiswaMouseEntered

    private void dataSiswaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dataSiswaMouseExited

        pn_main.setBackground(new Color(51, 153, 255));
        pn_line.setBackground(new Color(51, 153, 255));
    }//GEN-LAST:event_dataSiswaMouseExited

    private void dataGuruMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dataGuruMouseClicked
        pn_main1.setBackground(new Color(119, 205, 255));
        pn_line1.setBackground(new Color(13, 146, 244));
        
        FormDataGuru formDataGuru = new FormDataGuru(); //buat variabel form baru
        pn_utama.removeAll();
        pn_utama.add(formDataGuru, BorderLayout.CENTER); //tambahkan form disini
        pn_utama.repaint();
        pn_utama.revalidate();
    }//GEN-LAST:event_dataGuruMouseClicked

    private void dataGuruMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dataGuruMouseEntered
        pn_main1.setBackground(new Color(250,250,250));
        pn_line1.setBackground(new Color(0,102,153));
    }//GEN-LAST:event_dataGuruMouseEntered

    private void dataGuruMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dataGuruMouseExited
        pn_main1.setBackground(new Color(51, 153, 255));
        pn_line1.setBackground(new Color(51, 153, 255));
    }//GEN-LAST:event_dataGuruMouseExited

    private void kehadiranMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_kehadiranMouseClicked
        pn_main3.setBackground(new Color(119, 205, 255));
        pn_line3.setBackground(new Color(13, 146, 244));
        
        FormDataAbsensi formDataAbsensi = new FormDataAbsensi(); //buat variabel form baru
        pn_utama.removeAll();
        pn_utama.add(formDataAbsensi, BorderLayout.CENTER); //tambahkan form disini
        pn_utama.repaint();
        pn_utama.revalidate();
    }//GEN-LAST:event_kehadiranMouseClicked

    private void kehadiranMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_kehadiranMouseEntered
        pn_main3.setBackground(new Color(250,250,250));
        pn_line3.setBackground(new Color(0,102,153));
    }//GEN-LAST:event_kehadiranMouseEntered

    private void kehadiranMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_kehadiranMouseExited
        pn_main3.setBackground(new Color(51, 153, 255));
        pn_line3.setBackground(new Color(51, 153, 255));
    }//GEN-LAST:event_kehadiranMouseExited

    private void rombelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rombelMouseClicked
        pn_main4.setBackground(new Color(119, 205, 255));
        pn_line4.setBackground(new Color(13, 146, 244));
        
        rombelawal rombelawal = new rombelawal();
        pn_utama.setLayout(new BorderLayout());
        pn_utama.removeAll();
        pn_utama.add(rombelawal, BorderLayout.CENTER);
        pn_utama.repaint();
        pn_utama.revalidate();
    }//GEN-LAST:event_rombelMouseClicked

    private void rombelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rombelMouseEntered
        pn_main4.setBackground(new Color(250,250,250));
        pn_line4.setBackground(new Color(0,102,153));
    }//GEN-LAST:event_rombelMouseEntered

    private void rombelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rombelMouseExited
        pn_main4.setBackground(new Color(51, 153, 255));
        pn_line4.setBackground(new Color(51, 153, 255));
    }//GEN-LAST:event_rombelMouseExited

    private void mapelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mapelMouseClicked
        pn_main5.setBackground(new Color(119, 205, 255));
        pn_line5.setBackground(new Color(13, 146, 244));
        
        FormMengajarGuru formMengajarGuru = new FormMengajarGuru(); //buat variabel form baru
        pn_utama.removeAll();
        pn_utama.add(formMengajarGuru, BorderLayout.CENTER); //tambahkan form disini
        pn_utama.repaint();
        pn_utama.revalidate();
    }//GEN-LAST:event_mapelMouseClicked

    private void mapelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mapelMouseEntered
        pn_main5.setBackground(new Color(250,250,250));
        pn_line5.setBackground(new Color(0,102,153));
    }//GEN-LAST:event_mapelMouseEntered

    private void mapelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mapelMouseExited
        pn_main5.setBackground(new Color(51, 153, 255));
        pn_line5.setBackground(new Color(51, 153, 255));
    }//GEN-LAST:event_mapelMouseExited

    private void reportRombelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reportRombelMouseClicked
        pn_main6.setBackground(new Color(119, 205, 255));
        pn_line6.setBackground(new Color(13, 146, 244));
        
        laporanrombel lpr = new laporanrombel(); //buat variabel form baru
        pn_utama.removeAll();
        pn_utama.add(lpr, BorderLayout.CENTER); //tambahkan form disini
        pn_utama.repaint();
        pn_utama.revalidate();
    }//GEN-LAST:event_reportRombelMouseClicked

    private void reportRombelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reportRombelMouseEntered
        pn_main6.setBackground(new Color(250,250,250));
        pn_line6.setBackground(new Color(0,102,153));
    }//GEN-LAST:event_reportRombelMouseEntered

    private void reportRombelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reportRombelMouseExited
        pn_main6.setBackground(new Color(51, 153, 255));
        pn_line6.setBackground(new Color(51, 153, 255));
    }//GEN-LAST:event_reportRombelMouseExited

    private void reportMapelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reportMapelMouseClicked
        pn_main7.setBackground(new Color(119, 205, 255));
        pn_line7.setBackground(new Color(13, 146, 244));
        
        lpMl lpml = new lpMl(); //buat variabel form baru
        //lpml.setVisible(true);
        
        pn_utama.removeAll();
        pn_utama.add(lpml, BorderLayout.CENTER); //tambahkan form disini
        pn_utama.repaint();
        pn_utama.revalidate();
    }//GEN-LAST:event_reportMapelMouseClicked

    private void reportMapelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reportMapelMouseEntered
        pn_main7.setBackground(new Color(250,250,250));
        pn_line7.setBackground(new Color(0,102,153));
    }//GEN-LAST:event_reportMapelMouseEntered

    private void reportMapelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reportMapelMouseExited
        pn_main7.setBackground(new Color(51, 153, 255));
        pn_line7.setBackground(new Color(51, 153, 255));
    }//GEN-LAST:event_reportMapelMouseExited

    private void reportKehadiranMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reportKehadiranMouseClicked
        pn_main9.setBackground(new Color(119, 205, 255));
        pn_line9.setBackground(new Color(13, 146, 244));
        
        lpGr lpgr = new lpGr(); //buat variabel form baru
        pn_utama.removeAll();
        pn_utama.add(lpgr, BorderLayout.CENTER); //tambahkan form disini
        pn_utama.repaint();
        pn_utama.revalidate();
    }//GEN-LAST:event_reportKehadiranMouseClicked

    private void reportKehadiranMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reportKehadiranMouseEntered
        pn_main9.setBackground(new Color(250,250,250));
        pn_line9.setBackground(new Color(0,102,153));
    }//GEN-LAST:event_reportKehadiranMouseEntered

    private void reportKehadiranMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reportKehadiranMouseExited
        pn_main9.setBackground(new Color(51, 153, 255));
        pn_line9.setBackground(new Color(51, 153, 255));
    }//GEN-LAST:event_reportKehadiranMouseExited

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
       
    }//GEN-LAST:event_formMouseDragged

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        
    }//GEN-LAST:event_formMousePressed

    private void dataSiswa1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dataSiswa1MouseClicked
        pn_main2.setBackground(new Color(119, 205, 255));
        pn_line2.setBackground(new Color(13, 146, 244));
        
        Dashboard db = new Dashboard(); //buat variabel form baru
        pn_utama.removeAll();
        pn_utama.add(db, BorderLayout.CENTER); //tambahkan form disini
        pn_utama.repaint();
        pn_utama.revalidate();
    }//GEN-LAST:event_dataSiswa1MouseClicked

    private void dataSiswa1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dataSiswa1MouseEntered
        pn_main2.setBackground(new Color(250,250,250));
        pn_line2.setBackground(new Color(0,102,153));
    }//GEN-LAST:event_dataSiswa1MouseEntered

    private void dataSiswa1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dataSiswa1MouseExited
        pn_main2.setBackground(new Color(51, 153, 255));
        pn_line2.setBackground(new Color(51, 153, 255));
    }//GEN-LAST:event_dataSiswa1MouseExited

    private void reportdataSiswaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reportdataSiswaMouseClicked
        pn_main10.setBackground(new Color(119, 205, 255));
        pn_line10.setBackground(new Color(13, 146, 244));
        
        lpSw lpmr = new lpSw(); //buat variabel form baru
        pn_utama.removeAll();
        pn_utama.add(lpmr, BorderLayout.CENTER); //tambahkan form disini
        pn_utama.repaint();
        pn_utama.revalidate();
    }//GEN-LAST:event_reportdataSiswaMouseClicked

    private void reportdataSiswaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reportdataSiswaMouseEntered
        pn_main10.setBackground(new Color(250,250,250));
        pn_line10.setBackground(new Color(0,102,153));
    }//GEN-LAST:event_reportdataSiswaMouseEntered

    private void reportdataSiswaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reportdataSiswaMouseExited
        pn_main10.setBackground(new Color(51, 153, 255));
        pn_line10.setBackground(new Color(51, 153, 255));
    }//GEN-LAST:event_reportdataSiswaMouseExited

    private void reportKehadiran2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reportKehadiran2MouseClicked
       pn_main12.setBackground(new Color(119, 205, 255));
        pn_line12.setBackground(new Color(13, 146, 244));
        
        laporanGuru lpgr = new laporanGuru(); //buat variabel form baru
        pn_utama.removeAll();
        pn_utama.add(lpgr, BorderLayout.CENTER); //tambahkan form disini
        pn_utama.repaint();
        pn_utama.revalidate();
    }//GEN-LAST:event_reportKehadiran2MouseClicked

    private void reportKehadiran2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reportKehadiran2MouseEntered
        pn_main12.setBackground(new Color(250,250,250));
        pn_line12.setBackground(new Color(0,102,153));
    }//GEN-LAST:event_reportKehadiran2MouseEntered

    private void reportKehadiran2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reportKehadiran2MouseExited
        pn_main12.setBackground(new Color(51, 153, 255));
        pn_line12.setBackground(new Color(51, 153, 255));
    }//GEN-LAST:event_reportKehadiran2MouseExited

    private void pn_utamaAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_pn_utamaAncestorAdded
        Dashboard db = new Dashboard(); //buat variabel form baru
        pn_utama.removeAll();
        pn_utama.add(db, BorderLayout.CENTER); //tambahkan form disini
        pn_utama.repaint();
        pn_utama.revalidate();
    }//GEN-LAST:event_pn_utamaAncestorAdded

    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            System.setProperty("sun.java2d.uiScale", "1.0"); 
            new MenuUtama().setVisible(true);
            
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel dataGuru;
    private javax.swing.JLabel dataSiswa;
    private javax.swing.JLabel dataSiswa1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private com.mtsarridho.pallate.JpanelGradient jpanelGradient1;
    private javax.swing.JLabel kehadiran;
    private javax.swing.JLabel mapel;
    private javax.swing.JPanel pn_dasar;
    private javax.swing.JPanel pn_kanan;
    private javax.swing.JPanel pn_kiri;
    private javax.swing.JPanel pn_line;
    private javax.swing.JPanel pn_line1;
    private javax.swing.JPanel pn_line10;
    private javax.swing.JPanel pn_line12;
    private javax.swing.JPanel pn_line2;
    private javax.swing.JPanel pn_line3;
    private javax.swing.JPanel pn_line4;
    private javax.swing.JPanel pn_line5;
    private javax.swing.JPanel pn_line6;
    private javax.swing.JPanel pn_line7;
    private javax.swing.JPanel pn_line9;
    private javax.swing.JPanel pn_main;
    private javax.swing.JPanel pn_main1;
    private javax.swing.JPanel pn_main10;
    private javax.swing.JPanel pn_main12;
    private javax.swing.JPanel pn_main2;
    private javax.swing.JPanel pn_main3;
    private javax.swing.JPanel pn_main4;
    private javax.swing.JPanel pn_main5;
    private javax.swing.JPanel pn_main6;
    private javax.swing.JPanel pn_main7;
    private javax.swing.JPanel pn_main9;
    private javax.swing.JPanel pn_utama;
    private javax.swing.JLabel reportKehadiran;
    private javax.swing.JLabel reportKehadiran2;
    private javax.swing.JLabel reportMapel;
    private javax.swing.JLabel reportRombel;
    private javax.swing.JLabel reportdataSiswa;
    private javax.swing.JLabel rombel;
    // End of variables declaration//GEN-END:variables
}
