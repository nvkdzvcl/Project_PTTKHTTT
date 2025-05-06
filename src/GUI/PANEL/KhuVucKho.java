package GUI.PANEL;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class KhuVucKho extends JPanel {
    private JTable tblKhuVuc;
    private DefaultTableModel modelKhuVuc;

    private JTable tblSanPham;
    private DefaultTableModel modelSanPham;

    // Map từ Mã khu vực → dữ liệu sample {Tên SP, Số lượng}
    private final Map<Integer, Object[][]> productsInArea = new HashMap<>();

    public KhuVucKho() {
        initSampleData();
        setLayout(new BorderLayout(10, 10));
        initTopPanel();
        initCenterPanel();
    }

    private void initSampleData() {
        productsInArea.put(1, new Object[][]{
                {"Áo thun cổ tròn", 70},
                {"Quần jean nam",     70},
                {"Áo khoác gió",      50},
                {"Váy nữ dài",        78},
                {"Áo sơ mi trắng",    46}
        });
        productsInArea.put(2, new Object[][]{
                {"Áo hoodie",        55},
                {"Quần short nữ",    80},
                {"Chân váy xòe",     30},
                {"Đầm maxi",         45},
                {"Áo len cổ lọ",     20}
        });
        productsInArea.put(3, new Object[][]{
                {"Áo khoác da",      10},
                {"Quần jogger",      65},
                {"Áo crop top",      25},
                {"Đầm bodycon",      15},
                {"Áo sơ mi caro",    40}
        });
        productsInArea.put(4, new Object[][]{
                {"Jumpsuit",         18},
                {"Áo vest",          12},
                {"Quần tây",         28},
                {"Quần legging",     50},
                {"Áo blazer",        22}
        });
        productsInArea.put(5, new Object[][]{
                {"Áo polo",          52},
                {"Áo bomber",        22},
                {"Quần khaki",       60},
                {"Áo peplum",        38},
                {"Váy xếp ly",       33}
        });
    }

    private void initTopPanel() {
        JPanel top = new JPanel(new BorderLayout());
        // Toolbar bên trái
        JPanel leftTb = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 5));
        String[] icons = {"/icon/them.png","/icon/sua.png","/icon/xoa.png"};
        String[] texts = {"Thêm","Sửa","Xóa"};
        for (int i = 0; i < icons.length; i++) {
            JButton btn = new JButton(texts[i], loadIcon(icons[i]));
            btn.setVerticalTextPosition(SwingConstants.BOTTOM);
            btn.setHorizontalTextPosition(SwingConstants.CENTER);
            leftTb.add(btn);
        }
        top.add(leftTb, BorderLayout.WEST);

        // Filter + tìm kiếm + làm mới bên phải
        JPanel rightFilter = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 5));
        JComboBox<String> cb = new JComboBox<>(new String[]{"Tất cả","Khu vực A","Khu vực B","Khu vực C","Khu vực D","Khu vực E"});
        JTextField txt = new JTextField("Nhập nội dung tìm kiếm...");
        txt.setPreferredSize(new Dimension(200, 30));
        setPlaceholder(txt, "Nhập nội dung tìm kiếm...");
        JButton btnRefresh = new JButton(loadIcon("/icon/refresh.png"));
        rightFilter.add(cb);
        rightFilter.add(txt);
        rightFilter.add(btnRefresh);
        top.add(rightFilter, BorderLayout.EAST);

        add(top, BorderLayout.NORTH);
    }

    private void initCenterPanel() {
        // Bảng khu vực
        modelKhuVuc = new DefaultTableModel(new Object[]{"Mã kho","Tên khu vực","Địa chỉ"}, 0) {
            public boolean isCellEditable(int r, int c) { return false; }
        };
        tblKhuVuc = new JTable(modelKhuVuc);
        tblKhuVuc.setRowHeight(25);
        tblKhuVuc.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblKhuVuc.getColumnModel().getColumn(2).setPreferredWidth(500);
        JScrollPane spKhu = new JScrollPane(tblKhuVuc);
        // dữ liệu sample khu vực
        Object[][] areas = {{1,"Khu vực A","căn hộ C3.2, chung cư LoveraVista Khang Điền, số 2, đường số 19, xã Phong Phú, huyện Bình Chánh, thành phố Hồ Chí Minh"}
                ,{2,"Khu vực B","số 101 Nguyễn Trãi, phường 2, quận 5, thành phố Hồ Chí Minh"}
                ,{3,"Khu vực C","số 55 Trần Hưng Đạo, phường Nguyễn Cư Trinh, quận 1, thành phố Hồ Chí Minh"}
                ,{4,"Khu vực D","số 7 Phan Văn Trị, phường 12, quận Gò Vấp, thành phố Hồ Chí Minh"}
                ,{5,"Khu vực E", "số 88 Phạm Thế Hiện, phường 4, quận 8, thành phố Hồ Chí Minh"}
        };
        for (Object[] r : areas) modelKhuVuc.addRow(r);

        // Bảng sản phẩm
        modelSanPham = new DefaultTableModel(new Object[]{"Tên sản phẩm","Số lượng"}, 0) {
            public boolean isCellEditable(int r, int c) { return false; }
        };
        tblSanPham = new JTable(modelSanPham);
        tblSanPham.setRowHeight(25);
        JScrollPane spSp = new JScrollPane(tblSanPham);
        JPanel rightPn = new JPanel(new BorderLayout());
        rightPn.setBorder(BorderFactory.createTitledBorder("Danh sách sản phẩm đang có ở khu vực"));
        rightPn.add(spSp, BorderLayout.CENTER);

        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, spKhu, rightPn);
        split.setDividerLocation(500);
        add(split, BorderLayout.CENTER);

        // Sự kiện chọn khu vực
        tblKhuVuc.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting() && tblKhuVuc.getSelectedRow() != -1) {
                    int id = (int) modelKhuVuc.getValueAt(tblKhuVuc.getSelectedRow(), 0);
                    loadProducts(id);
                }
            }
        });
        // Chọn mặc định row đầu
        if (modelKhuVuc.getRowCount() > 0) {
            tblKhuVuc.setRowSelectionInterval(0, 0);
            loadProducts((int) modelKhuVuc.getValueAt(0, 0));
        }
    }

    private void loadProducts(int areaId) {
        modelSanPham.setRowCount(0);
        Object[][] data = productsInArea.get(areaId);
        if (data != null) for (Object[] r : data) modelSanPham.addRow(r);
    }

    private ImageIcon loadIcon(String path) {
        java.net.URL url = getClass().getResource(path);
        return url != null ? new ImageIcon(new ImageIcon(url).getImage()
                .getScaledInstance(32, 32, Image.SCALE_SMOOTH))
                : new ImageIcon();
    }

    private void setPlaceholder(JTextField txt, String text) {
        txt.setForeground(Color.GRAY);
        txt.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent e) {
                if (txt.getText().equals(text)) {
                    txt.setText("\"");
                    txt.setForeground(Color.BLACK);
                }
            }
            public void focusLost(java.awt.event.FocusEvent e) {
                if (txt.getText().isEmpty()) {
                    txt.setText(text);
                    txt.setForeground(Color.GRAY);
                }
            }
        });
    }
}