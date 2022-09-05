import java.util.*;

public class TopologicalSort {

    public static Collection<String> topSort(Map<String, List<String>> graph) {
        List<String> sorted = new ArrayList<>();

        Map<String, Integer> nodeDependencies = getNodesDependencies(graph);

        while (!graph.isEmpty()) {
            String nodeToRemove = findNodeWithNoDependencies(nodeDependencies, graph);

            if (nodeToRemove == null) {
                throw new IllegalArgumentException();
            }

            removeEdgesToLinkedNodes(nodeToRemove, graph, nodeDependencies);
            graph.remove(nodeToRemove);
            sorted.add(nodeToRemove);
        }

        return sorted;
    }

    public static Collection<String> topSortThroughDfs(Map<String, List<String>> graph) {

        Deque<String> sorted = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();
        Set<String> partOfCurrentTraversal = new HashSet<>();

        for (Map.Entry<String, List<String>> entry : graph.entrySet()) {
            dfs(entry.getKey(), graph, visited, sorted, partOfCurrentTraversal);
        }

        return sorted;
    }




    private static void dfs(String node, Map<String, List<String>> graph, Set<String> visited,
                            Deque<String> sorted, Set<String> partOfCurrentTraversal) {

        if (!visited.contains(node)) {
            visited.add(node);
            partOfCurrentTraversal.add(node);

            for (String linkedNode : graph.get(node)) {

                if (partOfCurrentTraversal.contains(linkedNode)) {
                    throw new IllegalArgumentException();
                }

                if (!visited.contains(linkedNode)) {
                    dfs(linkedNode, graph, visited, sorted, partOfCurrentTraversal);
                }
            }
            sorted.push(node);
            partOfCurrentTraversal.remove(node);
        }
    }

    private static void removeEdgesToLinkedNodes
            (String nodeToRemove, Map<String, List<String>> graph, Map<String, Integer> nodeDependencies) {

        for (String linkedNode : graph.get(nodeToRemove)) {
            nodeDependencies.put(linkedNode, nodeDependencies.get(linkedNode) - 1);
        }
    }

    private static String findNodeWithNoDependencies
            (Map<String, Integer> nodeDependencies, Map<String, List<String>> graph) {
        return graph.keySet().stream()
                .filter(k -> nodeDependencies.get(k) == 0)
                .findFirst()
                .orElse(null);
    }

    private static Map<String, Integer> getNodesDependencies(Map<String, List<String>> graph) {
        Map<String, Integer> nodeDependencies = new LinkedHashMap<>();

        for (Map.Entry<String, List<String>> node : graph.entrySet()) {
            nodeDependencies.putIfAbsent(node.getKey(), 0);
            for (String linkedNode : node.getValue()) {
                Integer count = nodeDependencies.get(linkedNode) == null ? 1 : nodeDependencies.get(linkedNode) + 1;
                nodeDependencies.put(linkedNode, count);
            }
        }
        return nodeDependencies;
    }

}
