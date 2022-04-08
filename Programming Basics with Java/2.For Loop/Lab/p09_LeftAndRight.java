package For_Loop_4.Lab;

import java.util.Scanner;

public class p09_LeftAndRight {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numberInputs = Integer.parseInt(scanner.nextLine());
        int leftAndRightTypedNumbers = numberInputs * 2;
        int left = 0;
        int right = 0;

        for (int i = 1; i <= (leftAndRightTypedNumbers); i++) {
            int typedNumber = Integer.parseInt(scanner.nextLine());
            if (i <= numberInputs) {
                left += typedNumber;
            } else {
                right += typedNumber;
            }
        }
        if (left == right) {
            System.out.printf("Yes, sum = %d", left);
        } else {
            int difference = Math.abs(left - right);
            System.out.printf("No, diff = %d", difference);
        }

    }
}
