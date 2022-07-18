package _9_ExamPrep;

import java.util.*;
import java.util.stream.IntStream;

public class p03_Molecules {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int numberOfMolecules = Integer.parseInt(scanner.nextLine());
        int moleculeConnections = Integer.parseInt(scanner.nextLine());

        Map<Integer, List<Integer>> moleculeGraph = new LinkedHashMap<>(numberOfMolecules);
        Map<Integer, Boolean> visitedNodes = new LinkedHashMap<>(numberOfMolecules);

        IntStream.rangeClosed(1, numberOfMolecules)
                .forEach(node -> {
                    moleculeGraph.put(node, new ArrayList<>());
                    visitedNodes.put(node, Boolean.FALSE);
                });

        for (int i = 0; i < moleculeConnections; i++) {
            String[] relation = scanner.nextLine().split("\\s+");

            int source = Integer.parseInt(relation[0]);
            int destination = Integer.parseInt(relation[1]);

            moleculeGraph.get(source).add(destination);
        }

        int startingNode = scanner.nextInt();

        ArrayDeque<Integer> bfsQueue = new ArrayDeque<>();
        bfsQueue.offer(startingNode);

        while (!bfsQueue.isEmpty()) {

            int node = bfsQueue.poll();
            visitedNodes.put(node, Boolean.TRUE);

            for (int linkedNode : moleculeGraph.get(node)) {
                if (!visitedNodes.get(linkedNode)) {
                    bfsQueue.offer(linkedNode);
                }
            }
        }

        visitedNodes.entrySet().stream()
                .filter(e -> !e.getValue())
                .forEach(e -> System.out.print(e.getKey() + " "));
    }
}

