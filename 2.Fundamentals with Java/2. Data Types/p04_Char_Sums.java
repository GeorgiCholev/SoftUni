package DataTypes_2.Exercise;

import java.util.Scanner;

public class p04_Char_Sums {
    public static void main(String[] args) {
        Scanner scanner = new Scanner (System.in);
        int lines = Integer.parseInt(scanner.nextLine());
        int sum = 0;
        int letterInt;
        for (int i = 1; i <= lines; i++) {
            letterInt = scanner.nextLine().charAt(0);
            sum += letterInt;
        }
        System.out.printf("The sum equals: %d", sum);
    }
}
