package For_Loop_4.Lab;

import java.util.Scanner;

public class p03_step3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int lastNumber = Integer.parseInt(scanner.nextLine());

        for (int currentNumber = 1; currentNumber <= lastNumber; currentNumber += 3) {
            System.out.println(currentNumber);
        }
    }
}
