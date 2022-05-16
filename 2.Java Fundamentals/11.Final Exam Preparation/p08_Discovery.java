package Fundamentals_ExamPrep_11.August9th2020;

import java.util.*;

public class p03_Discovery {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Plant> plants = fillMapWithInput(scanner);
        String line = scanner.nextLine();
        while (!line.equals("Exhibition")) {
            performCommand(line, plants);
            line = scanner.nextLine();
        }
        System.out.println("Plants for the exhibition:");
        plants.forEach((key, value) -> {
            value.setAverageRating();
            System.out.println(value.getInfo());
        });
    }

    private static void performCommand(String line, Map<String, Plant> plants) {
        String[] components = line.split("[: -]+");
        String name = components[1];
        if (plants.containsKey(name)) {
            String command = components[0];
            switch (command) {
                case "Rate":
                    int rating = Integer.parseInt(components[2]);
                    plants.get(name).addRating(rating);
                    break;
                case "Update":
                    String newRarity = components[2];
                    plants.get(name).setRarity(newRarity);
                    break;
                case "Reset":
                    plants.get(name).setRatings(new ArrayList<>());
                    break;
            }
        } else {
            System.out.println("error");
        }
    }

    private static Map<String, Plant> fillMapWithInput(Scanner scanner) {
        Map<String, Plant> plants = new LinkedHashMap<>();
        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < n; i++) {
            String[] components = scanner.nextLine().split("<->");
            String name = components[0];
            if (!plants.containsKey(name)) {
                plants.put(name, new Plant(name, components[1]));
            } else {
                plants.get(name).setRarity(components[1]);
            }
        }
        return plants;
    }

    static class Plant {
        String name;
        String rarity;
        List<Integer> ratings = new ArrayList<>();
        double averageRating;

        public void setRatings(List<Integer> ratings) {
            this.ratings = ratings;
        }

        void addRating(int rating) {
            this.ratings.add(rating);
        }

        public void setRarity(String rarity) {
            this.rarity = rarity;
        }

        Plant(String name, String rarity) {
            this.name = name;
            this.rarity = rarity;
        }

        String getInfo() {
            return String.format("- %s; Rarity: %s; Rating: %.2f", this.name, this.rarity, this.averageRating);
        }

        public void setAverageRating() {
            this.averageRating = this.ratings.stream().mapToInt(r -> r).average().orElse(0.00);
        }
    }
}
