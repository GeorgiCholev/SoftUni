package ExamPreparation;

import java.util.Scanner;

public class p06_1_BarCode {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int start = Integer.parseInt(scanner.nextLine());
        int end = Integer.parseInt(scanner.nextLine());
        String text = start + "";
        int one = Integer.parseInt(text.charAt(0) + "");
        int two = Integer.parseInt(text.charAt(1) + "");
        int three = Integer.parseInt(text.charAt(2) + "");
        int four = Integer.parseInt(text.charAt(3) + "");
        String text2 = end + "";
        int five = Integer.parseInt(text2.charAt(0) + "");
        int six = Integer.parseInt(text2.charAt(1) + "");
        int seven = Integer.parseInt(text2.charAt(2) + "");
        int eight = Integer.parseInt(text2.charAt(3) + "");

        for (int i = one; i <= five; i++) {
            if (i % 2 != 0) {
                for (int j = two; j <= six; j++) {
                    if (j % 2 != 0) {
                        for (int k = three; k <= seven; k++) {
                            if (k % 2 != 0) {
                                for (int l = four; l <= eight; l++) {
                                    if (l % 2 != 0) {
                                        System.out.printf("%d%d%d%d" + " ", i, j, k, l);
                                    }
                                }
                            }
                        }

                    }
                }


            }
        }
    }
}

