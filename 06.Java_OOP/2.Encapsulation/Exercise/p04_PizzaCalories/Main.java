package _2_Encapsulation.Exercise.p04_PizzaCalories;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //Pizza {pizzaName} {numberOfToppings}
        String[] pizzaInfo = scanner.nextLine().split(" ");
        String pizzaName = pizzaInfo[1];
        int numberOfToppings = Integer.parseInt(pizzaInfo[2]);
        Pizza pizza;
        try {
            pizza = new Pizza(pizzaName, numberOfToppings);

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }

        //Dough {flourType} {bakingTechnique} {weightInGrams}
        String[] doughInfo = scanner.nextLine().split(" ");
        String flourType = doughInfo[1];
        String bakingTechnique = doughInfo[2];
        double doughWeight = Double.parseDouble(doughInfo[3]);
        Dough dough;
        try {
            dough = new Dough(flourType, bakingTechnique, doughWeight);
            pizza.setDough(dough);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }

        //Topping {toppingType} {weightInGrams}
        String input = scanner.nextLine();
        while (!"END".equals(input)) {
            String[] toppingsInfo = input.split(" ");
            String toppingType = toppingsInfo[1];
            double toppingWeight = Double.parseDouble(toppingsInfo[2]);
            Topping topping;
            try {
                topping = new Topping(toppingType, toppingWeight);
                pizza.addTopping(topping);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                return;
            }
            input = scanner.nextLine();
        }

        System.out.println(pizza);
    }
}
