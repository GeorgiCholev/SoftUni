package _2_CombinatorialProblems;

import java.util.Scanner;

public class p01_Permutation {

    // Swap Algorithm for Permutations without repeating elements {A, B, C}

    public static String[] elements;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        elements = scanner.nextLine().split(" ");
        permute(0);
    }

    private static void permute(int index) {

        if (index == elements.length) {
            print();
            return;
        }

        permute(index + 1);
        for (int i = index + 1; i < elements.length; i++) {
            swap(index, i);
            permute(index + 1);
            swap(i, index);
        }
    }

    private static void swap(int first, int second) {
        String temp = elements[first];
        elements[first] = elements[second];
        elements[second] = temp;

    }

    private static void print() {
        System.out.println(String.join(" ", elements));
    }
}
