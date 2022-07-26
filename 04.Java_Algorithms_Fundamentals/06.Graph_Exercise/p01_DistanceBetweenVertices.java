package _6_Exercise_Graph;

import java.util.*;

public class p01_DistanceBetweenVertices {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int numberOfVertices = Integer.parseInt(scanner.nextLine());
        int numberOfPairsToTrack = Integer.parseInt(scanner.nextLine());

        Map<Integer, int[]> graph = new HashMap<>(numberOfVertices);

        for (int i = 0; i < numberOfVertices; i++) {

            String[] nodeData = scanner.nextLine().split(":");
            int node = Integer.parseInt(nodeData[0]);

            if (nodeData.length > 1) {
                int[] nodeDirections = Arrays.stream(nodeData[1].split("\\s+"))
                        .mapToInt(Integer::parseInt).toArray();

                graph.put(node, nodeDirections);
            } else {
                graph.put(node, new int[0]);
            }
        }

        for (int i = 0; i < numberOfPairsToTrack; i++) {

            String[] pair = scanner.nextLine().split("-");
            int source = Integer.parseInt(pair[0]);
            int destination = Integer.parseInt(pair[1]);

            findDistanceThroughBFS(source, destination, graph);
        }

    }

    private static void findDistanceThroughBFS(int source, int finalNode, Map<Integer, int[]> graph) {

        Map<Integer, Boolean> visited = fillBooleanVisitationMap(graph);
        // path keeps as value node previous to the one in key
        Map<Integer, Integer> path = new HashMap<>();

        boolean foundPath = false;

        ArrayDeque<Integer> searchQueue = new ArrayDeque<>();
        searchQueue.offer(source);
        visited.put(source, Boolean.TRUE);

        while (!searchQueue.isEmpty()) {

            int node = searchQueue.poll();
            if (node == finalNode) {
                foundPath = true;
                break;
            }

            for (int linkedNode : graph.get(node)) {

                if (!visited.get(linkedNode)) {
                    path.put(linkedNode, node);
                    visited.put(linkedNode, Boolean.TRUE);
                    searchQueue.offer(linkedNode);
                }

            }

        }

        int distance = -1;

        if (foundPath) {
            distance = 1;
            int prevNode = path.get(finalNode);
            while (prevNode != source) {
                prevNode = path.get(prevNode);
                distance++;
            }
        }

        System.out.println("{" + source + ", " + finalNode + "} -> " + distance);
    }

    private static Map<Integer, Boolean> fillBooleanVisitationMap(Map<Integer, int[]> graph) {

        Map<Integer, Boolean> visited = new HashMap<>(graph.size());
        Set<Integer> nodes = graph.keySet();

        for (Integer node : nodes) {
            visited.put(node, Boolean.FALSE);
        }
        return visited;
    }
}
