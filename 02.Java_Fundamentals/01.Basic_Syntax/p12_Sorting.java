package BasicSyntax_1.More.Exercises;

import java.util.Scanner;

public class p01_Sorting {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n1 = scanner.nextInt();
        int n2 = scanner.nextInt();
        int n3 = scanner.nextInt();
        if (n1 > n2 && n1 > n3) {
            System.out.println(n1);
            if (n2 > n3) {
                System.out.println(n2);
                System.out.println(n3);
            } else {
                System.out.println(n3);
                System.out.println(n2);
            }
        } else if (n2 > n1 && n2 > n3) {
            System.out.println(n2);
            if (n1 > n3) {
                System.out.println(n1);
                System.out.println(n3);
            } else {
                System.out.println(n3);
                System.out.println(n1);
            }
        } else if (n3 > n2 && n3 > n1) {
            System.out.println(n3);
            if (n1 > n2) {
                System.out.println(n1);
                System.out.println(n2);
            } else {
                System.out.println(n2);
                System.out.println(n1);
            }
        }
    }
}
