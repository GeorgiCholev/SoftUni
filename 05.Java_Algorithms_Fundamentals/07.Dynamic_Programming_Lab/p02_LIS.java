package _7_DynamicProgramming;

import java.util.*;
import java.util.stream.Collectors;

public class p02_LIS {

    //Longest Increasing Subsequence

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] numbers = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt).toArray();

        // Holds the length of the longest subsequence up to its index.
        int[] longestLis = new int[numbers.length];
        Arrays.fill(longestLis, 1);
        // Holds the index of the previous number in its longest subsequence
        int[] prevIndexInSequence = new int[numbers.length];
        Arrays.fill(prevIndexInSequence, -1);

        for (int currentIndex = 1; currentIndex < numbers.length; currentIndex++) {

            for (int prevIndex = 0; prevIndex < currentIndex; prevIndex++) {

                if (numbers[currentIndex] > numbers[prevIndex]) {
                    int candidateLis = longestLis[prevIndex] + 1;

                    if (candidateLis > longestLis[currentIndex]) {
                        longestLis[currentIndex] = candidateLis;
                        prevIndexInSequence[currentIndex] = prevIndex;
                    }
                }
            }
        }

        int biggestLength = 1;
        int lastIndexOfLis = -1;
        for (int i = 0; i < longestLis.length; i++) {
            if (longestLis[i] > biggestLength) {
                biggestLength = longestLis[i];
                lastIndexOfLis = i;
            }
        }

        if (biggestLength == 1) {
            System.out.println(numbers[0]);
            return;
        }

        List<Integer> longestSubsequence = new ArrayList<>(biggestLength);

        while (lastIndexOfLis != -1) {
            longestSubsequence.add(numbers[lastIndexOfLis]);
            lastIndexOfLis = prevIndexInSequence[lastIndexOfLis];
        }

        List<String> LIS = longestSubsequence.stream()
                .sorted(Comparator.naturalOrder())
                .map(String::valueOf)
                .collect(Collectors.toList());

        System.out.println(String.join(" ", LIS));
    }
}
