package GUI.PANEL;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.net.URL;

public class PhanQuyen extends JPanel {
    public PhanQuyen() {
        setLayout(new BorderLayout(10, 10));

        // --------- PHẦN NÚT CHỨC NĂNG (TOP) -----------
        JPanel P = new JPanel(new BorderLayout());
        JPanel P1 = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JButton btnthem = createIconButton("Thêm", resizeIcon("/icon/them.png")); setButtonFlat(btnthem);
        JButton btnsua  = createIconButton("Sửa", resizeIcon("/icon/sua.png")); setButtonFlat(btnsua);
        JButton btnxoa  = createIconButton("Xóa", resizeIcon("/icon/xoa.png")); setButtonFlat(btnxoa);
//        JButton btnct   = createIconButton("Chi Tiết", resizeIcon("/icon/chitiet.png")); setButtonFlat(btnct);
        JButton btnlm   = createIconButton("Làm Mới", resizeIcon("/icon/lammoi.png")); setButtonFlat(btnlm);

        P1.add(btnthem);
        P1.add(btnsua);
        P1.add(btnxoa);
//        P1.add(btnct);

        JPanel P2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JComboBox<String> pl = new JComboBox<>(new String[]{"Tất Cả"});
        pl.setPreferredSize(new Dimension(100,40));
        JTextField tf = new JTextField(20);
        tf.setPreferredSize(new Dimension(100,40));
        P2.add(pl);
        P2.add(tf);
        P2.add(btnlm);

        P.add(P1, BorderLayout.WEST);
        P.add(P2, BorderLayout.EAST);
        add(P, BorderLayout.NORTH);

        // --------- PHẦN BẢNG PHÂN QUYỀN -----------
        String[] columns = {"Mã loại nhân viên","Loại nhân viên"};
        DefaultTableModel model = new DefaultTableModel(columns, 0) {
            @Override public boolean isCellEditable(int row, int col) { return false; }
        };
        JTable bangpq = new JTable(model);
        bangpq.setRowHeight(24);
        JTableHeader header = bangpq.getTableHeader();

        // Thêm 5 nhóm Quản lý
        for (int i = 1; i <= 5; i++) {
            String code = String.format("PQ%03d", i);
            String name = "Quản lý " + i;
            model.addRow(new Object[]{code, name});
        }
        // Thêm 6 nhóm Nhân viên
        for (int i = 1; i <= 6; i++) {
            String code = String.format("PQ%03d", 5 + i);
            String name = "Nhân viên " + i;
            model.addRow(new Object[]{code, name});
        }

        JScrollPane scrollPane = new JScrollPane(bangpq);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }

    private ImageIcon resizeIcon(String path) {
        URL url = getClass().getResource(path);
        if (url != null) {
            Image img = new ImageIcon(url).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
            return new ImageIcon(img);
        }
        return null;
    }

    private JButton createIconButton(String text, ImageIcon icon) {
        JButton btn = new JButton(text, icon);
        btn.setVerticalTextPosition(SwingConstants.BOTTOM);
        btn.setHorizontalTextPosition(SwingConstants.CENTER);
        return btn;
    }

    private void setButtonFlat(JButton button) {
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setOpaque(false);
    }
}
