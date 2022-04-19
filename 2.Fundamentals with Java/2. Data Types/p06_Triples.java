package DataTypes_2.Exercise;

import java.util.Scanner;

public class p06_Triples {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numberLetters = Integer.parseInt(scanner.nextLine());
        for (char i = 'a'; i <= ('a' + numberLetters - 1); i++) {
            for (char j = 'a'; j <= ('a' + numberLetters - 1); j++) {
                for (char k = 'a'; k <= ('a' + numberLetters - 1); k++) {
                    System.out.print(i + "" + j + "" + k + "");
                    System.out.println();
                }
            }
        }
    }
}
