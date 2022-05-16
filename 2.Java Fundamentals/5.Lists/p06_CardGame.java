package Lists_5.Exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class p06_CardGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String input2 = scanner.nextLine();
        List<Integer> leftHand = stringSplitToList(input);
        List<Integer> rightHand = stringSplitToList(input2);
        while (leftHand.size() > 0 && rightHand.size() > 0) {
            if (leftHand.get(0) > rightHand.get(0)) {
                leftHand.add(leftHand.get(0));
                leftHand.add(rightHand.get(0));
                leftHand.remove(0);
                rightHand.remove(0);
            } else if (rightHand.get(0) > leftHand.get(0)) {
                rightHand.add(rightHand.get(0));
                rightHand.add(leftHand.get(0));
                rightHand.remove(0);
                leftHand.remove(0);
            } else {
                leftHand.remove(0);
                rightHand.remove(0);
            }
        }
        int sum = 0;
        if (leftHand.size() == 0) {
            for (int number : rightHand) {
                sum += number;
            }
            System.out.printf("Second player wins! Sum: %d", sum);

    }
        if (rightHand.size() == 0) {
            for (int number : leftHand) {
                sum += number;
            }
            System.out.printf("First player wins! Sum: %d", sum);

    }
    }

    private static List<Integer> stringSplitToList(String input) {
        String[] arrayNumbers = input.split(" ");
        List<Integer> numbers = new ArrayList<>();
        for (String number : arrayNumbers) {
            numbers.add(Integer.parseInt(number));
        }
        return numbers;
    }
}
