package SetsAndMaps_03.Exercise;

import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public class p02_SetsOfElements {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int firstSetSize = scanner.nextInt();
        Set<Integer> firstSet = new LinkedHashSet<>(firstSetSize);
        int secondSetSize = scanner.nextInt();
        Set<Integer> secondSet = new LinkedHashSet<>(secondSetSize);

        fillSet(scanner, firstSet, firstSetSize);
        fillSet(scanner, secondSet, secondSetSize);

        firstSet.retainAll(secondSet);
        firstSet.forEach(n -> System.out.print(n + " "));
    }

    private static void fillSet(Scanner scanner, Set<Integer> set, int size) {
        for (int i = 0; i < size; i++) {
            set.add(scanner.nextInt());
        }
    }
}
