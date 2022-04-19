package While_Loop_5.Exercise;

import java.util.Scanner;

public class p06_cake {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int width = Integer.parseInt(scanner.nextLine());
        int length = Integer.parseInt(scanner.nextLine());
        int wholeCake = width * length;
        String input = scanner.nextLine();
        boolean finished = false;
        int neededCake = 0;

        while (!input.equals("STOP")) {
            int pieces = Integer.parseInt(input);
            if (pieces <= wholeCake) {
                wholeCake -= pieces;
            } else {
                neededCake = pieces - wholeCake;
                break;
            }
            if (wholeCake == 0) {
                finished = true;
                break;
            }
            input = scanner.nextLine();
        }
        if (input.equals("STOP")) {
            finished = true;
        }
        if (finished) {
            System.out.printf("%d pieces are left.", wholeCake);
        } else {
            System.out.printf("No more cake left! You need %d pieces more.", neededCake);
        }
    }
}
