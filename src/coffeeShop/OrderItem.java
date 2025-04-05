package PACKAGE_NAME.coffeeShop;

public class OrderItem {
    private Product product;
    private int quantity;

    public OrderItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }
    public double priceOfOrder(Product product){
        return quantity*product.getPrice();
    }
}
