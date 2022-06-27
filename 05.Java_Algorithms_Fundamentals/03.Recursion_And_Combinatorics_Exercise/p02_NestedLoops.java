package _3_Exercise_RecursionAndCombinatorics;

import java.util.Arrays;
import java.util.Scanner;

public class p02_NestedLoops {
    //Program that simulates the execution of n nested loops from 1 to n
    // which prints the values of all its iteration variables at any given time on a single line.

    public static int[] numbers;
    public static StringBuilder output = new StringBuilder();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        numbers = new int[n];
        permute(0);
        System.out.println(output.toString());
    }

    private static void permute(int index) {
        if (index == numbers.length) {
            append();
            return;
        }

        for (int i = 1; i <= numbers.length; i++) {
            numbers[index] = i;
            permute(index + 1);
        }
    }

    private static void append() {
        Arrays.stream(numbers).forEach(n -> output.append(n).append(" "));
        output.append(System.lineSeparator());
    }
}
