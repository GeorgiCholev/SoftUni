package BasicSyntax_1.Exercise;

import java.util.Scanner;

public class p01_Ages {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int age = Integer.parseInt(scanner.nextLine());
        String values = "";

        if (age >= 0 && age <= 2) {
            values = "baby";
        } else if (age >= 2 && age <= 13) {
            values = "child";
        } else if ( age >= 13 && age <= 19) {
            values = "teenager";
        } else if (age >= 19 && age <= 65) {
            values = "adult";
        } else if (age >= 66) {
            values = "elder";
        }
        System.out.println(values);
    }
}
