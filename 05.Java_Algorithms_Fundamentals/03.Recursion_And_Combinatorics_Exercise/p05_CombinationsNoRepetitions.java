package _3_Exercise_RecursionAndCombinatorics;

import java.util.Arrays;
import java.util.Scanner;

public class p05_CombinationsNoRepetitions {
    //Generate all combinations with no duplicates of k elements from a set of n elements (k <= n)

    public static int n;
    public static int[] combination;
    public static StringBuilder output = new StringBuilder();


    public static void main(String[] args) {
        Scanner scanner = new Scanner (System.in);
        n = scanner.nextInt();
        int k = scanner.nextInt();
        combination = new int[k];
        combine(0, 1);
        System.out.println(output.toString());
    }

    private static void combine(int index, int start) {
        if (index == combination.length) {
            Arrays.stream(combination).forEach(el -> output.append(el).append(" "));
            output.append(System.lineSeparator());
            return;
        }

        for (int i = start; i <= n; i++) {
            combination[index] = i;
            combine(index + 1, i + 1);
        }
    }
}
