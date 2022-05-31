package Maps_8.Exercise;


import java.util.*;

public class p07_Academy {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        Map<String, List<Double>> gradesOfStudents = new LinkedHashMap<>();
        Map<String, Double> averageGrade = new LinkedHashMap<>();
        for (int i = 0; i < n * 2; i++) {
            String name = scanner.nextLine();
            i++;
            double grade = Double.parseDouble(scanner.nextLine());
            gradesOfStudents.putIfAbsent(name, new ArrayList<>());
            gradesOfStudents.get(name).add(grade);
        }

        gradesOfStudents.entrySet().stream()
                .filter(entry -> {
                    double average = entry.getValue().stream().mapToDouble(g -> g).average().orElse(Double.NaN);
                    boolean qualifies = average >= 4.50;
                    if (qualifies) {
                        averageGrade.put(entry.getKey(), average);
                    }
                    return (qualifies);
                })
                .forEach(entry -> {
                    String gradeFormatted = String.format("%.2f", averageGrade.get(entry.getKey()));
                    System.out.println(entry.getKey() + " -> " + gradeFormatted);
                });

    }
}
