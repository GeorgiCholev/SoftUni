package _2_Encapsulation.Exercise.p04_PizzaCalories;

import java.util.ArrayList;
import java.util.List;

public class Pizza {

    private String name;
    private Dough dough;
    private List<Topping> toppings;

    public Pizza(String name, int numberOfToppings) {
        setName(name);
        setToppings(numberOfToppings);
    }

    public void addTopping(Topping topping) {
        toppings.add(topping);
    }

    public double getOverallCalories() {
        double doughCalories = dough.calculateCalories();
        double toppingsCalories = toppings.stream().mapToDouble(Topping::calculateCalories).sum();
        return doughCalories + toppingsCalories;
    }

    public String getName() {
        return name;
    }

    public void setDough(Dough dough) {
        this.dough = dough;
    }

    @Override
    public String toString() {
        return name + " - " + String.format("%.2f", getOverallCalories());
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty() || name.length() > 15 || name.length() < 1) {
            throw new IllegalArgumentException("Pizza name should be between 1 and 15 symbols.");
        }
        this.name = name;
    }

    private void setToppings(int numberOfToppings) {
        if (numberOfToppings < 0 || numberOfToppings > 10) {
            throw new IllegalArgumentException("Number of toppings should be in range [0..10].");
        }
        this.toppings = new ArrayList<>();
    }
}
