package _7_examPrep;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class p03_RoadTrip {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] itemValues = Arrays.stream(reader.readLine().split(", "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int[] itemWeights = Arrays.stream(reader.readLine().split(", "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int maxCapacity = Integer.parseInt(reader.readLine());

        int[][] table = new int[itemValues.length + 1][maxCapacity + 1];

        int maxValue = dynamicProgrammingApproach(itemValues, itemWeights, maxCapacity, table);

        System.out.print("Maximum value: " + maxValue);
    }

    private static int dynamicProgrammingApproach(int[] values, int[] weights, int max, int[][] table) {

        for (int i = 1; i <= values.length; i++) {
            for (int j = 0; j <= max; j++) {
                int value = values[i - 1];
                int weight = weights[i - 1];

                int excluding = table[i - 1][j];

                int including = 0;
                if (j >= weight) {
                    including = table[i - 1][j - weight] + value;
                }

                table[i][j] = Math.max(including, excluding);
            }
        }

        int maxValue = Integer.MIN_VALUE;
        for (int i = 0; i < table.length; i++) {
            maxValue = Math.max(maxValue, table[i][table[i].length - 1]);
        }

        return maxValue;
    }
}
