package FunctionalProgramming_05.Lab;

import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class p06_EvenOrOdd {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] range = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int start = range[0];
        int end = range[1];
        String type = scanner.nextLine();
        Predicate<Integer> predicate = getPredicate(type);

        System.out.println(IntStream.rangeClosed(start, end).boxed()
                .filter(predicate)
                .map(String::valueOf)
                .collect(Collectors.joining(" ")));
    }

    private static Predicate<Integer> getPredicate(String type) {
        switch (type) {
            case "even":
                return n -> n % 2 == 0;
            case "odd":
                return n -> n % 2 != 0;
        }
        return null;
    }
}
