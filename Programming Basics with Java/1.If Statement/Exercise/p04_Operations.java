package If_Continuation_3.Exercise;

import java.util.Scanner;

public class p06_Operations {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int number1 = Integer.parseInt(scanner.nextLine());
        int number2 = Integer.parseInt(scanner.nextLine());
        int result = 0;
        double divisionResult = 0.0;
        String operation = scanner.nextLine();
        switch (operation) {
            case "+":
                result = number1 + number2;
                if (result % 2 == 0) {
                    System.out.printf("%d + %d = %d - even", number1, number2, result);
                } else {
                    System.out.printf("%d + %d = %d - odd", number1, number2, result);
                }
                break;
            case "-":
                result = number1 - number2;
                if (result % 2 == 0) {
                    System.out.printf("%d - %d = %d - even", number1, number2, result);
                } else {
                    System.out.printf("%d - %d = %d - odd", number1, number2, result);
                }
                break;
            case "*":
                result = number1 * number2;
                if (result % 2 == 0) {
                    System.out.printf("%d * %d = %d - even", number1, number2, result);
                } else {
                    System.out.printf("%d * %d = %d - odd", number1, number2, result);
                }
                break;

            case "/":
                if (number2 == 0) {
                    System.out.printf("Cannot divide %d by zero", number1);
                    break;
                }
                divisionResult = (number1 * 1.0) / number2;
                System.out.printf("%d / %d = %.2f", number1, number2, divisionResult);
                break;
            case "%":
                if (number2 == 0) {
                    System.out.printf("Cannot divide %d by zero", number1);
                    break;
                }
                result = number1 % number2;
                System.out.printf("%d %% %d = %d", number1, number2, result);
        }
    }
}
