package Arrays_3.Exercise;

import java.util.Scanner;

public class p04_Rotation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] numbers = scanner.nextLine().split(" ");
        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < n; i++) {
            String firstNumber = numbers[0];
            for (int j = 0; j < numbers.length - 1; j++) {
                numbers[j] = numbers[j + 1];
            }
            numbers[numbers.length - 1] = firstNumber;
        }
        System.out.println(String.join(" ", numbers));
    }
}
