
package com.mtsarridho.form;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import com.mtsarridho.config.KoneksiDatabase;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
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

    // Monthly summary statistics
    private int totalHadir = 0;
    private int totalTidakHadir = 0;
    private double persentaseKehadiran = 0.0;
    private boolean isTableFiltered = false;

    // Summary UI components
    private javax.swing.JPanel panelSummary;
    private javax.swing.JComboBox<String> cbBulan;
    private javax.swing.JComboBox<String> cbTahun;
    private javax.swing.JButton btnFilter;
    private javax.swing.JButton btnSemua;
    private javax.swing.JButton btnExportCsv;
    private javax.swing.JLabel lblTotalHadir;
    private javax.swing.JLabel lblTotalTidakHadir;
    private javax.swing.JLabel lblPersentase;

    // Teacher search and status filter UI components
    private javax.swing.JTextField txtCari;
    private javax.swing.JComboBox<String> cbStatus;
    private javax.swing.JButton btnCari;
    private javax.swing.JButton btnResetCari;

    private static final String[] STATUS_KEHADIRAN = {
        "Semua Status", "Hadir", "Tidak Hadir"
    };

    // Filtered attendance totals UI components
    private int filteredTotalData = 0;
    private int filteredTotalHadir = 0;
    private int filteredTotalTidakHadir = 0;
    private javax.swing.JPanel pnlFilteredTotals;
    private javax.swing.JLabel lblFilteredTotalData;
    private javax.swing.JLabel lblFilteredHadir;
    private javax.swing.JLabel lblFilteredTidakHadir;

    private static final String[] NAMA_BULAN = {
        "Januari", "Februari", "Maret", "April", "Mei", "Juni",
        "Juli", "Agustus", "September", "Oktober", "November", "Desember"
    };

    @Override
    public void onDataChanged() {
        tampilkanDataAbsensiGuru();
        setupTableSorter();
        updateMonthlySummary();
        updateFilteredTotals();
    }

    public FormDataAbsensi() {
        initComponents();
        initSummaryComponents();
        tampilkanDataAbsensiGuru();
        setupTableSorter();
        updateMonthlySummary();
        updateFilteredTotals();
    }

    /**
     * Initialize the monthly attendance summary UI components,
     * including Month/Year filters and statistics cards.
     */
    private void initSummaryComponents() {
        panelSummary = new JPanel(new BorderLayout(15, 0));
        panelSummary.setBackground(Color.WHITE);
        panelSummary.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(218, 224, 233)),
            BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));

        // Container for Filter and Search rows on the left
        JPanel pnlControls = new JPanel();
        pnlControls.setLayout(new BoxLayout(pnlControls, BoxLayout.Y_AXIS));
        pnlControls.setOpaque(false);

        // Row 1: Filter Controls
        JPanel pnlFilter = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 2));
        pnlFilter.setOpaque(false);

        JLabel lblFilterTitle = new JLabel("Rekap Bulanan:");
        lblFilterTitle.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblFilterTitle.setForeground(new Color(50, 50, 50));

        JLabel lblBulanTitle = new JLabel("Bulan:");
        lblBulanTitle.setFont(new Font("Segoe UI", Font.PLAIN, 12));

        cbBulan = new JComboBox<String>(NAMA_BULAN) {
            @Override
            public void setSelectedItem(Object anObject) {
                if (anObject instanceof Integer) {
                    int m = (Integer) anObject;
                    if (m >= 1 && m <= 12) {
                        setSelectedIndex(m - 1);
                        return;
                    }
                } else if (anObject instanceof String) {
                    String str = ((String) anObject).trim();
                    try {
                        int m = Integer.parseInt(str);
                        if (m >= 1 && m <= 12) {
                            setSelectedIndex(m - 1);
                            return;
                        }
                    } catch (NumberFormatException ignored) {}
                    for (int i = 0; i < NAMA_BULAN.length; i++) {
                        if (NAMA_BULAN[i].equalsIgnoreCase(str)) {
                            setSelectedIndex(i);
                            return;
                        }
                    }
                }
                super.setSelectedItem(anObject);
            }
        };
        cbBulan.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        cbBulan.setPreferredSize(new Dimension(115, 30));

        JLabel lblTahunTitle = new JLabel("Tahun:");
        lblTahunTitle.setFont(new Font("Segoe UI", Font.PLAIN, 12));

        cbTahun = new JComboBox<String>() {
            @Override
            public void setSelectedItem(Object anObject) {
                if (anObject != null) {
                    String val = anObject.toString().trim();
                    DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) getModel();
                    boolean found = false;
                    for (int i = 0; i < model.getSize(); i++) {
                        if (model.getElementAt(i).equals(val)) {
                            setSelectedIndex(i);
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        model.insertElementAt(val, 0);
                        setSelectedIndex(0);
                        return;
                    }
                }
                super.setSelectedItem(anObject);
            }
        };
        cbTahun.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        cbTahun.setPreferredSize(new Dimension(80, 30));

        populateYears();

        int currentMonth = LocalDate.now().getMonthValue();
        cbBulan.setSelectedIndex(currentMonth - 1);

        btnFilter = new JButton("FILTER");
        btnFilter.setFont(new Font("Segoe UI", Font.BOLD, 11));
        btnFilter.setBackground(new Color(13, 146, 244));
        btnFilter.setForeground(Color.WHITE);
        btnFilter.setFocusPainted(false);
        btnFilter.setPreferredSize(new Dimension(75, 30));
        btnFilter.setToolTipText("Terapkan filter bulan, tahun, dan pencarian");

        btnSemua = new JButton("SEMUA");
        btnSemua.setFont(new Font("Segoe UI", Font.BOLD, 11));
        btnSemua.setBackground(new Color(108, 117, 125));
        btnSemua.setForeground(Color.WHITE);
        btnSemua.setFocusPainted(false);
        btnSemua.setPreferredSize(new Dimension(75, 30));
        btnSemua.setToolTipText("Tampilkan semua data kehadiran tanpa filter");

        btnExportCsv = new JButton("EXPORT CSV");
        btnExportCsv.setFont(new Font("Segoe UI", Font.BOLD, 11));
        btnExportCsv.setBackground(new Color(40, 167, 69));
        btnExportCsv.setForeground(Color.WHITE);
        btnExportCsv.setFocusPainted(false);
        btnExportCsv.setPreferredSize(new Dimension(105, 30));
        btnExportCsv.setToolTipText("Ekspor data kehadiran guru periode ini ke file CSV");

        pnlFilter.add(lblFilterTitle);
        pnlFilter.add(lblBulanTitle);
        pnlFilter.add(cbBulan);
        pnlFilter.add(lblTahunTitle);
        pnlFilter.add(cbTahun);
        pnlFilter.add(btnFilter);
        pnlFilter.add(btnSemua);
        pnlFilter.add(btnExportCsv);

        // Row 2: Teacher Search and Status Filter Controls
        JPanel pnlSearch = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 2));
        pnlSearch.setOpaque(false);

        JLabel lblSearchTitle = new JLabel("Cari Guru:");
        lblSearchTitle.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblSearchTitle.setForeground(new Color(50, 50, 50));

        txtCari = new JTextField();
        txtCari.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        txtCari.setPreferredSize(new Dimension(170, 30));
        txtCari.setToolTipText("Ketik nama guru untuk mencari...");

        JLabel lblStatusTitle = new JLabel("Status:");
        lblStatusTitle.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblStatusTitle.setForeground(new Color(50, 50, 50));

        cbStatus = new JComboBox<>(STATUS_KEHADIRAN);
        cbStatus.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        cbStatus.setPreferredSize(new Dimension(120, 30));
        cbStatus.setToolTipText("Filter berdasarkan status kehadiran guru");

        btnCari = new JButton("CARI");
        btnCari.setFont(new Font("Segoe UI", Font.BOLD, 11));
        btnCari.setBackground(new Color(13, 146, 244));
        btnCari.setForeground(Color.WHITE);
        btnCari.setFocusPainted(false);
        btnCari.setPreferredSize(new Dimension(70, 30));
        btnCari.setToolTipText("Cari kehadiran berdasarkan nama guru");

        btnResetCari = new JButton("RESET");
        btnResetCari.setFont(new Font("Segoe UI", Font.BOLD, 11));
        btnResetCari.setBackground(new Color(108, 117, 125));
        btnResetCari.setForeground(Color.WHITE);
        btnResetCari.setFocusPainted(false);
        btnResetCari.setPreferredSize(new Dimension(70, 30));
        btnResetCari.setToolTipText("Reset pencarian dan filter status");

        pnlSearch.add(lblSearchTitle);
        pnlSearch.add(txtCari);
        pnlSearch.add(lblStatusTitle);
        pnlSearch.add(cbStatus);
        pnlSearch.add(btnCari);
        pnlSearch.add(btnResetCari);

        pnlControls.add(pnlFilter);
        pnlControls.add(pnlSearch);

        // Row 3: Filtered Attendance Totals
        pnlFilteredTotals = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 2));
        pnlFilteredTotals.setOpaque(false);

        JLabel lblFilteredTitle = new JLabel("Data Terfilter:");
        lblFilteredTitle.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblFilteredTitle.setForeground(new Color(50, 50, 50));

        lblFilteredTotalData = new JLabel("0");
        lblFilteredHadir = new JLabel("0");
        lblFilteredTidakHadir = new JLabel("0");

        JPanel badgeTotal = createBadge("Total Data:", lblFilteredTotalData, new Color(50, 50, 50), new Color(245, 245, 245), new Color(210, 215, 220));
        JPanel badgeHadir = createBadge("Hadir:", lblFilteredHadir, new Color(46, 125, 50), new Color(232, 245, 233), new Color(200, 230, 201));
        JPanel badgeTidakHadir = createBadge("Tidak Hadir:", lblFilteredTidakHadir, new Color(198, 40, 40), new Color(255, 235, 238), new Color(255, 205, 210));

        pnlFilteredTotals.add(lblFilteredTitle);
        pnlFilteredTotals.add(badgeTotal);
        pnlFilteredTotals.add(badgeHadir);
        pnlFilteredTotals.add(badgeTidakHadir);

        pnlControls.add(pnlFilteredTotals);

        // Right Panel: Summary Cards
        JPanel pnlCards = new JPanel(new FlowLayout(FlowLayout.RIGHT, 12, 2));
        pnlCards.setOpaque(false);

        // Card 1: Total Hadir
        JPanel cardHadir = createSummaryCard("TOTAL HADIR", new Color(46, 125, 50), new Color(232, 245, 233), new Color(200, 230, 201));
        lblTotalHadir = new JLabel("0", SwingConstants.CENTER);
        lblTotalHadir.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTotalHadir.setForeground(new Color(27, 94, 32));
        cardHadir.add(lblTotalHadir, BorderLayout.CENTER);

        // Card 2: Total Tidak Hadir
        JPanel cardTidakHadir = createSummaryCard("TOTAL TIDAK HADIR", new Color(198, 40, 40), new Color(255, 235, 238), new Color(255, 205, 210));
        lblTotalTidakHadir = new JLabel("0", SwingConstants.CENTER);
        lblTotalTidakHadir.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTotalTidakHadir.setForeground(new Color(183, 28, 28));
        cardTidakHadir.add(lblTotalTidakHadir, BorderLayout.CENTER);

        // Card 3: Persentase Kehadiran
        JPanel cardPersentase = createSummaryCard("PERSENTASE", new Color(21, 101, 192), new Color(227, 242, 253), new Color(187, 222, 251));
        lblPersentase = new JLabel("0.0%", SwingConstants.CENTER);
        lblPersentase.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblPersentase.setForeground(new Color(13, 71, 161));
        cardPersentase.add(lblPersentase, BorderLayout.CENTER);

        pnlCards.add(cardHadir);
        pnlCards.add(cardTidakHadir);
        pnlCards.add(cardPersentase);

        panelSummary.add(pnlControls, BorderLayout.WEST);
        panelSummary.add(pnlCards, BorderLayout.EAST);

        // Add panelSummary to the NORTH region of FormDataAbsensi
        add(panelSummary, BorderLayout.NORTH);

        // Register action listeners for interactive filtering and searching
        cbBulan.addActionListener(e -> {
            updateMonthlySummary();
            if (isTableFiltered) {
                tampilkanDataAbsensiGuru();
                setupTableSorter();
            }
        });

        cbTahun.addActionListener(e -> {
            updateMonthlySummary();
            if (isTableFiltered) {
                tampilkanDataAbsensiGuru();
                setupTableSorter();
            }
        });

        btnFilter.addActionListener(e -> {
            isTableFiltered = true;
            updateMonthlySummary();
            tampilkanDataAbsensiGuru();
            setupTableSorter();
        });

        btnSemua.addActionListener(e -> {
            isTableFiltered = false;
            if (txtCari != null) {
                txtCari.setText("");
            }
            if (cbStatus != null) {
                cbStatus.setSelectedIndex(0);
            }
            tampilkanSemuaDataAbsensiGuru();
            setupTableSorter();
            updateMonthlySummary();
        });

        btnExportCsv.addActionListener(e -> exportAttendanceCsv());

        btnCari.addActionListener(e -> {
            tampilkanDataAbsensiGuru();
            setupTableSorter();
            updateMonthlySummary();
        });

        txtCari.addActionListener(e -> {
            tampilkanDataAbsensiGuru();
            setupTableSorter();
            updateMonthlySummary();
        });

        cbStatus.addActionListener(e -> {
            tampilkanDataAbsensiGuru();
            setupTableSorter();
        });

        btnResetCari.addActionListener(e -> {
            if (txtCari != null) {
                txtCari.setText("");
            }
            if (cbStatus != null) {
                cbStatus.setSelectedIndex(0);
            }
            tampilkanDataAbsensiGuru();
            setupTableSorter();
            updateMonthlySummary();
        });
    }

    private JPanel createSummaryCard(String title, Color headerColor, Color bgColor, Color borderColor) {
        JPanel card = new JPanel(new BorderLayout(0, 2));
        card.setBackground(bgColor);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(borderColor, 1, true),
            BorderFactory.createEmptyBorder(4, 10, 4, 10)
        ));
        card.setPreferredSize(new Dimension(130, 48));

        JLabel lblTitle = new JLabel(title, SwingConstants.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 10));
        lblTitle.setForeground(headerColor);
        card.add(lblTitle, BorderLayout.NORTH);

        return card;
    }

    private JPanel createBadge(String title, JLabel valueLabel, Color textColor, Color bgColor, Color borderColor) {
        JPanel badge = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 2));
        badge.setBackground(bgColor);
        badge.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(borderColor, 1, true),
            BorderFactory.createEmptyBorder(2, 6, 2, 6)
        ));

        JLabel lblTitle = new JLabel(title);
        lblTitle.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        lblTitle.setForeground(textColor);

        valueLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
        valueLabel.setForeground(textColor);

        badge.add(lblTitle);
        badge.add(valueLabel);
        return badge;
    }

    /**
     * Populate cbTahun with distinct years from database and surrounding years.
     */
    private void populateYears() {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        int currentYear = LocalDate.now().getYear();
        Set<Integer> years = new TreeSet<>(Collections.reverseOrder());

        // Default year range around current year
        for (int y = currentYear + 2; y >= currentYear - 5; y--) {
            years.add(y);
        }

        // Include any distinct years from actual attendance database records
        try {
            Connection conn = KoneksiDatabase.getKoneksi();
            if (conn != null) {
                String sql = "SELECT DISTINCT YEAR(waktu) AS tahun FROM tb_kehadiran_guru WHERE waktu IS NOT NULL ORDER BY tahun DESC";
                try (PreparedStatement ps = conn.prepareStatement(sql);
                     ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        int yr = rs.getInt("tahun");
                        if (yr > 1900 && yr < 2200) {
                            years.add(yr);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Error fetching distinct attendance years: " + e.getMessage());
        }

        for (Integer yr : years) {
            model.addElement(String.valueOf(yr));
        }
        cbTahun.setModel(model);
        cbTahun.setSelectedItem(String.valueOf(currentYear));
    }

    /**
     * Recalculates monthly summary statistics using currently selected month & year
     * and any active teacher name search filter.
     */
    public void updateMonthlySummary() {
        updateMonthlySummary(getSelectedBulan(), getSelectedTahun(), getSearchKeyword());
    }

    /**
     * Aggregates attendance records from database for specified month and year using PreparedStatement.
     * Computes Total Hadir, Total Tidak Hadir, and Attendance Percentage.
     */
    public void updateMonthlySummary(int bulan, int tahun) {
        updateMonthlySummary(bulan, tahun, getSearchKeyword());
    }

    /**
     * Aggregates attendance records from database for specified month, year, and optional teacher name filter.
     * Computes Total Hadir, Total Tidak Hadir, and Attendance Percentage.
     */
    public void updateMonthlySummary(int bulan, int tahun, String teacherName) {
        int hadir = 0;
        int tidakHadir = 0;
        String keyword = (teacherName != null) ? teacherName.trim() : "";

        try {
            Connection conn = KoneksiDatabase.getKoneksi();
            if (conn != null) {
                String sql;
                if (keyword.isEmpty()) {
                    sql = "SELECT kehadiran, COUNT(*) AS jumlah " +
                          "FROM tb_kehadiran_guru " +
                          "WHERE YEAR(waktu) = ? AND MONTH(waktu) = ? " +
                          "GROUP BY kehadiran";
                } else {
                    sql = "SELECT kehadiran, COUNT(*) AS jumlah " +
                          "FROM tb_kehadiran_guru " +
                          "WHERE YEAR(waktu) = ? AND MONTH(waktu) = ? AND nama LIKE ? " +
                          "GROUP BY kehadiran";
                }
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setInt(1, tahun);
                    ps.setInt(2, bulan);
                    if (!keyword.isEmpty()) {
                        ps.setString(3, "%" + keyword + "%");
                    }
                    try (ResultSet rs = ps.executeQuery()) {
                        while (rs.next()) {
                            String status = rs.getString("kehadiran");
                            int count = rs.getInt("jumlah");
                            if (status != null) {
                                String trimmed = status.trim();
                                if ("Hadir".equalsIgnoreCase(trimmed)) {
                                    hadir += count;
                                } else if ("Tidak Hadir".equalsIgnoreCase(trimmed)) {
                                    tidakHadir += count;
                                }
                            }
                        }
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Error calculating monthly attendance summary: " + e.getMessage());
            JOptionPane.showMessageDialog(this, "Gagal memuat rekap kehadiran bulanan dari database.", "Database Error", JOptionPane.ERROR_MESSAGE);
        }

        this.totalHadir = hadir;
        this.totalTidakHadir = tidakHadir;
        int totalEvaluated = hadir + tidakHadir;
        this.persentaseKehadiran = totalEvaluated > 0 ? ((double) hadir / totalEvaluated) * 100.0 : 0.0;

        if (lblTotalHadir != null) {
            lblTotalHadir.setText(String.valueOf(this.totalHadir));
        }
        if (lblTotalTidakHadir != null) {
            lblTotalTidakHadir.setText(String.valueOf(this.totalTidakHadir));
        }
        if (lblPersentase != null) {
            lblPersentase.setText(String.format(java.util.Locale.US, "%.1f%%", this.persentaseKehadiran));
        }
    }
    
    private void setupTableSorter() {
        DefaultTableModel model = (DefaultTableModel) tableDataAbsen.getModel();
        sorter = new TableRowSorter<>(model);
        sorter.addRowSorterListener(e -> {
            updateFilteredTotals();
        });
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
        setupTableSorter();
        updateMonthlySummary();
        updateFilteredTotals();
    }
    
    public void tampilkanDataAbsensiGuru() {
        String keyword = getSearchKeyword();
        String status = getSelectedStatus();
        if (isTableFiltered) {
            tampilkanDataAbsensiGuru(getSelectedBulan(), getSelectedTahun(), keyword, status);
        } else {
            tampilkanSemuaDataAbsensiGuru(keyword, status);
        }
    }

    public void tampilkanSemuaDataAbsensiGuru() {
        tampilkanSemuaDataAbsensiGuru(getSearchKeyword(), getSelectedStatus());
    }

    public void tampilkanSemuaDataAbsensiGuru(String teacherName) {
        tampilkanSemuaDataAbsensiGuru(teacherName, getSelectedStatus());
    }

    public void tampilkanSemuaDataAbsensiGuru(String teacherName, String status) {
        isTableFiltered = false;
        String keyword = (teacherName != null) ? teacherName.trim() : "";
        String st = (status != null) ? status.trim() : "";
        if ("Semua Status".equalsIgnoreCase(st)) {
            st = "";
        }

        StringBuilder sql = new StringBuilder("SELECT id_kehadiran, nama, kehadiran, waktu FROM tb_kehadiran_guru");
        List<Object> params = new ArrayList<>();
        boolean hasWhere = false;

        if (!keyword.isEmpty()) {
            sql.append(hasWhere ? " AND" : " WHERE").append(" nama LIKE ?");
            params.add("%" + keyword + "%");
            hasWhere = true;
        }

        if (!st.isEmpty()) {
            sql.append(hasWhere ? " AND" : " WHERE").append(" kehadiran = ?");
            params.add(st);
            hasWhere = true;
        }

        sql.append(" ORDER BY waktu DESC, id_kehadiran DESC");
        loadTableData(sql.toString(), params.isEmpty() ? null : params.toArray());
    }

    public void tampilkanDataAbsensiGuru(int bulan, int tahun) {
        tampilkanDataAbsensiGuru(bulan, tahun, getSearchKeyword(), getSelectedStatus());
    }

    public void tampilkanDataAbsensiGuru(int bulan, int tahun, String teacherName) {
        tampilkanDataAbsensiGuru(bulan, tahun, teacherName, getSelectedStatus());
    }

    public void tampilkanDataAbsensiGuru(int bulan, int tahun, String teacherName, String status) {
        isTableFiltered = true;
        String keyword = (teacherName != null) ? teacherName.trim() : "";
        String st = (status != null) ? status.trim() : "";
        if ("Semua Status".equalsIgnoreCase(st)) {
            st = "";
        }

        StringBuilder sql = new StringBuilder("SELECT id_kehadiran, nama, kehadiran, waktu FROM tb_kehadiran_guru WHERE YEAR(waktu) = ? AND MONTH(waktu) = ?");
        List<Object> params = new ArrayList<>();
        params.add(tahun);
        params.add(bulan);

        if (!keyword.isEmpty()) {
            sql.append(" AND nama LIKE ?");
            params.add("%" + keyword + "%");
        }

        if (!st.isEmpty()) {
            sql.append(" AND kehadiran = ?");
            params.add(st);
        }

        sql.append(" ORDER BY waktu DESC, id_kehadiran DESC");
        loadTableData(sql.toString(), params.toArray());
    }

    public void cariData(String keyword) {
        if (txtCari != null) {
            txtCari.setText(keyword != null ? keyword : "");
        }
        tampilkanDataAbsensiGuru();
        setupTableSorter();
        updateMonthlySummary();
    }

    public void cariDataGuru(String keyword) {
        cariData(keyword);
    }

    public void filterStatus(String status) {
        setSelectedStatus(status);
        tampilkanDataAbsensiGuru();
        setupTableSorter();
    }

    private void loadTableData(String sql, int[] params) {
        if (params == null) {
            loadTableData(sql, (Object[]) null);
        } else {
            Object[] objParams = new Object[params.length];
            for (int i = 0; i < params.length; i++) {
                objParams[i] = params[i];
            }
            loadTableData(sql, objParams);
        }
    }

    private void loadTableData(String sql, Object[] params) {
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        model.addColumn("NO");
        model.addColumn("NAMA GURU");
        model.addColumn("KEHADIRAN");
        model.addColumn("TANGGAL");
        
        listIdKehadiran.clear();
        
        try {
            Connection conn = KoneksiDatabase.getKoneksi();
            if (conn != null) {
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    if (params != null) {
                        for (int i = 0; i < params.length; i++) {
                            ps.setObject(i + 1, params[i]);
                        }
                    }
                    try (ResultSet rs = ps.executeQuery()) {
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
                    }
                }
            }
            
            tableDataAbsen.setModel(model);
            applyTableFormatting();
            updateFilteredTotals();
            
        } catch (SQLException e) {
            System.err.println("Error displaying attendance data: " + e.getMessage());
            JOptionPane.showMessageDialog(this, "Gagal menampilkan data kehadiran guru: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Calculates and updates the concise summary of currently visible attendance records
     * (Total Data, Hadir, and Tidak Hadir) directly from the existing table data.
     */
    public void updateFilteredTotals() {
        int total = 0;
        int hadir = 0;
        int tidakHadir = 0;

        if (tableDataAbsen != null && tableDataAbsen.getModel() != null) {
            DefaultTableModel model = (DefaultTableModel) tableDataAbsen.getModel();
            if (tableDataAbsen.getRowSorter() != null && tableDataAbsen.getRowSorter().getViewRowCount() >= 0) {
                total = tableDataAbsen.getRowCount();
                for (int i = 0; i < total; i++) {
                    int modelRow = tableDataAbsen.convertRowIndexToModel(i);
                    Object val = model.getValueAt(modelRow, 2);
                    if (val != null) {
                        String status = val.toString().trim();
                        if ("Hadir".equalsIgnoreCase(status)) {
                            hadir++;
                        } else if ("Tidak Hadir".equalsIgnoreCase(status)) {
                            tidakHadir++;
                        }
                    }
                }
            } else {
                total = model.getRowCount();
                for (int i = 0; i < total; i++) {
                    Object val = model.getValueAt(i, 2);
                    if (val != null) {
                        String status = val.toString().trim();
                        if ("Hadir".equalsIgnoreCase(status)) {
                            hadir++;
                        } else if ("Tidak Hadir".equalsIgnoreCase(status)) {
                            tidakHadir++;
                        }
                    }
                }
            }
        }

        this.filteredTotalData = total;
        this.filteredTotalHadir = hadir;
        this.filteredTotalTidakHadir = tidakHadir;

        if (lblFilteredTotalData != null) {
            lblFilteredTotalData.setText(String.valueOf(total));
        }
        if (lblFilteredHadir != null) {
            lblFilteredHadir.setText(String.valueOf(hadir));
        }
        if (lblFilteredTidakHadir != null) {
            lblFilteredTidakHadir.setText(String.valueOf(tidakHadir));
        }
    }

    private void applyTableFormatting() {
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
    }

    // Public getters and utility methods for statistical values and UI components
    public int getTotalHadir() {
        return totalHadir;
    }

    public int getTotalTidakHadir() {
        return totalTidakHadir;
    }

    public double getPersentaseKehadiran() {
        return persentaseKehadiran;
    }

    public int getSelectedBulan() {
        if (cbBulan != null) {
            return cbBulan.getSelectedIndex() + 1;
        }
        return LocalDate.now().getMonthValue();
    }

    public int getSelectedTahun() {
        if (cbTahun != null) {
            try {
                Object item = cbTahun.getSelectedItem();
                if (item != null) {
                    return Integer.parseInt(item.toString().trim());
                }
            } catch (NumberFormatException ignored) {}
        }
        return LocalDate.now().getYear();
    }

    public JComboBox<String> getCbBulan() {
        return cbBulan;
    }

    public JComboBox<String> getCbTahun() {
        return cbTahun;
    }

    public JLabel getLblTotalHadir() {
        return lblTotalHadir;
    }

    public JLabel getLblTotalTidakHadir() {
        return lblTotalTidakHadir;
    }

    public JLabel getLblPersentase() {
        return lblPersentase;
    }

    public JButton getBtnFilter() {
        return btnFilter;
    }

    public JButton getBtnSemua() {
        return btnSemua;
    }

    public JPanel getPanelSummary() {
        return panelSummary;
    }

    public com.mtsarridho.pallate.JTableCustom getTableDataAbsen() {
        return tableDataAbsen;
    }

    public void setFilterBulanTahun(int bulan, int tahun) {
        if (cbBulan != null && bulan >= 1 && bulan <= 12) {
            cbBulan.setSelectedIndex(bulan - 1);
        }
        if (cbTahun != null) {
            cbTahun.setSelectedItem(String.valueOf(tahun));
        }
        updateMonthlySummary();
    }

    public void filterBulanan(int bulan, int tahun) {
        setFilterBulanTahun(bulan, tahun);
        tampilkanDataAbsensiGuru(bulan, tahun);
        setupTableSorter();
    }

    public void filterBulanan(int bulan, int tahun, String teacherName) {
        filterBulanan(bulan, tahun, teacherName, getSelectedStatus());
    }

    public void filterBulanan(int bulan, int tahun, String teacherName, String status) {
        setKeywordCari(teacherName);
        setSelectedStatus(status);
        setFilterBulanTahun(bulan, tahun);
        tampilkanDataAbsensiGuru(bulan, tahun, teacherName, status);
        setupTableSorter();
    }

    public void filterGuru(String teacherName) {
        cariData(teacherName);
    }

    public JTextField getTxtCari() {
        return txtCari;
    }

    public JTextField getSearchTxt() {
        return txtCari;
    }

    public JComboBox<String> getCbStatus() {
        return cbStatus;
    }

    public String getSelectedStatus() {
        if (cbStatus != null) {
            Object item = cbStatus.getSelectedItem();
            if (item != null) {
                String val = item.toString().trim();
                if (!val.isEmpty() && !"Semua Status".equalsIgnoreCase(val)) {
                    return val;
                }
            }
        }
        return "";
    }

    public void setSelectedStatus(String status) {
        if (cbStatus != null && status != null) {
            String trimmed = status.trim();
            for (int i = 0; i < cbStatus.getItemCount(); i++) {
                if (cbStatus.getItemAt(i).equalsIgnoreCase(trimmed)) {
                    cbStatus.setSelectedIndex(i);
                    return;
                }
            }
        }
    }

    public int getFilteredTotalData() {
        return filteredTotalData;
    }

    public int getTotalData() {
        return filteredTotalData;
    }

    public int getFilteredTotalHadir() {
        return filteredTotalHadir;
    }

    public int getFilteredHadir() {
        return filteredTotalHadir;
    }

    public int getFilteredTotalTidakHadir() {
        return filteredTotalTidakHadir;
    }

    public int getFilteredTidakHadir() {
        return filteredTotalTidakHadir;
    }

    public JLabel getLblFilteredTotalData() {
        return lblFilteredTotalData;
    }

    public JLabel getLblTotalData() {
        return lblFilteredTotalData;
    }

    public JLabel getLblFilteredHadir() {
        return lblFilteredHadir;
    }

    public JLabel getLblFilteredTidakHadir() {
        return lblFilteredTidakHadir;
    }

    public JPanel getPnlFilteredTotals() {
        return pnlFilteredTotals;
    }

    public JButton getBtnCari() {
        return btnCari;
    }

    public JButton getBtnResetCari() {
        return btnResetCari;
    }

    public void setKeywordCari(String keyword) {
        if (txtCari != null) {
            txtCari.setText(keyword != null ? keyword : "");
        }
    }

    /**
     * Retrieves the current search keyword from txtCari.
     * Trims leading and trailing whitespace.
     * Returns empty string if empty or set to placeholder.
     *
     * @return trimmed search keyword
     */
    public String getSearchKeyword() {
        if (txtCari != null) {
            String text = txtCari.getText();
            if (text != null) {
                String trimmed = text.trim();
                if (!trimmed.isEmpty() && !trimmed.equalsIgnoreCase("Search")) {
                    return trimmed;
                }
            }
        }
        return "";
    }

    public JButton getBtnExportCsv() {
        return btnExportCsv;
    }

    /**
     * Interactive handler for the Export CSV button.
     * Checks if attendance data exists for the selected period, prompts user with JFileChooser,
     * confirms file overwrite if needed, and writes attendance records.
     */
    public void exportAttendanceCsv() {
        int bulan = getSelectedBulan();
        int tahun = getSelectedTahun();
        String keyword = getSearchKeyword();
        String status = getSelectedStatus();

        // Check if there are any records for the selected month, year, teacher name, and status first
        try {
            Connection conn = KoneksiDatabase.getKoneksi();
            if (conn != null) {
                StringBuilder checkSql = new StringBuilder("SELECT COUNT(*) FROM tb_kehadiran_guru WHERE YEAR(waktu) = ? AND MONTH(waktu) = ?");
                List<Object> checkParams = new ArrayList<>();
                checkParams.add(tahun);
                checkParams.add(bulan);

                if (!keyword.isEmpty()) {
                    checkSql.append(" AND nama LIKE ?");
                    checkParams.add("%" + keyword + "%");
                }
                if (!status.isEmpty()) {
                    checkSql.append(" AND kehadiran = ?");
                    checkParams.add(status);
                }

                try (PreparedStatement ps = conn.prepareStatement(checkSql.toString())) {
                    for (int i = 0; i < checkParams.size(); i++) {
                        ps.setObject(i + 1, checkParams.get(i));
                    }
                    try (ResultSet rs = ps.executeQuery()) {
                        if (rs.next() && rs.getInt(1) == 0) {
                            String namaBulan = (bulan >= 1 && bulan <= 12) ? NAMA_BULAN[bulan - 1] : String.valueOf(bulan);
                            StringBuilder infoMsg = new StringBuilder("Tidak ada data kehadiran");
                            if (!status.isEmpty()) {
                                infoMsg.append(" dengan status \"").append(status).append("\"");
                            }
                            if (!keyword.isEmpty()) {
                                infoMsg.append(" untuk guru \"").append(keyword).append("\"");
                            }
                            infoMsg.append(" pada periode ").append(namaBulan).append(" ").append(tahun).append(".");

                            JOptionPane.showMessageDialog(
                                this,
                                infoMsg.toString(),
                                "Informasi Ekspor",
                                JOptionPane.INFORMATION_MESSAGE
                            );
                            return;
                        }
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Error checking attendance records before export: " + e.getMessage());
            JOptionPane.showMessageDialog(this, "Gagal memeriksa data kehadiran dari database.", "Database Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Configure file chooser with default filename
        StringBuilder fileNameBuilder = new StringBuilder("teacher_attendance");
        if (!status.isEmpty()) {
            fileNameBuilder.append("_").append(status.toLowerCase().replaceAll("[^a-z0-9_-]", "_"));
        }
        if (!keyword.isEmpty()) {
            fileNameBuilder.append("_").append(keyword.replaceAll("[^a-zA-Z0-9_-]", "_"));
        }
        fileNameBuilder.append(String.format("_%04d_%02d.csv", tahun, bulan));
        String defaultFileName = fileNameBuilder.toString();

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Simpan File CSV Kehadiran Guru");
        fileChooser.setSelectedFile(new File(defaultFileName));
        fileChooser.setFileFilter(new FileNameExtensionFilter("CSV Files (*.csv)", "csv"));

        int userSelection = fileChooser.showSaveDialog(this);
        if (userSelection != JFileChooser.APPROVE_OPTION) {
            return;
        }

        File selectedFile = fileChooser.getSelectedFile();
        if (selectedFile == null) {
            return;
        }

        // Ensure .csv extension
        if (!selectedFile.getName().toLowerCase().endsWith(".csv")) {
            selectedFile = new File(selectedFile.getParentFile(), selectedFile.getName() + ".csv");
        }

        // Confirm overwrite if file already exists
        if (selectedFile.exists()) {
            int confirm = JOptionPane.showConfirmDialog(
                this,
                "File \"" + selectedFile.getName() + "\" sudah ada. Apakah Anda ingin menimpanya?",
                "Konfirmasi Timpa File",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
            );
            if (confirm != JOptionPane.YES_OPTION) {
                return;
            }
        }

        // Perform CSV file export
        try {
            int count = exportAttendanceToCsv(selectedFile, bulan, tahun, keyword, status);
            JOptionPane.showMessageDialog(
                this,
                "Data kehadiran guru (" + count + " data) berhasil diekspor ke:\n" + selectedFile.getAbsolutePath(),
                "Ekspor Berhasil",
                JOptionPane.INFORMATION_MESSAGE
            );
        } catch (IOException e) {
            System.err.println("File write error exporting CSV: " + e.getMessage());
            JOptionPane.showMessageDialog(this, "Gagal menulis file CSV:\n" + e.getMessage(), "Error Ekspor", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            System.err.println("Database error exporting CSV: " + e.getMessage());
            JOptionPane.showMessageDialog(this, "Gagal mengambil data kehadiran dari database.", "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Programmatically exports teacher attendance records for the specified month and year
     * to the given destination CSV file.
     * Compatible with Microsoft Excel (UTF-8 BOM, standard CRLF, RFC 4180 quoting).
     *
     * @param targetFile destination CSV file
     * @param bulan month (1-12)
     * @param tahun year (e.g. 2026)
     * @return number of records exported
     * @throws IOException on file write error
     * @throws SQLException on database query error
     */
    public int exportAttendanceToCsv(File targetFile, int bulan, int tahun) throws IOException, SQLException {
        return exportAttendanceToCsv(targetFile, bulan, tahun, null, null);
    }

    /**
     * Programmatically exports teacher attendance records for the specified month, year, and optional teacher name
     * to the given destination CSV file.
     * Compatible with Microsoft Excel (UTF-8 BOM, standard CRLF, RFC 4180 quoting).
     *
     * @param targetFile destination CSV file
     * @param bulan month (1-12)
     * @param tahun year (e.g. 2026)
     * @param teacherName optional teacher name filter (empty/null for all)
     * @return number of records exported
     * @throws IOException on file write error
     * @throws SQLException on database query error
     */
    public int exportAttendanceToCsv(File targetFile, int bulan, int tahun, String teacherName) throws IOException, SQLException {
        return exportAttendanceToCsv(targetFile, bulan, tahun, teacherName, null);
    }

    /**
     * Programmatically exports teacher attendance records for the specified month, year, optional teacher name,
     * and optional attendance status to the given destination CSV file.
     * Compatible with Microsoft Excel (UTF-8 BOM, standard CRLF, RFC 4180 quoting).
     *
     * @param targetFile destination CSV file
     * @param bulan month (1-12)
     * @param tahun year (e.g. 2026)
     * @param teacherName optional teacher name filter (empty/null for all)
     * @param status optional attendance status filter (empty/null/Semua Status for all)
     * @return number of records exported
     * @throws IOException on file write error
     * @throws SQLException on database query error
     */
    public int exportAttendanceToCsv(File targetFile, int bulan, int tahun, String teacherName, String status) throws IOException, SQLException {
        Connection conn = KoneksiDatabase.getKoneksi();
        if (conn == null) {
            throw new SQLException("Database connection is not available.");
        }

        String keyword = (teacherName != null) ? teacherName.trim() : "";
        String st = (status != null) ? status.trim() : "";
        if ("Semua Status".equalsIgnoreCase(st)) {
            st = "";
        }

        StringBuilder sql = new StringBuilder("SELECT waktu, nama, kehadiran FROM tb_kehadiran_guru WHERE YEAR(waktu) = ? AND MONTH(waktu) = ?");
        List<Object> params = new ArrayList<>();
        params.add(tahun);
        params.add(bulan);

        if (!keyword.isEmpty()) {
            sql.append(" AND nama LIKE ?");
            params.add("%" + keyword + "%");
        }
        if (!st.isEmpty()) {
            sql.append(" AND kehadiran = ?");
            params.add(st);
        }
        sql.append(" ORDER BY waktu ASC, nama ASC");

        int count = 0;
        try (PreparedStatement ps = conn.prepareStatement(sql.toString())) {
            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }

            try (ResultSet rs = ps.executeQuery();
                 FileOutputStream fos = new FileOutputStream(targetFile);
                 OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
                 BufferedWriter writer = new BufferedWriter(osw)) {

                // Write UTF-8 Byte Order Mark (BOM) for seamless Microsoft Excel compatibility
                writer.write("\uFEFF");

                // Clear and descriptive CSV column headers
                writer.write("No,Tanggal,Nama Guru,Status Kehadiran\r\n");

                while (rs.next()) {
                    count++;
                    java.sql.Date d = rs.getDate("waktu");
                    String tanggal = (d != null) ? d.toString() : (rs.getString("waktu") != null ? rs.getString("waktu").trim() : "");
                    String nama = rs.getString("nama") != null ? rs.getString("nama").trim() : "";
                    String kehadiran = rs.getString("kehadiran") != null ? rs.getString("kehadiran").trim() : "";

                    writer.write(String.valueOf(count));
                    writer.write(",");
                    writer.write(escapeCsvField(tanggal));
                    writer.write(",");
                    writer.write(escapeCsvField(nama));
                    writer.write(",");
                    writer.write(escapeCsvField(kehadiran));
                    writer.write("\r\n");
                }
                writer.flush();
            }
        }
        return count;
    }

    /**
     * Escapes CSV values following RFC 4180 standards.
     * Values containing commas, double quotes, or newlines are enclosed in double quotes,
     * with internal double quotes escaped by doubling them ("").
     *
     * @param field input text
     * @return RFC 4180 escaped string
     */
    public static String escapeCsvField(String field) {
        if (field == null) {
            return "";
        }
        boolean needQuotes = field.contains(",") || field.contains("\"") ||
                             field.contains("\n") || field.contains("\r");
        if (needQuotes) {
            return "\"" + field.replace("\"", "\"\"") + "\"";
        }
        return field;
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
            if (conn != null) {
                String sql = "DELETE FROM tb_kehadiran_guru WHERE id_kehadiran = ?";
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setInt(1, idKehadiran);
                    int result = stmt.executeUpdate();
                    if (result > 0) {
                        JOptionPane.showMessageDialog(this, "Data berhasil dihapus.", "Sukses", JOptionPane.INFORMATION_MESSAGE);
                        refreshTable();
                    } else {
                        JOptionPane.showMessageDialog(this, "Data gagal dihapus.", "Peringatan", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Error deleting attendance record: " + e.getMessage());
            JOptionPane.showMessageDialog(this, "Terjadi kesalahan saat menghapus data:\n" + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
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
