package Maps_8.Exercise;

import java.util.*;

public class p03_Farming {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String[] items = input.split("\\s+");
        Map<String, Integer> quantityOfQualityItems = new HashMap<>();
        Map<String, Integer> quantityOfInferiorItems = new LinkedHashMap<>();
        for (int i = 0; i < items.length; i++) {
            int quantity = Integer.parseInt(items[i]);
            i++;
            String item = items[i].toLowerCase();
            if (item.equals("shards") || item.equals("fragments") || item.equals("motes")) {
                addItem(quantity, item, quantityOfQualityItems);
                if (quantityOfQualityItems.get(item) >= 250) {
                    quantityOfQualityItems.put(item, quantityOfQualityItems.get(item) - 250);
                    printObtainedItem(item);
                    break;
                }
            } else {
                addItem(quantity, item, quantityOfInferiorItems);
            }
        }
        printQualityItems(quantityOfQualityItems, "shards");
        printQualityItems(quantityOfQualityItems, "fragments");
        printQualityItems(quantityOfQualityItems, "motes");

        quantityOfInferiorItems.forEach((key, value) -> System.out.printf("%s: %d%n", key, value));

    }

    private static void printQualityItems(Map<String, Integer> quantityOfQualityItems, String item) {
        if (quantityOfQualityItems.containsKey(item)) {
            System.out.println(item + ": " + quantityOfQualityItems.get(item));
        } else {
            System.out.println(item + ": 0");
        }
    }

    private static void printObtainedItem(String item) {
        switch (item) {
            case "shards":
                System.out.println("Shadowmourne obtained!");
                break;
            case "fragments":
                System.out.println("Valanyr obtained!");
                break;
            case "motes":
                System.out.println("Dragonwrath obtained!");
                break;
        }
    }

    private static void addItem(int quantity, String item, Map<String, Integer> map) {
      map.putIfAbsent(item, 0);
        map.put(item, map.get(item) + quantity);
    }


}

