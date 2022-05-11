package Arrays_3.Exercise;

import java.util.Scanner;

public class p11_LIS {
    public static void main(String[] args) {
        Scanner scanner = new Scanner (System.in);
        String input = scanner.nextLine();
        int[] numbers = inputIntoIntArray(input);
        int[] lisLength = new int[numbers.length];
        int[] lisPrevIndex = new int[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            lisLength[i] = 1;
            lisPrevIndex[i] = -1;
        }

        for (int currentIndex = 1; currentIndex < numbers.length; currentIndex++) {
            for (int prevIndex = 0; prevIndex < currentIndex; prevIndex++) {
                if (numbers[prevIndex] < numbers[currentIndex]) {
                    int candidate = lisLength[prevIndex] + 1;
                    if (candidate > lisLength[currentIndex]) {
                        lisLength[currentIndex] = candidate;
                        lisPrevIndex[currentIndex] = prevIndex;
                    }
                }
            }
        }
        int maxLength = 0;
        int maxLengthIndex = -1;
        for (int i = 0; i < numbers.length; i++) {
            if (lisLength[i] > maxLength) {
                maxLength = lisLength[i];
                maxLengthIndex = i;
            }
        }
        int[] longestSequence = new int[maxLength];
        int lisIndexCounter = 0;
        int index = maxLengthIndex;
        while (index != -1) {
            longestSequence[lisIndexCounter] = numbers[index];
            lisIndexCounter++;
            index = lisPrevIndex[index];
        }

        for (int i = longestSequence.length - 1; i >= 0; i--) {
            if (i > 0) {
                System.out.print(longestSequence[i] + " ");
            } else {
                System.out.print(longestSequence[i]);
            }
        }
    }

    private static int[] inputIntoIntArray(String input) {
        String[] inputStringArray = input.split("\\s+");
        int[] intArray = new int[inputStringArray.length];
        for (int i = 0; i < inputStringArray.length; i++) {
            intArray[i] = Integer.parseInt(inputStringArray[i]);
        }
        return intArray;
    }
}
