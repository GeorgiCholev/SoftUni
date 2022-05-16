package Arrays_3.Exercise;

import java.util.Scanner;

public class p01_Train {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        int[] passengers = new int[n];
        String[] passengersArray = new String[n];
        int sum = 0;
        for (int i = 0; i < n; i++) {
            passengersArray[i] = scanner.nextLine();
            passengers[i] = Integer.parseInt(passengersArray[i]);
            sum += passengers[i];
        }
        System.out.println(String.join(" ", passengersArray));
        System.out.print(sum);
    }
}
