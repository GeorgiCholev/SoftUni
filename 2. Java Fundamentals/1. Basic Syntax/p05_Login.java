package BasicSyntax_1.Exercise;

import java.util.Scanner;

public class p05_Login {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String username = scanner.nextLine();
        String password = "";
        String attempt;
        for (int i = username.length() - 1; i >= 0; i--) {
            char symbol = username.charAt(i);
            password += symbol;
        }
        for (int j = 1; j <= 4; j++) {
            attempt = scanner.nextLine();
            if (!attempt.equals(password) && j != 4) {
                System.out.println("Incorrect password. Try again.");
            } else if (!attempt.equals(password)) {
                System.out.printf("User %s blocked!", username);
            } else {
                System.out.printf("User %s logged in.", username);
                break;
            }
        }
    }
}
