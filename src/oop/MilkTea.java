package PACKAGE_NAME.oop;

import java.util.List;

public class MilkTea extends Product {
    private List<String> stringList;
    private int sugarLevel, iceLevel;

    public MilkTea(String idProduct, String name, String size, double price, int sugarLevel, int iceLevel) {
        super(idProduct, name, size, price);
        this.sugarLevel = sugarLevel;
        this.iceLevel = iceLevel;
    }
}
