package _1_RecursionAndBacktracking;

import java.util.Arrays;
import java.util.Scanner;

public class p01_RecursiveArraySum {
    public static void main(String[] args) {
        Scanner scanner = new Scanner (System.in);

        int[] numbers = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        System.out.println(sumArray(numbers, 0));
    }

    private static int sumArray(int[] numbers, int index) {
        if (index == numbers.length - 1) {
            return numbers[index];
        }
        return numbers[index] + sumArray(numbers, index + 1);
    }
}
