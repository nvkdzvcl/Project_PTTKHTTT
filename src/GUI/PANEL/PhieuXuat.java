package GUI.PANEL;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class PhieuXuat extends JPanel {
    public PhieuXuat() {
        setLayout(new BorderLayout(10, 10));

        // --------- PHẦN NÚT CHỨC NĂNG (TOP) -----------
        JPanel topBar = new JPanel(new BorderLayout());
        JPanel toolPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 5));
        String[] btnTexts = {"Thêm", "Chi tiết", "Hủy phiếu"};
        String[] btnIcons = {"/icon/them.png", "/icon/chitiet.png", "/icon/huyphieu.png"};
        for (int i = 0; i < btnTexts.length; i++) {
            JButton btn = createIconButton(btnTexts[i], resizeIcon(btnIcons[i]));
            btn.setOpaque(false);
            btn.setFocusPainted(false);
            btn.setBorderPainted(false);
            // Xử lý sự kiện cho nút Thêm
            if ("Thêm".equals(btnTexts[i])) {
                btn.addActionListener(e -> {
                    JDialog dlg = new JDialog(
                            (Frame) SwingUtilities.getWindowAncestor(this),
                            "Thêm Phiếu Xuất", true
                    );
                    dlg.setContentPane(new ThemPhieuNhap());
                    dlg.pack();
                    dlg.setLocationRelativeTo(this);
                    dlg.setVisible(true);
                });
            }
            toolPanel.add(btn);
        }
        topBar.add(toolPanel, BorderLayout.WEST);

        JPanel quickFilter = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 5));
        JComboBox<String> cbFilter = new JComboBox<>(new String[]{"Tất Cả", "Mã phiếu nhập", "Khách hàng", "Nhân viên xuất"});
        cbFilter.setPreferredSize(new Dimension(140, 30));
        JTextField tfSearch = new JTextField("Nhập nội dung tìm kiếm...");
        tfSearch.setPreferredSize(new Dimension(180, 30));
        addPlaceholder(tfSearch, "Nhập nội dung tìm kiếm...");
        JButton btnRefresh = createIconButton("", resizeIcon("/icon/lammoi.png"));
        quickFilter.add(cbFilter);
        quickFilter.add(tfSearch);
        quickFilter.add(btnRefresh);
        topBar.add(quickFilter, BorderLayout.EAST);

        add(topBar, BorderLayout.NORTH);

        // --------- PHẦN GIAO DIỆN CHÍNH -----------
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        mainPanel.add(createLeftFilterPanel(), BorderLayout.WEST);

        String[] columns = {"Mã phiếu xuất", "Khách hàng", "Nhân viên xuất", "Thời gian", "Tổng tiền"};
        DefaultTableModel model = new DefaultTableModel(columns, 0) {
            @Override public boolean isCellEditable(int row, int col) { return false; }
        };
        JTable table = new JTable(model);
        table.setRowHeight(24);
        JScrollPane scrollPane = new JScrollPane(table);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        add(mainPanel, BorderLayout.CENTER);

        // Thêm 25 dòng sample data, thời gian tăng dần từ 01/06/2024 với bước 15 ngày
        String[] providers = {
                "Nguyễn Văn A",    "Trần Thị B",      "Lê Văn C",        "Phạm Thị D",      "Hoàng Văn E",
                "Vũ Thị F",        "Đặng Văn G",      "Bùi Thị H",       "Trương Văn I",    "Phan Thị K",
                "Lý Văn L",        "Đỗ Thị M",        "Ngô Văn N",       "Dương Thị O",     "Võ Văn P",
                "Lê Thị Q",        "Nguyễn Văn R",    "Trần Thị S",      "Phạm Văn T",      "Hoàng Thị U",
                "Vũ Văn V",        "Đặng Thị W",      "Bùi Văn X",       "Trương Thị Y",    "Phan Văn Z"
        };

        String[] staffers  = {"Nguyễn Văn A", "Trần Thị B", "Lê Thị C", "Phạm Văn D", "Hoàng Hạ E"};
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        GregorianCalendar startCal = new GregorianCalendar(2024, Calendar.JUNE, 1, 9, 30);
        for (int i = 0; i < 25; i++) {
            String code  = String.format("PX%03d", i+1);
            String prov  = providers[i % providers.length];
            String staff = staffers[i % staffers.length];

            Calendar c = (Calendar) startCal.clone();
            c.add(Calendar.DAY_OF_MONTH, i * 15);
            String dateStr = sdf.format(c.getTime());

            int amount = 2_000_000 + ((i * 3) % 90) * 1_000_000; // 20–110 triệu
            String totalStr = String.format("%,d", amount);

            model.addRow(new Object[]{code, prov, staff, dateStr, totalStr});
        }
    }

    private JPanel createLeftFilterPanel() {
        JPanel left = new JPanel(new GridBagLayout());
        left.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Bộ lọc tìm kiếm"),
                new EmptyBorder(5, 5, 5, 5)
        ));
        left.setPreferredSize(new Dimension(240, 0));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill    = GridBagConstraints.HORIZONTAL;
        gbc.insets  = new Insets(4, 4, 4, 4);
        gbc.anchor  = GridBagConstraints.WEST;
        gbc.weightx = 0;

        int y = 0;
        gbc.gridx = 0; gbc.gridy = y++;
        left.add(new JLabel("Nhà cung cấp:"), gbc);
        gbc.gridy = y++; gbc.weightx = 1.0;
        left.add(new JComboBox<>(new String[]{"Tất cả", "Công Ty A", "Công Ty B", "Công Ty C"}), gbc);
        gbc.weightx = 0;

        gbc.gridy = y++;
        left.add(new JLabel("Nhân viên xuất:"), gbc);
        gbc.gridy = y++; gbc.weightx = 1.0;
        left.add(new JComboBox<>(new String[]{"Tất cả", "Nguyễn Văn A", "Trần Thị B", "Lê Thị C"}), gbc);
        gbc.weightx = 0;

        gbc.gridy = y++;
        left.add(new JLabel("Từ ngày:"), gbc);
        gbc.gridy = y++; gbc.weightx = 1.0;
        JSpinner spFrom = new JSpinner(new SpinnerDateModel(new Date(), null, null, Calendar.MINUTE));
        spFrom.setEditor(new JSpinner.DateEditor(spFrom, "dd/MM/yyyy HH:mm"));
        left.add(spFrom, gbc);
        gbc.weightx = 0;

        gbc.gridy = y++;
        left.add(new JLabel("Đến ngày:"), gbc);
        gbc.gridy = y++; gbc.weightx = 1.0;
        JSpinner spTo = new JSpinner(new SpinnerDateModel(new Date(), null, null, Calendar.MINUTE));
        spTo.setEditor(new JSpinner.DateEditor(spTo, "dd/MM/yyyy HH:mm"));
        left.add(spTo, gbc);
        gbc.weightx = 0;

        gbc.gridy = y++; gbc.gridwidth = 1; gbc.weightx = 1.0;
        JButton btnApply = new JButton("Áp dụng");
        left.add(btnApply, gbc);

        // Nút "Xóa bộ lọc"
        gbc.gridy = y++;
        JButton btnClear = new JButton("Xóa bộ lọc");
        left.add(btnClear, gbc);

        return left;
    }

    private ImageIcon resizeIcon(String path) {
        URL url = getClass().getResource(path);
        if (url != null) {
            ImageIcon icon = new ImageIcon(url);
            Image img = icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
            return new ImageIcon(img);
        }
        return null;
    }

    private JButton createIconButton(String text, ImageIcon icon) {
        JButton btn = new JButton(text, icon);
        btn.setVerticalTextPosition(SwingConstants.BOTTOM);
        btn.setHorizontalTextPosition(SwingConstants.CENTER);
        btn.setPreferredSize(new Dimension(90, 60));
        return btn;
    }

    private void addPlaceholder(JTextField tf, String ph) {
        tf.setForeground(Color.GRAY);
        tf.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent e) {
                if (tf.getText().equals(ph)) {
                    tf.setText("");
                    tf.setForeground(Color.BLACK);
                }
            }
            public void focusLost(java.awt.event.FocusEvent e) {
                if (tf.getText().isEmpty()) {
                    tf.setText(ph);
                    tf.setForeground(Color.GRAY);
                }
            }
        });
    }
}
