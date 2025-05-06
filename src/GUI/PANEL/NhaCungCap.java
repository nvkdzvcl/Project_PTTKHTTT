package GUI.PANEL;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.net.URL;

public class NhaCungCap extends JPanel {
    public NhaCungCap() {
        setLayout(new BorderLayout(10, 10));

        // --------- PHẦN NÚT CHỨC NĂNG (TOP) -----------
        JPanel P = new JPanel(new BorderLayout());
        JPanel P1 = new JPanel();

        ImageIcon addIcon = resizeimg(new ImageIcon(getClass().getResource("/icon/them.png")));
        JButton btnthem = createIconButton("Thêm", addIcon);
        setButtonFlat(btnthem);

        ImageIcon suaicon = resizeimg(new ImageIcon(getClass().getResource("/icon/sua.png")));
        JButton btnsua = createIconButton("Sửa", suaicon);
        setButtonFlat(btnsua);

        ImageIcon xoaicon = resizeimg(new ImageIcon(getClass().getResource("/icon/xoa.png")));
        JButton btnxoa = createIconButton("Xóa", xoaicon);
        setButtonFlat(btnxoa);

//        ImageIcon chitieticon = resizeimg(new ImageIcon(getClass().getResource("/icon/chitiet.png")));
//        JButton btnct = createIconButton("Chi Tiết", chitieticon);
//        setButtonFlat(btnct);



        ImageIcon lmcon = resizeimg(new ImageIcon(getClass().getResource("/icon/lammoi.png")));
        JButton btnlm = createIconButton("Làm Mới", lmcon);
        setButtonFlat(btnlm);

        P1.setLayout(new FlowLayout(FlowLayout.LEFT));
        P1.add(btnthem);
        P1.add(btnsua);
        P1.add(btnxoa);
//        P1.add(btnct);

        String[] cb = {"Tất Cả","Mã NCC","Tên NCC","Địa chỉ","Email","Số điện thoại"};
        JComboBox<String> pl = new JComboBox<>(cb);
        pl.setPreferredSize(new Dimension(120, 40));
        JTextField tf = new JTextField(20);
        tf.setPreferredSize(new Dimension(120, 40));
        JPanel P2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        P2.add(pl);
        P2.add(tf);
        P2.add(btnlm);

        P.add(P1, BorderLayout.WEST);
        P.add(P2, BorderLayout.EAST);
        add(P, BorderLayout.NORTH);

        // --------- PHẦN BẢNG NHÀ CUNG CẤP -----------
        String[] columns = {"Mã Nhà cung cấp","Tên Nhà cung cấp","Địa chỉ","Email","Số điện thoại"};
        DefaultTableModel model = new DefaultTableModel(columns, 0) {
            @Override public boolean isCellEditable(int row, int col) { return false; }
        };
        JTable bangnv = new JTable(model);
        bangnv.setRowHeight(24);
        JTableHeader header = bangnv.getTableHeader();

        // Thêm 25 dòng sample dữ liệu
        Object[][] sample = {
                {"NCC001","Công ty ABC","Hà Nội","contact@abc.com","0247123456"},
                {"NCC002","Công ty DEF","Hồ Chí Minh","info@def.com","0287654321"},
                {"NCC003","Công ty GHI","Đà Nẵng","sales@ghi.com","0236371829"},
                {"NCC004","Công ty JKL","Hải Phòng","support@jkl.com","0225312456"},
                {"NCC005","Công ty MNO","Cần Thơ","hello@mno.com","0292123456"},
                {"NCC006","Công ty PQR","Nha Trang","contact@pqr.com","0258321456"},
                {"NCC007","Công ty STU","Huế","info@stu.com","0234382736"},
                {"NCC008","Công ty VWX","Vũng Tàu","sales@vwx.com","0254351829"},
                {"NCC009","Công ty YZA","Quảng Ninh","support@yza.com","0203382745"},
                {"NCC010","Công ty BCD","Phú Yên","hello@bcd.com","0257351829"},
                {"NCC011","Công ty EFG","Bình Dương","contact@efg.com","0274381920"},
                {"NCC012","Công ty HIJ","Thanh Hóa","info@hij.com","0237371829"},
                {"NCC013","Công ty KLM","Ninh Bình","sales@klm.com","0229361728"},
                {"NCC014","Công ty NOP","Quảng Bình","support@nop.com","0235532817"},
                {"NCC015","Công ty QRS","Kon Tum","hello@qrs.com","0260382741"},
                {"NCC016","Công ty TUV","Đắk Lắk","contact@tuv.com","0262328182"},
                {"NCC017","Công ty WXY","Gia Lai","info@wxy.com","0269321728"},
                {"NCC018","Công ty ZAB","Sóc Trăng","sales@zab.com","0293338172"},
                {"NCC019","Công ty CDE","Long An","support@cde.com","0270321829"},
                {"NCC020","Công ty EHI","Bình Thuận","hello@ehi.com","0250258172"},
                {"NCC021","Công ty IJK","Kiên Giang","contact@ijk.com","0291302178"},
                {"NCC022","Công ty LMN","Cà Mau","info@lmn.com","0292328173"},
                {"NCC023","Công ty OPQ","An Giang","sales@opq.com","0293328174"},
                {"NCC024","Công ty RST","Bạc Liêu","support@rst.com","0294328175"},
                {"NCC025","Công ty UVW","Trà Vinh","hello@uvw.com","0295328176"}
        };
        for (Object[] row : sample) {
            model.addRow(row);
        }

        JScrollPane scrollPane = new JScrollPane(bangnv);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }

    public ImageIcon resizeimg(ImageIcon img) {
        Image tmp = img.getImage();
        Image tmp2 = tmp.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        return new ImageIcon(tmp2);
    }

    private JButton createIconButton(String text, ImageIcon icon) {
        JButton button = new JButton(text, icon);
        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        return button;
    }

    private void setButtonFlat(JButton button) {
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setOpaque(false);
    }
}
