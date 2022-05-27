package SetsAndMaps_03.Exercise;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class p04_CountSymbols {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        Map<Character, Integer> occurrenceOfSymbols = new TreeMap<>();
        for (int i = 0; i < input.length(); i++) {
            char symbol = input.charAt(i);
            occurrenceOfSymbols.putIfAbsent(symbol, 0);
            occurrenceOfSymbols.put(symbol, occurrenceOfSymbols.get(symbol) + 1);
        }
        occurrenceOfSymbols.forEach((k, v) -> System.out.println(k + ": " + v + " time/s"));
    }

}
