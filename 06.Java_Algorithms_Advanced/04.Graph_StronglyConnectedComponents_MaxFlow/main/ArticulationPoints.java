import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArticulationPoints {

    private static List<Integer>[] graph;
    private static List<Integer> articulationPoints;
    private static boolean[] visited;
    private static int[] parents;
    private static int[] depths;
    private static int[] lowPoints;

    public static List<Integer> findArticulationPoints(List<Integer>[] targetGraph) {
        graph = targetGraph;
        articulationPoints = new ArrayList<>();
        visited = new boolean[graph.length];
        parents = new int[graph.length];
        Arrays.fill(parents, -1);
        depths = new int[graph.length];
        lowPoints = new int[graph.length];

        dfs(0, 1);

        return articulationPoints;
    }

    private static void dfs(int node, int depth) {
        visited[node] = true;
        depths[node] = depth;
        lowPoints[node] = depth;

        int linkedNodesCount = 0;
        boolean isArticulationPoint = false;

        for (int linkedNode : graph[node]) {

            if (!visited[linkedNode]) {

                parents[linkedNode] = node;
                dfs(linkedNode, depth + 1);
                linkedNodesCount++;

                if (lowPoints[linkedNode] >= depths[node]) {
                    isArticulationPoint = true;
                }

                lowPoints[node] = Math.min(lowPoints[node], lowPoints[linkedNode]);

            } else if (linkedNode != parents[node]) {
                lowPoints[node] = Math.min(lowPoints[node], depths[linkedNode]);
            }

        }

        if ((parents[node] != -1 && isArticulationPoint) || (parents[node] == -1 && linkedNodesCount > 1)) {
            articulationPoints.add(node);
        }
    }
}
