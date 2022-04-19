package DataTypes_2.Exercise;

import java.util.Scanner;

public class p03_Elevator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int number = Integer.parseInt(scanner.nextLine());
        int capacity = Integer.parseInt(scanner.nextLine());
        int course = number / capacity;
        int overflow = number % capacity;
        if (overflow > 0) {
            System.out.printf("%d", course + 1);
        } else {
            System.out.println(course);
        }
    }
}