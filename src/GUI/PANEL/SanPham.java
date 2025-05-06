package GUI.PANEL;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class SanPham extends JPanel {
    private JTable bangsp;
    private DefaultTableModel model;

    public SanPham() {
        setLayout(new BorderLayout(10, 10));

        // === PHẦN TOOLBAR + FILTER ===
        JPanel top = new JPanel(new BorderLayout());
        // toolbar bên trái
        JPanel leftToolbar = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 5));
        JButton btnthem  = createIconButton("Thêm", resizeimg(new ImageIcon(getClass().getResource("/icon/them.png"))));
        JButton btnsua   = createIconButton("Sửa",  resizeimg(new ImageIcon(getClass().getResource("/icon/sua.png"))));
        JButton btnchitiet   = createIconButton("Chi tiết",  resizeimg(new ImageIcon(getClass().getResource("/icon/chitiet.png"))));
        JButton btnxoa   = createIconButton("Xóa",  resizeimg(new ImageIcon(getClass().getResource("/icon/xoa.png"))));
        // bỏ viền / background nếu muốn
        for (JButton b : new JButton[]{btnthem, btnchitiet, btnsua, btnxoa}) {
            b.setOpaque(false);
            b.setFocusPainted(false);
            b.setBorderPainted(false);
        }
        leftToolbar.add(btnthem);
        leftToolbar.add(btnsua);
        leftToolbar.add(btnchitiet);
        leftToolbar.add(btnxoa);

        // filter + tìm kiếm bên phải
        JPanel rightFilter = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 5));
        JComboBox<String> cmbFilter = new JComboBox<>(new String[]{"Tất Cả", "Top"});
        cmbFilter.setPreferredSize(new Dimension(100, 40));
        // khởi tạo text field
        JTextField txtSearch = new JTextField();
        txtSearch.setPreferredSize(new Dimension(150, 40));

// placeholder text
        String placeholder = "Tìm kiếm…";
// set ban đầu
        txtSearch.setText(placeholder);
        txtSearch.setForeground(Color.GRAY);

