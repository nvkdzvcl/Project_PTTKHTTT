package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InventoryCheckUI extends JFrame {
    // header fields
    private JTextField txtSlipId;
    private JTextField txtEmployee;
    private JTextField txtDate;
    private JButton btnDetail;

    // detail panel (bảng + nút), ẩn ban đầu
    private JPanel detailPanel;
    private DefaultTableModel model;
    private JTable table;
    private JButton btnAdd;
    private JButton btnPrint;

    public InventoryCheckUI() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Phiếu Kiểm Kê Kho Hàng");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        // === HEADER PANEL ===
        JPanel header = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets  = new Insets(5,5,5,5);
        c.anchor  = GridBagConstraints.WEST;

        // Mã phiếu
        c.gridx = 0; c.gridy = 0;
        header.add(new JLabel("Mã phiếu:"), c);
        txtSlipId = new JTextField(12);
        c.gridx = 1;
        header.add(txtSlipId, c);

        // Ngày kiểm (mặc định là hôm nay)
        c.gridx = 2;
        header.add(new JLabel("Ngày kiểm:"), c);
        txtDate = new JTextField(12);
        txtDate.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        c.gridx = 3;
        header.add(txtDate, c);

        // Nhân viên
        c.gridx = 0; c.gridy = 1;
        header.add(new JLabel("Nhân viên:"), c);
        txtEmployee = new JTextField(12);
        c.gridx = 1;
        header.add(txtEmployee, c);

        // Nút Chi tiết phiếu
        btnDetail = new JButton("Chi tiết phiếu");
        c.gridx = 3; c.gridy = 1;
        header.add(btnDetail, c);

        // === DETAIL PANEL (ẩn) ===
        detailPanel = new JPanel(new BorderLayout(5,5));
        detailPanel.setVisible(false);

        // Bảng chi tiết
        String[] cols = { "Mã SP", "Tồn HT", "Thực tế", "Chênh lệch", "Ghi chú" };
        model = new DefaultTableModel(cols, 0) {
            // Cho phép chỉnh sửa cột Ghi chú
            @Override
            public boolean isCellEditable(int row, int col) {
                return col == 4;
            }
        };
        table = new JTable(model);
        detailPanel.add(new JScrollPane(table), BorderLayout.CENTER);

        // Nút THÊM và XUẤT PHIẾU
        JPanel pnlButtons = new JPanel();
        btnAdd   = new JButton("Thêm");
        btnPrint = new JButton("Xuất phiếu");
        pnlButtons.add(btnAdd);
        pnlButtons.add(btnPrint);
        detailPanel.add(pnlButtons, BorderLayout.SOUTH);

        // === LAYOUT TỔNG ===
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(header,      BorderLayout.NORTH);
        getContentPane().add(detailPanel, BorderLayout.CENTER);

        // === ACTIONS ===
        btnDetail.addActionListener(e -> {
            detailPanel.setVisible(true);
            // focus vào bảng
            table.requestFocusInWindow();
        });

        btnAdd.addActionListener(e -> showAddDialog());

        btnPrint.addActionListener(e -> printSlip());
    }

    private void showAddDialog() {
        JPanel p = new JPanel(new GridLayout(0,2,5,5));
        JTextField fCode     = new JTextField();
        JTextField fExpected = new JTextField();
        JTextField fActual   = new JTextField();
        JTextField fNote     = new JTextField();

        p.add(new JLabel("Mã sản phẩm:"));   p.add(fCode);
        p.add(new JLabel("Số lượng hệ thống:")); p.add(fExpected);
        p.add(new JLabel("Số lượng thực tế:"));   p.add(fActual);
        p.add(new JLabel("Ghi chú:"));            p.add(fNote);

        int option = JOptionPane.showConfirmDialog(
                this, p, "Thêm chi tiết kiểm kê", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE
        );
        if (option != JOptionPane.OK_OPTION) return;

        try {
            String code     = fCode.getText().trim();
            int expected    = Integer.parseInt(fExpected.getText().trim());
            int actual      = Integer.parseInt(fActual.getText().trim());
            String note     = fNote.getText().trim();
            int diff        = actual - expected;
            model.addRow(new Object[]{ code, expected, actual, diff, note });
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Vui lòng nhập đúng định dạng số!", "Lỗi định dạng", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void printSlip() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== PHIẾU KIỂM KÊ ===\n");
        sb.append("Mã phiếu: ").append(txtSlipId.getText()).append("\n");
        sb.append("Ngày kiểm: ").append(txtDate.getText()).append("\n");
        sb.append("Nhân viên: ").append(txtEmployee.getText()).append("\n\n");
        sb.append(String.format("%-10s %-10s %-10s %-10s %-10s\n",
                "MãSP","TồnHT","ThựcTế","ChênhLệch","GhiChú"));
        for (int i = 0; i < model.getRowCount(); i++) {
            sb.append(String.format("%-10s %-10s %-10s %-10s %-10s\n",
                    model.getValueAt(i,0),
                    model.getValueAt(i,1),
                    model.getValueAt(i,2),
                    model.getValueAt(i,3),
                    model.getValueAt(i,4)
            ));
        }
        JTextArea ta = new JTextArea(sb.toString());
        ta.setFont(new Font("Monospaced", Font.PLAIN, 12));
        ta.setEditable(false);
        JOptionPane.showMessageDialog(this, new JScrollPane(ta),
                "Phiếu kiểm kê", JOptionPane.PLAIN_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new InventoryCheckUI().setVisible(true);
        });
    }
}
