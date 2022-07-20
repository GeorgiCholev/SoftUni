package _10_Prior_Exams;

import java.util.Scanner;

public class p03_SuperSet {

    private static final StringBuilder output = new StringBuilder(System.lineSeparator());
    private static String[] numbers;
    private static String[] combination;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        numbers = scanner.nextLine().split(", ");

        for (int i = 1; i <= numbers.length; i++) {
            combination = new String[i];
            combine(0, 0);
        }

        System.out.println(output);
    }

    private static void combine(int index, int start) {

        if (index == combination.length) {
            output.append(String.join(" ", combination))
                    .append(System.lineSeparator());
            return;
        }

        for (int i = start; i < numbers.length; i++) {
            combination[index] = numbers[i];
            combine(index + 1, i + 1);
        }
    }
}
