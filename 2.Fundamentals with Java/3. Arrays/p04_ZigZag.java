package Arrays_3.Exercise;

import java.util.Scanner;

public class p03_ZigZag {
    public static void main(String[] args) {
        Scanner scanner = new Scanner (System.in);

        int n = Integer.parseInt(scanner.nextLine());
        String[] one = new String[n];
        String[] two = new String[n];

        for (int i = 0; i < n; i++) {
            String[] test = scanner.nextLine().split(" ");
            if (i % 2 == 0) {
                one[i] = test[0];
                two[i] = test[1];
            } else {
                two[i] = test[0];
                one[i] = test[1];
            }
        }
        System.out.print(String.join(" ", one) + "\n" + String.join(" ", two));
    }
}
