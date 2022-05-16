package For_Loop_4.Lab;

import java.util.Scanner;

public class p02_Nto1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int lastNumber = Integer.parseInt(scanner.nextLine());

        for(int number = lastNumber; number >= 1; number--) {
            System.out.println(number);
        }
    }
}
