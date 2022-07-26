package _5_Lab_Graph;

import java.util.*;
import java.util.stream.Collectors;

public class GraphAlgorithms {


    public static List<List<Integer>> fillGraphFromConsole() {
        Scanner scanner = new Scanner(System.in);
        int numberOfNodes = Integer.parseInt(scanner.nextLine());  // [0 ... (numberOfNodes - 1)]

        List<List<Integer>> graphOfNodes = new ArrayList<>(numberOfNodes);
        for (int node = 0; node < numberOfNodes; node++) {

            String destinationsOfCurrentNode = scanner.nextLine();

            if (destinationsPresent(destinationsOfCurrentNode)) {
                List<Integer> destinations = Arrays.stream(destinationsOfCurrentNode.split("\\s+"))
                        .map(Integer::parseInt).collect(Collectors.toList());
                graphOfNodes.add(node, destinations);
            } else {
                graphOfNodes.add(node, new ArrayList<>());
            }
        }
        return graphOfNodes;
    }

    private static boolean destinationsPresent(String destinationsOfCurrentNode) {
        return destinationsOfCurrentNode != null && !destinationsOfCurrentNode.trim().isEmpty();
    }


    public static void bfs(int node, List<List<Integer>> graph, boolean[] visited, StringBuilder output) {

        Deque<Integer> graphQueue = new ArrayDeque<>();

        graphQueue.offer(node);
        visited[node] = true;

        while (!graphQueue.isEmpty()) {

            int nextNode = graphQueue.poll();
            output.append(nextNode).append(" ");

            List<Integer> childrenOfNode = graph.get(nextNode);

            for (int child : childrenOfNode) {
                if (!visited[child]) {
                    graphQueue.offer(child);
                    visited[child] = true;
                }
            }

        }
    }


    // Depth-First Search Algorithm
    public static void dfs(int node, List<List<Integer>> graph, boolean[] visited, StringBuilder output) {

        if (!visited[node]) {
            visited[node] = true;
        } else {
            return;
        }

        List<Integer> nodeDestinations = graph.get(node);

        for (int nextNode : nodeDestinations) {
            dfs(nextNode, graph, visited, output);
        }
        output.append(node).append(" ");
    }
}
