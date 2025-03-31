package PACKAGE_NAME;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class OrderPanel extends JFrame {
    public OrderPanel() {
        setTitle("Highlands Coffee Management");
        setSize(900, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Menu Bar
        JMenuBar menuBar = new JMenuBar();
        JMenu tinhNang = new JMenu("Tính năng");
        JMenu exit = new JMenu("Exit");
        exit.addActionListener(e -> System.exit(0));
        menuBar.add(tinhNang);
        menuBar.add(exit);
        setJMenuBar(menuBar);

        // Left Panel - Thông tin hóa đơn và khách hàng


        JPanel billInfoPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        billInfoPanel.setBorder(BorderFactory.createTitledBorder("Thông tin hóa đơn"));
        billInfoPanel.add(new JLabel("ID Hóa đơn:"));
        billInfoPanel.add(new JTextField());
        billInfoPanel.add(new JLabel("Ngày:"));
        billInfoPanel.add(new JTextField());

        JPanel customerInfoPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        customerInfoPanel.setBorder(BorderFactory.createTitledBorder("Thông tin khách hàng"));
        customerInfoPanel.add(new JLabel("Tên:"));
        customerInfoPanel.add(new JTextField());
        customerInfoPanel.add(new JLabel("Số điện thoại:"));
        customerInfoPanel.add(new JTextField());
        customerInfoPanel.add(new JLabel("Địa chỉ:"));
        customerInfoPanel.add(new JTextField());
        customerInfoPanel.add(new JLabel("Điểm tích lũy:"));
        customerInfoPanel.add(new JTextField());

        JButton saveButton = new JButton("Lưu");
        JPanel saveButtonPanel = new JPanel();
        saveButtonPanel.add(saveButton);

        JPanel leftContainer = new JPanel(new BorderLayout());
        leftContainer.add(billInfoPanel, BorderLayout.NORTH);
        leftContainer.add(customerInfoPanel, BorderLayout.CENTER);
        leftContainer.add(saveButtonPanel, BorderLayout.SOUTH);

        JPanel leftPanel = new JPanel(new GridBagLayout()); // Dùng GridBagLayout để căn giữa
        GridBagConstraints gbc = new GridBagConstraints();
        // Chiếm nhiều hàng(gridheight) hoặc nhiều cột (gridWidth)
        // Căn chỉnh theo hướng (anchor)
        // Căng theo chiều ngang/dọc (fill)
        // Dãn khoảng cách (weightx,weighty)
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 1; // Để căn giữa theo chiều dọc
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        leftPanel.add(leftContainer, gbc);

        // Right Panel - Danh sách sản phẩm
        JPanel rightPanel = new JPanel(new BorderLayout());
        String[] columnNames = {"ID", "Tên", "Kích cỡ", "Số lượng", "Giá", "Thành tiền", "Ghi chú"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 1 );
        JTable table = new JTable(tableModel);
        JTableHeader header = table.getTableHeader();
        header.setBackground(Color.decode("#B4EBE6"));
        JScrollPane scrollPane = new JScrollPane(table);
        rightPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new GridLayout(1, 5));
        bottomPanel.add(new JLabel("Tổng: "));
        bottomPanel.add(new JTextField());
        bottomPanel.add(new JTextField());
        bottomPanel.add(new JLabel("Khuyến mãi: "));
        bottomPanel.add(new JTextField());
        rightPanel.add(bottomPanel, BorderLayout.SOUTH);

        // Add components to frame
        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    public static void main(String[] args) {
         new OrderPanel();
    }
}
