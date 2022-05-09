package Arrays_3.Exercise;

import java.util.Scanner;

public class p06_EqualSums {
    public static void main(String[] args) {
        Scanner scanner = new Scanner (System.in);

        String input = scanner.nextLine();
        String[] numbersText = input.split(" ");
        int[] numbers = new int[numbersText.length];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = Integer.parseInt(numbersText[i]);
        }
        boolean equal = false;
        int leftSum = 0;
        int rightSum = 0;
        for (int i = 0; i < numbers.length; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                rightSum += numbers[j];
            }
            if (rightSum == leftSum) {
                equal = true;
                System.out.println(i);
                break;
            }
            rightSum = 0;
            leftSum += numbers[i];
        }
        if (!equal) {
            System.out.println("no");
        }
    }
}
