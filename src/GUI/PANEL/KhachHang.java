package GUI.PANEL;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;

public class KhachHang extends JPanel {
    public KhachHang() {
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

        String[] cb = {"Tất Cả","Mã khách hàng","Tên khách hàng","Địa chỉ","Số điện thoại","Ngày tham gia"};
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

        // --------- PHẦN BẢNG KHÁCH HÀNG -----------
        String[] columns = {"Mã khách hàng","Tên khách hàng","Địa chỉ","Số điện thoại","Ngày tham gia"};
        DefaultTableModel model = new DefaultTableModel(columns, 0) {
            @Override public boolean isCellEditable(int row, int col) { return false; }
        };
        JTable bangkh = new JTable(model);
        bangkh.setRowHeight(24);
        JTableHeader header = bangkh.getTableHeader();

        // Thêm 25 dòng dữ liệu sample
        Object[][] sample = {
                {"KH001","Nguyễn Văn A","Hà Nội","0912345678","15/03/2021"},
                {"KH002","Trần Thị B","Hồ Chí Minh","0987654321","22/04/2021"},
                {"KH003","Lê Văn C","Đà Nẵng","0901234567","30/05/2021"},
                {"KH004","Phạm Thị D","Hải Phòng","0976543210","10/06/2021"},
                {"KH005","Hoàng Văn E","Cần Thơ","0911223344","18/07/2021"},
                {"KH006","Vũ Thị F","Nha Trang","0933445566","25/08/2021"},
                {"KH007","Đặng Văn G","Huế","0944556677","02/09/2021"},
                {"KH008","Bùi Thị H","Vũng Tàu","0955667788","14/10/2021"},
                {"KH009","Trương Văn I","Quảng Ninh","0966778899","28/11/2021"},
                {"KH010","Phan Thị K","Phú Yên","0977889900","05/12/2021"},
                {"KH011","Lý Văn L","Bình Dương","0988990011","12/01/2022"},
                {"KH012","Đỗ Thị M","Thanh Hóa","0910091122","23/02/2022"},
                {"KH013","Ngô Văn N","Ninh Bình","0901100223","01/03/2022"},
                {"KH014","Dương Thị O","Quảng Bình","0932211334","15/04/2022"},
                {"KH015","Võ Văn P","Kon Tum","0943322445","28/05/2022"},
                {"KH016","Lê Thị Q","Đắk Lắk","0954433556","10/06/2022"},
                {"KH017","Nguyễn Văn R","Gia Lai","0965544667","20/07/2022"},
                {"KH018","Trần Thị S","Sóc Trăng","0976655778","03/08/2022"},
                {"KH019","Phạm Văn T","Long An","0987766889","17/09/2022"},
                {"KH020","Hoàng Thị U","Bình Thuận","0918877990","01/10/2022"},
                {"KH021","Vũ Văn V","Kiên Giang","0909988112","15/11/2022"},
                {"KH022","Đặng Thị W","Cà Mau","0931099223","29/12/2022"},
                {"KH023","Bùi Văn X","An Giang","0942100334","14/01/2023"},
                {"KH024","Trương Thị Y","Bạc Liêu","0953211445","27/02/2023"},
                {"KH025","Phan Văn Z","Trà Vinh","0964322556","12/03/2023"}
        };
        for (Object[] row : sample) {
            model.addRow(row);
        }

        JScrollPane scrollPane = new JScrollPane(bangkh);
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

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 700);
        frame.add(new KhachHang());
        frame.setVisible(true);
    }
}
