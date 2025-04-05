package PACKAGE_NAME.coffeeShop;

import java.util.ArrayList;
import java.util.List;

public class Store {
    private List<Order> orderList;
    private List<Product> productList;
    private List<Customer> customerList;

    public Store() {
        this.orderList = new ArrayList<>();
        this.productList = new ArrayList<>();
        this.customerList = new ArrayList<>();
//        initializeMockCustomers(); // Thêm dữ liệu giả lập
    }
    // Phương thức lấy danh sách khách hàng
    public List<Customer> getCustomerList() {
        return customerList;
    }

    // Thêm khách hàng mới vào danh sách
    public void addCustomer(Customer customer) {
        customerList.add(customer);
    }

    // Tạo khách hàng giả lập để kiểm tra
    private void initializeMockCustomers() {
        customerList.add(new Customer("0123456789", "Nguyễn Văn A", "Hà Nội", 50));
        customerList.add(new Customer("0987654321", "Trần Thị B", "TP. HCM", 30));
    }

    public void addProducts(Product m) {
        productList.add(m);
    }

    public void addCustomers(Customer cus) {
        customerList.add(cus);
    }
}
