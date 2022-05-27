package SetsAndMaps_03.Exercise;

import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public class p01_UniqueUsernames {
    public static void main(String[] args) {
        Scanner scanner = new Scanner (System.in);
        int numberUsernames = Integer.parseInt(scanner.nextLine());

        Set<String> uniqueUsernames = new LinkedHashSet<>();
        for (int i = 0; i < numberUsernames; i++) {
            uniqueUsernames.add(scanner.nextLine());
        }
        uniqueUsernames.forEach(System.out::println);
    }
}
