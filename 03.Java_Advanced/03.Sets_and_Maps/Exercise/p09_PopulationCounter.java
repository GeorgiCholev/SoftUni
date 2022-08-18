package SetsAndMaps_03.Exercise;

import java.util.*;
import java.util.stream.Collectors;

public class p09_PopulationCounter {

    public static void main(String[] args) {
        Map<String, Long> countryByPopulation = new LinkedHashMap<>();
        Map<String, Map<String, Long>> cityPopInCountry = new HashMap<>();

        Scanner scanner = new Scanner(System.in);
        String input;
        while (!"report".equals(input = scanner.nextLine())) {
            String[] components = input.split("\\|");
            String city = components[0];
            String country = components[1];
            long population = Long.parseLong(components[2]);

            countryByPopulation.putIfAbsent(country, 0L);
            countryByPopulation.put(country, countryByPopulation.get(country) + population);

            cityPopInCountry.putIfAbsent(country, new LinkedHashMap<>());
            Map<String, Long> cityByPopulation = cityPopInCountry.get(country);
            cityByPopulation.putIfAbsent(city, population);
        }

    //        Bulgaria (total population: 3)
    //        =>Veliko Tarnovo: 2
    //        =>Sofia: 1

        System.out.print(countryByPopulation.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .map(e -> e.getKey() + " (total population: " + e.getValue() + ")" + System.lineSeparator() +
                        cityPopInCountry.get(e.getKey()).entrySet().stream()
                                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                                .map(valueEntry -> "=>" + valueEntry.getKey() + ": " + valueEntry.getValue())
                                .collect(Collectors.joining(System.lineSeparator())))
                .collect(Collectors.joining(System.lineSeparator())));
    }

}
