package Lists_5.Exercise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class p10_CoursePlanning {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        List<String> courses = new ArrayList<>(Arrays.asList(input.split(", ")));
        String line = scanner.nextLine();
        while (!line.equals("course start")) {
            String[] components = line.split(":");
            String command = components[0];
            switch (command) {
                case "Add":
                    String lessonToAdd = components[1];
                    if (!courses.contains(lessonToAdd)) {
                        courses.add(lessonToAdd);
                    }
                    break;
                case "Insert":
                    String lessonToInsert = components[1];
                    int position = Integer.parseInt(components[2]);
                    if (!courses.contains(lessonToInsert)) {
                        courses.add(position, lessonToInsert);
                    }
                    break;
                case "Remove":
                    String lessonToRemove = components[1];
                    int indexToRemove = courses.indexOf(lessonToRemove);
                    if (indexToRemove != -1) {
                        if (hasAnExercise(courses, lessonToRemove) != -1) {
                            courses.remove(indexToRemove + 1);
                        }
                        courses.remove(indexToRemove);
                    }
                    break;
                case "Swap":
                    String lessonOne = components[1];
                    int indexOfLessonOne = courses.indexOf(lessonOne);
                    String lessonTwo = components[2];
                    int indexOfLessonTwo = courses.indexOf(lessonTwo);
                    if (indexOfLessonOne != -1 && indexOfLessonTwo != -1) {
                        int exerciseOneIndex = hasAnExercise(courses, lessonOne);
                        int exerciseTwoIndex = hasAnExercise(courses, lessonTwo);
                        courses.set(indexOfLessonOne, lessonTwo);
                        courses.set(indexOfLessonTwo, lessonOne);
                        if (exerciseOneIndex != -1) {
                            String exerciseOne = courses.get(exerciseOneIndex);
                            courses.remove(exerciseOneIndex);
                            courses.add(courses.indexOf(lessonOne) + 1, exerciseOne);
                        }
                        if (exerciseTwoIndex != -1) {
                            String exerciseTwo = courses.get(exerciseTwoIndex);
                            courses.remove(exerciseTwoIndex);
                            courses.add(courses.indexOf(lessonTwo) + 1, exerciseTwo);
                        }
                    }
                    break;
                case "Exercise":
                    String lesson = components[1];
                    String exercise = lesson + "-Exercise";
                    int lessonIndex = courses.indexOf(lesson);
                    if (lessonIndex == -1) {
                        courses.add(lesson);
                        courses.add(exercise);
                    } else if (!courses.contains(exercise)) {
                        courses.add(lessonIndex + 1, exercise);
                    }
                    break;
            }

            line = scanner.nextLine();
        }
        for (int i = 0; i < courses.size(); i++) {
            System.out.println((i + 1) + "." + courses.get(i));
        }
    }

    private static int hasAnExercise(List<String> courses, String lesson) {
        return courses.indexOf(lesson + "-Exercise");
    }


}
