package _6_Exercise_Graph;

import java.util.*;

public class p06_RoadReconstruction {
    //  Find all the streets that are important (only way to a given city)
    //  and cannot be removed and print them.

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberOfCities = Integer.parseInt(scanner.nextLine());
        int numberOfStreets = Integer.parseInt(scanner.nextLine());

        int[][] townGraph = new int[numberOfCities][numberOfCities];

        constructStreetsBetweenBuildings(scanner, numberOfStreets, townGraph);

        List<String> essentialStreets = new ArrayList<>();

        findEssentialStreets(townGraph, essentialStreets);

        if (essentialStreets.size() == 0) {
            System.out.println("No Important streets.");
        } else {
            System.out.println("Important streets:" + System.lineSeparator()
                    + String.join(System.lineSeparator(), essentialStreets));
        }
    }

    private static void findEssentialStreets(int[][] townGraph, List<String> essentialStreets) {
        for (int i = 0; i < townGraph.length; i++) {
            for (int j = 0; j < townGraph[i].length; j++) {
                if (buildingsShareStreet(i, j, townGraph)
                        && streetIsNotAdded(i, j, essentialStreets) && streetIsEssential(i, j, townGraph)) {
                    essentialStreets.add(i + " " + j);
                }
            }
        }
    }

    private static boolean streetIsNotAdded(int first, int second, List<String> essentialStreets) {
        return !essentialStreets.contains(first + " " + second) &&
                !essentialStreets.contains(second + " " + first);
    }

    private static boolean streetIsEssential(int first, int second, int[][] graph) {
        graph[first][second] = 0;
        graph[second][first] = 0;

        boolean[] visited = new boolean[graph.length];

        Deque<Integer> queue = new ArrayDeque<>();
        queue.offer(first);

        int[] prevBuilding = new int[graph.length];
        Arrays.fill(prevBuilding, -1);

        while (!queue.isEmpty()) {
            int current = queue.poll();
            visited[current] = true;

            for (int i = 0; i < graph[current].length; i++) {
                if (prevBuilding[current] != i && graph[current][i] == 1 && !visited[i]) {
                    if (i == second) {
                        graph[first][second] = 1;
                        graph[second][first] = 1;
                        return false;
                    }
                    prevBuilding[i] = current;
                    queue.offer(i);
                }
            }
        }
        graph[first][second] = 1;
        graph[second][first] = 1;
        return true;
    }

    private static boolean buildingsShareStreet(int one, int two, int[][] graph) {
        return graph[one][two] == 1;
    }

    private static void constructStreetsBetweenBuildings(Scanner scanner, int numberOfStreets, int[][] townGraph) {
        for (int i = 0; i < numberOfStreets; i++) {
            String[] streetComp = scanner.nextLine().split(" - ");
            int buildingOne = Integer.parseInt(streetComp[0]);
            int buildingTwo = Integer.parseInt(streetComp[1]);

            townGraph[buildingOne][buildingTwo] = 1;
            townGraph[buildingTwo][buildingOne] = 1;

        }
    }
}
