package _5_Lab_Graph;

import java.util.List;

    // Implement DFS algorithm (Depth-First-Search) to traverse a graph and find its connected
    // components (nodes connected to each other either directly, or through other nodes).
    // The graph nodes are numbered from 0 to n-1.
    // The graph comes from the console with every line representing directions of corresponding node.

public class ConnectedComponents {
    public static void main(String[] args) {

        List<List<Integer>> graphOfNodes = GraphAlgorithms.fillGraphFromConsole();

        boolean[] visited = new boolean[graphOfNodes.size()];
        for (int i = 0; i < graphOfNodes.size(); i++) {
            StringBuilder output = new StringBuilder("Connected component: ");
            if (!visited[i]) {
                // GraphAlgorithms.bfs implements Breadth-First Search
                GraphAlgorithms.dfs(i, graphOfNodes, visited, output);
                System.out.println(output);
            }
        }

    }
}
