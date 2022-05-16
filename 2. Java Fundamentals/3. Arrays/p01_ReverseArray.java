package Arrays_3.Lab;

public class ReverseArray {
    public static void main(String[] args) {
        int[] numbers = new int[]{1, 2, 3, 4, 5, 8, 144, 16};
        reverseIntArray(numbers);
        for (int number : numbers) {
            System.out.print(number + "\s");
        }
    }

    private static void reverseIntArray(int[] numbers) {
        for (int i = 0; i <= numbers.length / 2; i++) {
            int temporary = numbers[numbers.length - 1 - i];
            numbers[numbers.length - 1 - i] = numbers[i];
            numbers[i] = temporary;
        }
    }
}
