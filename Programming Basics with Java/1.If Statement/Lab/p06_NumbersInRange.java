package If_Continuation_3.Lab;

import java.util.Scanner;

public class p06_NumbersInRange {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int product = Integer.parseInt(scanner.nextLine());
        if (product == 0 || (product >= 100 && product <= 200)){
        } else {
            System.out.println("invalid");
        }

        }
    }

