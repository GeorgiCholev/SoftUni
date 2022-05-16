package Lists_5.Exercise;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class p05_Bombs {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        List<Integer> numbers = Arrays.stream(line.split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        String bombLine = scanner.nextLine();
        List<Integer> bombAndPower = Arrays.stream(bombLine.split(" "))
                .map(Integer::parseInt).collect(Collectors.toList());
        int bomb = bombAndPower.get(0);
        int power = bombAndPower.get(1);
        while (numbers.contains(bomb)) {
            int bombIndex = numbers.indexOf(bomb);
            int start = bombIndex - power;
            int end = bombIndex + power;
            if (start < 0) {
                start = 0;
            }
            if (end >= numbers.size()) {
                end = numbers.size() - 1;
            }
            for (int i = start; i <= end; i++) {
                numbers.remove(start);
            }
        }
        int sum = 0;
        for (int number : numbers) {
            sum += number;
        }
        System.out.println(sum);
    }
}
