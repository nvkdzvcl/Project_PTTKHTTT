package GUI.PANEL;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.net.URL;

public class PhieuXuat extends JPanel {

    public PhieuXuat() {
        // Sử dụng BorderLayout với khoảng cách 10 pixel
        setLayout(new BorderLayout(10, 10));

        // --------- PHẦN NÚT CHỨC NĂNG (TOP) -----------
        JPanel P = new JPanel(new BorderLayout());
        JPanel P1 = new JPanel(new FlowLayout(FlowLayout.LEFT));

        // Nút Thêm
        ImageIcon addIcon = resizeimg(new ImageIcon(getClass().getResource("/icon/them.png")));
        JButton btnthem = createIconButton("Thêm", addIcon);
        btnthem.setOpaque(false);
        btnthem.setFocusPainted(false);
        btnthem.setBorderPainted(false);
        P1.add(btnthem);

        // Nút Chi tiết
        ImageIcon chitieticon = resizeimg(new ImageIcon(getClass().getResource("/icon/chitiet.png")));
        JButton btnchitiet = createIconButton("Chi tiêt", chitieticon);
        btnchitiet.setOpaque(false);
        btnchitiet.setFocusPainted(false);
        btnchitiet.setBorderPainted(false);
        P1.add(btnchitiet);

        // Nút Hủy phiếu
        ImageIcon huyphieuicon = resizeimg(new ImageIcon(getClass().getResource("/icon/huyphieu.png")));
        JButton btnhuyphieu = createIconButton("Hủy phiếu", huyphieuicon);
        btnhuyphieu.setOpaque(false);
        btnhuyphieu.setFocusPainted(false);
        btnhuyphieu.setBorderPainted(false);
        P1.add(btnhuyphieu);

        // Nút Xuất Excel
        ImageIcon xuatexcelicon = resizeimg(new ImageIcon(getClass().getResource("/icon/xuatexcel.png")));
        JButton btnxuatexcel = createIconButton("Xuất Excel", xuatexcelicon);
        btnxuatexcel.setOpaque(false);
        btnxuatexcel.setFocusPainted(false);
        btnxuatexcel.setBorderPainted(false);
        P1.add(btnxuatexcel);

        // Nút Làm mới
        ImageIcon lmcon = resizeimg(new ImageIcon(getClass().getResource("/icon/lammoi.png")));
        JButton btnlm = createIconButton("Làm Mới", lmcon);
        btnlm.setOpaque(false);
        btnlm.setFocusPainted(false);
        btnlm.setVerticalTextPosition(SwingConstants.CENTER);
        btnlm.setHorizontalTextPosition(SwingConstants.RIGHT);

        // Panel chứa công cụ tìm kiếm (bên phải của thanh chức năng)
        JPanel P2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        String[] cb = {"Tất Cả", "Mã hóa đơn", "Khách hàng", "Nhân viên bán"};
        JComboBox<String> pl = new JComboBox<>(cb);
        pl.setPreferredSize(new Dimension(100, 40));
        JTextField tf = new JTextField(20);
        tf.setPreferredSize(new Dimension(100, 40));
        P2.add(pl);
        P2.add(tf);
        P2.add(btnlm);

        // Ghép hai panel con vào panel P
        P.add(P1, BorderLayout.WEST);
        P.add(P2, BorderLayout.EAST);

        // Thêm panel chứa các nút chức năng vào phần NORTH của giao diện
        add(P, BorderLayout.NORTH);

        // --------- PHẦN GIAO DIỆN CHÍNH -----------
        // Tạo một panel trung tâm để chứa cả bộ lọc tìm kiếm và khu vực hiển thị nội dung
        JPanel centerPanel = new JPanel(new BorderLayout(10, 10));

        // Thêm bộ lọc tìm kiếm vào phần WEST
        JPanel filterPanel = createLeftFilterPanel();
        centerPanel.add(filterPanel, BorderLayout.WEST);

        // Ví dụ: Thêm bảng dữ liệu vào phần CENTER (bạn có thể thay bảng mẫu này bằng bảng của bạn)
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Mã hóa đơn");
        model.addColumn("Khách hàng");
        model.addColumn("Nhân viên bán");
        model.addColumn("Thời gian");
        model.addColumn("Tổng tiền");

        // Thêm một số dòng mẫu (các dòng này chỉ để minh họa)
        model.addRow(new Object[]{"HD001", "Anh Khanh đẹp trai", "Nguyễn Văn A", "01/01/2025", "1,000,000"});
        model.addRow(new Object[]{"HD002", "Anh Khang đẹp trai", "Trần Thị B", "02/01/2025", "2,000,000"});

        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        // Thêm centerPanel vào phần CENTER của giao diện chính
        add(centerPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    // Phương thức thay đổi kích thước icon
    public ImageIcon resizeimg(ImageIcon img) {
        Image tmp = img.getImage();
        Image tmp2 = tmp.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        return new ImageIcon(tmp2);
    }

    // Tạo nút có icon và text
    private JButton createIconButton(String text, ImageIcon icon) {
        JButton button = new JButton(text);
        if (icon != null) {
            button.setIcon(icon);
        }
        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        return button;
    }

    private JPanel createLeftFilterPanel() {
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new GridBagLayout());
        leftPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Bộ lọc tìm kiếm"),
                new EmptyBorder(5, 5, 5, 10)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        leftPanel.add(new JLabel("Khách hàng:"), gbc);

        gbc.gridy++;
        gbc.weightx = 1.0;
        leftPanel.add(new JComboBox<>(new String[] {
                "Tất cả", "Nguyễn Văn A", "Trần Thị B"
        }), gbc);
        gbc.weightx = 0;

        gbc.gridy++;
        leftPanel.add(new JLabel("Nhân viên bán:"), gbc);

        gbc.gridy++;
        gbc.weightx = 1.0;
        leftPanel.add(new JComboBox<>(new String[] {
                "Tất cả", "Vũ Hồng Vĩnh Khang", "Nguyễn Văn Khanh", "Hàn Gia Hào"
        }), gbc);
        gbc.weightx = 0;

        gbc.gridy++;
        leftPanel.add(new JLabel("Từ ngày:"), gbc);

        gbc.gridy++;
        gbc.weightx = 1.0;
        leftPanel.add(new JTextField(), gbc);
        gbc.weightx = 0;

        gbc.gridy++;
        leftPanel.add(new JLabel("Đến ngày:"), gbc);

        gbc.gridy++;
        gbc.weightx = 1.0;
        leftPanel.add(new JTextField(), gbc);
        gbc.weightx = 0;

        gbc.gridy++;
        leftPanel.add(new JLabel("Từ số tiền (VND):"), gbc);

        gbc.gridy++;
        gbc.weightx = 1.0;
        leftPanel.add(new JTextField(), gbc);
        gbc.weightx = 0;

        gbc.gridy++;
        leftPanel.add(new JLabel("Đến số tiền (VND):"), gbc);

        gbc.gridy++;
        gbc.weightx = 1.0;
        leftPanel.add(new JTextField(), gbc);

        gbc.weightx = 0;
        gbc.gridy++;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.VERTICAL;
        leftPanel.add(new JLabel(), gbc);

//        leftPanel.setPreferredSize(new Dimension(220, leftPanel.getPreferredSize().height));
        leftPanel.setPreferredSize(new Dimension(220, 0)); // hoặc có thể bỏ luôn

        return leftPanel;
    }

    private ImageIcon loadIcon(String path) {
        URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Không thể tải icon: " + path);
            return null;
        }
    }
}
