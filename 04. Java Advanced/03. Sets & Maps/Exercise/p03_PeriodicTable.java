package SetsAndMaps_03.Exercise;

import java.util.Collections;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class p03_PeriodicTable {
    public static void main(String[] args) {
        Scanner scanner = new Scanner (System.in);

        int numberOfCompounds = Integer.parseInt(scanner.nextLine());
        Set<String> elements = new TreeSet<>();
        for (int i = 0; i < numberOfCompounds; i++) {
            String[] elementsInACompound = scanner.nextLine().split("\\s+");
            Collections.addAll(elements, elementsInACompound);
        }
        System.out.println(String.join(" ", elements));
    }
}
