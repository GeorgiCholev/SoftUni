package Arrays_3.Exercise;

import java.util.Scanner;

public class p02_Common_LMNTs {

    public static void main(String[] args) {
        Scanner scanner = new Scanner (System.in);

        String[] commonOne = scanner.nextLine().split(" ");
        String[] commonTwo = scanner.nextLine().split(" ");

        for (String second : commonTwo) {
            for (String first : commonOne) {
                if (second.equals(first)) {
                    System.out.print(second + " ");
                }
            }
        }
    }
}
