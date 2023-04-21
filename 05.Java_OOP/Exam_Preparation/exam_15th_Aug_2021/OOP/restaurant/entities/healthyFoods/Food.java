package restaurant.entities.healthyFoods;

import restaurant.entities.healthyFoods.interfaces.HealthyFood;

import static restaurant.common.ExceptionMessages.*;

public class Food implements HealthyFood {

    private String name;
    private double portion;
    private double price;

    protected Food(String name, double portion, double price) {
        setName(name);
        setPortion(portion);
        setPrice(price);
    }

    @Override
    public String getName() {
        return name;
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException(INVALID_NAME);
        }
        this.name = name;
    }

    @Override
    public double getPortion() {
        return portion;
    }

    private void setPortion(double portion) {
        zeroOrLessThrows(portion, INVALID_PORTION);
        this.portion = portion;
    }

    @Override
    public double getPrice() {
        return price;
    }

    private void setPrice(double price) {
        zeroOrLessThrows(price, INVALID_PRICE);
        this.price = price;
    }

    private void zeroOrLessThrows(double arg, String excMessage) {
        if (arg <= 0) {
            throw new IllegalArgumentException(excMessage);
        }
    }
}
