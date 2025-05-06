package GUI.PANEL;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class PhieuKiemKe extends JPanel {
    private JTable table;
    private DefaultTableModel model;

    public PhieuKiemKe() {
        setLayout(new BorderLayout(10, 10));
        setBackground(Color.WHITE);

        // ---------- Thanh công cụ ----------
        JPanel topBar = new JPanel(new BorderLayout());

// === Left Toolbar ===
        JPanel leftToolbar = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 5));
        JButton btnthem     = createIconButton("Thêm", resizeIcon(new ImageIcon(getClass().getResource("/icon/them.png"))));
        JButton btnchitiet  = createIconButton("Chi tiết", resizeIcon(new ImageIcon(getClass().getResource("/icon/chitiet.png"))));
        JButton btnxoa      = createIconButton("Xóa", resizeIcon(new ImageIcon(getClass().getResource("/icon/xoa.png"))));

        for (JButton btn : new JButton[]{btnthem, btnchitiet, btnxoa}) {
            btn.setOpaque(false);
            btn.setFocusPainted(false);
            btn.setBorderPainted(false);
        }

        leftToolbar.add(btnthem);
        leftToolbar.add(btnchitiet);
        leftToolbar.add(btnxoa);
        topBar.add(leftToolbar, BorderLayout.WEST);

// === Right Filter Bar ===
        JPanel rightFilter = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 5));
        JComboBox<String> cbFilter = new JComboBox<>(new String[]{"Tất cả", "Mã phiếu", "Nhân viên"});
        cbFilter.setPreferredSize(new Dimension(120, 30));

        JTextField txtSearch = new JTextField("Tìm kiếm...");
        txtSearch.setForeground(Color.GRAY);
        txtSearch.setPreferredSize(new Dimension(160, 30));

        txtSearch.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txtSearch.getText().equals("Tìm kiếm...")) {
                    txtSearch.setText("");
                    txtSearch.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (txtSearch.getText().isEmpty()) {
                    txtSearch.setText("Tìm kiếm...");
                    txtSearch.setForeground(Color.GRAY);
                }
            }
        });

        JButton btnRefresh = createIconButton("", resizeIcon(new ImageIcon(getClass().getResource("/icon/lammoi.png"))));
        btnRefresh.setToolTipText("Làm mới");

        rightFilter.add(cbFilter);
        rightFilter.add(txtSearch);
        rightFilter.add(btnRefresh);
        topBar.add(rightFilter, BorderLayout.EAST);

// Add topBar to main panel
        add(topBar, BorderLayout.NORTH);




        // ---------- Bảng dữ liệu ----------
        model = new DefaultTableModel(new String[]{"Mã phiếu kiểm kê", "Nhân viên kiểm kê", "Thời gian"}, 0) {
            public boolean isCellEditable(int row, int col) { return false; }
        };
        table = new JTable(model);
        table.setRowHeight(25);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // ---------- Dữ liệu giả ----------
        generateFakeData();

        // ---------- Sự kiện ----------
        btnchitiet.addActionListener(e -> showDetailDialog());
        btnxoa.addActionListener(e -> deleteSelectedRow());
        btnthem.addActionListener(e -> JOptionPane.showMessageDialog(this, "Chức năng thêm chưa được triển khai"));
    }

    private void generateFakeData() {
        String[] staffNames = {"Nguyễn Văn A", "Trần Thị B", "Lê Văn C", "Phạm Thị D"};
        Calendar cal = Calendar.getInstance();
        cal.set(2021, Calendar.JANUARY, 28);
        int id = 1;
        while (cal.get(Calendar.YEAR) < 2025 || (cal.get(Calendar.YEAR) == 2025 && cal.get(Calendar.MONTH) < Calendar.APRIL)) {
            String maPhieu = String.format("KK%03d", id++);
            String nv = staffNames[new Random().nextInt(staffNames.length)];
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            model.addRow(new Object[]{maPhieu, nv, sdf.format(cal.getTime())});
            cal.add(Calendar.MONTH, 1);
        }
    }

    private void deleteSelectedRow() {
        int r = table.getSelectedRow();
        if (r == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn 1 dòng để xóa!");
            return;
        }
        if (JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa phiếu này?", "Xác nhận", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            model.removeRow(r);
        }
    }

    private void showDetailDialog() {
        int row = table.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một phiếu để xem chi tiết.");
            return;
        }

        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Chi tiết phiếu kiểm kê", true);
        dialog.setSize(600, 400);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(new BorderLayout(10, 10));

        // Fake product detail data
        String[] productNames = {"Áo thun", "Quần jean", "Áo khoác", "Giày sneaker", "Váy nữ", "Áo sơ mi"};
        String[] notes = {"OK", "Cần kiểm tra lại", "Thiếu tem", "Lỗi mã vạch", "Sai màu", "Bao bì rách"};

        DefaultTableModel detailModel = new DefaultTableModel(new String[]{"Tên sản phẩm", "SL tồn thực tế", "Chênh lệch", "Ghi chú"}, 0) {
            public boolean isCellEditable(int row, int col) { return false; }
        };

        Random rand = new Random();
        for (int i = 0; i < 5; i++) {
            String name = productNames[rand.nextInt(productNames.length)];
            int sl = 50 + rand.nextInt(50);
            int chenhlech = 2 + rand.nextInt(19);
            String note = notes[rand.nextInt(notes.length)];
            detailModel.addRow(new Object[]{name, sl, chenhlech, note});
        }

        JTable detailTable = new JTable(detailModel);
        detailTable.setRowHeight(25);
        JScrollPane sp = new JScrollPane(detailTable);
        dialog.add(sp, BorderLayout.CENTER);

        JButton closeBtn = new JButton("Đóng");
        closeBtn.addActionListener(e -> dialog.dispose());
        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottom.add(closeBtn);
        dialog.add(bottom, BorderLayout.SOUTH);

        dialog.setVisible(true);
    }

    private JButton createIconButton(String text, ImageIcon icon) {
        JButton btn = new JButton(text, icon);
        btn.setVerticalTextPosition(SwingConstants.BOTTOM);
        btn.setHorizontalTextPosition(SwingConstants.CENTER);
        btn.setPreferredSize(new Dimension(90, 60));
        return btn;
    }

    private ImageIcon resizeIcon(ImageIcon img) {
        Image i = img.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        return new ImageIcon(i);
    }
}
