package _8_Exam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class p01_TrainsPartTwo {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int trainDepots = Integer.parseInt(reader.readLine());
        int trainTracks = Integer.parseInt(reader.readLine());

        Map<Integer, Map<Integer, Integer>> graph = new HashMap<>();

        int[] startAndEnd = Arrays.stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int start = startAndEnd[0];
        int end = startAndEnd[1];

        readGraphRelationsFromConsole(reader, trainTracks, graph);

        Deque<Integer> shortestPath = new ArrayDeque<>();

        int totalDistance = findTotalDistance(start, end, graph, shortestPath);

        String outputForShortestPath = shortestPath.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(" "));

        System.out.print(outputForShortestPath + System.lineSeparator() + totalDistance);
    }

    private static int findTotalDistance
            (int start, int end, Map<Integer, Map<Integer, Integer>> graph, Deque<Integer> shortestPath) {

        boolean[] visited = new boolean[graph.size()];
        int[] prev = new int[graph.size()];
        Arrays.fill(prev, -1);
        int[] distanceFromStart = new int[graph.size()];
        Arrays.fill(distanceFromStart, Integer.MAX_VALUE);

        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.comparingInt(n -> distanceFromStart[n]));
        queue.offer(start);
        distanceFromStart[start] = 0;

        while (!queue.isEmpty()) {
            int track = queue.poll();
            visited[track] = true;

            for (Map.Entry<Integer, Integer> entry : graph.get(track).entrySet()) {
                int nextTrack = entry.getKey();
                int distanceBetweenTracks = entry.getValue();

                if (!visited[nextTrack]) {
                    int candidateDistance = distanceFromStart[track] + distanceBetweenTracks;

                    if (candidateDistance < distanceFromStart[nextTrack]) {
                        prev[nextTrack] = track;
                        distanceFromStart[nextTrack] = candidateDistance;
                        queue.offer(nextTrack);
                    }
                }

            }
        }

        findShortestPath(end, prev, shortestPath);

        return distanceFromStart[end];
    }

    private static void findShortestPath(int e, int[] prev, Deque<Integer> path) {
        int nextTrack = e;

        while (nextTrack != -1) {
            path.push(nextTrack);
            nextTrack = prev[nextTrack];
        }
    }

    private static void readGraphRelationsFromConsole
            (BufferedReader r, int trainTracks, Map<Integer, Map<Integer, Integer>> graph) throws IOException {
        for (int i = 0; i < trainTracks; i++) {
            int[] components = Arrays.stream(r.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            int trackOne = components[0];
            int trackTwo = components[1];
            int distance = components[2];

            graph.putIfAbsent(trackOne, new HashMap<>());
            graph.get(trackOne).put(trackTwo, distance);
            graph.putIfAbsent(trackTwo, new HashMap<>());
            graph.get(trackTwo).put(trackOne, distance);
        }
    }
}
