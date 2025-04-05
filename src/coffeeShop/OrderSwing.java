package PACKAGE_NAME.coffeeShop;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class OrderSwing extends JFrame {
    private Store store;
    private CoffeeManagementSwing coffeeManagement;
    private CardLayout cardLayout;
    private JMenuBar menuBar;
    private JMenu tinhNang;
    private JMenuItem action, home;
    private JPanel billInfoPanel, customerInfoPanel, saveButtonPanel, leftContainer, leftPanel, rightPanel, bottomPanel, orderPanel, main;
    private JButton saveButton, exit;
    private DefaultTableModel tableModel;
    private JTable table;
    private JTableHeader header;
    private JScrollPane scrollPane;
    private JTextField txtBill, txtDate, txtName, txtPhoneNumber, txtAddress, txtLoyatlyPoints, txtTotal, txtPromotion, txtAfterPromotion;

    public OrderSwing(Store store) {
        this.store = store;
        createData();
        initializeUI();
        addPhoneNumberListener();
        addSaveButtonListener();

    }

    private void initializeUI() {
        setTitle("Quản lý quán cà phê");
        setSize(700, 530);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setResizable(false);

        // CardLayout setup
        cardLayout = new CardLayout();
        main = new JPanel(cardLayout);
        orderPanel = new JPanel(new BorderLayout());
        coffeeManagement = new CoffeeManagementSwing();
        main.add(orderPanel, "orderPanel");
        main.add(coffeeManagement, "managementPanel");

        // Menu Bar
        menuBar = new JMenuBar();
        tinhNang = new JMenu("Tính năng");
        action = new JMenuItem("Quản lý");
        home = new JMenuItem("Hóa đơn");
        tinhNang.add(action);
        tinhNang.add(home);

        action.addActionListener(e -> cardLayout.show(main, "managementPanel"));
        home.addActionListener(e -> cardLayout.show(main, "orderPanel"));

        exit = new JButton("Thoát");
        exit.setBorder(null);
        exit.setOpaque(false);
        exit.setContentAreaFilled(false);
        exit.setBorderPainted(false);
        exit.addActionListener(e -> System.exit(0));
        menuBar.add(tinhNang);
        menuBar.add(exit);
        setJMenuBar(menuBar);

        // Left Panel - Thông tin hóa đơn và khách hàng
        billInfoPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        billInfoPanel.setBorder(BorderFactory.createTitledBorder("Thông tin hóa đơn"));

        billInfoPanel.add(new JLabel("ID Hóa đơn:"));
        txtBill = new JTextField(generateBillID());
        txtBill.setEditable(false);
        billInfoPanel.add(txtBill);

        billInfoPanel.add(new JLabel("Ngày:"));
        txtDate = new JTextField(getCurrentDate());
        txtDate.setEditable(false);
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
        txtLoyatlyPoints = new JTextField("0");
        txtLoyatlyPoints.setEditable(false);
        customerInfoPanel.add(txtLoyatlyPoints);

        saveButton = new JButton("Lưu");
        saveButtonPanel = new JPanel();
        saveButtonPanel.add(saveButton);

        leftContainer = new JPanel(new BorderLayout());
        leftContainer.add(billInfoPanel, BorderLayout.NORTH);
        leftContainer.add(customerInfoPanel, BorderLayout.CENTER);
        leftContainer.add(saveButtonPanel, BorderLayout.SOUTH);

        leftPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 1;
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
        txtTotal = new JTextField("0");
        txtTotal.setEditable(false);
        txtAfterPromotion = new JTextField("0");
        txtAfterPromotion.setEditable(false);
        bottomPanel.add(txtTotal);
        bottomPanel.add(txtAfterPromotion);

        bottomPanel.add(new JLabel("Khuyến mãi: "));
        txtPromotion = new JTextField("0");
        txtPromotion.setEditable(false);
        bottomPanel.add(txtPromotion);
        rightPanel.add(bottomPanel, BorderLayout.SOUTH);

        orderPanel.add(leftPanel, BorderLayout.WEST);
        orderPanel.add(rightPanel, BorderLayout.CENTER);
        add(main);
        tableModel.addTableModelListener(e -> {
            if (!isUpdatingTable) { // Chỉ gọi khi không phải đang cập nhật từ calculateTotalPrice
                calculateTotalPrice();
            }
        });
        setVisible(true);
    }

    private void addPhoneNumberListener() {
        txtPhoneNumber.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String phone = txtPhoneNumber.getText().trim();
                Customer customer = findCustomerByPhone(phone);
                if (customer != null) {
                    System.out.println("1");
                    txtName.setText(customer.getName());
                    txtAddress.setText(customer.getAddress());
                    txtLoyatlyPoints.setText(String.valueOf(customer.getLoyatlyPoints()));
                    txtName.setEditable(false); // Không cho chỉnh sửa nếu đã tồn tại
                    txtAddress.setEditable(false);
                } else {
                    System.out.println("2");
                    txtName.setText("");
                    txtAddress.setText("");
                    txtLoyatlyPoints.setText("0");
                    txtName.setEditable(true); // Cho phép nhập nếu là khách mới
                    txtAddress.setEditable(true);
                }
            }
        });
    }



    private void updateManagementPanel() {
        DefaultTableModel customerModel = (DefaultTableModel) coffeeManagement.customerTable.getModel();
        customerModel.setRowCount(0); // Xóa dữ liệu cũ
        for (Customer customer : store.getCustomerList()) {
            customerModel.addRow(new Object[]{customer.getName(), customer.getNumberPhone()});
        }
    }

    private Customer findCustomerByPhone(String phone) {
        for (Customer customer : store.getCustomerList()) {
            if (customer.getNumberPhone().equals(phone)) {
                return customer;
            }
        }
        return null;
    }

    private void addSaveButtonListener() {
        saveButton.addActionListener(e -> {
            String phone = txtPhoneNumber.getText().trim();
            String name = txtName.getText().trim();
            String address = txtAddress.getText().trim();

            if (phone.isEmpty() || name.isEmpty() || address.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin khách hàng!");
                return;
            }

            // Tính toán trước khi lưu
            calculateTotalPrice();

            Customer customer = findCustomerByPhone(phone);
            if (customer == null) {
                customer = new Customer(phone, name, address, 0);
                store.addCustomer(customer);
                JOptionPane.showMessageDialog(this, "Đã lưu thông tin khách hàng mới!");
                System.out.println("Danh sách khách hàng trong Store:");
                for (Customer c : store.getCustomerList()) {
                    System.out.println(c.getNumberPhone() + " - " + c.getName());
                }
            }

            updateManagementPanel();
        });
    }

    public String generateBillID() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
    }

    public String getCurrentDate() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
    private boolean isUpdatingTable = false;

    private void calculateTotalPrice() {
        if (isUpdatingTable) return; // Ngăn gọi lại khi đang cập nhật

        isUpdatingTable = true; // Bắt đầu cập nhật

        double total = 0.0;
        int rowCount = tableModel.getRowCount();

        for (int i = 0; i < rowCount; i++) {
            try {
                Object quantityObj = tableModel.getValueAt(i, 3);
                Object priceObj = tableModel.getValueAt(i, 4);

                if (quantityObj != null && priceObj != null) {
                    int quantity = Integer.parseInt(quantityObj.toString());
                    double price = Double.parseDouble(priceObj.toString());
                    double lineTotal = quantity * price;

                    tableModel.setValueAt(String.format("%.2f", lineTotal), i, 5);
                    total += lineTotal;
                } else {
                    tableModel.setValueAt("0.00", i, 5);
                }
            } catch (NumberFormatException e) {
                tableModel.setValueAt("0.00", i, 5);
                continue;
            }
        }

        txtTotal.setText(String.format("%.2f", total));

        double promotion = 0.0;
        if (total > 100000) {
            promotion = total * 0.1;
        }
        txtPromotion.setText(String.format("%.2f", promotion));

        double afterPromotion = total - promotion;
        txtAfterPromotion.setText(String.format("%.2f", afterPromotion));

        isUpdatingTable = false; // Kết thúc cập nhật
    }

    public static void main(String[] args) {
        Store store = new Store();

        new OrderSwing(store);
    }
    private void createData() {
        MilkTea m1 = new MilkTea("01", "Trà Sửa Dâu", "M", 20, 0, 0);
        MilkTea m2 = new MilkTea("01", "Trà Sửa Dâu", "L", 18, 0, 0);
        MatchaLatte ma1 = new MatchaLatte("02", "Sửa gấu Mathch", "M", 25, "");
        MatchaLatte ma2 = new MatchaLatte("02", "Sửa gấu Mathch", "L", 22, "");
        CheeseCake c1 = new CheeseCake("03", "Bánh phô mai", "M", 35, "", false);
        CheeseCake c2 = new CheeseCake("03", "Bánh phô mai", "L", 30, "", false);
        PanCake p1 = new PanCake("04", "Bánh kép", "M", 40, false, false);
        PanCake p2 = new PanCake("04", "Bánh kép", "L", 35, false, false);
        store.addProducts(m1);
        store.addProducts(m2);
        store.addProducts(ma1);
        store.addProducts(ma2);
        store.addProducts(c1);
        store.addProducts(c2);
        store.addProducts(p1);
        store.addProducts(p2);
        Customer cus1 = new Customer("1", "0", "Tp HCM", 0);
        Customer cus2 = new Customer("2", "0372684976", "Tp HCM", 0);
        Customer cus3 = new Customer("3", "0372278941", "Tp HCM", 0);
        Customer cus4 = new Customer("4", "0372572893", "Tp HCM", 0);
        Customer cus5 = new Customer("5", "0372297492", "Tp HCM", 0);
        store.addCustomers(cus1);
        store.addCustomers(cus2);
        store.addCustomers(cus3);
        store.addCustomers(cus4);
        store.addCustomers(cus5);
    }
}