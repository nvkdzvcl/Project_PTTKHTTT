package GUI.PANEL;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class SizeDialog extends JDialog {
    private JTextField txtSize;
    private JTable    tblSize;
    private DefaultTableModel model;
    private JButton   btnThem, btnXoa, btnSua;

    public SizeDialog(Frame parent) {
        super(parent, "Quản lý Size", true);
        initComponents();
        pack();
        setLocationRelativeTo(parent);
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        // 1) HEADER
        JPanel header = new JPanel();
        header.setBackground(new Color(41, 128, 185));
        JLabel lblTitle = new JLabel("DANH MỤC SIZE");
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        header.add(lblTitle);
        add(header, BorderLayout.NORTH);

        // 2) FORM + TABLE
        // 2.1 form nhập
        JPanel form = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        form.setBorder(new EmptyBorder(5,10,5,10));
        form.add(new JLabel("Size"));
        txtSize = new JTextField(10);
        form.add(txtSize);

        // placeholder
        String placeholder = "Nhập size...";
        txtSize.setText(placeholder);
        txtSize.setForeground(Color.GRAY);
        txtSize.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                if (txtSize.getText().equals(placeholder)) {
                    txtSize.setText("");
                    txtSize.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                if (txtSize.getText().isEmpty()) {
                    txtSize.setText(placeholder);
                    txtSize.setForeground(Color.GRAY);
                }
            }
        });

        // 2.2 bảng size
        String[] cols = {"Mã size", "Size"};
        model = new DefaultTableModel(cols, 0) {
            @Override public boolean isCellEditable(int r, int c) {
                return false;
            }
        };
        tblSize = new JTable(model);
        tblSize.setRowHeight(25);
        tblSize.getTableHeader().setReorderingAllowed(false);

        // sample data
        Object[][] sample = {
                {1, "S"},
                {2, "M"},
                {3, "L"},
                {4, "XL"},
                {5, "2XL"}
        };
        for (Object[] row : sample) {
            model.addRow(row);
        }

        JScrollPane scroll = new JScrollPane(tblSize);
        scroll.setBorder(BorderFactory.createEmptyBorder());

        // gộp form + bảng
        JPanel center = new JPanel();
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
        center.add(form);
        center.add(scroll);
        add(center, BorderLayout.CENTER);

        // 3) FOOTER – Thêm / Xóa / Sửa
        JPanel footer = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        btnThem = makeButton("Thêm", new Color(46, 204, 113));
        btnXoa  = makeButton("Xóa",  new Color(231,  76,  60));
        btnSua  = makeButton("Sửa",  new Color(52, 152, 219));
        footer.add(btnThem);
        footer.add(btnXoa);
        footer.add(btnSua);
        add(footer, BorderLayout.SOUTH);
    }

    // helper tạo button nền màu + chữ trắng
    private JButton makeButton(String text, Color bg) {
        JButton b = new JButton(text);
        b.setBackground(bg);
        b.setForeground(Color.WHITE);
        b.setFocusPainted(false);
        b.setPreferredSize(new Dimension(100, 40));
        return b;
    }

    // Test dialog riêng
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SizeDialog dlg = new SizeDialog(null);
            dlg.setVisible(true);
            System.exit(0);
        });
    }
}
