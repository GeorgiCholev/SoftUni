import java.util.*;

public class Dijkstra {

    public static List<Integer> dijkstraAlgorithm(int[][] graph, int sourceNode, int destinationNode) {

        int[] distances = new int[graph.length];
        Arrays.fill(distances, Integer.MAX_VALUE);

        int[] prevNodes = new int[graph.length];
        Arrays.fill(prevNodes, -1);

        boolean[] visited = new boolean[graph.length];

        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.comparingInt(node -> distances[node]));

        queue.offer(sourceNode);
        distances[sourceNode] = 0;

        while (!queue.isEmpty()) {
            int minNode = queue.poll();
            visited[minNode] = true;

            int[] nodeRelations = graph[minNode];
            for (int linkedNode = 0; linkedNode < nodeRelations.length; linkedNode++) {

                if (nodeRelations[linkedNode] != 0 && !visited[linkedNode]) {
                    int newDistance = distances[minNode] + nodeRelations[linkedNode];

                    if (newDistance < distances[linkedNode]) {
                        distances[linkedNode] = newDistance;
                        prevNodes[linkedNode] = minNode;
                    }
                    queue.offer(linkedNode);
                }
            }
        }

        if (prevNodes[destinationNode] == -1) {
            return null;
        }

        List<Integer> shortestPath = new ArrayList<>();
        shortestPath.add(destinationNode);
        int nodeToAdd = destinationNode;
        while ((nodeToAdd = prevNodes[nodeToAdd]) != -1) {
            shortestPath.add(nodeToAdd);
        }
        Collections.reverse(shortestPath);
        return shortestPath;
    }
}
