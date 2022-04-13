package While_Loop_5.Lab;

import java.util.Scanner;

public class p02_Password {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String name = scanner.nextLine();
        String pass = scanner.nextLine();

        String attemptedPass = scanner.nextLine();
        while (!attemptedPass.equals(pass)) {
            attemptedPass = scanner.nextLine();
        }
        System.out.printf("Welcome %s!", name);
    }
}
