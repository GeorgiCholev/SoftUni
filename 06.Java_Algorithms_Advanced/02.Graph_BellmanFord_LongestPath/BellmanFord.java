package _2_Graph_Alg_BellmanFord;

import java.util.*;
import java.util.stream.Collectors;

public class BellmanFord {

//    Test
//    6
//    8
//    4 6 -1
//    3 6 2
//    4 3 -4
//    2 4 1
//    1 2 8
//    6 5 -2
//    5 3 1
//    1 3 10
//    1
//    6

    private static List<Edge> edgesInGraph;

    public static void main(String[] args) {
        edgesInGraph = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        int nodes = Integer.parseInt(scanner.nextLine());
        fillGraph(scanner);

        int start = Integer.parseInt(scanner.nextLine());
        int end = Integer.parseInt(scanner.nextLine());

        Deque<Integer> sequenceOfNodes;
        try {
            sequenceOfNodes = bellmanFord(nodes, start, end);
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
            return;
        }

        int pathWeight = sequenceOfNodes.pop();
        String sequence = sequenceOfNodes.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(" "));

        System.out.print(sequence +
                System.lineSeparator() +
                pathWeight);
    }

    private static Deque<Integer> bellmanFord(int nodes, int start, int end) {

        int[] distances = new int[nodes + 1];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[start] = 0;

        int[] previous = new int[nodes + 1];
        Arrays.fill(previous, -1);

        for (int i = 0; i < nodes - 1; i++) {
            for (Edge edge : edgesInGraph) {
                relaxation(edge, previous, distances);
            }
        }

        findNegativeCycleInGraph(distances, nodes);

        Deque<Integer> sequence = new ArrayDeque<>();
        sequence.push(end);
        int nextNode = previous[end];
        while (nextNode != -1) {
            sequence.push(nextNode);
            nextNode = previous[nextNode];
        }

        int pathWeight = distances[end];
        sequence.push(pathWeight);

        return sequence;
    }

    private static void findNegativeCycleInGraph(int[] dist, int n) {
        for (int i = 0; i < n - 1; i++) {
            for (Edge e : edgesInGraph) {
                int from = e.getSource();
                int to = e.getDest();
                int weight = e.getWeight();

                if (dist[from] < Integer.MAX_VALUE) {
                    int candidateDist = dist[from] + weight;

                    if (candidateDist < dist[to]) {
                        throw new IllegalStateException("Negative Cycle Detected");
                    }

                }

            }
        }
    }

    private static void relaxation(Edge e, int[] prev, int[] dist) {
        int from = e.getSource();
        int to = e.getDest();
        int weight = e.getWeight();

        if (dist[from] < Integer.MAX_VALUE) {
            int candidateDist = dist[from] + weight;
            if (candidateDist < dist[to]) {
                dist[to] = candidateDist;
                prev[to] = from;
            }
        }
    }

    private static void fillGraph(Scanner s) {
        int edges = Integer.parseInt(s.nextLine());
        for (int i = 0; i < edges; i++) {
            int[] components = Arrays.stream(s.nextLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int source = components[0];
            int dest = components[1];
            int weight = components[2];
            edgesInGraph.add(new Edge(source, dest, weight));
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
