import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class ConnectedComponents {

    public static List<Deque<Integer>> getConnectedComponents(List<List<Integer>> graph) {
        List<Deque<Integer>> connectedComponents = new ArrayList<>();
        boolean[] isVisited = new boolean[graph.size()];

        for (int node = 0; node < graph.size(); node++) {
            if (!isVisited[node]) {
                connectedComponents.add(new ArrayDeque<>());
                Deque<Integer> newComponent = connectedComponents.get(connectedComponents.size() - 1);
                dfs(node, graph, isVisited, newComponent);
//              bfs(node, graph, isVisited, newComponent);
            }
        }
        return connectedComponents;
    }

    private static void dfs(int node, List<List<Integer>> graph, boolean[] isVisited, Deque<Integer> components) {
        if (!isVisited[node]) {
            isVisited[node] = true;
        } else {
            return;
        }

        List<Integer> connectedNodes = graph.get(node);
        for (int nextNode : connectedNodes) {
            dfs(nextNode, graph, isVisited, components);
        }
        components.offer(node);
    }

    private static void bfs(int node, List<List<Integer>> graph, boolean[] isVisited, Deque<Integer> components) {
        Deque<Integer> graphQueue = new ArrayDeque<>();
        graphQueue.offer(node);
        isVisited[node] = true;

        while (!graphQueue.isEmpty()) {
            int nextNode = graphQueue.poll();
            components.offer(nextNode);
            List<Integer> connectedNodes = graph.get(nextNode);
            for (int connectedNode : connectedNodes) {
                if (!isVisited[connectedNode]) {
                    graphQueue.offer(connectedNode);
                    isVisited[connectedNode] = true;
                }
            }
        }
    }
}
