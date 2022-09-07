import java.util.*;
import java.util.stream.Collectors;

public class Kruskal {

    public static List<Edge> kruskal(int numberOfVertices, List<Edge> edges) {
        List<Edge> minSpanningForest = new ArrayList<>();

        ArrayDeque<Edge> sortedQueue = edges.stream()
                .sorted(Comparator.comparingInt(Edge::getWeight))
                .collect(Collectors.toCollection(ArrayDeque::new));

        int[] parents = new int[numberOfVertices];
        for (int vertex = 0; vertex < numberOfVertices; vertex++) {
            parents[vertex] = vertex;
        }

        while (!sortedQueue.isEmpty()) {
            Edge minEdge = sortedQueue.poll();

            int source = minEdge.getStartNode();
            int dest = minEdge.getEndNode();

            int sourceRoot = findRoot(source, parents);
            int destRoot = findRoot(dest, parents);

            if (sourceRoot != destRoot) {
                minSpanningForest.add(minEdge);
                parents[sourceRoot] = destRoot;
            }
        }

        return minSpanningForest;
    }

    public static int findRoot(int node, int[] parents) {
        int root = node;
        while (parents[root] != root) {
            root = parents[root];
        }
        return root;
    }
}
