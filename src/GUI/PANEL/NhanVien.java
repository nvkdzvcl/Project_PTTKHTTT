package GUI.PANEL;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NhanVien extends JPanel {
    public NhanVien(){
        setLayout(new BorderLayout(10, 10));
        JPanel P = new JPanel(new BorderLayout());
        JPanel P1 = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JButton btnthem = createIconButton("Thêm", resizeIcon("/icon/them.png")); setButtonFlat(btnthem);
        JButton btnsua  = createIconButton("Sửa",  resizeIcon("/icon/sua.png")); setButtonFlat(btnsua);
        JButton btnxoa  = createIconButton("Xóa", resizeIcon("/icon/xoa.png")); setButtonFlat(btnxoa);
//        JButton btnct   = createIconButton("Chi Tiết", resizeIcon("/icon/chitiet.png")); setButtonFlat(btnct);
        JButton btnlm   = createIconButton("Làm Mới", resizeIcon("/icon/lammoi.png")); setButtonFlat(btnlm);

        P1.add(btnthem); P1.add(btnsua); P1.add(btnxoa); P1.add(btnlm);

        JPanel P2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JComboBox<String> pl = new JComboBox<>(new String[]{"Tất Cả","MNV","Họ Tên","Giới Tính","Ngày Sinh"});
        pl.setPreferredSize(new Dimension(120,40));
        JTextField tf = new JTextField(); tf.setPreferredSize(new Dimension(120,40));
        P2.add(pl); P2.add(tf); P2.add(btnlm);

        P.add(P1, BorderLayout.WEST);
        P.add(P2, BorderLayout.EAST);
        add(P, BorderLayout.NORTH);

        String[] columns = {"MNV","Họ Tên","Giới Tính","Ngày Sinh","SDT","Email","Địa chỉ"};
        DefaultTableModel model = new DefaultTableModel(columns, 0) {
            @Override public boolean isCellEditable(int row, int col) { return false; }
        };
        JTable bangnv = new JTable(model);
        bangnv.setRowHeight(24);
        JTableHeader header = bangnv.getTableHeader();

        // Thêm 25 dòng dữ liệu sample
        Object[][] sample = {
                {"NV001","Nguyễn Văn A","Nam","01/01/1980","0912345678","a@example.com","Hà Nội"},
                {"NV002","Trần Thị B","Nữ","02/02/1981","0987654321","b@example.com","Hồ Chí Minh"},
                {"NV003","Lê Văn C","Nam","03/03/1982","0901111222","c@example.com","Đà Nẵng"},
                {"NV004","Phạm Thị D","Nữ","04/04/1983","0972222333","d@example.com","Hải Phòng"},
                {"NV005","Hoàng Văn E","Nam","05/05/1984","0963333444","e@example.com","Cần Thơ"},
                {"NV006","Vũ Thị F","Nữ","06/06/1985","0954444555","f@example.com","Nha Trang"},
                {"NV007","Đặng Văn G","Nam","07/07/1986","0945555666","g@example.com","Huế"},
                {"NV008","Bùi Thị H","Nữ","08/08/1987","0936666777","h@example.com","Vũng Tàu"},
                {"NV009","Trương Văn I","Nam","09/09/1988","0927777888","i@example.com","Quảng Ninh"},
                {"NV010","Phan Thị K","Nữ","10/10/1989","0918888999","k@example.com","Phú Yên"},
                {"NV011","Lý Văn L","Nam","11/11/1990","0909999000","l@example.com","Bình Dương"},
                {"NV012","Đỗ Thị M","Nữ","12/12/1991","0910000111","m@example.com","Thanh Hóa"},
                {"NV013","Ngô Văn N","Nam","13/01/1992","0921111222","n@example.com","Ninh Bình"},
                {"NV014","Dương Thị O","Nữ","14/02/1993","0932222333","o@example.com","Quảng Bình"},
                {"NV015","Võ Văn P","Nam","15/03/1994","0943333444","p@example.com","Kon Tum"},
                {"NV016","Lê Thị Q","Nữ","16/04/1995","0954444555","q@example.com","Đắk Lắk"},
                {"NV017","Nguyễn Văn R","Nam","17/05/1996","0965555666","r@example.com","Gia Lai"},
                {"NV018","Trần Thị S","Nữ","18/06/1997","0976666777","s@example.com","Sóc Trăng"},
                {"NV019","Phạm Văn T","Nam","19/07/1998","0987777888","t@example.com","Long An"},
                {"NV020","Hoàng Thị U","Nữ","20/08/1999","0918888999","u@example.com","Bình Thuận"},
                {"NV021","Vũ Văn V","Nam","21/09/2000","0909999001","v@example.com","Kiên Giang"},
                {"NV022","Đặng Thị W","Nữ","22/10/2001","0910000112","w@example.com","Cà Mau"},
                {"NV023","Bùi Văn X","Nam","23/11/2002","0921111223","x@example.com","An Giang"},
                {"NV024","Trương Thị Y","Nữ","24/12/2003","0932222334","y@example.com","Bạc Liêu"},
                {"NV025","Phan Văn Z","Nam","25/01/2004","0943333445","z@example.com","Trà Vinh"}
        };
        for (Object[] row : sample) model.addRow(row);

        JScrollPane scrollPane = new JScrollPane(bangnv);
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
