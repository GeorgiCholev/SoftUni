package _1_Abstraction_and_Enum.Lab.PointInRectangle;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner (System.in);

        int[] coordinates = getCoordinates(scanner);

        Point A = new Point(coordinates[0],coordinates[1]);
        Point C = new Point(coordinates[2],coordinates[3]);

        Rectangle rectangle = new Rectangle(A, C);

        int incomingPoints = scanner.nextInt();
        while (incomingPoints-- > 0) {

            int x = scanner.nextInt();
            int y = scanner.nextInt();
            Point P = new Point(x, y);

            System.out.println(rectangle.contains(P));
        }
    }

    private static int[] getCoordinates(Scanner scanner) {
        return Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}
