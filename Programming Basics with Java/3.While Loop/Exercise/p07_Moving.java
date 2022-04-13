package While_Loop_5.Exercise;

import java.util.Scanner;

public class p07_Moving {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int width = Integer.parseInt(scanner.nextLine());
        int height = Integer.parseInt(scanner.nextLine());
        int length = Integer.parseInt(scanner.nextLine());
        String input = scanner.nextLine();

        int roomSpace = width * height * length;
        boolean filled = false;

        while (!input.equals("Done")) {
            int numberBoxes = Integer.parseInt(input);
            roomSpace -= numberBoxes;
            if (roomSpace < 0) {
                filled = true;
                break;
            }
            input = scanner.nextLine();
        }
        if (filled) {
            int diff = Math.abs(roomSpace);
            System.out.printf("No more free space! You need %d Cubic meters more.", diff);
        } else {
            System.out.printf("%d Cubic meters left.", roomSpace);
        }
    }
}



