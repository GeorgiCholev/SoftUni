package ExamPreparation;

import java.util.Scanner;

public class P01_2_Birthday {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double rent = Double.parseDouble(scanner.nextLine());
        double cake = rent * 0.2;
        double drinks = cake * 0.55;
        double animator = rent / 3;
        double budget = rent + cake + drinks + animator;
        System.out.println(budget);
    }
}
