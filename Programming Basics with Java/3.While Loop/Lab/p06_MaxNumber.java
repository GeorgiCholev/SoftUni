package While_Loop_5.Lab;

import java.util.Scanner;

public class p06_MaxNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String n = scanner.nextLine();
        int max = Integer.MIN_VALUE;


        while (!n.equals("Stop")) {
            int parsedN = Integer.parseInt(n);
            if (parsedN > max) {
                max = parsedN;
            }
            n = scanner.nextLine();
        }
        System.out.println(max);
    }
}

