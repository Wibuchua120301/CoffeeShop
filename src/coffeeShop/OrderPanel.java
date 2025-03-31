package PACKAGE_NAME.coffeeShop;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class OrderPanel extends JFrame {
    private CoffeeManagement coffeeManagement;
    private CardLayout cardLayout;
    private JMenuBar menuBar;
    private JMenu tinhNang;
    private JMenuItem action, home, exit;
    private JPanel billInfoPanel, customerInfoPanel, saveButtonPanel, leftContainer, leftPanel, rightPanel, bottomPanel, orderPanel, main;
    private JButton saveButton;
    private DefaultTableModel tableModel;
    private JTable table;
    private JTableHeader header;
    private JScrollPane scrollPane;
    private JTextField txtBill, txtDate, txtName, txtPhoneNumber, txtAddress, txtLoyatlyPoints, txtTotal, txtPromotion;

    public OrderPanel() {
        setTitle("Quản lý quán cà phê");
        setSize(700, 530);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // cardLayout
        cardLayout = new CardLayout();
        main = new JPanel(cardLayout);
        orderPanel = new JPanel();
        coffeeManagement = new CoffeeManagement();
        main.add(orderPanel, "orderPanel");
        main.add(coffeeManagement, "managementPanel");

        // Menu Bar
        menuBar = new JMenuBar();
        tinhNang = new JMenu("Tính năng");
        action = new JMenuItem("Quản lý");
        home = new JMenuItem("Hóa đơn");
        tinhNang.add(action);
        tinhNang.add(home);
        action.addActionListener(e -> {
            cardLayout.show(main, "managementPanel");
        });
        home.addActionListener(e -> {
            cardLayout.show(main, "orderPanel");
        });
        exit = new JMenuItem("Thoát");
        exit.addActionListener(e -> System.exit(0));
        menuBar.add(tinhNang);
        menuBar.add(exit);
        setJMenuBar(menuBar);

        // Left Panel - Thông tin hóa đơn và khách hàng
        billInfoPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        billInfoPanel.setBorder(BorderFactory.createTitledBorder("Thông tin hóa đơn"));
        billInfoPanel.add(new JLabel("ID Hóa đơn:"));
        txtBill = new JTextField();
        billInfoPanel.add(txtBill);
        billInfoPanel.add(new JLabel("Ngày:"));
        txtDate = new JTextField();
        billInfoPanel.add(txtDate);

        customerInfoPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        customerInfoPanel.setBorder(BorderFactory.createTitledBorder("Thông tin khách hàng"));
        customerInfoPanel.add(new JLabel("Tên:"));
        txtName = new JTextField();
        customerInfoPanel.add(txtName);
        customerInfoPanel.add(new JLabel("Số điện thoại:"));
        txtPhoneNumber = new JTextField();
        customerInfoPanel.add(txtPhoneNumber);
        customerInfoPanel.add(new JLabel("Địa chỉ:"));
        txtAddress = new JTextField();
        customerInfoPanel.add(txtAddress);
        customerInfoPanel.add(new JLabel("Điểm tích lũy:"));
        txtLoyatlyPoints = new JTextField();
        customerInfoPanel.add(txtLoyatlyPoints);

        saveButton = new JButton("Lưu");
        saveButtonPanel = new JPanel();
        saveButtonPanel.add(saveButton);

        leftContainer = new JPanel(new BorderLayout());
        leftContainer.add(billInfoPanel, BorderLayout.NORTH);
        leftContainer.add(customerInfoPanel, BorderLayout.CENTER);
        leftContainer.add(saveButtonPanel, BorderLayout.SOUTH);

        leftPanel = new JPanel(new GridBagLayout()); // Dùng GridBagLayout để căn giữa
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
        rightPanel = new JPanel(new BorderLayout());
        String[] columnNames = {"ID", "Tên", "Kích cỡ", "Số lượng", "Giá", "Thành tiền", "Ghi chú"};
        tableModel = new DefaultTableModel(columnNames, 1);
        table = new JTable(tableModel);
        header = table.getTableHeader();
        header.setBackground(Color.decode("#B4EBE6"));
        scrollPane = new JScrollPane(table);
        rightPanel.add(scrollPane, BorderLayout.CENTER);

        bottomPanel = new JPanel(new GridLayout(1, 5));
        bottomPanel.add(new JLabel("Tổng: "));
        txtTotal = new JTextField();
        bottomPanel.add(txtTotal);
        bottomPanel.add(new JLabel("Khuyến mãi: "));
        txtPromotion = new JTextField();
        bottomPanel.add(txtPromotion);
        rightPanel.add(bottomPanel, BorderLayout.SOUTH);

        // Add components to frame
        orderPanel.add(leftPanel, BorderLayout.WEST);
        orderPanel.add(rightPanel, BorderLayout.CENTER);
        add(main);
        setVisible(true);
    }

    public static void main(String[] args) {
        new OrderPanel();
    }
}
