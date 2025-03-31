package PACKAGE_NAME.oop;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {
    private String idOrder;
    private Date orderDate;
    private Customer customer;
    private double promotion, totalPrice;
    private List<OrderItem> orderItemList;

    public Order(String idOrder, Date orderDate, Customer customer, double promotion, double totalPrice) {
        this.orderItemList = new ArrayList<>();
        this.idOrder = idOrder;
        this.orderDate = orderDate;
        this.customer = customer;
        this.promotion = promotion;
        this.totalPrice = totalPrice;
    }
}
