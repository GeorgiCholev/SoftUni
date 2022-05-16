package For_Loop_4.Lab;

import java.util.Scanner;

public class p07_NumberSequence {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int howManyNumbers = Integer.parseInt(scanner.nextLine());
        int sumNumbersN = 0;

        for (int quantityNumbers = 1; quantityNumbers <= howManyNumbers; quantityNumbers++) {
            int numberN = Integer.parseInt(scanner.nextLine());
            sumNumbersN += numberN;
            }
        System.out.println(sumNumbersN);
        }

    }

