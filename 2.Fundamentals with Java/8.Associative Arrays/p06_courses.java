package Maps_8.Exercise;

import java.util.*;

public class p06_courses {
    public static void main(String[] args) {
        Scanner scanner = new Scanner (System.in);
        String line = scanner.nextLine();
        Map<String, List<String>> studentsOfCourse = new LinkedHashMap<>();
        while (!line.equals("end")) {
            String[] components = line.split(" : ");
            String course = components[0];
            String name = components[1];
            if (!studentsOfCourse.containsKey(course)) {
                studentsOfCourse.put(course, new ArrayList<>());
            }
            studentsOfCourse.get(course).add(name);
            line = scanner.nextLine();
        }

        studentsOfCourse.forEach((key, value) -> {
            System.out.println(key + ": " + value.size());
            value.forEach(name -> System.out.println("-- " + name));
        });
    }
}
