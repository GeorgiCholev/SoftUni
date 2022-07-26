package _9_ExamPrep;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class p02_ClusterBorder {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] singleShipsTime = parseInput(reader);
        int[] pairedShipsTime = parseInput(reader);
        int[] optimalTime = new int[singleShipsTime.length + 1];

        optimalTime[0] = 0;
        optimalTime[1] = singleShipsTime[0];

        for (int i = 2; i < optimalTime.length; i++) {

            int single = optimalTime[i - 1] + singleShipsTime[i - 1];
            int paired = optimalTime[i - 2] + pairedShipsTime[i - 2];

            optimalTime[i] = Math.min(single, paired);

        }

        List<String> outputPairs = new ArrayList<>();

        int index = optimalTime.length - 1;
        while (index > 0) {
            String outputPair;
            if (optimalTime[index] - optimalTime[index - 1] == singleShipsTime[index - 1]) {
                outputPair = "Single " + index;
                index--;
            } else {
                outputPair = "Pair of " + (index - 1) + " and " + index;
                index -= 2;
            }
            outputPairs.add(outputPair);
        }

        StringBuilder output = new StringBuilder();
        output.append("Optimal Time: ").append(optimalTime[optimalTime.length - 1])
                .append(System.lineSeparator());

        Collections.reverse(outputPairs);
        outputPairs.forEach(pair -> output.append(pair).append(System.lineSeparator()));

        System.out.print(output);
    }


    private static int[] parseInput(BufferedReader scanner) throws IOException {
        return Arrays.stream(scanner.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt).toArray();
    }
}
