package GUI.PANEL;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedHashSet;
import java.util.Set;

public class ThemPhieuNhap extends JPanel {
    private JTable tblSanPham, tblChiTiet;
    private DefaultTableModel modelSanPham, modelChiTiet;
    private JTextField txtTimKiem, txtMaSP, txtTenSP, txtGiaNhap, txtSoLuong, txtMaPN;
    private JComboBox<String> cbKichThuoc, cbThuongHieu, cbXuatXu, cbNhanVien, cbTrangThai;
    private JLabel lbTongTien;
    private JPanel colorPanel;
    private String selectedColorName;
    private static int nextPN = 1;

    public ThemPhieuNhap() {
        setLayout(null);
        setBackground(Color.WHITE);

        // Left: search & product table
        txtTimKiem = new JTextField();
        txtTimKiem.setBounds(10, 10, 345, 25);
        add(txtTimKiem);
        modelSanPham = new DefaultTableModel(new Object[]{"Mã SP","Tên SP","Số lượng"}, 0);
        tblSanPham = new JTable(modelSanPham);
        loadSampleProducts();
        JScrollPane sp1 = new JScrollPane(tblSanPham);
        sp1.setBounds(10, 45, 345, 300);
        add(sp1);
        tblSanPham.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && tblSanPham.getSelectedRow()>=0) {
                int r = tblSanPham.getSelectedRow();
                txtMaSP.setText(modelSanPham.getValueAt(r,0).toString());
                txtTenSP.setText(modelSanPham.getValueAt(r,1).toString());
            }
        });

        // Detail table
        modelChiTiet = new DefaultTableModel(
                new Object[]{"STT","Mã SP","Tên SP","Thương Hiệu","Xuất xứ","Màu sắc","Size","Đơn giá","SL"}, 0
        );
        tblChiTiet = new JTable(modelChiTiet);
        JScrollPane sp2 = new JScrollPane(tblChiTiet);
        sp2.setBounds(10, 420, 1070, 260);
        add(sp2);

        // Center form
        int x0=360;
        JLabel lb;
        lb = new JLabel("Mã SP"); lb.setBounds(x0,45,100,25); add(lb);
        txtMaSP = new JTextField(); txtMaSP.setBounds(x0+110,45,235,25); add(txtMaSP);
        lb = new JLabel("Tên SP"); lb.setBounds(x0,85,100,25); add(lb);
        txtTenSP = new JTextField(); txtTenSP.setBounds(x0+110,85,235,25); add(txtTenSP);
        lb = new JLabel("Màu sắc"); lb.setBounds(x0,125,100,25); add(lb);
        colorPanel = new JPanel(new GridLayout(2,7,5,5)); colorPanel.setBounds(x0+110,125,235,50);
        add(colorPanel);
        setupColorPalette();
        lb = new JLabel("Kích thước"); lb.setBounds(x0,185,100,25); add(lb);
        cbKichThuoc = new JComboBox<>(new String[]{"S","M","L","XL","2XL"}); cbKichThuoc.setBounds(x0+110,185,235,25); add(cbKichThuoc);
        lb = new JLabel("Thương hiệu"); lb.setBounds(x0,225,100,25); add(lb);
        cbThuongHieu = new JComboBox<>(new String[]{"Coolmate","Lining","Nike","Adidas"}); cbThuongHieu.setBounds(x0+110,225,235,25); add(cbThuongHieu);
        lb = new JLabel("Xuất xứ"); lb.setBounds(x0,265,100,25); add(lb);
        cbXuatXu = new JComboBox<>(new String[]{"Việt Nam","Trung Quốc","Hàn Quốc","Mỹ"}); cbXuatXu.setBounds(x0+110,265,235,25); add(cbXuatXu);
        lb = new JLabel("Giá nhập"); lb.setBounds(x0,305,100,25); add(lb);
        txtGiaNhap = new JTextField(); txtGiaNhap.setBounds(x0+110,305,120,25); add(txtGiaNhap);
        lb = new JLabel("Số lượng"); lb.setBounds(x0+240,305,60,25); add(lb);
        txtSoLuong = new JTextField(); txtSoLuong.setBounds(x0+305,305,40,25); add(txtSoLuong);

        // Right panel
        int x1 = 750;

