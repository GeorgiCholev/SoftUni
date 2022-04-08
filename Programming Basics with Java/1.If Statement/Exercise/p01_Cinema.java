package If_Continuation_3.Exercise;

import java.util.Scanner;

public class p01_Cinema {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String projection = scanner.nextLine();
        int r = Integer.parseInt(scanner.nextLine());
        int c = Integer.parseInt(scanner.nextLine());
        int space = r * c;
        double income = 0.0;
        switch (projection){
            case "Premiere":
                income = space * 12;
                break;
            case "Normal":
                income = space * 7.5;
                break;
            case "Discount":
                income = space * 5;
                break;
        }
        System.out.printf("%.2f leva", income);

        }

    }

