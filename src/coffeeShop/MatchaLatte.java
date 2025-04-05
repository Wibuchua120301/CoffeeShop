package PACKAGE_NAME.coffeeShop;

public class MatchaLatte extends Product {
    private String milkType;

    public MatchaLatte(String idProduct, String name, String size, double price, String milkType) {
        super(idProduct, name, size, price);
        this.milkType = milkType;
    }

}
