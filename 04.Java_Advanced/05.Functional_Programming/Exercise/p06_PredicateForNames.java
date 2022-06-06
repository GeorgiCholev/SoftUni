package FunctionalProgramming_05.Exercise;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class p06_PredicateForNames {
    public static void main(String[] args) {
        Scanner scanner = new Scanner (System.in);

        int lengthNotToPass = Integer.parseInt(scanner.nextLine());
        List<String> names = Arrays.stream(scanner.nextLine().split(" "))
                .collect(Collectors.toList());

        Predicate<String> fitsLength = name -> name.length() <= lengthNotToPass;
        names.stream().filter(fitsLength).forEach(System.out::println);
    }
}
