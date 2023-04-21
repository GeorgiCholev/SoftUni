package _10_Prior_Exams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class p04_Paths {

    private static final StringBuilder output = new StringBuilder();
    private static final List<Integer> path = new ArrayList<>();
    private static List<List<Integer>> graph;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numberOfNodes = Integer.parseInt(scanner.nextLine());

        graph = new ArrayList<>();

        for (int i = 0; i < numberOfNodes - 1; i++) {
            graph.add(Arrays.stream(scanner.nextLine().split("\\s+"))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList()));
        }

        for (int i = 0; i < graph.size(); i++) {
            depthFirstSearch(i);
        }

        System.out.print(output);
    }

    private static void depthFirstSearch(int node) {

        if (node == graph.size()) {
            path.add(node);
            output.append(path.stream().map(String::valueOf).collect(Collectors.joining(" ")))
                    .append(System.lineSeparator());
            path.remove(path.size() - 1);
            return;
        }

        path.add(node);
        for (int linkedNode : graph.get(node)) {
            depthFirstSearch(linkedNode);
        }
        path.remove(path.size() - 1);
    }
}
