package FunctionalProgramming_05.Lab;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

public class p04_AddVAT {
    public static void main(String[] args) {
        Scanner scanner = new Scanner (System.in);

        UnaryOperator<Double> addVat = p -> p * 1.20;
        List<Double> prices = Arrays.stream(scanner.nextLine().split(", "))
                .map(Double::parseDouble).map(addVat).collect(Collectors.toList());

        Consumer<Double> printsFormattedDouble = d -> System.out.printf("%.2f%n", d);
        System.out.println("Prices with VAT: ");
        prices.forEach(printsFormattedDouble);
    }
}
