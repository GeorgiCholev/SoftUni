package While_Loop_5.Lab;

import java.util.Scanner;

public class p07_MinNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int min = Integer.MAX_VALUE;
        String n = scanner.nextLine();

        while (!n.equals("Stop")) {
            int parsedN = Integer.parseInt(n);
            if (parsedN < min) {
                min = parsedN;
            }
            n = scanner.nextLine();
        }
        System.out.println(min);
    }
}
