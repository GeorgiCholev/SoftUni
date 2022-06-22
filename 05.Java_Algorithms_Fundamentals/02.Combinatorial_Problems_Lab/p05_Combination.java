package _2_CombinatorialProblems;

import java.util.Scanner;

public class p05_Combination {

    // Given a set of elements, generate all combinations of k elements without repetition.

    public static String[] elements;
    public static String[] combination;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        elements = scanner.nextLine().split(" ");
        combination = new String[scanner.nextInt()];
        combinate(0, 0);
    }

    private static void combinate(int index, int start) {
        if (index == combination.length) {
            print();
            return;
        }
        for (int i = start; i < elements.length; i++) {
            combination[index] = elements[i];
            combinate(index + 1, i + 1); // replace (index + 1, i + 1) with (index + 1, i)
                                                    // to find elements with repetition
        }
    }

    private static void print() {
        System.out.println(String.join(" ", combination));
    }
}
