package _3_Exercise_RecursionAndCombinatorics;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class p07_Cinema {

    public static List<String> people;
    public static String[] seats;
    public static String[] notFixedSeats;
    public static boolean[] isUsed;

    public static void main(String[] args) {
        Scanner scanner = new Scanner (System.in);
        people = Arrays.stream(scanner.nextLine().split(", ")).collect(Collectors.toList());
        seats = new String[people.size()];
        isUsed = new boolean[people.size()];

        String line = scanner.nextLine();
        while (!line.equals("generate")) {
            String[] components = line.split(" - ");
            String person = components[0];
            int fixedPosition = Integer.parseInt(components[1]);
            seats[fixedPosition - 1] = person;
            people.remove(person);
            line = scanner.nextLine();
        }

        notFixedSeats = new String[people.size()];
        isUsed = new boolean[people.size()];
        permute(0);


    }

    private static void permute(int index) {
        if (index == notFixedSeats.length) {
            print();
            return;
        }

        for (int i = 0; i < notFixedSeats.length; i++) {
            if (!isUsed[i]) {
                isUsed[i] = true;
                notFixedSeats[index] = people.get(i);
                permute(index + 1);
                isUsed[i] = false;
            }
        }
    }

    private static void print() {
        StringBuilder output = new StringBuilder();
        int indexPermuted = 0;
        for (String seat : seats) {
            if (seat != null) {
                output.append(seat).append(" ");
            } else {
                output.append(notFixedSeats[indexPermuted++]).append(" ");
            }
        }
        System.out.println(output.toString().trim());
    }
}
