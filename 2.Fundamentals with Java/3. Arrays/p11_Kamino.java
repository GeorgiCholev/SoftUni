package Arrays_3.Exercise;

import java.util.Scanner;

public class p09_Kamino {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int length = Integer.parseInt(scanner.nextLine());
        String input = scanner.nextLine();

        int winnerIndex = Integer.MAX_VALUE;
        int winnerSum = 0;
        String winnerArray = "";
        int winnerSubsequence = 0;
        int winnerIndexStart = 0;
        int index = 0;

        while (!input.equals("Clone them!")) {

            int startingIndex = Integer.MAX_VALUE;
            int startingSubsequence = 1;
            int subsequence = 1;
            int startingSum = 0;
            index++;
            int sum = 0;

            String[] sequenceDNAString = input.split("!+");
            int[] sequenceDNA = new int[sequenceDNAString.length];
            for (int k = 0; k < sequenceDNA.length; k++) {
                sequenceDNA[k] = Integer.parseInt(sequenceDNAString[k]);
            }
            for (int i = 0; i < sequenceDNA.length; i++) {
                if (sequenceDNA[i] == 1) {
                    sum++;
                    for (int j = i + 1; j < sequenceDNA.length; j++) {
                        if (sequenceDNA[i] == sequenceDNA[j]) {
                            subsequence++;
                            if (i < startingIndex) {
                                startingIndex = i;
                            }
                        } else {
                            break;
                        }
                    }
                    if (subsequence > startingSubsequence) {
                        startingSubsequence = subsequence;
                    }
                    if (sum > startingSum) {
                        startingSum = sum;
                    }
                }
                subsequence = 1;
            }

            if (startingSubsequence > winnerSubsequence) {
                winnerSubsequence = startingSubsequence;
                winnerIndex = startingIndex;
                winnerSum = startingSum;
                winnerArray = String.join(" ", sequenceDNAString);
                winnerIndexStart = index;
            } else if (startingSubsequence == winnerSubsequence && startingIndex < winnerIndex) {
                winnerIndex = startingIndex;
                winnerSum = startingSum;
                winnerArray = String.join(" ", sequenceDNAString);
                winnerIndexStart = index;
            } else if (startingSubsequence == winnerSubsequence && startingIndex == winnerIndex && sum > winnerSum) {
                winnerSum = startingSum;
                winnerArray = String.join(" ", sequenceDNAString);
                winnerIndexStart = index;
            }
                                                     
            input = scanner.nextLine();
        }
        System.out.printf("Best DNA sample %d with sum: %d.%n" + "%s", winnerIndexStart, winnerSum, winnerArray);
    }
}
