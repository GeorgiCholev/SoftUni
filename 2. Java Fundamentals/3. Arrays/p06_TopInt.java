package Arrays_3.Exercise;

import java.util.Scanner;

public class p05_TopInt {
    public static void main(String[] args) {
        Scanner scanner = new Scanner (System.in);
        String input = scanner.nextLine();
        String[] numbersText = input.split(" ");
        int[] numbers = new int[numbersText.length];
        boolean print = true;
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = Integer.parseInt(numbersText[i]);
        }
        for (int j = 0; j < numbers.length; j++) {
            for (int i = j + 1; i < numbers.length; i++) {
                if (numbers[i] >= numbers[j]) {
                    print = false;
                    break;
                }
            }
            if (print) {
            System.out.print(numbers[j] + " ");}
            print = true;
        }
    }
}
