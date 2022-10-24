package _8_Exam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.stream.Collectors;

public class p03_Code {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] first = Arrays.stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int[] second = Arrays.stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int[][] table = new int[first.length + 1][second.length + 1];

        for (int i = 1; i <= first.length; i++) {
            for (int j = 1; j <= second.length; j++) {

                int one = first[i - 1];
                int two = second[j - 1];

                if (one == two) {
                    table[i][j] = table[i - 1][j - 1] + 1;
                } else {
                    table[i][j] = Math.max(table[i - 1][j], table[i][j - 1]);
                }
            }
        }

        Deque<Integer> lcs = new ArrayDeque<>();
        int row = first.length;
        int col = second.length;

        while (table[row][col] > 0) {
            if (first[row - 1] == second[col - 1]) {
                lcs.push(first[row - 1]);
                row--;
                col--;
            } else {
                if (table[row - 1][col] > table[row][col - 1]) {
                    row--;
                } else {
                    col--;
                }
            }
        }

        int lcsLength = lcs.size();
        String lcsElements = lcs.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(" "));

        System.out.println(lcsElements + System.lineSeparator() + lcsLength);
    }
}
