package Nested_Loops_6.Extra;

import java.util.Scanner;

public class p02_Letters {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String start = scanner.nextLine();
        String end = scanner.nextLine();
        String skip = scanner.nextLine();
        char startChar = start.charAt(0);
        char endChar = end.charAt(0);
        char skipChar = skip.charAt(0);
        int counter = 0;

        for (char i = startChar; i <= endChar; i++) {
            for (char j = startChar; j <= endChar; j++) {
                for (char k = startChar; k <= endChar; k++) {
                    if(i != skipChar && j != skipChar && k!= skipChar) {
                        counter++;
                        System.out.print(i + "" + j + "" + k + "" + " ");
                    }
                }
            }
        }
        System.out.println(counter);
    }
}



