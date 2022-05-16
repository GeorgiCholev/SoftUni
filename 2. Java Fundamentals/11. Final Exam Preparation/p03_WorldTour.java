package Fundamentals_ExamPrep_11.August9th2020;

import java.util.Scanner;

public class p01_WorldTour {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder trip = new StringBuilder(scanner.nextLine());
        String command = scanner.nextLine();
        while (!command.equals("Travel")) {
            performCommand(trip, command);
            System.out.println(trip);
            command = scanner.nextLine();
        }
        System.out.println("Ready for world tour! Planned stops: " + trip);
    }

    private static void performCommand(StringBuilder trip, String command) {
        String[] components = command.split(":");
        String action = components[0];
        switch (action) {
            case "Add Stop":
                int index = Integer.parseInt(components[1]);
                String stop = components[2];
                if (index >= 0 && index < trip.length()) {
                    trip.insert(index, stop);
                }
                break;
            case "Remove Stop":
                int startIndex = Integer.parseInt(components[1]);
                int endIndex = Integer.parseInt(components[2]);
                if ((startIndex >= 0 && startIndex < trip.length()) && (endIndex >= 0 && endIndex < trip.length())) {
                    trip.delete(startIndex, endIndex + 1);
                }
                break;
            case "Switch":
                String oldStop = components[1];
                String newStop = components[2];
                int indexOldStop = trip.indexOf(oldStop);
                while (indexOldStop != -1) {
                    trip.replace(indexOldStop, indexOldStop + oldStop.length(), newStop);
                    indexOldStop = trip.indexOf(oldStop, indexOldStop + newStop.length());
                }
                break;
        }
    }
}
