package _7_examPrep;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class p01_VampireLabyrinth {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int numberOfVampireLairs = Integer.parseInt(reader.readLine());
        int numberOfWays = Integer.parseInt(reader.readLine());

        int[][] labyrinth = new int[numberOfVampireLairs][numberOfVampireLairs];

        String[] components = reader.readLine().split("\\s+");
        int landingPlace = Integer.parseInt(components[0]);
        int temple = Integer.parseInt(components[1]);

        readWaysFromConsole(reader, numberOfWays, labyrinth);

        Deque<Integer> leastCrowdedPathStack = new ArrayDeque<>();
        int vampireCount = findTotalVampireCount(labyrinth, landingPlace, temple, leastCrowdedPathStack);

        String leastCrowdedPath = leastCrowdedPathStack.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(" "));

        System.out.print(leastCrowdedPath + System.lineSeparator() + vampireCount);
    }

    private static void readWaysFromConsole(BufferedReader r, int numberOfWays, int[][] labyrinth) throws IOException {
        for (int i = 0; i < numberOfWays; i++) {
            String[] components = r.readLine().split("\\s+");
            int from = Integer.parseInt(components[0]);
            int to = Integer.parseInt(components[1]);
            int vampireCount = Integer.parseInt(components[2]);

            labyrinth[from][to] = vampireCount;
            labyrinth[to][from] = vampireCount;
        }
    }

    private static int findTotalVampireCount(int[][] labyrinth, int start, int end, Deque<Integer> minPath) {

        int[] vampireCountByLair = new int[labyrinth.length];

        findLeastCrowdedVampirePath(labyrinth, start, end, vampireCountByLair, minPath);

        return vampireCountByLair[end];
    }

    private static void findLeastCrowdedVampirePath(int[][] labyrinth, int s, int e, int[] count, Deque<Integer> p) {
        boolean[] visitedLairs = new boolean[labyrinth.length];

        int[] prevLairs = new int[labyrinth.length];
        Arrays.fill(prevLairs, -1);

        Arrays.fill(count, Integer.MAX_VALUE);
        count[s] = 0;

        dijkstraApproach(labyrinth, s, visitedLairs, prevLairs, count);

        recreateLeastCrowdedPath(e, p, prevLairs);
    }

    private static void recreateLeastCrowdedPath(int e, Deque<Integer> p, int[] prev) {
        p.push(e);

        int lair = prev[e];
        while (lair != -1) {
            p.push(lair);
            lair = prev[lair];
        }
    }

    private static void dijkstraApproach(int[][] labyrinth, int s, boolean[] v, int[] prev, int[] c) {
        PriorityQueue<Integer> lairsByVampireCount = new PriorityQueue<>(Comparator.comparingInt(l -> c[l]));
        lairsByVampireCount.offer(s);

        while (!lairsByVampireCount.isEmpty()) {
            int leastCrowdedLair = lairsByVampireCount.poll();
            v[leastCrowdedLair] = true;

            for (int nextLair = 0; nextLair < labyrinth[leastCrowdedLair].length; nextLair++) {
                int vampireCount = labyrinth[leastCrowdedLair][nextLair];

                if (!v[nextLair] && vampireCount != 0) {
                    int candidateWay = c[leastCrowdedLair] + vampireCount;
                    if (candidateWay < c[nextLair]) {
                        c[nextLair] = candidateWay;
                        prev[nextLair] = leastCrowdedLair;
                    }
                    lairsByVampireCount.offer(nextLair);
                }

            }
        }
    }
}
