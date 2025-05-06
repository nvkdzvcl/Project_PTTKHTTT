package GUI.PANEL;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class XuatXuDialog extends JDialog {
    private JTextField txtXuatXu;
    private JTable    tblXuatXu;
    private DefaultTableModel model;
    private JButton   btnThem, btnXoa, btnSua;

    public XuatXuDialog(Frame parent) {
        super(parent, "Quản lý Xuất xứ", true);
        initComponents();
        pack();
        setLocationRelativeTo(parent);
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        // 1) HEADER
        JPanel header = new JPanel();
        header.setBackground(new Color(41, 128, 185));
        JLabel lblTitle = new JLabel("XUẤT XỨ SẢN PHẨM");
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        header.add(lblTitle);
        add(header, BorderLayout.NORTH);

        // 2) FORM + TABLE
        JPanel form = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        form.setBorder(new EmptyBorder(5,10,5,10));
        form.add(new JLabel("Xuất xứ"));
        txtXuatXu = new JTextField(20);
        form.add(txtXuatXu);

        // placeholder
        String placeholder = "Nhập xuất xứ...";
        txtXuatXu.setText(placeholder);
        txtXuatXu.setForeground(Color.GRAY);
        txtXuatXu.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                if (txtXuatXu.getText().equals(placeholder)) {
                    txtXuatXu.setText("");
                    txtXuatXu.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                if (txtXuatXu.getText().isEmpty()) {
                    txtXuatXu.setText(placeholder);
                    txtXuatXu.setForeground(Color.GRAY);
                }
            }
        });

        // table
        String[] cols = {"Mã xuất xứ", "Xuất xứ"};
        model = new DefaultTableModel(cols, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        tblXuatXu = new JTable(model);
        tblXuatXu.setRowHeight(25);
        tblXuatXu.getTableHeader().setReorderingAllowed(false);

        // sample data
        Object[][] sample = {
                {1, "Việt Nam"},
                {2, "Thái Lan"},
                {3, "Trung Quốc"},
                {4, "Hàn Quốc"},
                {5, "Mỹ"},
                {6, "Nhật Bản"},
                {7, "Đức"},
                {8, "Ý"},
                {9, "Anh"},
                {10,"Canada"}
        };
        for (Object[] row : sample) model.addRow(row);

        JScrollPane scroll = new JScrollPane(tblXuatXu);
        scroll.setBorder(BorderFactory.createEmptyBorder());

        // center
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
            XuatXuDialog dlg = new XuatXuDialog(null);
            dlg.setVisible(true);
            System.exit(0);
        });
    }
}
