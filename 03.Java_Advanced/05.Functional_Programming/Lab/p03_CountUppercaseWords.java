package FunctionalProgramming_05.Lab;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class p03_CountUppercaseWords {
    public static void main(String[] args) {
        Scanner scanner = new Scanner (System.in);

        Predicate<String> startCharUppercase = w -> Character.isUpperCase(w.charAt(0));
        List<String> wordsWithUppercase = Arrays.stream(scanner.nextLine().split("\\s+"))
                .filter(startCharUppercase).collect(Collectors.toList());

        Function<List<String>, Integer> sumOfList = List::size;
        System.out.println(sumOfList.apply(wordsWithUppercase));


        Consumer<String> wordsListed = System.out::println;
        wordsWithUppercase.forEach(wordsListed);

    }
}
