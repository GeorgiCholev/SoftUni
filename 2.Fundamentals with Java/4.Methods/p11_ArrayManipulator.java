package Methods_4.Exercise;

import java.util.Scanner;

public class p11_ArrayManipulator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String[] textArray = input.split(" ");
        int[] numbers = parseArrayToInt(textArray);
        String command = scanner.nextLine();
        while (!command.equals("end")) {
            String[] commandArray = command.split(" ");
            if (commandArray[0].equals("exchange")) {
                int index = Integer.parseInt(commandArray[1]);
                if (index >= 0 && index < numbers.length) {
                    doExchange(index, numbers);
                } else {
                    System.out.println("Invalid index");
                }
            } else if (commandArray[0].equals("max")) {
                String form = commandArray[1];
                if (form.equals("odd")) {
                    boolean odd = findOdd(numbers);
                    if (odd) {
                        int maxOddIndex = findMaxOddIndex(numbers);
                        System.out.println(maxOddIndex);
                    } else {
                        System.out.println("No matches");
                    }
                } else {
                    boolean even = findEven(numbers);
                    if (even) {
                        int maxEvenIndex = findMaxEvenIndex(numbers);
                        System.out.println(maxEvenIndex);
                    } else {
                        System.out.println("No matches");
                    }
                }
            } else if (commandArray[0].equals("min")) {
                String form = commandArray[1];
                if (form.equals("odd")) {
                    boolean odd = findOdd(numbers);
                    if (odd) {
                        int minOddIndex = findMinOddIndex(numbers);
                        System.out.println(minOddIndex);

                    } else {
                        System.out.println("No matches");
                    }
                } else {
                    boolean even = findEven(numbers);
                    if (even) {
                        int minEvenIndex = findMinEvenIndex(numbers);
                        System.out.println(minEvenIndex);
                    } else {
                        System.out.println("No matches");
                    }
                }
            } else if (commandArray[0].equals("first")) {
                int count = Integer.parseInt(commandArray[1]);
                if (count > numbers.length) {
                    System.out.println("Invalid count");
                    command = scanner.nextLine();
                    continue;
                } else {
                    String form = commandArray[2];
                    if (form.equals("odd")) {
                        boolean odd = findOdd(numbers);
                        if (odd) {
                            String[] firstCountOdd = findFirstNeededOddNumbers(numbers, count);
                            System.out.print('[');
                            printArrayOfFirst(firstCountOdd);
                            System.out.println(']');
                        } else {
                            System.out.println("[]");
                        }
                    } else {
                        boolean even = findEven(numbers);
                        if (even) {
                            String[] firstCountEven = findFirstNeededEvenNumbers(numbers, count);
                            System.out.print('[');
                            printArrayOfFirst(firstCountEven);
                            System.out.println(']');
                        } else {
                            System.out.println("[]");
                        }
                    }
                }
            } else if (commandArray[0].equals("last")) {
                int count = Integer.parseInt(commandArray[1]);
                if (count > numbers.length) {
                    System.out.println("Invalid count");
                    command = scanner.nextLine();
                    continue;
                }
                String form = commandArray[2];
                if (form.equals("odd")) {
                    boolean odd = findOdd(numbers);
                    if (odd) {
                        String[] lastCountOdd = findLastNeededOddNumbers(numbers, count);
                        System.out.print("[");
                        printArrayOfLast(lastCountOdd);
                        System.out.println("]");
                    } else {
                        System.out.println("[]");
                    }
                } else {
                    boolean even = findEven(numbers);
                    if (even) {
                        String[] lastEvenCount = findLastNeededEvenNumbers(numbers, count);
                        System.out.print("[");
                        printArrayOfLast(lastEvenCount);
                        System.out.println("]");
                    } else {
                        System.out.println("[]");
                    }
                }
            }
            command = scanner.nextLine();
        }
        System.out.print("[");
        printIntArray(numbers);
        System.out.println("]");
    }


    private static int[] parseArrayToInt(String[] textArray) {
        int[] numbers = new int[textArray.length];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = Integer.parseInt(textArray[i]);
        }
        return numbers;
    }

    private static void doExchange(int indexSplit, int[] numbers) {
        int[] beforeSplit = new int[indexSplit + 1];
        int[] afterSplit = new int[numbers.length - (indexSplit + 1)];

        System.arraycopy(numbers, 0, beforeSplit, 0, beforeSplit.length);
        for (int i = 0; i < afterSplit.length; i++) {
            afterSplit[afterSplit.length - 1 - i] = numbers[numbers.length - 1 - i];
        }
        System.arraycopy(afterSplit, 0, numbers, 0, afterSplit.length);
        int counter = 0;
        for (int i = beforeSplit.length - 1; i >= 0; i--) {
            numbers[numbers.length - 1 - counter] = beforeSplit[i];
            counter++;
        }
    }

    private static boolean findOdd(int[] numbers) {
        boolean odd = false;
        for (int number : numbers) {
            if (number % 2 != 0) {
                odd = true;
                break;
            }
        }
        return odd;
    }

    private static int findMaxOddIndex(int[] numbers) {
        int maxOdd = Integer.MIN_VALUE;
        int maxOddIndex = -1;
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] % 2 != 0) {
                if (numbers[i] >= maxOdd) {
                    maxOdd = numbers[i];
                    maxOddIndex = i;
                }
            }
        }
        return maxOddIndex;
    }

    private static boolean findEven(int[] numbers) {
        boolean even = false;
        for (int number : numbers) {
            if (number % 2 == 0) {
                even = true;
                break;
            }
        }
        return even;
    }

    private static int findMaxEvenIndex(int[] numbers) {
        int maxEven = Integer.MIN_VALUE;
        int maxEvenIndex = -1;
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] % 2 == 0) {
                if (numbers[i] >= maxEven) {
                    maxEven = numbers[i];
                    maxEvenIndex = i;
                }
            }
        }
        return maxEvenIndex;
    }

    private static int findMinOddIndex(int[] numbers) {
        int minOddIndex = -1;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] % 2 != 0) {
                if (numbers[i] <= min) {
                    min = numbers[i];
                    minOddIndex = i;
                }
            }
        }
        return minOddIndex;
    }

    private static int findMinEvenIndex(int[] numbers) {
        int minEvenIndex = -1;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] % 2 == 0) {
                if (numbers[i] <= min) {
                    min = numbers[i];
                    minEvenIndex = i;
                }
            }
        }
        return minEvenIndex;
    }

    private static String[] findFirstNeededOddNumbers(int[] numbers, int count) {
        String needed = "";
        int countOdd = 0;
        for (int number : numbers) {
            if (number % 2 != 0) {
                countOdd++;
                if (countOdd <= count) {
                    needed += number + " ";
                } else {
                    break;
                }
            }
        }
        return needed.split(" ");
    }

    private static void printArrayOfFirst(String[] numbers) {
        String print = String.join(", ", numbers);
        System.out.print(print);
    }

    private static String[] findFirstNeededEvenNumbers(int[] numbers, int count) {
        String needed = "";
        int countEven = 0;
        for (int number : numbers) {
            if (number % 2 == 0) {
                countEven++;
                if (countEven <= count) {
                    needed += number + " ";
                } else {
                    break;
                }
            }
        }
        return needed.split(" ");
    }

    private static String[] findLastNeededOddNumbers(int[] numbers, int count) {
        String oddNeeded = "";
        int countOdd = 0;
        for (int i = numbers.length - 1; i >= 0; i--) {
            if (numbers[i] % 2 != 0) {
                countOdd++;
                if (countOdd <= count) {
                    oddNeeded += numbers[i] + " ";
                } else {
                    break;
                }
            }
        }
        return oddNeeded.split(" ");
    }

    private static void printArrayOfLast(String[] numbers) {
        for (int i = numbers.length - 1; i >= 0; i--) {
            System.out.print(numbers[i]);
            if (i != 0) {
                System.out.print(", ");
            }
        }
    }

    private static String[] findLastNeededEvenNumbers(int[] numbers, int count) {
        String evenNeeded = "";
        int evenCount = 0;
        for (int i = numbers.length - 1; i >= 0; i--) {
            if (numbers[i] % 2 == 0) {
                evenCount++;
                if (evenCount <= count) {
                    evenNeeded += numbers[i] + " ";
                } else {
                    break;
                }
            }
        }
        return evenNeeded.split(" ");
    }

    private static void printIntArray(int[] numbers) {
        for (int i = 0; i < numbers.length; i++) {
            System.out.print(numbers[i]);
            if (i != numbers.length - 1) {
                System.out.print(", ");
            }
        }
    }
}
