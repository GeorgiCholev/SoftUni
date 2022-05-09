package Methods_4.Exercise;

import java.util.Scanner;

public class p01_SmallestOf3 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int one = Integer.parseInt(scanner.nextLine());
        int two = Integer.parseInt(scanner.nextLine());
        int three = Integer.parseInt(scanner.nextLine());
        findMin(one, two, three);
    }

    private static void findMin(int one, int two, int three) {
        System.out.println(Math.min(Math.min(one, two), three));
    }
}


