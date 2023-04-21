package _8_Exam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class p02_Creep {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int zones = Integer.parseInt(reader.readLine());
        int vines = Integer.parseInt(reader.readLine());

        List<Vine> hiveMind = new ArrayList<>();

        int[][] graph = new int[zones][zones];
        readHiveMindRelationsFromTheConsole(reader, vines, graph);

        List<Vine> minSpanningCoverage = new ArrayList<>();

        int totalDistance = findDistanceInMinSpanningVinesInHiveMind(hiveMind, zones, minSpanningCoverage, graph);

        String spanOutput = minSpanningCoverage.stream()
                .map(Vine::zonesCovered)
                .collect(Collectors.joining(System.lineSeparator()));

        System.out.println(spanOutput + System.lineSeparator() + totalDistance);
    }

    private static int findDistanceInMinSpanningVinesInHiveMind(List<Vine> hiveMind, int z, List<Vine> minSpan, int[][] graph) {
        int[] prev = new int[z];
        setEachZonePrevAsItself(z, prev);

        return kruskalApproach(hiveMind, minSpan, prev, graph);
    }

    private static int kruskalApproach(List<Vine> hiveMind, List<Vine> minSpan, int[] prev, int[][] graph) {
        PriorityQueue<Vine> sortedVines = new PriorityQueue<>(Comparator.comparingInt(Vine::getDistance));

        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[i].length; j++) {
                if (graph[i][j] != 0) {
                    sortedVines.offer(new Vine(i, j, graph[i][j]));
                }
            }
        }

        int totalDistance = 0;

        while (!sortedVines.isEmpty()) {
            Vine minVine = sortedVines.poll();
            int zOne = minVine.zoneOne;
            int zTwo = minVine.zoneTwo;

            int zOneRoot = findRoot(prev, zOne);
            int zTwoRoot = findRoot(prev, zTwo);

            if (zOneRoot != zTwoRoot) {
                totalDistance += minVine.getDistance();
                minSpan.add(minVine);
                prev[zTwoRoot] = zOneRoot;
            }
        }

        return totalDistance;
    }

    private static int findRoot(int[] prev, int zone) {
        int root = prev[zone];
        while (root != prev[root]) {
            root = prev[root];
        }
        return root;
    }

    private static void setEachZonePrevAsItself(int z, int[] prev) {
        for (int i = 0; i < z; i++) {
            prev[i] = i;
        }
    }

    private static void readHiveMindRelationsFromTheConsole
            (BufferedReader reader, int vines, int[][] graph) throws IOException {
        for (int i = 0; i < vines; i++) {
            int[] components = Arrays.stream(reader.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            int zoneOne = components[0];
            int zoneTwo = components[1];
            int distance = components[2];

            graph[zoneOne][zoneTwo] = distance;
        }
    }

    static class Vine {
        int zoneOne;
        int zoneTwo;
        int distance;

        public Vine(int zoneOne, int zoneTwo, int distance) {
            this.zoneOne = zoneOne;
            this.zoneTwo = zoneTwo;
            this.distance = distance;
        }

        public int getDistance() {
            return distance;
        }

        public String zonesCovered() {
            return zoneOne + " " + zoneTwo;
        }
    }

}