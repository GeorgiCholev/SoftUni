package Fundamentals_ExamPrep_11.April4th2020;

import java.util.Scanner;

public class p01_PasswordReset {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder buffer = new StringBuilder(scanner.nextLine());
        String line = scanner.nextLine();
        while (!line.equals("Done")) {
            String[] components = line.split(" ");
            String command = components[0];
            switch (command) {
                case "TakeOdd":
                    takeOdd(buffer);
                    System.out.println(buffer);
                    break;
                case "Cut":
                    cut(buffer, components);
                    System.out.println(buffer);
                    break;
                case "Substitute":
                    boolean substitute = substitute(buffer, components);
                    if (substitute) {
                        System.out.println(buffer);
                    } else {
                        System.out.println("Nothing to replace!");
                    }
                    break;
            }
            line = scanner.nextLine();
        }
        System.out.println("Your password is: " + buffer);
    }

    private static boolean substitute(StringBuilder buffer, String[] components) {
        String substring = components[1];
        String substitute = components[2];
        int index = buffer.indexOf(substring);
        if (index == -1) {
            return false;
        } else {
            while (index != -1) {
                buffer.delete(index, index + substring.length());
                buffer.insert(index, substitute);
                index = buffer.indexOf(substring, index + substitute.length());
            }
            return true;
        }
    }

    private static void cut(StringBuilder buffer, String[] components) {
        int index = Integer.parseInt(components[1]);
        int length = Integer.parseInt(components[2]);
        buffer.delete(index, index + length);
    }

    private static void takeOdd(StringBuilder buffer) {
        for (int i = buffer.length() - 1; i >= 0; i--) {
            if (i % 2 == 0) {
                buffer.deleteCharAt(i);
            }
        }
    }
}
