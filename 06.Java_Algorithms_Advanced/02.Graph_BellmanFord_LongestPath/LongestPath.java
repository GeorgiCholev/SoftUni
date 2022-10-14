package _2_Graph_Alg_BellmanFord;

import java.util.*;

public class LongestPath {

    static List<Edge> edges;
    static List<List<Integer>> graph;

    public static void main(String[] args) {
        edges = new ArrayList<>();
        graph = new ArrayList<>();

        Scanner scanner = new Scanner (System.in);
        fillGraph(scanner);

        int start = Integer.parseInt(scanner.nextLine());
        int end = Integer.parseInt(scanner.nextLine());

        Deque<Integer> topSort = topSort(start);

        System.out.println(longestPath(topSort, start, end));
    }

    private static int longestPath(Deque<Integer> topSort, int start, int end) {
        int[] dist = new int[graph.size()];
        Arrays.fill(dist, Integer.MIN_VALUE);
        dist[start] = 0;

        while (!topSort.isEmpty()) {
            int node = topSort.pop();
            for (Edge edge : edges) {
                int from = edge.getSource();
                int to = edge.getDest();
                int weight = edge.getWeight();

                if (node == from && dist[from] > Integer.MIN_VALUE) {
                    int candidateDist = dist[from] + weight;

                    if (dist[to] < candidateDist) {
                        dist[to] = candidateDist;
                    }

                }

            }
        }
        return dist[end];
    }

    private static Deque<Integer> topSort(int startNode) {
        Set<Integer> visited = new HashSet<>();
        Deque<Integer> sorted = new ArrayDeque<>();

        depthFirstSearch(startNode, visited, sorted);

        return sorted;
    }

    private static void depthFirstSearch(int node, Set<Integer> visited, Deque<Integer> sorted) {
        if (visited.contains(node)) {
            return;
        }

        visited.add(node);
        for (int linkedNode : graph.get(node)) {
            depthFirstSearch(linkedNode, visited, sorted);
        }
        sorted.push(node);
    }

    private static void fillGraph(Scanner s) {

        int numberOfNodes = Integer.parseInt(s.nextLine());

        for (int i = 0; i < numberOfNodes + 1; i++) {
            graph.add(new ArrayList<>());
        }

        int numberOfEdges = Integer.parseInt(s.nextLine());
        for (int i = 0; i < numberOfEdges; i++) {
            int[] components = Arrays.stream(s.nextLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int source = components[0];
            int dest = components[1];
            int weight = components[2];
            edges.add(new Edge(source, dest, weight));
            graph.get(source).add(dest);
        }
    }

    static class Edge {
        private final int source;
        private final int dest;
        private final int weight;

        public Edge(int source, int dest, int weight) {
            this.source = source;
            this.dest = dest;
            this.weight = weight;
        }

        public int getSource() {
            return source;
        }

        public int getDest() {
            return dest;
        }

        public int getWeight() {
            return weight;
        }
    }
}
