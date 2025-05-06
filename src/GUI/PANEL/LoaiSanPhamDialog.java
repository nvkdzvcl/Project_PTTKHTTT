package GUI.PANEL;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class LoaiSanPhamDialog extends JDialog {
    private JTextField txtTenLoai;
    private JTable    tblLoai;
    private DefaultTableModel model;
    private JButton   btnThem, btnXoa, btnSua;

    public LoaiSanPhamDialog(Frame parent) {
        super(parent, "Quản lý loại sản phẩm", true);
        initComponents();
        pack();
        setLocationRelativeTo(parent);
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        // 1) HEADER
        JPanel header = new JPanel();
        header.setBackground(new Color(41, 128, 185));
        JLabel lblTitle = new JLabel("LOẠI SẢN PHẨM");
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        header.add(lblTitle);
        add(header, BorderLayout.NORTH);

        // 2) FORM + TABLE
        // 2.1 form nhập
        JPanel form = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        form.setBorder(new EmptyBorder(5,10,5,10));
        form.add(new JLabel("Tên loại sản phẩm"));
        txtTenLoai = new JTextField(20);
        form.add(txtTenLoai);

        // placeholder
        String pho = "Nhập tên loại sản phẩm...";
        txtTenLoai.setText(pho);
        txtTenLoai.setForeground(Color.GRAY);
        txtTenLoai.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent e) {
                if (txtTenLoai.getText().equals(pho)) {
                    txtTenLoai.setText("");
                    txtTenLoai.setForeground(Color.BLACK);
                }
            }
            public void focusLost(java.awt.event.FocusEvent e) {
                if (txtTenLoai.getText().isEmpty()) {
                    txtTenLoai.setText(pho);
                    txtTenLoai.setForeground(Color.GRAY);
                }
            }
        });

        // 2.2 bảng loại sản phẩm
        String[] cols = {"Mã loại", "Tên loại"};
        model = new DefaultTableModel(cols, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        tblLoai = new JTable(model);
        tblLoai.setRowHeight(25);
        tblLoai.getTableHeader().setReorderingAllowed(false);

        // sample data
        Object[][] sample = {
                {1, "Áo thun"},
                {2, "Áo sơ mi"},
                {3, "Áo khoác"},
                {4, "Quần jean"},
                {5, "Quần short"},
                {6, "Chân váy"},
                {7, "Đầm"},
                {8, "Áo hoodie"},
                {9, "Áo len"},
                {10,"Áo vest"},
                {11,"Quần tây"},
                {12,"Áo sơ mi nữ"}
        };
        for (Object[] row : sample) {
            model.addRow(row);
        }

        JScrollPane scroll = new JScrollPane(tblLoai);
        scroll.setBorder(BorderFactory.createEmptyBorder());

        // gộp form + bảng vào center
        JPanel center = new JPanel();
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
        center.add(form);
        center.add(scroll);
        add(center, BorderLayout.CENTER);

        // 3) FOOTER – các nút Thêm / Xóa / Sửa
        JPanel footer = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        btnThem = makeButton("Thêm", new Color(46, 204, 113));
        btnXoa  = makeButton("Xóa",  new Color(231,  76,  60));
        btnSua  = makeButton("Sửa",  new Color(52, 152, 219));
        footer.add(btnThem);
        footer.add(btnXoa);
        footer.add(btnSua);
        add(footer, BorderLayout.SOUTH);
    }

    // helper tạo button màu & chữ trắng
    private JButton makeButton(String text, Color bg) {
        JButton b = new JButton(text);
        b.setBackground(bg);
        b.setForeground(Color.WHITE);
        b.setFocusPainted(false);
        b.setPreferredSize(new Dimension(100, 40));
        return b;
    }

    // chỉ test dialog riêng
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoaiSanPhamDialog dlg = new LoaiSanPhamDialog(null);
            dlg.setVisible(true);
            System.exit(0);
        });
    }
}
