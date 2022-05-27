package SetsAndMaps_03.Lab;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class p09_LargestThreeNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner (System.in);
        Arrays.stream(scanner.nextLine().split("\\s+")).map(Integer::parseInt).
                sorted(Comparator.reverseOrder()).limit(3).forEach(n -> System.out.print(n + " "));
    }
}
