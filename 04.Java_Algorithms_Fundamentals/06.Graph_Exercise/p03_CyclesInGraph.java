package _6_Exercise_Graph;

import java.util.*;

public class p03_CyclesInGraph {

    public static void main(String[] args) {

        Map<Character, List<Character>> graph = new HashMap<>();
        readUndirectedGraphFromConsole(graph);

        Set<Character> visitedNodes = new HashSet<>();
        Set<Character> currentTraversal = new HashSet<>();

        Character nodeToStartFrom = graph.entrySet().iterator().next().getKey();
        try {
            depthFirstSearch(nodeToStartFrom, '-', graph, visitedNodes, currentTraversal);
            System.out.println("Acyclic: Yes");
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }

    }

    private static void depthFirstSearch(char node, char prevNode, Map<Character, List<Character>> graph,
                                         Set<Character> visitedNodes, Set<Character> traversal) {

        if (traversal.contains(node)) {
            throw new IllegalStateException("Acyclic: No");
        }
        if (visitedNodes.contains(node)) {
            return;
        }

        traversal.add(node);
        visitedNodes.add(node);

        graph.get(node).remove(Character.valueOf(prevNode));

        for (char linkedNode : graph.get(node)) {
            depthFirstSearch(linkedNode, node, graph, visitedNodes, traversal);
        }
        traversal.remove(node);
    }

    private static void readUndirectedGraphFromConsole(Map<Character, List<Character>> graph) {
        Scanner scanner = new Scanner(System.in);
        String input;
        while (!(input = scanner.nextLine()).equals("End")) {
            String[] relationship = input.split("-");
            char nodeOne = relationship[0].charAt(0);
            char nodeTwo = relationship[1].charAt(0);

            graph.putIfAbsent(nodeOne, new ArrayList<>());
            graph.putIfAbsent(nodeTwo, new ArrayList<>());
            graph.get(nodeOne).add(nodeTwo);
            graph.get(nodeTwo).add(nodeOne);
        }
    }
}
