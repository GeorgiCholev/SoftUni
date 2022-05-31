package DataTypes_2.Exercise;

import java.util.Scanner;

public class p01_INT_Operations {
    public static void main(String[] args) {
        Scanner scanner = new Scanner (System.in);

        int one = Integer.parseInt(scanner.nextLine());
        int two = Integer.parseInt(scanner.nextLine());
        int three = Integer.parseInt(scanner.nextLine());
        int four = Integer.parseInt(scanner.nextLine());
        int sum = (((one + two) / three) * four);
        System.out.println(sum);
    }
}
