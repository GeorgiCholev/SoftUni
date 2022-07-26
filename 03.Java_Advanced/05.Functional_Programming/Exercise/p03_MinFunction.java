package FunctionalProgramming_05.Exercise;

import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Function;

public class p03_MinFunction {
    public static void main(String[] args) {
        Scanner scanner = new Scanner (System.in);

        int[] numbers = Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();

        Function<int[], Integer> getMin = arr -> Arrays.stream(arr).min().getAsInt();
        System.out.println(getMin.apply(numbers));
    }
}
