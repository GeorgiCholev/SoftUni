package FunctionalProgramming_05.Exercise;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import java.util.function.Consumer;

public class p08_CustomComparator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner (System.in);
        int[] numbers = Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();
        Comparator<Integer> sorter = (first, second) -> {
            if (first % 2 == 0 && second % 2 != 0) {
                return -1;
            } else if (first % 2 != 0 && second % 2 == 0) {
                return 1;
            } else {
           //     Integer.compare(first, second);
                return first.compareTo(second);
            }
        };
        Consumer<Integer> printer = n -> System.out.print(n + " ");
        Arrays.stream(numbers).boxed()
                .sorted(sorter).forEach(printer);
    }
}
