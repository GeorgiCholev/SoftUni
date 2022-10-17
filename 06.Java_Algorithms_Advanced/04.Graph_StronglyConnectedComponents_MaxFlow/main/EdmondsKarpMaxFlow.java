import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class EdmondsKarpMaxFlow {

    private static int[][] graph;

    public static int findMaxFlow(int[][] targetGraph) {
        graph = targetGraph;
        int[] parents = new int[graph.length];
        Arrays.fill(parents, -1);

        int source = 0;
        int maxFlow = 0;
        while (bfs(source, parents)) {
            int nextNode = graph.length - 1;
            int flow = Integer.MAX_VALUE;

            while (nextNode != source) {
                flow = Math.min(flow, graph[parents[nextNode]][nextNode]);
                nextNode = parents[nextNode];
            }

            maxFlow += flow;
            nextNode = graph.length - 1;
            while (nextNode != source) {
                graph[parents[nextNode]][nextNode] -= flow;
                graph[nextNode][parents[nextNode]] += flow;
                nextNode = parents[nextNode];
            }
        }

        return maxFlow;
    }

    private static boolean bfs(int source, int[] parents) {
        Deque<Integer> nodeQueue = new ArrayDeque<>();
        boolean[] visited = new boolean[graph.length];

        nodeQueue.offer(source);
        visited[source] = true;

        while (!nodeQueue.isEmpty()) {
            int node = nodeQueue.poll();

            for (int linkedNode = 0; linkedNode < graph.length; linkedNode++) {
                int capacity = graph[node][linkedNode];
                if (capacity > 0 && !visited[linkedNode]) {
                    visited[linkedNode] = true;
                    parents[linkedNode] = node;
                    nodeQueue.offer(linkedNode);
                }
            }
        }

        int sink = graph.length - 1;
        return visited[sink];
    }
}
