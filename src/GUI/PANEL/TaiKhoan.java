package GUI.PANEL;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.net.URL;

public class TaiKhoan extends JPanel {
    public TaiKhoan() {
        setLayout(new BorderLayout(10, 10));

        // --------- PHẦN NÚT CHỨC NĂNG (TOP) -----------
        JPanel P = new JPanel(new BorderLayout());
        JPanel P1 = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JButton btnthem = createIconButton("Thêm", resizeIcon("/icon/them.png")); setButtonFlat(btnthem);
        JButton btnsua  = createIconButton("Sửa",  resizeIcon("/icon/sua.png"));  setButtonFlat(btnsua);
        JButton btnxoa  = createIconButton("Xóa", resizeIcon("/icon/xoa.png")); setButtonFlat(btnxoa);
//        JButton btnct   = createIconButton("Chi Tiết", resizeIcon("/icon/chitiet.png")); setButtonFlat(btnct);
        JButton btnlm   = createIconButton("Làm Mới", resizeIcon("/icon/lammoi.png")); setButtonFlat(btnlm);

        P1.add(btnthem); P1.add(btnsua); P1.add(btnxoa); P1.add(btnlm);

        JPanel P2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JComboBox<String> pl = new JComboBox<>(new String[]{"Tất Cả","Mã nhân viên","Username"});
        pl.setPreferredSize(new Dimension(120,40));
        JTextField tf = new JTextField(); tf.setPreferredSize(new Dimension(120,40));
        P2.add(pl); P2.add(tf); P2.add(btnlm);

        P.add(P1, BorderLayout.WEST);
        P.add(P2, BorderLayout.EAST);
        add(P, BorderLayout.NORTH);

        // --------- PHẦN BẢNG TÀI KHOẢN -----------
        String[] columns = {"Mã nhân viên","Tên đăng nhập","Chức vụ","Trạng thái"};
        DefaultTableModel model = new DefaultTableModel(columns, 0) {
            @Override public boolean isCellEditable(int row, int col) { return false; }
        };
        JTable bangtk = new JTable(model);
        bangtk.setRowHeight(24);
        JTableHeader header = bangtk.getTableHeader();

        // Thêm 25 dòng sample:
        // 5 roles "Quản lý 1".."Quản lý 5", 6 roles "Nhân viên 1".."Nhân viên 6", mỗi loại 2 người
        int id = 1;
        for (int i = 1; i <= 5; i++) {
            for (int j = 1; j <= 2; j++) {
                model.addRow(new Object[]{
                        String.format("NV%03d", id++),
                        String.format("ql%d_user%d", i, j),
                        String.format("Quản lý %d", i),
                        "Hoạt động"
                });
            }
        }
        for (int i = 1; i <= 6; i++) {
            for (int j = 1; j <= 2; j++) {
                model.addRow(new Object[]{
                        String.format("NV%03d", id++),
                        String.format("nv%d_user%d", i, j),
                        String.format("Nhân viên %d", i),
                        "Hoạt động"
                });
            }
        }

        JScrollPane scrollPane = new JScrollPane(bangtk);
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