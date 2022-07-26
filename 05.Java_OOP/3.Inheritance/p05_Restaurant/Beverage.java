package _3_Inheritance.p05_Restaurant;

import java.math.BigDecimal;

public class Beverage extends Product {

    private double milliliters;

    public Beverage(String name, BigDecimal price, double millilitres) {
        super(name, price);
        this.milliliters = millilitres;
    }

    public double getMilliliters() {
        return milliliters;
    }
}
