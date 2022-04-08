package For_Loop_4.Exercise;

import java.util.Scanner;

public class p01_UpTo1000 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner (System.in);

        for (int i = 7; i <= 1000; i+= 10 ) {
            System.out.println(i);
        }
    }
}
