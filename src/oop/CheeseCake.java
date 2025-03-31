package PACKAGE_NAME.oop;

public class CheeseCake extends Product {
    private String flavor;
    private boolean hasTopping;

    public CheeseCake(String idProduct, String name, String size, double price, String flavor, boolean hasTopping) {
        super(idProduct, name, size, price);
        this.flavor = flavor;
        this.hasTopping = hasTopping;
    }
}
