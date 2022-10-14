import java.util.*;

public class StronglyConnectedComponents {

    private static List<Integer>[] graph;
    private static List<Integer>[] reversedGraph;
    private static List<List<Integer>> stronglyConnectedComponents;

    public static List<List<Integer>> findStronglyConnectedComponents(List<Integer>[] targetGraph) {
        stronglyConnectedComponents = new ArrayList<>();
        graph = targetGraph;
        boolean[] visited = new boolean[graph.length];
        Deque<Integer> stack = new ArrayDeque<>();

        for (int node = 0; node < graph.length; node++) {
            if (!visited[node]) {
                dfs(node, visited, stack);
            }
        }

        reverseGraph();

        visited = new boolean[reversedGraph.length];
        while (!stack.isEmpty()) {
            int nextNode = stack.pop();

            if (!visited[nextNode]) {
                stronglyConnectedComponents.add(new ArrayList<>());
                reversedDfs(nextNode, visited);
            }
        }

        return stronglyConnectedComponents;
    }

    private static void reversedDfs(int node, boolean[] visited) {
        if (visited[node]) {
            return;
        }

        visited[node] = true;
        for (int linkedNode : reversedGraph[node]) {
            reversedDfs(linkedNode, visited);
        }
        stronglyConnectedComponents.get(stronglyConnectedComponents.size() - 1).add(node);
    }

    @SuppressWarnings("unchecked")
    private static void reverseGraph() {
        reversedGraph = new ArrayList[graph.length];
        for (int i = 0; i < reversedGraph.length; i++) {
            reversedGraph[i] = new ArrayList<>();
        }

        for (int node = 0; node < graph.length; node++) {
            for (int linkedNode : graph[node]) {
                List<Integer> linkedNodeRelations = reversedGraph[linkedNode];
                linkedNodeRelations.add(node);
            }
        }
    }

    private static void dfs(int node, boolean[] visited, Deque<Integer> stack) {
        if (visited[node]) {
            return;
        }
        visited[node] = true;

        for (int linkedNode : graph[node]) {
            dfs(linkedNode, visited, stack);
        }
        stack.push(node);
    }
}
