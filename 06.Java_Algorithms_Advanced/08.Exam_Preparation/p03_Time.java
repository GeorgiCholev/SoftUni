package _7_examPrep;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class p03_Time {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] firstTimeline = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int[] secondTimeline = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int[][] elementsTable = new int[firstTimeline.length + 1][secondTimeline.length + 1];

        ArrayDeque<Integer> commonSubsequentElements = new ArrayDeque<>();

        dynamicProgrammingApproach(firstTimeline, secondTimeline, elementsTable, commonSubsequentElements);

        printSolution(commonSubsequentElements);
    }

    private static void printSolution(ArrayDeque<Integer> commonSubsequentElements) {
        int subsequenceLength = commonSubsequentElements.size();

        String subsequenceOrder = commonSubsequentElements
                .stream()
                .map(String::valueOf)
                .collect(Collectors.joining(" "));

        System.out.print(subsequenceOrder + System.lineSeparator() + subsequenceLength);
    }

    private static void dynamicProgrammingApproach
            (int[] first, int[] second, int[][] table, ArrayDeque<Integer> stack) {

        fillTable(first, second, table);

        findLongestCommonSubsequence(first, second, table, stack);
    }

    private static void findLongestCommonSubsequence(int[] f, int[] s, int[][] t, ArrayDeque<Integer> stack) {
        int row = f.length;
        int column = s.length;
        while (t[row][column] > 0) {

            if (f[row - 1] == s[column - 1]) {
                stack.push(f[row - 1]);
                row--;
                column--;
            } else {
                if (t[row - 1][column] > t[row][column - 1]) {
                    row--;
                } else {
                    column--;
                }
            }

        }
    }

    private static void fillTable(int[] f, int[] s, int[][] t) {
        for (int firstIndex = 1; firstIndex <= f.length; firstIndex++) {
            for (int secondIndex = 1; secondIndex <= s.length; secondIndex++) {

                int firstElement = f[firstIndex - 1];
                int secondElement = s[secondIndex - 1];

                if (firstElement == secondElement) {
                    t[firstIndex][secondIndex] = t[firstIndex - 1][secondIndex - 1] + 1;
                } else {
                    t[firstIndex][secondIndex] = Math.max(
                            t[firstIndex - 1][secondIndex], t[firstIndex][secondIndex - 1]
                    );
                }

            }
        }
    }
}
