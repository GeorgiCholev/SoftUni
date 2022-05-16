package Arrays_3.Exercise;

import java.util.Scanner;

public class p07_MaxSequence {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] input = scanner.nextLine().split(" ");
        int[] numbers = new int[input.length];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = Integer.parseInt(input[i]);
        }
        int counter = 0;
        int winnerNumber = numbers[0];
        int winnerCounter = 0;
        for (int i = 0; i < numbers.length; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                if (numbers[i] == numbers[j]) {
                    counter++;
                    if (counter > winnerCounter) {
                        winnerNumber = numbers[i];
                        winnerCounter = counter;
                    }
                } else {
                    break;
                }
            }
            counter = 0;
        }
        for (int i = 0; i <= winnerCounter; i++) {
            System.out.print(winnerNumber + " ");
        }
    }
}
