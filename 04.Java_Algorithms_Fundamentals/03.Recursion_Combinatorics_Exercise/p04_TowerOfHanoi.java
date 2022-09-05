package _3_Exercise_RecursionAndCombinatorics;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Scanner;
import java.util.stream.Collectors;

public class p04_TowerOfHanoi {
    public static ArrayDeque<Integer> source = new ArrayDeque<>();
    public static ArrayDeque<Integer> spare = new ArrayDeque<>();
    public static ArrayDeque<Integer> destination = new ArrayDeque<>();
    public static int steps = 1;
    public static StringBuilder output = new StringBuilder();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int disk = scanner.nextInt();
        for (int i = disk; i >= 1; i--) {
            source.push(i);
        }
       addOutput();
        move(disk, source, destination, spare);
        System.out.print(output.toString().trim());
    }

    private static void move(int n, ArrayDeque<Integer> start, ArrayDeque<Integer> destination, ArrayDeque<Integer> spare) {
        if (n == 1) {
            destination.push(start.pop());
            output.append("Step #").append(steps++).append(": Moved disk").append(System.lineSeparator());
            addOutput();
            return;
        }
        move(n - 1, start, spare, destination);
        move(1, start, destination, spare);
        move(n - 1, spare, destination, start);
    }

    private static void addOutput() {
       output.append("Source: ").append(formatRod(source)).append(System.lineSeparator())
               .append("Destination: ").append(formatRod(destination)).append(System.lineSeparator())
               .append("Spare: ").append(formatRod(spare)).append(System.lineSeparator())
               .append(System.lineSeparator());
    }

    private static String formatRod(ArrayDeque<Integer> rod) {
        return rod.stream().sorted(Comparator.reverseOrder())
                .map(String::valueOf).collect(Collectors.joining(", "));
    }
}
