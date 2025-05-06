package GUI.PANEL.ThongKe;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Random;

public class ThongKe extends JPanel {
    public ThongKe() {
        setLayout(new BorderLayout());

        JTabbedPane mainTabs = new JTabbedPane();
        mainTabs.addTab("Tổng quan", createTongQuanPanel());
        mainTabs.addTab("Doanh thu", createDoanhThuPanel());

        add(mainTabs, BorderLayout.CENTER);
    }

    private JPanel createTongQuanPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));

        JPanel top = new JPanel(new GridLayout(1, 2, 10, 10));
        top.add(createInfoBox("Khách hàng từ trước đến nay", "242"));
        top.add(createInfoBox("Nhân viên đang hoạt động", "22"));
        panel.add(top, BorderLayout.NORTH);

        String[] cols = {"Ngày", "Chi phí", "Doanh thu", "Lợi nhuận"};
        DefaultTableModel model = new DefaultTableModel(cols, 0);
        JTable table = new JTable(model);
        JScrollPane scroll = new JScrollPane(table);
        panel.add(scroll, BorderLayout.CENTER);

        DecimalFormat df = new DecimalFormat("#,###");
        String[] dates = {"30/04/2025", "01/05/2025", "02/05/2025", "03/05/2025", "04/05/2025", "05/05/2025", "06/05/2025"};
        for (String date : dates) {
            int cp = date.equals("30/04/2025") ? 72_000_000 : 0;
            int dt = date.equals("06/05/2025") ? 80_000_000 : 2_000_000 + new Random().nextInt(5_000_000);
            int ln = dt - cp;
            model.addRow(new Object[]{date, df.format(cp) + " ₫", df.format(dt) + " ₫", df.format(ln) + " ₫"});
        }

        return panel;
    }

    private JPanel createDoanhThuPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JTabbedPane subTabs = new JTabbedPane();

        subTabs.addTab("Theo năm", createTheoNamPanel());
        subTabs.addTab("Từng tháng trong năm", createTheoThangPanel());
        subTabs.addTab("Từng ngày trong tháng", createTungNgayTrongThangPanel());
        subTabs.addTab("Từ ngày đến ngày", new JPanel()); // placeholder

        panel.add(subTabs, BorderLayout.CENTER);
        return panel;
    }

    private JPanel createTheoNamPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JComboBox<Integer> cbFrom = new JComboBox<>(new Integer[]{2021, 2022, 2023, 2024, 2025});
        JComboBox<Integer> cbTo = new JComboBox<>(new Integer[]{2021, 2022, 2023, 2024, 2025});
        cbTo.setSelectedItem(2025);
        top.add(new JLabel("Từ năm:")); top.add(cbFrom);
        top.add(new JLabel("Đến năm:")); top.add(cbTo);
        top.add(new JButton("Thống kê"));
        top.add(new JButton("Làm mới"));
        panel.add(top, BorderLayout.NORTH);

        String[] cols = {"Năm", "Vốn", "Doanh thu", "Lợi nhuận"};
        DefaultTableModel model = new DefaultTableModel(cols, 0);
        JTable table = new JTable(model);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);

        int[] von = {400_000_000, 480_000_000, 550_000_000, 620_000_000, 700_000_000};
        int[] dt = {700_000_000, 820_000_000, 950_000_000, 1_050_000_000, 1_200_000_000};
        DecimalFormat df = new DecimalFormat("#,###");
        for (int i = 0; i < 5; i++) {
            int ln = dt[i] - von[i];
            model.addRow(new Object[]{
                    2021 + i,
                    df.format(von[i]) + " ₫",
                    df.format(dt[i]) + " ₫",
                    df.format(ln) + " ₫"
            });
        }

        return panel;
    }

    private JPanel createTheoThangPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));

        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        top.add(new JLabel("Chọn năm thống kê:"));
        JComboBox<Integer> cbYear = new JComboBox<>(new Integer[]{2021, 2022, 2023, 2024, 2025});
        cbYear.setSelectedItem(2025);
        top.add(cbYear);
        panel.add(top, BorderLayout.NORTH);

        String[] cols = {"Tháng", "Chi phí", "Doanh thu", "Lợi nhuận"};
        DefaultTableModel model = new DefaultTableModel(cols, 0);
        JTable table = new JTable(model);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);

        DecimalFormat df = new DecimalFormat("#,###");

        // ✅ Tạo dữ liệu giả cho cả 12 tháng
        Random rand = new Random();
        for (int i = 1; i <= 12; i++) {
            int chiPhi = 20_000_000 + rand.nextInt(20_000_000);  // 20–40 triệu
            int doanhThu = 30_000_000 + rand.nextInt(30_000_000); // 30–60 triệu
            int loiNhuan = doanhThu - chiPhi;

            model.addRow(new Object[]{
                    "Tháng " + i,
                    df.format(chiPhi) + " ₫",
                    df.format(doanhThu) + " ₫",
                    df.format(loiNhuan) + " ₫"
            });
        }

        return panel;
    }



    private JPanel createTungNgayTrongThangPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JComboBox<String> cbMonth = new JComboBox<>(new String[]{"January", "February", "March", "April", "May"});
        cbMonth.setSelectedItem("April");
        JComboBox<Integer> cbYear = new JComboBox<>(new Integer[]{2021, 2022, 2023, 2024, 2025});
        cbYear.setSelectedItem(2025);
        JButton btnThongKe = new JButton("Thống kê");

        top.add(new JLabel("Chọn tháng:")); top.add(cbMonth);
        top.add(new JLabel("Chọn năm:")); top.add(cbYear);
        top.add(btnThongKe);
        panel.add(top, BorderLayout.NORTH);

        String[] cols = {"Ngày", "Chi phí", "Doanh thu", "Lợi nhuận"};
        DefaultTableModel model = new DefaultTableModel(cols, 0);
        JTable table = new JTable(model);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);

        DecimalFormat df = new DecimalFormat("#,###");
        YearMonth ym = YearMonth.of(2025, 4);
        for (int d = 1; d <= ym.lengthOfMonth(); d++) {
            LocalDate date = ym.atDay(d);
            int cp = 3_000_000 + new Random().nextInt(5_000_000);
            int dt = 5_000_000 + new Random().nextInt(7_000_000);
            int ln = dt - cp;
            model.addRow(new Object[]{
                    date.toString(),
                    df.format(cp) + " ₫",
                    df.format(dt) + " ₫",
                    df.format(ln) + " ₫"
            });
        }

        return panel;
    }

    private JPanel createInfoBox(String title, String value) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY),
                title,
                TitledBorder.CENTER, TitledBorder.TOP));
        JLabel lbl = new JLabel(value, SwingConstants.CENTER);
        lbl.setFont(new Font("Arial", Font.BOLD, 22));
        panel.add(lbl, BorderLayout.CENTER);
        return panel;
    }
}
