package workout;

import java.util.ArrayList;
import java.util.List;

public class Workout {

    private String type;
    private int exerciseCount;

    private List<Exercise> exercises;

    public Workout(String type, int exerciseCount) {
        this.type = type;
        this.exerciseCount = exerciseCount;
        this.exercises = new ArrayList<>();
    }

    public void addExercise(Exercise exercise) {
        if (exercises.size() < exerciseCount) {
            exercises.add(exercise);
        }
    }

    public boolean removeExercise(String name, String muscle) {
        for (int i = 0; i < exercises.size(); i++) {
            if (exercises.get(i).getName().equals(name) &&
                    exercises.get(i).getMuscle().equals(muscle)) {

                exercises.remove(i);
                return true;

            }
        }
        return false;
    }

    public Exercise getExercise(String name, String muscle) {
        for (Exercise exercise : exercises) {
            if (exercise.getName().equals(name) &&
                    exercise.getMuscle().equals(muscle)) {
                return exercise;
            }
        }
        return null;
    }

    public Exercise getMostBurnedCaloriesExercise() {

        int mostCalories = Integer.MIN_VALUE;
        int index = -1;
        for (int i = 0; i < exercises.size(); i++) {
            if (exercises.get(i).getBurnedCalories() >= mostCalories) {
                mostCalories = exercises.get(i).getBurnedCalories();
                index = i;
            }
        }
        if (index == -1) {
            return null;
        }
        return exercises.get(index);
    }

    public int getExerciseCount() {
        return exercises.size();
    }

    public String getStatistics() {
        StringBuilder buffer = new StringBuilder("Workout type: " + this.type + System.lineSeparator());
        exercises.forEach(ex -> buffer.append(ex).append(System.lineSeparator()));
        return buffer.toString().trim();
    }
}
