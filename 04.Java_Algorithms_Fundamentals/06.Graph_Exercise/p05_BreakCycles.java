package _6_Exercise_Graph;

import java.util.*;
import java.util.stream.Collectors;

public class p05_BreakCycles {
//    remove edges that lead to cycles starting with the smallest of them in alphabetical order
//    (the smallest start vertex in alphabetical order and the smallest end vertex in alphabetical order.)


    static List<String> edgesToRemove = new ArrayList<>();

    public static void main(String[] args) {
        Map<String, List<String>> graph = new TreeMap<>();
        fillGraph(graph);

        Set<String> visited = new HashSet<>();
        String prevNode = "";
        for (String node : graph.keySet()) {
            if (!visited.contains(node)) {
                depthFirstSearch(node, prevNode, graph, visited);
                prevNode = node;
            }
        }

        System.out.print("Edges to remove: " + edgesToRemove.size() + System.lineSeparator() +
                edgesToRemove.stream().sorted().collect(Collectors.joining(System.lineSeparator())));
    }

    private static void depthFirstSearch
            (String node, String prevNode, Map<String, List<String>> graph, Set<String> visited) {

        visited.add(node);

        for (int i = 0; i < graph.get(node).size(); i++) {
            String linkedNode = graph.get(node).get(i);
            if (!linkedNode.equals(prevNode)) {
                if (linkedNodeFormsCycle(linkedNode, node, graph)) {
                    i--;
                }
            }
        }
    }

    private static boolean linkedNodeFormsCycle(String linkedNode, String source, Map<String, List<String>> graph) {
        int index = graph.get(source).indexOf(linkedNode);
        graph.get(source).remove(linkedNode);
        int indexOfSource = graph.get(linkedNode).indexOf(source);
        graph.get(linkedNode).remove(source);

        Deque<String> queue = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();

        queue.offer(source);
        while (!queue.isEmpty()) {
            String nextNode = queue.poll();

            visited.add(nextNode);
            for (String n : graph.get(nextNode)) {
                if (n.equals(linkedNode)) {
                    edgesToRemove.add(source + " - " + linkedNode);
                    return true;
                }

                if (!visited.contains(n)) {
                    visited.add(n);
                    queue.offer(n);
                }
            }
        }

        graph.get(source).add(index, linkedNode);
        graph.get(linkedNode).add(indexOfSource, source);
        return false;
    }

    private static void fillGraph(Map<String, List<String>> graph) {
        Scanner scanner = new Scanner(System.in);
        String input;
        while (!(input = scanner.nextLine()).equals("")) {
            String node = input.split(" -> ")[0];
            String[] linkedNodes = input.split(" -> ")[1].split(" ");

            graph.put(node, new ArrayList<>(Arrays.asList(linkedNodes)));
        }
    }
}
