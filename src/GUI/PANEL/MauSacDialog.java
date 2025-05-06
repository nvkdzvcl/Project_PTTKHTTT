package GUI.PANEL;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MauSacDialog extends JDialog {
    private JTextField txtTenMau;
    private JTable    tblColors;
    private DefaultTableModel model;
    private JButton   btnThem, btnXoa, btnSua;
    private final Color[] palette = {
            new Color( 40,  42,  43),  // Đen
            new Color(219, 209, 188),  // Be
            new Color(144, 113,  59),  // Nâu
            new Color(159, 169, 169),  // Xám nhạt
            new Color(208, 119, 113),  // Hồng nhạt
            new Color(149, 155, 120),  // Xanh rêu
            new Color( 79,  92, 112),  // Xanh biển đậm
            new Color(245, 241, 230),  // Trắng
            new Color(165,   5,  29),  // Đỏ
            new Color( 89,  86,  79),  // Olive
            new Color( 56, 126, 160),  // Xanh biển nhạt
            new Color( 60,  69,  37),  // Navy
            new Color( 57,  29,  43),  // Rượu vang
            new Color(181, 191, 108)   // Be đậm
    };
    private final String[] colorNames = {
            "Đen","Be","Nâu","Xám nhạt","Hồng nhạt",
            "Xanh rêu","Xanh biển đậm","Trắng","Đỏ","Olive",
            "Xanh biển nhạt","Navy","Rượu vang","Be đậm"
    };
    public MauSacDialog(Frame parent) {
        super(parent, "Quản lý màu sắc", true);
        initComponents();
        pack();
        setLocationRelativeTo(parent);
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        // 1) HEADER
        JPanel header = new JPanel();
        header.setBackground(new Color(41, 128, 185)); // màu xanh đậm
        JLabel lblTitle = new JLabel("MÀU SẮC SẢN PHẨM");
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        header.add(lblTitle);
        add(header, BorderLayout.NORTH);

        // 2) FORM + TABLE
        // 2.1 form nhập
        JPanel form = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        form.setBorder(new EmptyBorder(5,10,5,10));
        form.add(new JLabel("Tên màu sắc"));
        txtTenMau = new JTextField(20);
        form.add(txtTenMau);

        // placeholder đơn giản (mờ mờ)
        String pho = "Nhập tên màu...";
        txtTenMau.setText(pho);
        txtTenMau.setForeground(Color.GRAY);
        txtTenMau.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent e) {
                if (txtTenMau.getText().equals(pho)) {
                    txtTenMau.setText("");
                    txtTenMau.setForeground(Color.BLACK);
                }
            }
            public void focusLost(java.awt.event.FocusEvent e) {
                if (txtTenMau.getText().isEmpty()) {
                    txtTenMau.setText(pho);
                    txtTenMau.setForeground(Color.GRAY);
                }
            }
        });

        // 2.2 bảng màu
        String[] cols = {"Mã màu", "Tên màu", "Màu"};
        model = new DefaultTableModel(cols, 0) {
            @Override public boolean isCellEditable(int r, int c) {
                return false;
            }
        };
        tblColors = new JTable(model);
        tblColors.setRowHeight(30);
        tblColors.getTableHeader().setReorderingAllowed(false);

        // Fill data từ palette
        for (int i = 0; i < palette.length; i++) {
            model.addRow(new Object[]{ i+1, colorNames[i], palette[i] });
        }

        // Renderer cho cột “Màu”
        tblColors.getColumnModel()
                .getColumn(2)
                .setCellRenderer(new DefaultTableCellRenderer() {
                    @Override
                    public Component getTableCellRendererComponent(JTable table, Object value,
                                                                   boolean isSelected, boolean hasFocus,
                                                                   int row, int column) {
                        // Tạo label hoặc panel trống, set nền = giá trị Color
                        JLabel lbl = new JLabel();
                        lbl.setOpaque(true);
                        lbl.setBackground((Color) value);
                        return lbl;
                    }
                });

        JScrollPane scroll = new JScrollPane(tblColors);
        scroll.setBorder(BorderFactory.createEmptyBorder());

        // 2.3 gộp form + bảng
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

    // helper tạo button màu & trắng chữ
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
            MauSacDialog dlg = new MauSacDialog(null);
            dlg.setVisible(true);
            System.exit(0);
        });
    }
}
