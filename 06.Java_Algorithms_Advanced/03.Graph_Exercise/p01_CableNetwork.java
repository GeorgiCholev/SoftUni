package _3_Graph_Exercise;

import java.util.*;
import java.util.stream.Collectors;

public class p01_CableNetwork {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int budget = Integer.parseInt(scanner.nextLine().split(" ")[1]);
        int nodesCount = Integer.parseInt(scanner.nextLine().split(" ")[1]);
        int edgesCount = Integer.parseInt(scanner.nextLine().split(" ")[1]);

        Map<Integer, List<Edge>> graph = new HashMap<>();
        Set<Integer> visited = new HashSet<>();

        readEdgesFromConsole(scanner, edgesCount, graph, visited);

        Set<Edge> cutSetEdges = new HashSet<>();
        addEdgesThatFormCut(cutSetEdges, visited, graph);

        PriorityQueue<Edge> sortedEdges = new PriorityQueue<>(Comparator.comparingInt(Edge::getWeight));
        sortedEdges.addAll(cutSetEdges);

        int cost = prim(budget, visited, sortedEdges, nodesCount, graph);
        System.out.println("Budget used: " + cost);
    }

    private static int prim(int budget, Set<Integer> visited, PriorityQueue<Edge> sortedEdges,
                            int nodesCount, Map<Integer, List<Edge>> graph) {
        int cost = 0;
        while ((!sortedEdges.isEmpty()) && (visited.size() < nodesCount)) {
            Edge minEdge = sortedEdges.poll();
            int nodeOne = minEdge.nodeOne;
            int nodeTwo = minEdge.nodeTwo;
            int weight = minEdge.weight;
            int notConnectedNode = -1;

            if (cost + weight > budget) {
                break;
            }

            if (visited.contains(nodeOne) && !visited.contains(nodeTwo)) {
                notConnectedNode = nodeTwo;
            }

            if (visited.contains(nodeTwo) && !visited.contains(nodeOne)) {
                notConnectedNode = nodeOne;
            }

            if (notConnectedNode != -1) {

                visited.add(notConnectedNode);
                List<Edge> notConnectedEdges = graph.get(notConnectedNode)
                        .stream()
                        .filter(e -> visited.contains(e.nodeOne) && !visited.contains(e.nodeTwo) ||
                                (visited.contains(e.nodeTwo) && !visited.contains(e.nodeOne)))
                        .collect(Collectors.toList());

                sortedEdges.addAll(notConnectedEdges);
                cost += weight;
            }
        }
        return cost;
    }

    private static void addEdgesThatFormCut(Set<Edge> cutSetEdges, Set<Integer> visited,
                                            Map<Integer, List<Edge>> graph) {

        for (Map.Entry<Integer, List<Edge>> node : graph.entrySet()) {
            for (Edge edge : node.getValue()) {
                int nodeOne = edge.nodeOne;
                int nodeTwo = edge.nodeTwo;

                if (visited.contains(nodeOne) && !visited.contains(nodeTwo) ||
                        (visited.contains(nodeTwo) && !visited.contains(nodeOne))) {
                    cutSetEdges.add(edge);
                }
            }
        }
    }

    private static void readEdgesFromConsole(Scanner scanner, int edgesCount, Map<Integer, List<Edge>> graph,
                                             Set<Integer> visited) {
        for (int i = 0; i < edgesCount; i++) {
            String[] components = scanner.nextLine().split(" ");
            int nodeOne = Integer.parseInt(components[0]);
            int nodeTwo = Integer.parseInt(components[1]);
            int weight = Integer.parseInt(components[2]);

            Edge e = new Edge(nodeOne, nodeTwo, weight);
            addEdgeToGraph(graph, nodeOne, nodeTwo, e);
            addVisitedNodes(visited, components, e);
        }
    }

    private static void addVisitedNodes(Set<Integer> visited, String[] components, Edge e) {
        if (components.length == 4) {
            visited.add(e.nodeOne);
            visited.add(e.nodeTwo);
        }
    }

    private static void addEdgeToGraph(Map<Integer, List<Edge>> graph, int one, int two, Edge e) {
        graph.putIfAbsent(one, new ArrayList<>());
        graph.get(one).add(e);
        graph.putIfAbsent(two, new ArrayList<>());
        graph.get(two).add(e);
    }

    static class Edge {
        int nodeOne;
        int nodeTwo;
        int weight;

        public Edge(int nodeOne, int nodeTwo, int weight) {
            this.nodeOne = nodeOne;
            this.nodeTwo = nodeTwo;
            this.weight = weight;
        }

        public int getWeight() {
            return weight;
        }
    }
}
