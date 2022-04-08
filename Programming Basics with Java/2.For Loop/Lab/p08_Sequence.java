package For_Loop_4.Lab;

import java.util.Scanner;

public class p08_Sequence {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int quantityNumbers = Integer.parseInt(scanner.nextLine());

        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        for (int i = 1; i <= quantityNumbers; i++) {
            int number = Integer.parseInt(scanner.nextLine());
            if (number > max) {
                max = number;
            }
            if (number < min) {
                min = number;
            }
        }
        System.out.printf("Max number: %d%n", max);
        System.out.printf("Min number: %d", min);
        }
    }

