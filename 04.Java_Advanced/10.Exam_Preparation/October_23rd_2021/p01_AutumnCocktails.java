package ExamPreparation_10.October_23rd_2021;

import java.util.*;
import java.util.stream.Collectors;

public class p01_AutumnCocktails {
    public static void main(String[] args) {

        Map<String, Integer> numberOfCocktails = new HashMap<>();
        numberOfCocktails.put("Apple Hinny", 0);
        numberOfCocktails.put("High Fashion", 0);
        numberOfCocktails.put("Pear Sour", 0);
        numberOfCocktails.put("The Harvest", 0);

        Scanner scanner = new Scanner(System.in);
        ArrayDeque<Integer> ingredientQueue = new ArrayDeque<>();
        Arrays.stream(scanner.nextLine().split(" ")).map(Integer::parseInt).forEach(ingredientQueue::offer);

        ArrayDeque<Integer> freshnessStack = new ArrayDeque<>();
        Arrays.stream(scanner.nextLine().split(" ")).map(Integer::parseInt).forEach(freshnessStack::push);

        while (!ingredientQueue.isEmpty() && !freshnessStack.isEmpty()) {
            int ingredient = ingredientQueue.poll();

            if (ingredient == 0) {
                continue;
            }

            int freshnessLevel = freshnessStack.pop();
            int totalFreshness = ingredient * freshnessLevel;
            int quantity;
            boolean madeCocktail = true;
            switch (totalFreshness) {
                case 150:
                    quantity = numberOfCocktails.get("Pear Sour") + 1;
                    numberOfCocktails.put("Pear Sour", quantity);
                    break;
                case 250:
                    quantity = numberOfCocktails.get("The Harvest") + 1;
                    numberOfCocktails.put("The Harvest", quantity);
                    break;
                case 300:
                    quantity = numberOfCocktails.get("Apple Hinny") + 1;
                    numberOfCocktails.put("Apple Hinny", quantity);
                    break;
                case 400:
                    quantity = numberOfCocktails.get("High Fashion") + 1;
                    numberOfCocktails.put("High Fashion", quantity);
                    break;
                default:
                    madeCocktail = false;
                    break;
            }
            if (!madeCocktail) {
                ingredient += 5;
                ingredientQueue.offer(ingredient);
            }
        }

        Map<String, Integer> filteredCocktail = numberOfCocktails.entrySet().stream().filter(e -> e.getValue() != 0)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        numberOfCocktails = new TreeMap<>(filteredCocktail);

        if (numberOfCocktails.size() == 4) {
            System.out.println("It's party time! The cocktails are ready!");
        } else {
            System.out.println("What a pity! You didn't manage to prepare all cocktails.");
        }

        if (!ingredientQueue.isEmpty()) {
            System.out.print("Ingredients left: ");
            System.out.println(ingredientQueue.stream().mapToInt(i -> i).sum());
        }

        numberOfCocktails.forEach((k, v) -> System.out.println("# " + k + " --> " + v));
    }
}
