package TextProcessing_9.Exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class p05_MultiplyNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String big = scanner.nextLine();
        byte small = (byte) Integer.parseInt(scanner.nextLine());
        int remainder = 0;
        String addition;
        List<Character> longBoy = new ArrayList<>();
        StringBuilder longNumber = new StringBuilder();
        for (int i = big.length() - 1; i >= 0; i--) {
            int number = big.charAt(i) - '0';
            number = (number * small) + remainder;
            addition = String.valueOf(number);
            longBoy.add(0, addition.charAt(addition.length() - 1));
            longNumber.insert(0, addition.charAt(addition.length() - 1));
            remainder = number / 10;
        }
        if (remainder != 0) {
            addition = remainder + "";
            longBoy.add(0, addition.charAt(0));
            longNumber.insert(0, addition.charAt(0));
        }
        while (longNumber.charAt(0) == '0' && longBoy.size() > 1) {
            longBoy.remove(0);
            longNumber.deleteCharAt(0);
        }
        System.out.println(longNumber);
    }

}
