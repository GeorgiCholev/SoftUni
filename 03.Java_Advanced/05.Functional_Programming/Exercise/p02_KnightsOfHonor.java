package FunctionalProgramming_05.Exercise;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class p02_KnightsOfHonor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        List<String> names = Arrays.stream(scanner.nextLine().split(" "))
                .collect(Collectors.toList());

        Consumer<String> appendSir = name -> System.out.println("Sir " + name);
        names.forEach(appendSir);
    }
}