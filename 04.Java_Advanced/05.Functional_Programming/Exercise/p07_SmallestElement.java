package FunctionalProgramming_05.Exercise;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;

public class p07_SmallestElement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner (System.in);
        List<Integer> numbers = Arrays.stream(scanner.nextLine().split(" "))
                .map(Integer::parseInt).collect(Collectors.toList());

        Function<List<Integer>, Integer> findMin = l ->
                numbers.lastIndexOf(l.stream().mapToInt(n -> n).min().getAsInt());

        System.out.println(findMin.apply(numbers));
    }
}
