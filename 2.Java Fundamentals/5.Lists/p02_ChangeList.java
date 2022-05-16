package Lists_5.Exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class p02_ChangeList {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        List<Integer> numbers = stringToIntegerList(input);
        String request = scanner.nextLine();
        while (!request.equals("end")) {
            String[] currentRequest = request.split(" ");
            int element;
            switch (currentRequest[0]) {
                case "Delete":
                    element = Integer.parseInt(currentRequest[1]);
                    int index = 0;
                    while (index < numbers.size()) {
                        if (numbers.get(index) == element) {
                            numbers.remove(numbers.get(index));
                        } else {
                            index++;
                        }
                    }
                    break;
                case "Insert":
                    element = Integer.parseInt(currentRequest[1]);
                    int position = Integer.parseInt(currentRequest[2]);
                    numbers.add(position, element);
                    break;
            }
            request = scanner.nextLine();
        }
        numbers.forEach(el -> System.out.print(el + " "));
    }

    private static List<Integer> stringToIntegerList(String input) {
        List<Integer> numbers = new ArrayList<>();
        String[] inputArray = input.split(" ");
        for (String s : inputArray) {
            numbers.add(Integer.parseInt(s));
        }
        return numbers;
    }
}
