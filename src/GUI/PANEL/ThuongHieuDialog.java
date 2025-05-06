package GUI.PANEL;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ThuongHieuDialog extends JDialog {
    private JTextField txtThuongHieu;
    private JTable    tblThuongHieu;
    private DefaultTableModel model;
    private JButton   btnThem, btnXoa, btnSua;

    public ThuongHieuDialog(Frame parent) {
        super(parent, "Quản lý Thương hiệu", true);
        initComponents();
        pack();
        setLocationRelativeTo(parent);
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        // 1) HEADER
        JPanel header = new JPanel();
        header.setBackground(new Color(41, 128, 185));
        JLabel lblTitle = new JLabel("THƯƠNG HIỆU SẢN PHẨM");
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        header.add(lblTitle);
        add(header, BorderLayout.NORTH);

        // 2) FORM + TABLE
        JPanel form = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        form.setBorder(new EmptyBorder(5,10,5,10));
        form.add(new JLabel("Thương hiệu"));
        txtThuongHieu = new JTextField(20);
        form.add(txtThuongHieu);

        // placeholder
        String placeholder = "Nhập thương hiệu...";
        txtThuongHieu.setText(placeholder);
        txtThuongHieu.setForeground(Color.GRAY);
        txtThuongHieu.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                if (txtThuongHieu.getText().equals(placeholder)) {
                    txtThuongHieu.setText("");
                    txtThuongHieu.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                if (txtThuongHieu.getText().isEmpty()) {
                    txtThuongHieu.setText(placeholder);
                    txtThuongHieu.setForeground(Color.GRAY);
                }
            }
        });

        // table
        String[] cols = {"Mã thương hiệu", "Thương hiệu"};
        model = new DefaultTableModel(cols, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        tblThuongHieu = new JTable(model);
        tblThuongHieu.setRowHeight(25);
        tblThuongHieu.getTableHeader().setReorderingAllowed(false);

        // sample data
        Object[][] sample = {
                {1, "Coolmate"},
                {2, "Nike"},
                {3, "Adidas"},
                {4, "Zara"},
                {5, "Levis"},
                {6, "H&M"},
                {7, "Gucci"},
                {8, "Prada"},
                {9, "Converse"},
                {10,"Puma"}
        };
        for (Object[] row : sample) model.addRow(row);

        JScrollPane scroll = new JScrollPane(tblThuongHieu);
        scroll.setBorder(BorderFactory.createEmptyBorder());

        // center panel
        JPanel center = new JPanel();
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
        center.add(form);
        center.add(scroll);
        add(center, BorderLayout.CENTER);

        // footer
        JPanel footer = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        btnThem = makeButton("Thêm", new Color(46, 204, 113));
        btnXoa  = makeButton("Xóa",  new Color(231,  76,  60));
        btnSua  = makeButton("Sửa",  new Color(52, 152, 219));
        footer.add(btnThem);
        footer.add(btnXoa);
        footer.add(btnSua);
        add(footer, BorderLayout.SOUTH);
    }

    private JButton makeButton(String text, Color bg) {
        JButton b = new JButton(text);
        b.setBackground(bg);
        b.setForeground(Color.WHITE);
        b.setFocusPainted(false);
        b.setPreferredSize(new Dimension(100, 40));
        return b;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ThuongHieuDialog dlg = new ThuongHieuDialog(null);
            dlg.setVisible(true);
            System.exit(0);
        });
    }
}