// Mã phiếu nhập
        lb = new JLabel("Mã phiếu nhập");
        lb.setBounds(x1, 45, 100, 25);
        add(lb);
        txtMaPN = new JTextField();
        txtMaPN.setBounds(x1 + 110, 45, 220, 25);
        txtMaPN.setEditable(false);
        txtMaPN.setText(String.valueOf(nextPN++));
        add(txtMaPN);

// NV giao
        lb = new JLabel("NV nhập");
        lb.setBounds(x1, 85, 100, 25);
        add(lb);
        cbNhanVien = new JComboBox<>(new String[]{"Nguyễn Văn Khanh", "Vũ Hồng Vĩnh Khang", "Hàn Gia Hào"});
        cbNhanVien.setBounds(x1 + 110, 85, 220, 25);
        add(cbNhanVien);

//// Khách hàng
//        lb = new JLabel("Khách hàng");
//        lb.setBounds(x1, 125, 100, 25);
//        add(lb);
//        JComboBox<String> cbKhachHang = new JComboBox<>(new String[]{"Nguyễn Văn A", "Trần Thị B", "Lê Văn C"});
//        cbKhachHang.setSelectedItem("Nguyễn Văn A");
//        cbKhachHang.setBounds(x1 + 110, 125, 220, 25);
//        add(cbKhachHang);

// Trạng thái
        lb = new JLabel("Trạng thái");
        lb.setBounds(x1, 125, 100, 25);
        add(lb);
        cbTrangThai = new JComboBox<>(new String[]{"Chờ", "Hoàn thành"});
        cbTrangThai.setBounds(x1 + 110, 125, 220, 25);
        add(cbTrangThai);
//
// Tổng tiền
        lbTongTien = new JLabel("Tổng tiền: 0 VNĐ");
        lbTongTien.setBounds(x1, 305, 300, 25);
        add(lbTongTien);





        // Buttons
        JButton b1 = new JButton("Thêm sản phẩm"); b1.setBounds(10,365,225,40); b1.setBackground(new Color(54,162,220)); b1.setForeground(Color.WHITE); add(b1);
