package _7_examPrep;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class p02_Pipelines {

    private static final String source = "source";
    private static final String sink = "sink";

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int agentsCount = Integer.parseInt(reader.readLine());
        int pipelinesCount = Integer.parseInt(reader.readLine());

        Map<String, Set<String>> graph = new LinkedHashMap<>();

        modelBipartiteGraph(graph, reader, agentsCount, pipelinesCount);

        printMaxDistribution(graph, agentsCount, pipelinesCount);
    }

    private static void printMaxDistribution(Map<String, Set<String>> graph, int agentsCount, int pipelinesCount) {
        
        StringBuilder result = new StringBuilder();

        edmondsKarpApproach(graph, agentsCount, result);

        System.out.print(result);
    }

    private static void edmondsKarpApproach
            (Map<String, Set<String>> g, int agents, StringBuilder result) {

        Map<String, String> prevNode = new LinkedHashMap<>();

        while (canReachSink(g, prevNode)) {
            String node = sink;
            while (!node.equals(source)) {
                String previous = prevNode.get(node);
                g.get(previous).remove(node);
                g.get(node).add(previous);
                node = previous;
            }
        }

        int i = 0;
        for (Map.Entry<String, Set<String>> e : g.entrySet()) {
            if (e.getKey().equals(sink)) {
                break;
            }
            if (i > agents) {
                String agent = String.join(" ", e.getValue());

                result.append(agent)
                        .append(" - ")
                        .append(e.getKey())
                        .append(System.lineSeparator());
            }
            i++;
        }
    }

    private static boolean canReachSink(Map<String, Set<String>> g, Map<String, String> prev) {
        return bfs(g, prev);
    }

    private static boolean bfs(Map<String, Set<String>> g, Map<String, String> prev) {
        Set<String> visited = new HashSet<>();
        visited.add(source);
        resetPrevNodesMap(g, prev);

        Deque<String> bfsQueue = new ArrayDeque<>();
        bfsQueue.offer(source);

        while (!bfsQueue.isEmpty()) {
            String node = bfsQueue.poll();
            for (String linkedNode : g.get(node)) {
                if (!visited.contains(linkedNode)) {
                    visited.add(linkedNode);
                    prev.put(linkedNode, node);
                    bfsQueue.offer(linkedNode);
                }
            }
        }

        return visited.contains(sink);
    }

    private static void resetPrevNodesMap(Map<String, Set<String>> graph, Map<String, String> prevNode) {
        for (String key : graph.keySet()) {
            prevNode.put(key, null);
        }
    }

    private static void modelBipartiteGraph
            (Map<String, Set<String>> graph, BufferedReader r, int agents, int pipelines) throws IOException {

        graph.put(source, new LinkedHashSet<>());

        fillGraphWithAgentsFromConsole(graph, r, agents);

        fillGraphWithPipelinesFromConsole(graph, r, pipelines);

        graph.put(sink, new LinkedHashSet<>());

        readAgentSinkRelations(graph, r, agents);
    }

    private static void readAgentSinkRelations
            (Map<String, Set<String>> g, BufferedReader r, int agents) throws IOException {

        for (int i = 1; i <= agents; i++) {

            String[] relations = r.readLine().split(", ");
            String agent = relations[0];

            for (int j = 1; j < relations.length; j++) {
                String pipeline = relations[j];
                g.get(agent).add(pipeline);
            }

        }

    }

    private static void fillGraphWithPipelinesFromConsole
            (Map<String, Set<String>> g, BufferedReader r, int pipelines) throws IOException {

        for (int i = 1; i <= pipelines; i++) {
            String pipelineName = r.readLine();
            g.put(pipelineName, new LinkedHashSet<>(List.of(sink)));
        }

    }

    private static void fillGraphWithAgentsFromConsole
            (Map<String, Set<String>> g, BufferedReader r, int agents) throws IOException {

        for (int i = 1; i <= agents; i++) {
            String agentIdentifier = r.readLine();
            g.put(agentIdentifier, new LinkedHashSet<>());
            g.get(source).add(agentIdentifier);
        }

    }

}
