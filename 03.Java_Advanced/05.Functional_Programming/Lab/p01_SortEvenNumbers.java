package FunctionalProgramming_05.Lab;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class p01_SortEvenNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Predicate<Integer> isEven = n -> n % 2 == 0;
        List<Integer> numbers = Arrays.stream(scanner.nextLine().split(", ")).map(Integer::parseInt)
                .filter(isEven).collect(Collectors.toList());

        if (numbers.size() == 0) {
            return;
        }

        String evenNumbers = numbers.stream().map(String::valueOf).collect(Collectors.joining(", "));
        System.out.println(evenNumbers);

        String sortedNumbers = numbers.stream().sorted().map(String::valueOf).collect(Collectors.joining(", "));
        System.out.println(sortedNumbers);
    }
}
