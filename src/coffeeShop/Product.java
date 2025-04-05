package PACKAGE_NAME.coffeeShop;

public abstract class Product {
    protected String idProduct, name, size;
    protected double price;

    public Product(String idProduct, String name, String size, double price) {
        this.idProduct = idProduct;
        this.name = name;
        this.size = size;
        this.price = price;
    }

    public String getIdProduct() {
        return idProduct;
    }

    public double getPrice() {
        return price;
    }

    public String getSize() {
        return size;
    }

    public String getName() {
        return name;
    }
}
