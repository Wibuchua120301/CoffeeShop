package PACKAGE_NAME;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class CoffeeManagement extends JFrame {
    private JMenu tinhNang;
    private JMenuItem exit;
    private JTable customerTable, invoiceTable, orderDetailsTable;
    private JTableHeader header,header2,header3;
    private JTextField txtTongDoanhThu;
    private String[] invoiceColumns, orderDetailsColumns;
    public CoffeeManagement() {
        setTitle("Highlands Coffee Management");
        setSize(900, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Menu Bar
        JMenuBar menuBar = new JMenuBar();
        JMenu tinhNang = new JMenu("Tính năng");
        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(e -> System.exit(0));
        menuBar.add(tinhNang);
        menuBar.add(exit);
        setJMenuBar(menuBar);

        // Left Panel - Danh sách khách hàng và Tổng doanh thu
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setPreferredSize(new Dimension(250, getHeight()));

        JPanel panelKhachHang = new JPanel(new BorderLayout());
        JPanel panelTongTien = new JPanel(new BorderLayout());

        String[] customerColumns = {"Tên", "Số điện thoại"};
        JTable customerTable = new JTable(new DefaultTableModel(customerColumns, 0));
        JTableHeader header = customerTable.getTableHeader();
        header.setBackground(Color.decode("#B4EBE6"));
        panelKhachHang.add(new JScrollPane(customerTable), BorderLayout.CENTER);
        panelKhachHang.setBorder(new TitledBorder("Danh sách khách hàng"));

        panelTongTien.setBorder(new TitledBorder("Tổng doanh thu"));
        JTextField txtTongDoanhThu = new JTextField();
        txtTongDoanhThu.setEditable(false);
        panelTongTien.add(txtTongDoanhThu, BorderLayout.CENTER);
        panelTongTien.setPreferredSize(new Dimension(leftPanel.getWidth(), 60));

        leftPanel.add(panelKhachHang, BorderLayout.CENTER);
        leftPanel.add(panelTongTien, BorderLayout.SOUTH);

        // Center Panel - Danh sách hóa đơn
        JPanel centerPanel = new JPanel(new BorderLayout());
        String[] invoiceColumns = {"ID", "Ngày", "Tổng"};
        JTable invoiceTable = new JTable(new DefaultTableModel(invoiceColumns, 0));
        JTableHeader header2 = invoiceTable.getTableHeader();
        header2.setBackground(Color.decode("#B4EBE6"));
        centerPanel.add(new JScrollPane(invoiceTable), BorderLayout.CENTER);
        centerPanel.setBorder(BorderFactory.createTitledBorder("Danh sách hóa đơn"));

        // Bottom Panel - Chi tiết hóa đơn
        JPanel bottomPanel = new JPanel(new BorderLayout());
        String[] orderDetailsColumns = {"ID", "Tên", "Kích cỡ", "SL", "Giá", "Ghi chú"};
        JTable orderDetailsTable = new JTable(new DefaultTableModel(orderDetailsColumns, 0));
        JTableHeader header3 = orderDetailsTable.getTableHeader();
        header3.setBackground(Color.decode("#B4EBE6"));
        bottomPanel.add(new JScrollPane(orderDetailsTable), BorderLayout.CENTER);
        bottomPanel.setBorder(BorderFactory.createTitledBorder("Chi tiết hóa đơn"));

        // Right Panel - Chia 2 khu vực danh sách hóa đơn và chi tiết hóa đơn
        JPanel rightPanel = new JPanel(new GridLayout(2, 1));
        rightPanel.add(centerPanel);
        rightPanel.add(bottomPanel);

        // Add panels to frame
        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    public static void main(String[] args) {
        new CoffeeManagement();
    }
}

