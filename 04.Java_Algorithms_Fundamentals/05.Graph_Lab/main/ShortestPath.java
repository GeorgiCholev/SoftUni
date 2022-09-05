import java.util.*;
import java.util.stream.Collectors;

public class ShortestPath {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        List<List<Integer>> graph = fillGraphWithInputConnections(scanner);

        int source = scanner.nextInt();
        int destination = scanner.nextInt();

        Deque<Integer> shortestPath = findShortestPath(source, destination, graph);
        int shortestPathEdges = shortestPath.size() - 1;
        String shortestPathSequence = shortestPath.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(" "));

        System.out.println("Shortest path length is: " + shortestPathEdges + "\n" + shortestPathSequence);
    }

    private static Deque<Integer> findShortestPath(int source, int destination, List<List<Integer>> graph) {

        int[] predecessor = new int[graph.size()];
        predecessor[source] = -1;
        findVerticesPredecessors(source, destination, graph, predecessor);

        Deque<Integer> shortestPath = new ArrayDeque<>();

        int currentVertex = destination;
        while (currentVertex != -1) {
            shortestPath.push(currentVertex);
            currentVertex = predecessor[currentVertex];
        }

        return shortestPath;
    }

    private static void findVerticesPredecessors(int source, int destination, List<List<Integer>> graph, int[] predecessor) {
        boolean[] isVisited = new boolean[graph.size()];
        isVisited[source] = true;

        Deque<Integer> verticesQueue = new ArrayDeque<>();
        verticesQueue.offer(source);

        while (!verticesQueue.isEmpty()) {
            int currentVertex = verticesQueue.poll();

            for (int linkedVertex : graph.get(currentVertex)) {
                if (!isVisited[linkedVertex]) {
                    predecessor[linkedVertex] = currentVertex;

                    if (linkedVertex == destination) {
                        return;
                    }

                    isVisited[linkedVertex] = true;
                    verticesQueue.offer(linkedVertex);
                }
            }
        }
    }

    private static List<List<Integer>> fillGraphWithInputConnections(Scanner scanner) {
        List<List<Integer>> graph = initializeGraphWithSizeFromInput(scanner);

        int edges = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < edges; i++) {
            int[] connection = Arrays.stream(scanner.nextLine().split("\\s"))
                    .mapToInt(Integer::parseInt).toArray();
            int predecessor = connection[0];
            int successor = connection[1];
            graph.get(predecessor).add(successor);
        }
        return graph;
    }

    private static List<List<Integer>> initializeGraphWithSizeFromInput(Scanner scanner) {
        int vertices = Integer.parseInt(scanner.nextLine());
        List<List<Integer>> graph = new ArrayList<>(vertices);

        for (int i = 0; i < vertices + 1; i++) {    // Ignore index 0
            graph.add(new ArrayList<>());
        }
        return graph;
    }
}
