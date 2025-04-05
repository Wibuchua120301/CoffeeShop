package PACKAGE_NAME.coffeeShop;

public class Customer {
    private String name, numberPhone, address;
    private int loyatlyPoints;

    public Customer(String name, String numberPhone, String address, int loyatlyPoints) {
        this.name = name;
        this.numberPhone = numberPhone;
        this.address = address;
        this.loyatlyPoints = loyatlyPoints;
    }

    public String getName() {
        return name;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public int getLoyatlyPoints() {
        return loyatlyPoints;
    }

    public String getAddress() {
        return address;
    }

}