// thêm FocusListener
        txtSearch.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txtSearch.getText().equals(placeholder)) {
                    txtSearch.setText("");
                    txtSearch.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (txtSearch.getText().isEmpty()) {
                    txtSearch.setText(placeholder);
                    txtSearch.setForeground(Color.GRAY);
                }
            }
        });

        txtSearch.setPreferredSize(new Dimension(150, 40));
        JButton btnRefresh = createIconButton("", resizeimg(new ImageIcon(getClass().getResource("/icon/lammoi.png"))));
        btnRefresh.setToolTipText("Làm mới");
        rightFilter.add(cmbFilter);
        rightFilter.add(txtSearch);
        rightFilter.add(btnRefresh);

        top.add(leftToolbar,   BorderLayout.WEST);
        top.add(rightFilter,   BorderLayout.EAST);
        add(top, BorderLayout.NORTH);

        // === PHẦN BẢNG DỮ LIỆU ===
        String[] columns = {
                "Mã SP","Tên SP","Thương Hiệu","Xuất xứ",
                "Size","Đơn giá",
                "Số lượng","Trạng thái"
        };
        // override isCellEditable để không cho người dùng sửa trực tiếp
        model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        bangsp = new JTable(model);
        bangsp.setRowHeight(25);
        bangsp.getTableHeader().setReorderingAllowed(false);

        // Ví dụ sample data, bạn thay bằng lấy từ DB
        Object[][] sampleData = {
                {  1, "Áo thun cổ tròn",  70, "Coolmate","Việt Nam", "S", 150_000, "Hoạt Động" },
                {  2, "Áo thun cổ tim",   50, "Coolmate","Việt Nam", "M", 160_000, "Hoạt Động" },
                {  3, "Áo sơ mi nam",     40, "Zara", "Tây Ban Nha", "L", 250_000, "Ngừng Bán" },
                {  4, "Áo khoác gió",     25, "Nike", "Mỹ", "XL", 300_000, "Hoạt Động" },
                {  5, "Quần jean nam",    60, "Levis", "Mỹ", "2XL", 400_000, "Hoạt Động" },
                {  6, "Quần short nữ",    80, "H&M", "Thụy Điển", "M", 200_000, "Hoạt Động" },
                {  7, "Đầm maxi",         30, "Zara", "Tây Ban Nha", "M", 500_000, "Hoạt Động" },
                {  8, "Váy chữ A",        45, "Mango", "Tây Ban Nha", "S", 350_000, "Hoạt Động" },
                {  9, "Áo len cổ lọ",     20, "Uniqlo", "Nhật Bản", "L", 280_000, "Ngừng Bán" },
                { 10, "Áo khoác da",      10, "Zara", "Tây Ban Nha", "L", 800_000, "Hoạt Động" },
                { 11, "Giày sneaker",     15, "Adidas", "Đức", "42", 600_000, "Hoạt Động" },
                { 12, "Giày boots nữ",    12, "Dr.Martens", "Anh", "38", 1_200_000, "Ngừng Bán" },
                { 13, "Áo hoodie",        55, "Nike", "Mỹ", "XL", 400_000, "Hoạt Động" },
                { 14, "Áo khoác bomber",  22, "Zara", "Tây Ban Nha", "M", 650_000, "Hoạt Động" },
                { 15, "Quần jogger",      65, "Adidas", "Đức", "L", 350_000, "Hoạt Động" },
                { 16, "Áo sơ mi nữ",      48, "H&M", "Thụy Điển", "S", 300_000, "Hoạt Động" },
                { 17, "Váy liền thân",    38, "Mango", "Tây Ban Nha", "M", 450_000, "Hoạt Động" },
                { 18, "Quần tây nam",     28, "Zara", "Tây Ban Nha", "2XL", 380_000, "Hoạt Động" },
                { 19, "Áo thun polo",     52, "Lacoste", "Pháp", "M", 700_000, "Hoạt Động" },
                { 20, "Áo khoác padded",  18, "The North", "Mỹ", "L", 1_500_000, "Hoạt Động" },
                { 21, "Áo thun polo",     52, "Lacoste", "Pháp", "M", 700_000, "Hoạt Động" },
                { 22, "Áo khoác padded",  18, "North Face", "Mỹ", "L", 1_500_000, "Hoạt Động" },
                { 23, "Áo thun polo",     52, "Lac", "Việt Nam", "M", 700_000, "Hoạt Động" },
                { 24, "Áo khoác padded",  18, "Catouis", "Việt Nam", "L", 1_500_000, "Hoạt Động" },
                { 25, "Áo khoác bomber",  22, "Zara", "Tây Ban Nha", "M", 650_000, "Hoạt Động" },
                { 26, "Áo khoác da",      10, "Zara", "Tây Ban Nha", "L", 800_000, "Hoạt Động" },
                { 27, "Áo thun polo",     52, "Lacoste", "Pháp", "M", 700_000, "Hoạt Động" },
                { 28, "Quần jean nam",    60, "Levis", "Mỹ", "2XL", 400_000, "Hoạt Động" },
                { 29, "Quần jogger",      65, "Adidas", "Đức", "L", 350_000, "Hoạt Động" },
                { 30, "Giày boots nữ",    12, "Dr.Martens", "Anh", "38", 1_200_000, "Ngừng Bán" },
                { 31, "Váy chữ A",        45, "Mango", "Tây Ban Nha", "S", 350_000, "Hoạt Động" },
                { 32, "Áo len cổ lọ",     20, "Uniqlo", "Nhật Bản", "L", 280_000, "Ngừng Bán" },
                { 33, "Áo thun cổ tim",   50, "Coolmate", "Việt Nam", "M", 160_000, "Hoạt Động" },
                { 34, "Áo sơ mi nam",     40, "Zara", "Tây Ban Nha", "L", 250_000, "Ngừng Bán" },

        };

        for (Object[] row : sampleData) {
            model.addRow(row);
        }

        JScrollPane scrollPane = new JScrollPane(bangsp);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        add(scrollPane, BorderLayout.CENTER);
        btnthem.addActionListener(e -> {
            Frame parent = (Frame) SwingUtilities.getWindowAncestor(this);
            Them_Xoa_SanPham dlgThemSanPham = new Them_Xoa_SanPham(parent, this);
            dlgThemSanPham.setVisible(true);
        });
        setVisible(true);
    }

    private JButton createIconButton(String text, ImageIcon icon) {
        JButton btn = new JButton(text, icon);
        btn.setVerticalTextPosition(SwingConstants.BOTTOM);
        btn.setHorizontalTextPosition(SwingConstants.CENTER);
        return btn;
    }

    private ImageIcon resizeimg(ImageIcon img) {
        Image i = img.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        return new ImageIcon(i);
    }

    // Test riêng panel
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame f = new JFrame("Panel Sản Phẩm");
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.setSize(1000, 600);
            f.setLocationRelativeTo(null);
            f.setContentPane(new SanPham());
            f.setVisible(true);
        });
    }
}
