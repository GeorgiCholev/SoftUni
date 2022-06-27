package _3_Exercise_RecursionAndCombinatorics;

import java.util.Scanner;

public class p01_ReverseArray {
    public static String[] elements;
    public static StringBuilder output = new StringBuilder();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        elements = scanner.nextLine().split(" ");
        appendNext(elements.length - 1);
        System.out.println(output.toString());
    }

    private static void appendNext(int index) {
        if (index < 0) {
            return;
        }
        output.append(elements[index]).append(" ");
        appendNext(index - 1);
    }
}
