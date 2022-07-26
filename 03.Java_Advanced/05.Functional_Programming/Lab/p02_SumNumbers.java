package FunctionalProgramming_05.Lab;

import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Function;

public class p02_SumNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner (System.in);
        int[] numbers = Arrays.stream(scanner.nextLine().split(", "))
                .mapToInt(Integer::parseInt).toArray();

        Function<int[], String> countFormat = arr -> "Count = " + arr.length;
        System.out.println(countFormat.apply(numbers));

        Function<int[], Integer> sumArrayElements = arr -> Arrays.stream(arr).sum();
        Function<Integer, String> sumFormat = sum -> "Sum = " + sum;

        int sum = sumArrayElements.apply(numbers);
        System.out.println(sumFormat.apply(sum));
    }
}
