package _10_Prior_Exams;

import java.util.Arrays;
import java.util.Scanner;

public class p02_PathFinder {

//    Write a program to check if a given path exists in a graph.

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int numberOfNodes = Integer.parseInt(scanner.nextLine());
        int[][] graph = new int[numberOfNodes][numberOfNodes];

        for (int i = 0; i < numberOfNodes; i++) {

            String line = scanner.nextLine();

            if (line.isEmpty()) {
                continue;
            }

            int[] linkedNodes = Arrays.stream(line.split("\\s+"))
                    .mapToInt(Integer::parseInt).toArray();
            for (int linkedNode : linkedNodes) {
                graph[i][linkedNode] = 1;
            }
        }

        int numberOfPathsToCheck = Integer.parseInt(scanner.nextLine());

        while (numberOfPathsToCheck-- > 0) {
            boolean pathIsValid = true;

            int[] path = Arrays.stream(scanner.nextLine().split("\\s+"))
                    .mapToInt(Integer::parseInt).toArray();

            for (int i = 1; i < path.length; i++) {
                int prevNode = path[i - 1];
                int currentNode = path[i];

                if (graph[prevNode][currentNode] != 1) {
                    pathIsValid = false;
                    break;
                }
            }

            System.out.println(pathIsValid ? "yes" : "no");
        }
    }
}
