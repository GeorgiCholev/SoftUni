package Nested_Loops_6.Extra;

import java.util.Scanner;

public class p13_PrimePairs {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int pair1Floor = Integer.parseInt(scanner.nextLine());
        int pair2Floor = Integer.parseInt(scanner.nextLine());
        int pair1Ceil = Integer.parseInt(scanner.nextLine());
        int pair2Ceil = Integer.parseInt(scanner.nextLine());

        pair1Ceil += pair1Floor;
        String pair1FloorString = pair1Floor + "";
        char pair1Floor1 = pair1FloorString.charAt(0);
        char pair1Floor2 = pair1FloorString.charAt(1);
        int realPair1Floor1 = Integer.parseInt(pair1Floor1 + "");
        int realPair1Floor2 = Integer.parseInt(pair1Floor2 + "");
        String pair1CeilString = pair1Ceil + "";
        char pair1Ceil1 = pair1CeilString.charAt(0);
        char pair1Ceil2 = pair1CeilString.charAt(1);
        int realPair1Ceil1 = Integer.parseInt(pair1Ceil1 + "");
        int realPair1Ceil2 = Integer.parseInt(pair1Ceil2 + "");

        pair2Ceil += pair2Floor;
        String pair2FloorString = pair2Floor + "";
        char pair2Floor1 = pair2FloorString.charAt(0);
        char pair2Floor2 = pair2FloorString.charAt(1);
        int realPair2Floor1 = Integer.parseInt(pair2Floor1 + "");
        int realPair2Floor2 = Integer.parseInt(pair2Floor2 + "");
        String pair2CeilString = pair2Ceil + "";
        char pair2Ceil1 = pair2CeilString.charAt(0);
        char pair2Ceil2 = pair2CeilString.charAt(1);
        int realPair2Ceil1 = Integer.parseInt(pair2Ceil1 + "");
        int realPair2Ceil2 = Integer.parseInt(pair2Ceil2 + "");




        for (int i = realPair1Floor1; i <= realPair1Ceil1; i++) {
            for (int j = realPair1Floor2; j <= realPair1Ceil2; j++) {
                boolean pair1Prime = true;
                String pair1 = i + "" + j + "";
                int realPair1 = Integer.parseInt(pair1);
                for (int k = 2; k < realPair1; k++) {
                    if (realPair1 % k == 0) {
                        pair1Prime = false;
                        break;
                    }
                }
                if (pair1Prime) {
                    for (int l = realPair2Floor1; l <= realPair2Ceil1; l++) {
                        for (int m = realPair2Floor2; m <= realPair2Ceil2; m++) {
                            boolean pair2Prime = true;
                            String pair2 = l + "" + m + "";
                            int realPair2 = Integer.parseInt(pair2);
                            for (int n = 2; n < realPair2; n++) {
                                if (realPair2 % n == 0) {
                                    pair2Prime = false;
                                    break;
                                }
                            }
                            if (pair2Prime) {
                                System.out.println(realPair1 + "" + realPair2 + "");
                            }
                        }
                    }
                }
            }
        }
    }
}



