package _2_Encapsulation.Exercise.p04_PizzaCalories;

import java.util.Arrays;

public class Topping {

    private String toppingType;
    private double weight;

    public Topping(String toppingType, double weight) {
        setToppingType(toppingType);
        setWeight(weight);
    }

    public double calculateCalories() {
        switch (toppingType) {
            case "Meat":
                return 1.2 * 2 * weight;
            case "Veggies":
                return 0.8 * 2 * weight;
            case "Cheese":
                return 1.1 * 2 * weight;
            default: // Sauce
                return 0.9 * 2 * weight;
        }
    }

    private void setToppingType(String toppingType) {
        validateTopping(toppingType, "Meat", "Veggies", "Cheese", "Sauce");
        this.toppingType = toppingType;
    }

    private void setWeight(double weight) {
        if (weight < 1 || weight > 50) {
            throw new IllegalArgumentException(toppingType + " weight should be in the range [1..50].");
        }
        this.weight = weight;
    }

    private void validateTopping(String parameterToValidate, String... possibleFields) {
        boolean isNotValid = Arrays.stream(possibleFields)
                .noneMatch(field -> field.equals(parameterToValidate));
        if (isNotValid) {
            throw new IllegalArgumentException("Cannot place " + parameterToValidate + " on top of your pizza.");
        }
    }
}
