package _3_Graph_Exercise;

import java.util.*;

public class p02_ModifiedKruskal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner (System.in);

        int nodesCount = Integer.parseInt(scanner.nextLine().split(" ")[1]);
        int edgesCount = Integer.parseInt(scanner.nextLine().split(" ")[1]);

        List<Edge> edges = new ArrayList<>();
        readEdgesFromConsole(edges, edgesCount, scanner);

        int forestWeight = kruskal(edges, nodesCount);
        System.out.println("Minimum spanning forest weight: " + forestWeight);
    }

    private static int kruskal(List<Edge> e, int n) {
        int forestWeight = 0;

        PriorityQueue<Edge> sortedEdges = new PriorityQueue<>(Comparator.comparingInt(Edge::getWeight));
        sortedEdges.addAll(e);

        Map<Integer, Integer> parents = new HashMap<>();
        for (int i = 0; i < n; i++) {
            parents.put(i, i);
        }

        while (!sortedEdges.isEmpty()) {
            Edge minEdge = sortedEdges.poll();
            int nodeOne = minEdge.nodeOne;
            int nodeTwo = minEdge.nodeTwo;
            int weight = minEdge.weight;

            int firstRoot = findRoot(nodeOne, parents);
            int secondRoot = findRoot(nodeTwo, parents);

            if (firstRoot != secondRoot) {
                forestWeight += weight;
                parents.put(secondRoot, firstRoot);
            }
        }
        return forestWeight;
    }

    private static int findRoot(int node, Map<Integer, Integer> p) {
        int root = p.get(node);
        while (root != p.get(root)) {
            root = p.get(root);
        }
        return root;
    }

    private static void readEdgesFromConsole(List<Edge> e, int edgesCount, Scanner s) {
        for (int i = 0; i < edgesCount; i++) {
            int[] components = Arrays.stream(s.nextLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int nodeOne = components[0];
            int nodeTwo = components[1];
            int weight = components[2];
            Edge edge = new Edge(nodeOne, nodeTwo, weight);

            e.add(edge);
        }
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
