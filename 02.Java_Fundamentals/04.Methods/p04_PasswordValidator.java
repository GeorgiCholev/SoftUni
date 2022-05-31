package Methods_4.Exercise;

import java.util.Scanner;

public class p04_PasswordValidator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String password = scanner.nextLine();
        checkPassword(password);
    }

    private static void checkPassword(String password) {
        String[] characters = password.split("");
        boolean letterDigits = true;
        boolean digitCounter = false;
        boolean passwordLength = false;
        int digitCounterChecker = 0;
        if (characters.length >= 6 && characters.length <= 10) {
            passwordLength = true;
        }
        for (String character : characters) {
            char symbol = character.charAt(0);
            if (!(symbol > 47 && symbol < 58) && !(symbol > 64 && symbol < 91) && !(symbol > 96 && symbol < 123)) {
                letterDigits = false;
            }
            if ((symbol > 47 && symbol < 58)) {
                digitCounterChecker++;
                if (digitCounterChecker >= 2) {
                    digitCounter = true;
                }
            }
        }
        if (!passwordLength) {
            System.out.println("Password must be between 6 and 10 characters");
        }
        if (!letterDigits) {
            System.out.println("Password must consist only of letters and digits");
        }
        if (!digitCounter) {
            System.out.println("Password must have at least 2 digits");
        }
        if (passwordLength && letterDigits && digitCounter) {
            System.out.println("Password is valid");
        }
    }
}
