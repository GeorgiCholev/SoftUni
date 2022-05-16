package Lists_5.Exercise;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class p04_ListOperations {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> numbers = Arrays.stream(scanner.nextLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        String operation = scanner.nextLine();
        while (!operation.equals("End")) {
            String[] operationParts = operation.split(" ");
            switch (operationParts[0]) {
                case "Add":
                    numbers.add(Integer.parseInt(operationParts[1]));
                    break;
                case "Insert":
                    int index = Integer.parseInt(operationParts[2]);
                    if (index >= 0 && index < numbers.size()) {
                        numbers.add(index, Integer.parseInt(operationParts[1]));
                    } else {
                        System.out.println("Invalid index");
                        operation = scanner.nextLine();
                        continue;
                    }
                    break;
                case "Remove":
                    index = Integer.parseInt(operationParts[1]);
                    if (index >= 0 && index < numbers.size()) {
                        numbers.remove(index);
                    } else {
                        System.out.println("Invalid index");
                        operation = scanner.nextLine();
                        continue;
                    }
                    break;
                case "Shift":
                    switch (operationParts[1]) {
                        case "left":
                            int count = Integer.parseInt(operationParts[2]);
                            int removed = 0;
                            while (removed < count) {
                                int removedNumber = numbers.remove(0);
                                numbers.add(removedNumber);
                                removed++;
                            }
                            break;

                        case "right":
                            count = Integer.parseInt(operationParts[2]);
                            removed = 0;
                            while (removed < count) {
                                int removedNumber = numbers.remove(numbers.size() - 1);
                                numbers.add(0, removedNumber);
                                removed++;
                            }
                            break;
                    }
                    break;
            }
            operation = scanner.nextLine();
        }
        numbers.forEach(e -> System.out.print(e + " "));

    }
}


