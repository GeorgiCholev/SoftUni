package _9_ExamPrep;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

public class p01_MonkeyBusiness {

//        You have found a number. Now you want to see in how many ways
//        you can combine the numbers from 1 to N in such a way that by using
//        addition or subtraction you will end up with zero as a result.

    private static int[] numbers;
    private static int[] variation;
    private static final StringBuilder output = new StringBuilder();
    private static int numberOfOutcomes = 0;

    public static void main(String[] args) {

        int lastNumber = new Scanner(System.in).nextInt();

        numbers = IntStream.rangeClosed(1, lastNumber).toArray();

        variation = new int[numbers.length];

        variate(0);

        output.append("Total Solutions: ").append(numberOfOutcomes);

        System.out.println(output);
    }

    private static void variate(int index) {

        if (index == numbers.length) {
            validateVariation();
            return;
        }

        variation[index] = numbers[index];
        variate(index + 1);

        variation[index] = -numbers[index];
        variate(index + 1);
    }

    private static void validateVariation() {

        int sum = Arrays.stream(variation).sum();
        if (sum == 0) {
            collectOutcome();
        }
    }

    private static void collectOutcome() {
        numberOfOutcomes++;
        String formattedNumber;

        for (int number : variation) {

            if (number < 0) {
                formattedNumber = String.valueOf(number);
            } else {
                formattedNumber = "+" + number;
            }
            output.append(formattedNumber).append(" ");
        }

        output.append(System.lineSeparator());
    }
}
