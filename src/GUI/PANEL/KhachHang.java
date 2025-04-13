package GUI.PANEL;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.net.URL;

public class KhachHang extends JPanel {
    public KhachHang() {
        setLayout(new BorderLayout(10, 10));

        JPanel P=new JPanel(new BorderLayout());
        JPanel P1=new JPanel();
        ImageIcon addIcon = resizeimg(new ImageIcon((getClass().getResource("/icon/them.png"))));
        JButton btnthem= createIconButton("Thêm", addIcon);

        btnthem.setOpaque(false);
        btnthem.setFocusPainted(false);
        btnthem.setBorderPainted(false);
        ImageIcon suaicon= resizeimg(new ImageIcon((getClass().getResource("/icon/sua.png"))));
        JButton btnsua= createIconButton("Sửa", suaicon);

        btnsua.setOpaque(false);
        btnsua.setFocusPainted(false);
        btnthem.setBorderPainted(false);
        ImageIcon xoaicon= resizeimg(new ImageIcon((getClass().getResource("/icon/xoa.png"))));
        JButton btnxoa= createIconButton("Xóa", xoaicon);
        btnxoa.setOpaque(false);
        btnxoa.setFocusPainted(false);
        btnxoa.setBorderPainted(false);

        ImageIcon chitieticon= resizeimg(new ImageIcon((getClass().getResource("/icon/chitiet.png"))));
        JButton btnct=createIconButton("Chi Tiết", chitieticon);
        btnct.setOpaque(false);
        btnct.setFocusPainted(false);
        btnct.setBorderPainted(false);

        ImageIcon nhapicon= resizeimg(new ImageIcon((getClass().getResource("/icon/nhapexcel.png"))));
        JButton btnnhap=createIconButton("Nhập Excel",nhapicon);
        btnnhap.setOpaque(false);
        btnnhap.setFocusPainted(false);
        btnnhap.setBorderPainted(false);

        ImageIcon xuaticon=resizeimg(new ImageIcon((getClass().getResource("/icon/xuatexcel.png"))));
        JButton btnxuat=createIconButton("Xuất", xuaticon);
        btnxuat.setOpaque(false);
        btnxuat.setFocusPainted(false);
        btnxuat.setBorderPainted(false);



        ImageIcon lmcon= resizeimg(new ImageIcon((getClass().getResource("/icon/lammoi.png"))));
        JButton btnlm= createIconButton("Làm Mới", lmcon);
        btnlm.setOpaque(false);
        btnlm.setFocusPainted(false);
        btnlm.setVerticalTextPosition(SwingConstants.CENTER);
        btnlm.setHorizontalTextPosition(SwingConstants.RIGHT);

        P1.setLayout(new FlowLayout(FlowLayout.LEFT));
        P1.add(btnthem);
        P1.add(btnsua);
        P1.add(btnxoa);
        P1.add(btnct);
        P1.add(btnnhap);
        P1.add(btnxuat);


        String[] cb={"Tất Cả","Mã khách hàng","Tên khách hàng","Địa chỉ","Số điện thoại","Ngày tham gia"};
        JComboBox pl=new JComboBox(cb);
        pl.setPreferredSize(new Dimension(100,40));
        JTextField tf=new JTextField(20);
        tf.setPreferredSize(new Dimension(100,40));
        JPanel P2=new JPanel(new FlowLayout(FlowLayout.RIGHT));
        P2.add(pl);
        P2.add(tf);
        P2.add(btnlm);
        P.add(P1, BorderLayout.WEST);
        P.add(P2,BorderLayout.EAST);
        add(P, BorderLayout.NORTH);
        String[] collum={"Mã khách hàng","Tên khách hàng","Địa chỉ","Số điện thoại","Ngày tham gia"};
        JTable bangkh=new JTable();
        DefaultTableModel model=new DefaultTableModel(collum,0);
        bangkh.setModel(model);
        JScrollPane scrollPane = new JScrollPane(bangkh);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        JTableHeader header = bangkh.getTableHeader();
        add(scrollPane,BorderLayout.CENTER);

        setVisible(true);
    }
    public ImageIcon resizeimg(ImageIcon img)
    {
        Image tmp=img.getImage();
        Image tmp2=tmp.getScaledInstance(30,30,Image.SCALE_SMOOTH);
        img=new ImageIcon(tmp2);
        return img;
    }

    private JButton createIconButton (String text, ImageIcon icon){
        JButton button = new JButton(text);
        if (icon != null) {
            button.setIcon(icon);
        }
        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        return button;
    }

    private void setButtonFlat (JButton button){
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setOpaque(false);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200,700);
        frame.add(new KhachHang());
        frame.setVisible(true);
    }
}



