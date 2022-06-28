package _3_Exercise_RecursionAndCombinatorics;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class p09_SchoolTeams {

    public static String[] team = new String[5];
    public static String[] girls;
    public static String[] boys;
    public static Set<String> uniqueTeams = new HashSet<>();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        girls = scanner.nextLine().split(", ");
        boys = scanner.nextLine().split(", ");
        combineGirls(0, 0);
        uniqueTeams.forEach(System.out::println);
    }

    private static void combineGirls(int index, int start) {
        if (index == 3) {
            combineBoys(3, 0);
            return;
        }
        for (int i = start; i < girls.length; i++) {
            team[index] = girls[i];
            combineGirls(index + 1, i + 1);
        }
    }

    private static void combineBoys(int index, int start) {
        if (index == team.length) {
            addToSet();
            return;
        }
        for (int i = start; i < boys.length; i++) {
            team[index] = boys[i];
            combineBoys(index + 1, i + 1);
        }
    }

    private static void addToSet() {
        uniqueTeams.add(String.join(", ", team));
    }
}
