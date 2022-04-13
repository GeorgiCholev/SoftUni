package While_Loop_5.Lab;

import java.util.Scanner;

public class p04_SequenceNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        int sequence = 1;

        while (sequence <= n) {
            System.out.println(sequence);
            sequence = (2 * sequence) + 1;
        }
    }
}
