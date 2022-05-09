package Arrays_3.Exercise;

import java.util.Scanner;

public class p08_MagicSum {
    public static void main(String[] args) {
        Scanner scanner = new Scanner (System.in);
        String input = scanner.nextLine();
        int sum =Integer.parseInt(scanner.nextLine());
        String[] textNumber = input.split(" ");
        int [] numbers = new int[textNumber.length];
        for (int i = 0; i < textNumber.length; i++) {
            numbers[i] = Integer.parseInt(textNumber[i]);
        }
        for (int i = 0; i < numbers.length; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                if (numbers[i] + numbers[j] == sum) {
                    System.out.println(numbers[i] + " " + numbers[j]);
                }
            }
        }
    }
}
