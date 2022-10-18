package _6_DynamicProgramming;

import java.util.*;
import java.util.stream.Collectors;

public class p01_Knapsack {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int knapsackCapacity = Integer.parseInt(scanner.nextLine());

        List<Item> items = new ArrayList<>();
        String line;
        while (!(line = scanner.nextLine()).equals("end")) {
            String[] components = line.split("\\s+");

            String name = components[0];
            int weight = Integer.parseInt(components[1]);
            int value = Integer.parseInt(components[2]);

            items.add(new Item(name, weight, value));
        }

        int[][] prices = new int[items.size() + 1][knapsackCapacity + 1];
        boolean[][] isItemTaken = new boolean[items.size() + 1][knapsackCapacity + 1];

        int itemIndex = 0;
        for (Item item : items) {
            itemIndex++;

            for (int capacityIndex = 0; capacityIndex <= knapsackCapacity; capacityIndex++) {
                int excluding = prices[itemIndex - 1][capacityIndex];

                int capacityIncludingItem = capacityIndex - item.weight;
                int including = 0;
                if (capacityIncludingItem >= 0) {
                    including = prices[itemIndex - 1][capacityIncludingItem] + item.getValue();
                }

                if (excluding >= including) {
                    prices[itemIndex][capacityIndex] = excluding;
                } else {
                    prices[itemIndex][capacityIndex] = including;
                    isItemTaken[itemIndex][capacityIndex] = true;
                }
            }
        }

        Deque<Item> takenItems = new ArrayDeque<>();
        int totalWeight = 0;
        int totalValue = 0;

        int takenItemIndex = prices.length - 1;
        int capacityIndex = knapsackCapacity;

        while (takenItemIndex > 0) {

            if (isItemTaken[takenItemIndex][capacityIndex]) {

                Item item = items.get(takenItemIndex - 1);
                takenItems.push(item);
                capacityIndex -= item.getWeight();
                totalWeight += item.getWeight();
                totalValue += item.getValue();
            }

            takenItemIndex--;
        }

        StringBuilder result = new StringBuilder();
        result.append("Total Weight: ").append(totalWeight).append(System.lineSeparator())
                .append("Total Value: ").append(totalValue).append(System.lineSeparator());

        String allItems = takenItems.stream()
                .map(Item::getName)
                .collect(Collectors.joining(System.lineSeparator()));
        result.append(allItems);

        System.out.print(result);
    }

    static class Item {
        private String name;
        private int weight;
        private int value;

        public Item(String name, int weight, int value) {
            this.name = name;
            this.weight = weight;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public int getWeight() {
            return weight;
        }

        public int getValue() {
            return value;
        }
    }
}
