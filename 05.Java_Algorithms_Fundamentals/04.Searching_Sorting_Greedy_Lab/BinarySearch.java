package _4_SearchingSortingGreedyAlgorithms;

import java.util.Arrays;
import java.util.Scanner;

public class BinarySearch {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] numbers = Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt).sorted().toArray();
        int number = scanner.nextInt();

        int index = -1;
        int start = 0;
        int end = numbers.length - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (numbers[mid] == number) {
                index = mid;
                break;
            } else if (numbers[mid] < number) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        System.out.println(index);
    }
}
