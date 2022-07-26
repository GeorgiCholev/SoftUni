package _2_CombinatorialProblems;

import java.util.Scanner;

public class p03_Variation {

    // Swap Algorithm for Variations without repeating elements ({A, B, C} in 2 slots)

    public static String[] elements;
    public static int slots;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        elements = scanner.nextLine().split(" ");
        slots = scanner.nextInt();
        variate(0);
    }

    private static void variate(int index) {
        if (index == slots) {
            print();
            return;
        }

        variate(index + 1);
        for (int i = index + 1; i < elements.length; i++) {
            swap(index, i);
            variate(index + 1);
            swap(i, index);
        }
    }

    private static void print() {
        for (int i = 0; i < slots; i++) {
            if (i == slots - 1) {
                System.out.print(elements[i]);
            } else {
                System.out.print(elements[i] + " ");
            }
        }
        System.out.println();
    }

    private static void swap(int first, int second) {
        String temp = elements[first];
        elements[first] = elements[second];
        elements[second] = temp;
    }
}