//        JButton b2 = new JButton("Nhập Excel"); b2.setBounds(185,365,170,40); b2.setBackground(new Color(93,163,113)); b2.setForeground(Color.WHITE); add(b2);
        JButton b3 = new JButton("Sửa sản phẩm"); b3.setBounds(245,365,225,40); b3.setBackground(new Color(242,161,0)); b3.setForeground(Color.WHITE); add(b3);
        JButton b4 = new JButton("Xóa sản phẩm"); b4.setBounds(480,365,225,40); b4.setBackground(new Color(236,91,91)); b4.setForeground(Color.WHITE); add(b4);
        JButton b5 = new JButton("Thêm Phiếu nhập"); b5.setBounds(750,365,330,40); b5.setBackground(new Color(93,163,113)); b5.setForeground(Color.WHITE); add(b5);

        // Initial detail data
        modelChiTiet.addRow(new Object[]{1, "1", "Áo thun cổ tròn", "Coolmate", "Việt Nam", "Đen", "S", 600000, 20});
        modelChiTiet.addRow(new Object[]{2, "2", "Quần jean nam", "Levis", "Thái Lan", "Xanh", "M", 800000, 30});
        modelChiTiet.addRow(new Object[]{3, "3", "Áo khoác gió", "Nike", "Mỹ", "Đỏ", "L", 900000, 10});
        lbTongTien.setText("Tổng tiền: 72,000,000 VNĐ");

        // Action listeners
        b1.addActionListener(e -> addProduct());
        b3.addActionListener(e -> editProduct());
        b4.addActionListener(e -> deleteProduct());
        b5.addActionListener(e -> finishPhieuNhap());
    }

    private void loadSampleProducts() {
        Object[][] data = {
                {1,  "Áo thun cổ tròn",   70},
                {2,  "Quần jean nam",     70},
                {3,  "Áo khoác gió",      50},
                {4,  "Váy nữ dài",        78},
                {5,  "Áo sơ mi trắng",    46},
                {6,  "Áo hoodie",         55},
                {7,  "Quần short nữ",     80},
                {8,  "Chân váy xòe",      30},
                {9,  "Đầm maxi",          45},
                {10, "Áo len cổ lọ",      20},
                {11, "Áo khoác da",       10},
                {12, "Quần jogger",       65},
                {13, "Áo crop top",       25},
                {14, "Đầm bodycon",       15},
                {15, "Áo sơ mi caro",     40},
                {16, "Jumpsuit",          18},
                {17, "Áo vest",           12},
                {18, "Quần tây",          28},
                {19, "Quần legging",      50},
                {20, "Áo blazer",         22},
                {21, "Áo polo",           52},
                {22, "Áo bomber",         22},
                {23, "Quần khaki",        60},
                {24, "Áo peplum",         38},
                {25, "Váy xếp ly",        33}
        };
        for (Object[] r : data) {
            modelSanPham.addRow(r);
        }
    }

    private void setupColorPalette() {
        Color[] palette = { new Color(40,42,43), new Color(219,209,188), new Color(144,113,59),
                new Color(159,169,169), new Color(208,119,113), new Color(149,155,120),
                new Color(79,92,112), new Color(245,241,235), new Color(165,5,29),
                new Color(89,86,79), new Color(56,126,160), new Color(60,69,37),
                new Color(57,29,43), new Color(181,191,108) };
        String[] names = {"Đen","Be","Nâu","Xám nhạt","Hồng nhạt","Xanh rêu","Xanh biển đậm",
                "Trắng","Đỏ","Olive","Xanh biển nhạt","Navy","Rượu vang","Be đậm"};
        for(int i=0;i<palette.length;i++){
            JPanel sw = new JPanel(); sw.setBackground(palette[i]); sw.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            String nm = names[i];
            sw.setToolTipText(nm);
            sw.addMouseListener(new MouseAdapter(){ public void mouseClicked(MouseEvent e){
                selectedColorName=nm;
                for(Component c: colorPanel.getComponents()) ((JPanel)c).setBorder(BorderFactory.createLineBorder(Color.GRAY));
                sw.setBorder(BorderFactory.createLineBorder(Color.RED,2));
            }});
            colorPanel.add(sw);
        }
    }

    private void addProduct() {
        try {
            int stt = modelChiTiet.getRowCount()+1;
            String ma = txtMaSP.getText(); String ten=txtTenSP.getText();
            String th = cbThuongHieu.getSelectedItem().toString();
            String xx = cbXuatXu.getSelectedItem().toString();
            String mc = selectedColorName;
            String sz = cbKichThuoc.getSelectedItem().toString();
            int dg = Integer.parseInt(txtGiaNhap.getText());
            int sl = Integer.parseInt(txtSoLuong.getText());
            modelChiTiet.addRow(new Object[]{stt,ma,ten,th,xx,mc,sz,dg,sl});
            recalcTotal(); clearForm();
        } catch(Exception ex){ JOptionPane.showMessageDialog(this,"Thông tin không hợp lệ","Lỗi",JOptionPane.ERROR_MESSAGE); }
    }
    private void editProduct(){ int r=tblChiTiet.getSelectedRow(); if(r<0) return;
        modelChiTiet.setValueAt(txtGiaNhap.getText(),r,7);
        modelChiTiet.setValueAt(txtSoLuong.getText(),r,8);
        recalcTotal();
    }
    private void deleteProduct(){ int r=tblChiTiet.getSelectedRow(); if(r<0) return; modelChiTiet.removeRow(r); recalcTotal(); }
    private void finishPhieuNhap(){ JOptionPane.showMessageDialog(this,"Phiếu nhập đã được tạo!","OK",JOptionPane.INFORMATION_MESSAGE); resetAll(); txtMaPN.setText(String.valueOf(nextPN++)); }

    private void recalcTotal(){ int sum=0; for(int i=0;i<modelChiTiet.getRowCount();i++){ sum += (int)modelChiTiet.getValueAt(i,7)*(int)modelChiTiet.getValueAt(i,8);} lbTongTien.setText("Tổng tiền: "+String.format("%,d",sum)+" VNĐ"); }
    private void clearForm(){ txtMaSP.setText(""); txtTenSP.setText(""); txtGiaNhap.setText(""); txtSoLuong.setText(""); cbKichThuoc.setSelectedIndex(0); cbThuongHieu.setSelectedIndex(0); cbXuatXu.setSelectedIndex(0);
        for(Component c: colorPanel.getComponents()) ((JPanel)c).setBorder(BorderFactory.createLineBorder(Color.GRAY));
        selectedColorName = null; }
    private void resetAll(){ modelChiTiet.setRowCount(0); recalcTotal(); clearForm(); }
}