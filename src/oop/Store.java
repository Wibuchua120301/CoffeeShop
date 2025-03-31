package PACKAGE_NAME.oop;

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
    }


}
