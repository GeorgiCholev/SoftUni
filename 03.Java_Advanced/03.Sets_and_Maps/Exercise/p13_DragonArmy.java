package SetsAndMaps_03.Exercise;

import java.util.*;
import java.util.stream.Collectors;

public class p13_DragonArmy {
    public static void main(String[] args) {
        Map<String, Map<String, Stats>> dragonStatsByType = new LinkedHashMap<>();

        Scanner scanner = new Scanner(System.in);
        int count = Integer.parseInt(scanner.nextLine());
        while (count-- > 0) {
            fillDragonDataIn(dragonStatsByType, scanner.nextLine().split("\\s"));
        }

        Map<String, Stats> averageStatsForType = getAverageStatsForAllDragonTypes(dragonStatsByType);

        System.out.println(dragonStatsByType.entrySet().stream()
                .map(entry -> {
                    String dragonsNameAndStatsFormat = entry.getValue().entrySet().stream()
                            .map(valueEntry -> "-" + valueEntry.getKey() +
                                    " -> damage: " + valueEntry.getValue().getDamageAsInt() +
                                    ", health: " + valueEntry.getValue().getHealthAsInt() +
                                    ", armor: " + valueEntry.getValue().getArmourAsInt())
                            .collect(Collectors.joining(System.lineSeparator()));

                    Stats averageStatsForEntry = averageStatsForType.get(entry.getKey());
                    return entry.getKey() +
                            "::(" + String.format("%.2f", averageStatsForEntry.getDamage()) +
                            "/" + String.format("%.2f", averageStatsForEntry.getHealth()) +
                            "/" + String.format("%.2f", averageStatsForEntry.getArmour()) + ")" +
                            System.lineSeparator() + dragonsNameAndStatsFormat;
                })
                .collect(Collectors.joining(System.lineSeparator())));
    }

    static Map<String, Stats> getAverageStatsForAllDragonTypes(Map<String, Map<String, Stats>> dragonStatsByType) {
        Map<String, Stats> averageStatsForType = new HashMap<>();
        for (Map.Entry<String, Map<String, Stats>> entry : dragonStatsByType.entrySet()) {
            Stats averageStats = new Stats(entry.getValue());
            averageStatsForType.put(entry.getKey(), averageStats);
        }

        return averageStatsForType;
    }

    static void fillDragonDataIn(Map<String, Map<String, Stats>> dragonStatsByType, String[] dragonData) {
        String type = dragonData[0];
        String name = dragonData[1];
        int damage = dragonData[2].equals("null") ? -1 : Integer.parseInt(dragonData[2]);
        int health = dragonData[3].equals("null") ? -1 : Integer.parseInt(dragonData[3]);
        int armour = dragonData[4].equals("null") ? -1 : Integer.parseInt(dragonData[4]);
        Stats dragonStats = new Stats(damage, health, armour);

        dragonStatsByType.putIfAbsent(type, new TreeMap<>());
        Map<String, Stats> statsByName = dragonStatsByType.get(type);
        statsByName.put(name, dragonStats);

    }

    static class Stats {
        private double damage;
        private double health;
        private double armour;

        public Stats(double damage, double health, double armour) {
            this.damage = damage == -1.00 ? 45 : damage;
            this.health = health == -1.00 ? 250 : health;
            this.armour = armour == -1.00 ? 10 : armour;
        }

        public Stats(Map<String, Stats> statsMap) {
            setAverageStatsForInput(statsMap);
        }

        private void setAverageStatsForInput(Map<String, Stats> statsMap) {
            double damage = 0;
            double health = 0;
            double armour = 0;
            int numberOfDragons = statsMap.size();

            for (Map.Entry<String, Stats> entry : statsMap.entrySet()) {
                Stats entryStats = entry.getValue();
                damage += entryStats.getDamage();
                health += entryStats.getHealth();
                armour += entryStats.getArmour();
            }

            this.damage = damage / numberOfDragons;
            this.health = health / numberOfDragons;
            this.armour = armour / numberOfDragons;
        }

        public double getDamage() {
            return damage;
        }

        public int getDamageAsInt() {
            return (int) getDamage();
        }

        public double getHealth() {
            return health;
        }

        public int getHealthAsInt() {
            return (int) getHealth();
        }

        public double getArmour() {
            return armour;
        }

        public int getArmourAsInt() {
            return (int) getArmour();
        }
    }
}
