package PACKAGE_NAME.coffeeShop;

public class PanCake extends Product {
    private boolean hasSyrup, hasButter;

    public PanCake(String idProduct, String name, String size, double price, boolean hasSyrup, boolean hasButter) {
        super(idProduct, name, size, price);
        this.hasSyrup = hasSyrup;
        this.hasButter = hasButter;
    }
}
