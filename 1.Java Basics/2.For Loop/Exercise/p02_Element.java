package For_Loop_4.Exercise;

import java.util.Scanner;

public class p02_Element {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int qNumbers = Integer.parseInt(scanner.nextLine());
        int max =Integer.MIN_VALUE;
        int sum = 0;

        for (int i = 1; i <= qNumbers; i++) {
            int number = Integer.parseInt(scanner.nextLine());
            sum += number;
            if (number > max) {
                max = number;
            }
        }
        sum -= max;
        if (sum == max) {
            System.out.println("Yes");
            System.out.printf("Sum = %d", sum);
        } else {
            max -= sum;
            System.out.println("No");
            System.out.printf("Diff = %d", Math.abs(max));
        }
    }
}
