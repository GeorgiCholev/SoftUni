package restaurant.entities.drinks;

import restaurant.entities.drinks.interfaces.Beverages;

import static restaurant.common.ExceptionMessages.*;

public class BaseBeverage implements Beverages {

    private String name;
    private int counter;
    private double price;
    private String brand;

    protected BaseBeverage(String name, int counter, double price, String brand) {
        setName(name);
        setCounter(counter);
        setPrice(price);
        setBrand(brand);
    }

    @Override
    public String getName() {
        return name;
    }

    private void setName(String name) {
        nullOrEmptyThrows(name, INVALID_NAME);
        this.name = name;
    }

    @Override
    public int getCounter() {
        return counter;
    }

    private void setCounter(int counter) {
        zeroOrLessThrows(counter, INVALID_COUNTER);
        this.counter = counter;
    }

    @Override
    public double getPrice() {
        return price;
    }

    private void setPrice(double price) {
        zeroOrLessThrows(price, INVALID_PRICE);
        this.price = price;
    }

    @Override
    public String getBrand() {
        return brand;
    }

    private void setBrand(String brand) {
        nullOrEmptyThrows(brand, INVALID_BRAND);
        this.brand = brand;
    }

    private  void nullOrEmptyThrows(String arg, String excMessage) {
        if (arg == null || arg.trim().isEmpty()) {
            throw new IllegalArgumentException(excMessage);
        }
    }

    private void zeroOrLessThrows(double arg, String excMessage) {
        if (arg <= 0) {
            throw new IllegalArgumentException(excMessage);
        }
    }
}
