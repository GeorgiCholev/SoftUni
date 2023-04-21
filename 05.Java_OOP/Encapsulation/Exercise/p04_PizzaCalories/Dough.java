package _2_Encapsulation.Exercise.p04_PizzaCalories;

import java.util.Arrays;

public class Dough {

    private String flourType;
    private String bakingTechnique;
    private double weight;

    public Dough(String flourType, String bakingTechnique, double weight) {
        setFlourType(flourType);
        setBakingTechnique(bakingTechnique);
        setWeight(weight);
    }

    public double calculateCalories() {

        double flourTypeModifier;

        switch (flourType) {
            case "White":
                flourTypeModifier = 1.5;
                break;
            default: // Wholegrain
                flourTypeModifier = 1.0;
                break;
        }

        double bakingTechniqueModifier;

        switch (bakingTechnique) {
            case "Crispy":
                bakingTechniqueModifier = 0.9;
                break;
            case "Chewy":
                bakingTechniqueModifier = 1.1;
                break;
            default: // Homemade
                bakingTechniqueModifier = 1.0;
                break;
        }

        return flourTypeModifier * bakingTechniqueModifier * 2 * weight;
    }

    private void setFlourType(String flourType) {
        validateTypeOfDough(flourType, "White", "Wholegrain");

        this.flourType = flourType;
    }

    private void setBakingTechnique(String bakingTechnique) {
        validateTypeOfDough(bakingTechnique, "Crispy", "Chewy", "Homemade");

        this.bakingTechnique = bakingTechnique;
    }

    private void setWeight(double weight) {
        if (weight < 1 || weight > 200) {
            throw new IllegalArgumentException("Dough weight should be in the range [1..200].");
        }
        this.weight = weight;
    }

    private void validateTypeOfDough(String parameterToValidate, String... possibleFields) {
        boolean isNotValid = Arrays.stream(possibleFields)
                .noneMatch(field -> field.equals(parameterToValidate));
        if (isNotValid) {
            throw new IllegalArgumentException("Invalid type of dough.");
        }
    }
}
