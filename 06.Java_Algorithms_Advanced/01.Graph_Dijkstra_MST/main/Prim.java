import java.util.*;

public class Prim {

    public static List<List<Edge>> minSpanningForest(List<Edge> edges) {
        List<List<Edge>> minSpanningForest = new ArrayList<>();

        Map<Integer, List<Edge>> graph = new HashMap<>();
        fillGraphWithNodesAndEdges(edges, graph);

        List<Edge> minSpanningTree;
        Set<Integer> visitedNodes = new HashSet<>(graph.size());
        for (int node : graph.keySet()) {
            if (!visitedNodes.contains(node)) {
                minSpanningTree = prim(node, graph, visitedNodes);
                minSpanningTree.sort(Comparator.comparingInt(Edge::getWeight));
                minSpanningForest.add(minSpanningTree);
            }
        }
        return minSpanningForest;
    }


    public static List<Edge> prim(int startNode, Map<Integer, List<Edge>> graph, Set<Integer> visitedNodes) {
        List<Edge> minSpanningTree = new ArrayList<>();

        PriorityQueue<Edge> sortedEdges = new PriorityQueue<>(Comparator.comparingInt(Edge::getWeight));
        visitedNodes.add(startNode);
        sortedEdges.addAll(graph.get(startNode));

        while (!sortedEdges.isEmpty()) {
            Edge minEdge = sortedEdges.poll();

            int nodeOne = minEdge.getStartNode();
            int nodeTwo = minEdge.getEndNode();
            int nonTreeNode = -1;

            if (visitedNodes.contains(nodeOne) && !visitedNodes.contains(nodeTwo)) {
                nonTreeNode = nodeTwo;
            }

            if (visitedNodes.contains(nodeTwo) && !visitedNodes.contains(nodeOne)) {
                nonTreeNode = nodeOne;
            }

            if (nonTreeNode != -1) {
                visitedNodes.add(nonTreeNode);
                sortedEdges.addAll(graph.get(nonTreeNode));
                minSpanningTree.add(minEdge);
            }
        }

        return minSpanningTree;
    }

    private static void fillGraphWithNodesAndEdges(List<Edge> edges, Map<Integer, List<Edge>> graph) {
        for (Edge edge : edges) {
            int nodeOne = edge.getStartNode();
            int nodeTwo = edge.getEndNode();

            graph.putIfAbsent(nodeOne, new ArrayList<>());
            graph.get(nodeOne).add(edge);

            graph.putIfAbsent(nodeTwo, new ArrayList<>());
            graph.get(nodeTwo).add(edge);
        }
    }

}
