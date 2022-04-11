package For_Loop_4.Lab;

import java.util.Scanner;

public class p04_PowerOf2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int lastPower = Integer.parseInt(scanner.nextLine());

        for (int currentPower = 0; currentPower <= lastPower; currentPower += 2) {
            double value = Math.pow(2, currentPower);
            System.out.printf("%.0f%n", value);
        }
    }
}
