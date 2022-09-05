package _4_SearchingSortingGreedyAlgorithms;

import java.util.Arrays;
import java.util.Scanner;

public class MergeSort {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] numbers = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        mergeSort(numbers, 0, numbers.length - 1);

        Arrays.stream(numbers).forEach(n -> System.out.print(n + " "));
    }

    private static void mergeSort(int[] array, int start, int end) {
        if (start == end) {
            return;
        }
        int mid = (start + end) / 2;
        mergeSort(array, start, mid);
        mergeSort(array, mid + 1, end);

        merge(array, start, mid, end);
    }

    private static void merge(int[] array, int start, int mid, int end) {
        int[] leftArray = new int[mid - start + 1];
        int[] rightArray = new int[end - mid];

        for (int i = 0; i < leftArray.length; i++) {
            leftArray[i] = array[start + i];
        }
        for (int i = 0; i < rightArray.length; i++) {
            rightArray[i] = array[mid + 1 + i];
        }

        int leftIndex = 0, rightIndex = 0;
        int originalIndex = start;
        while (leftIndex < leftArray.length && rightIndex < rightArray.length) {
            if (leftArray[leftIndex] <= rightArray[rightIndex]) {
                array[originalIndex] = leftArray[leftIndex];
                leftIndex++;
            } else {
                array[originalIndex] = rightArray[rightIndex];
                rightIndex++;
            }
            originalIndex++;
        }

        while (leftIndex < leftArray.length) {
            array[originalIndex] = leftArray[leftIndex];
            leftIndex++;
            originalIndex++;
        }

        while (rightIndex < rightArray.length) {
            array[originalIndex] = rightArray[rightIndex];
            rightIndex++;
            originalIndex++;
        }
    }
}
