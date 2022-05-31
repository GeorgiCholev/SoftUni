package SetsAndMaps_03.Lab;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class p04_CountNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<Double, Integer> occurrenceOfNumbers = new LinkedHashMap<>();
       double[] inputValues = Arrays.stream(scanner.nextLine().split("\\s+"))
               .mapToDouble(Double::parseDouble).toArray();
        for (double value : inputValues) {
            occurrenceOfNumbers.putIfAbsent(value, 0);
            occurrenceOfNumbers.put(value, occurrenceOfNumbers.get(value) + 1);
        }
        occurrenceOfNumbers.keySet().
                forEach(k -> System.out.printf("%.1f -> %d" + System.lineSeparator(), k, occurrenceOfNumbers.get(k)));
    }
}
