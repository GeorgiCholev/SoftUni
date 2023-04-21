package _6_DynamicProgramming;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;
import java.util.stream.Collectors;

public class p02_LCS {
    // Longest Common Subsequence
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String a = scanner.nextLine();
        String b = scanner.nextLine();

        int[][] lcs = new int[a.length() + 1][b.length() + 1];

        for (int indexLetterA = 1; indexLetterA <= a.length(); indexLetterA++) {
            for (int indexLetterB = 1; indexLetterB <= b.length(); indexLetterB++) {

                char letterA = a.charAt(indexLetterA - 1);
                char letterB = b.charAt(indexLetterB - 1);

                if (letterA == letterB) {
                    lcs[indexLetterA][indexLetterB] = lcs[indexLetterA - 1][indexLetterB - 1] + 1;
                } else {
                    lcs[indexLetterA][indexLetterB] = Math.max(
                            lcs[indexLetterA - 1][indexLetterB], lcs[indexLetterA][indexLetterB - 1]
                    );
                }

            }
        }

        Deque<Character> commonCharacters = new ArrayDeque<>();
        int row = a.length();
        int column = b.length();

        while (lcs[row][column] > 0) {
            if (a.charAt(row - 1) == b.charAt(column - 1)) {
                commonCharacters.push(a.charAt(row - 1));
                row--;
                column--;
            } else {
                if (lcs[row - 1][column] > lcs[row][column - 1]) {
                    row--;
                } else {
                    column--;
                }
            }
        }

        System.out.println("Subsequence count: " + lcs[a.length()][b.length()]);
        System.out.print("Longest Common Subsequence: "
                        + commonCharacters.stream()
                        .map(String::valueOf)
                        .collect(Collectors.joining(", "))
                );
    }
}
