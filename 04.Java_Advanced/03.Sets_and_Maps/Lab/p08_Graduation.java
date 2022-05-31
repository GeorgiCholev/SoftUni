package SetsAndMaps_03.Lab;

import java.util.*;

public class p08_Graduation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner (System.in);
        int studentNumber = Integer.parseInt(scanner.nextLine());
        Map<String, Double> studentGrades = new TreeMap<>();

        while (studentNumber > 0) {
            String name = scanner.nextLine();
            String[] input = scanner.nextLine().split("\\s+");
            double sum = 0.0;
            int count = 0;
            for (String grade : input) {
                double g = Double.parseDouble(grade);
                sum += g;
                count++;
            }
            double average = (sum / (count * 1.00));
//
//            Double average = Arrays.stream(scanner.nextLine().split("\\s+"))
//                    .mapToDouble(Double::parseDouble).average().orElse(0.00);

            studentGrades.putIfAbsent(name, average);

            studentNumber--;
        }
        studentGrades.forEach((k, v) -> System.out.println(k + " is graduated with " + v));
    }
}
