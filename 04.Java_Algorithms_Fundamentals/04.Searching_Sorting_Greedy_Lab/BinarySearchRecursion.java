package _4_SearchingSortingGreedyAlgorithms;

import java.util.Arrays;
import java.util.Scanner;

public class BinarySearchRecursion {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int[] sortedNumberArr = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .sorted().toArray();
        int number = Integer.parseInt(scanner.nextLine());
        int indexOfNumber = binarySearch(sortedNumberArr, number, 0, sortedNumberArr.length - 1);
        if (indexOfNumber != -1) {
            System.out.println(number + " exists at " + indexOfNumber);
        } else {
            System.out.println("Not present");
        }
    }

    private static int binarySearch(int[] arr, int number, int startIndex, int endIndex) {
        if (startIndex <= endIndex) {
            int middle = (startIndex + endIndex) / 2;
            if (arr[middle] == number) {
                return middle;
            } else if (number > arr[middle]) {
                return binarySearch(arr, number, middle + 1, endIndex);
            } else {
                return binarySearch(arr, number, startIndex, middle - 1);
            }
        } else {
            return -1;
        }
    }
}
