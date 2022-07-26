package _2_CombinatorialProblems;

import java.util.Scanner;

public class p04_VariationRepetition {

    //    Given a set of elements, find all variations of k elements with repetitions.

    public static String[] elements;
    public static String[] variation;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        elements = scanner.nextLine().split(" ");
        variation = new String[scanner.nextInt()];
        variate(0);
    }

    private static void variate(int index) {
        if (index == variation.length) {
            print();
            return;
        }
        for (String element : elements) {
            variation[index] = element;
            variate(index + 1);
        }

    }


    private static void print() {
        System.out.println(String.join(" ", variation));
    }
}
