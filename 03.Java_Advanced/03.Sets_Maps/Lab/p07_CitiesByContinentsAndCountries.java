package SetsAndMaps_03.Lab;

import java.util.*;

public class p07_CitiesByContinentsAndCountries {
    public static void main(String[] args) {
        Scanner scanner = new Scanner (System.in);
        Map<String, Map<String, ArrayList<String>>> citiesByContinentsAndCountries = new LinkedHashMap<>();
        int repetitions = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < repetitions; i++) {
            String[] data = scanner.nextLine().split("\\s+");
            String continent = data[0];
            String country = data[1];
            String city = data[2];
            citiesByContinentsAndCountries.putIfAbsent(continent, new LinkedHashMap<>());
            Map<String, ArrayList<String>> citiesByCountries = citiesByContinentsAndCountries.get(continent);
            citiesByCountries.putIfAbsent(country, new ArrayList<>());
            List<String> cities = citiesByCountries.get(country);
            cities.add(city);
        }

        citiesByContinentsAndCountries.forEach((continent, countries) -> {
            System.out.println(continent + ": ");
            countries.forEach((country, cities) -> {
                System.out.print(country + " -> ");
                System.out.println(String.join(", ", cities));
            });
        });
    }
}
