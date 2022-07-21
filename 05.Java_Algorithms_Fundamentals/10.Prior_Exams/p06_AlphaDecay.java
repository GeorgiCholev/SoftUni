package _10_Prior_Exams;

import java.util.Scanner;

public class p06_AlphaDecay {

    private static String[] variation;
    private static boolean[] isUsed;

    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);

        String[] nuclei = scanner.nextLine().split("\\s+");
        int countOfNuclei = scanner.nextInt();
        variation = new String[countOfNuclei];
        isUsed = new boolean[nuclei.length];

        variate(0, nuclei);
    }

    private static void variate(int index, String[] nuclei) {

        if (index == variation.length) {
            print();
            return;
        }

        for (int i = 0; i < nuclei.length; i++) {
            if (!isUsed[i]) {
                isUsed[i] = true;
                variation[index] = nuclei[i];
                variate(index + 1, nuclei);
                isUsed[i] = false;
            }
        }

    }

    private static void print() {
        for (String s : variation) {
            System.out.print(s + " ");
        }
        System.out.println();
    }
}
