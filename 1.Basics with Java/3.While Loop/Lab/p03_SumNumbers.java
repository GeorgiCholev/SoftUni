package While_Loop_5.Lab;

import java.util.Scanner;

public class p03_SumNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int number = Integer.parseInt(scanner.nextLine());
        int sum = 0;

        while (sum < number) {
            int followingNumbers = Integer.parseInt(scanner.nextLine());
            sum += followingNumbers;
        }
        System.out.println(sum);
    }
}
