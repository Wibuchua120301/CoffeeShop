package PACKAGE_NAME.oop;

public abstract class Product {
    protected String idProduct, name, size;
    protected double price;

    public Product(String idProduct, String name, String size, double price) {
        this.idProduct = idProduct;
        this.name = name;
        this.size = size;
        this.price = price;
    }
}
