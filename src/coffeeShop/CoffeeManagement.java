package PACKAGE_NAME.coffeeShop;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class CoffeeManagement extends JPanel {
    private JPanel leftPanel, panelKhachHang, panelTongTien, centerPanel, bottomPanel, rightPanel;
    private JTable customerTable, invoiceTable, orderDetailsTable;
    private JTableHeader header, header2, header3;
    private JTextField txtTongDoanhThu;

    public CoffeeManagement() {
        setSize(900, 500);
        setLayout(new BorderLayout());

        // Left Panel - Danh sách khách hàng và Tổng doanh thu
        leftPanel = new JPanel(new BorderLayout());
        leftPanel.setPreferredSize(new Dimension(250, getHeight()));

        panelKhachHang = new JPanel(new BorderLayout());
        panelTongTien = new JPanel(new BorderLayout());

        String[] customerColumns = {"Tên", "Số điện thoại"};
        customerTable = new JTable(new DefaultTableModel(customerColumns, 0));
        header = customerTable.getTableHeader();
        header.setBackground(Color.decode("#B4EBE6"));
        panelKhachHang.add(new JScrollPane(customerTable), BorderLayout.CENTER);
        panelKhachHang.setBorder(new TitledBorder("Danh sách khách hàng"));

        panelTongTien.setBorder(new TitledBorder("Tổng doanh thu"));
        txtTongDoanhThu = new JTextField();
        txtTongDoanhThu.setEditable(false);
        panelTongTien.add(txtTongDoanhThu, BorderLayout.CENTER);
        panelTongTien.setPreferredSize(new Dimension(leftPanel.getWidth(), 60));

        leftPanel.add(panelKhachHang, BorderLayout.CENTER);
        leftPanel.add(panelTongTien, BorderLayout.SOUTH);

        // Center Panel - Danh sách hóa đơn
        centerPanel = new JPanel(new BorderLayout());
        String[] invoiceColumns = {"ID", "Ngày", "Tổng"};
        invoiceTable = new JTable(new DefaultTableModel(invoiceColumns, 0));
        header2 = invoiceTable.getTableHeader();
        header2.setBackground(Color.decode("#B4EBE6"));
        centerPanel.add(new JScrollPane(invoiceTable), BorderLayout.CENTER);
        centerPanel.setBorder(BorderFactory.createTitledBorder("Danh sách hóa đơn"));

        // Bottom Panel - Chi tiết hóa đơn
        bottomPanel = new JPanel(new BorderLayout());
        String[] orderDetailsColumns = {"ID", "Tên", "Kích cỡ", "SL", "Giá", "Ghi chú"};
        orderDetailsTable = new JTable(new DefaultTableModel(orderDetailsColumns, 0));
        header3 = orderDetailsTable.getTableHeader();
        header3.setBackground(Color.decode("#B4EBE6"));
        bottomPanel.add(new JScrollPane(orderDetailsTable), BorderLayout.CENTER);
        bottomPanel.setBorder(BorderFactory.createTitledBorder("Chi tiết hóa đơn"));

        // Right Panel - Chia 2 khu vực danh sách hóa đơn và chi tiết hóa đơn
        rightPanel = new JPanel(new GridLayout(2, 1));
        rightPanel.add(centerPanel);
        rightPanel.add(bottomPanel);

        // Add panels to frame
        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);
        setVisible(true);
    }

}
