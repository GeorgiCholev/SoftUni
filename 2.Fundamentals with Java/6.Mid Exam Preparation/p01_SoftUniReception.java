package MidExam_6.Pre;

import java.util.Scanner;

public class p01_SoftUniReception {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int firstEmployeeEfficiency = Integer.parseInt(scanner.nextLine());
        int secondEmployeeEfficiency = Integer.parseInt(scanner.nextLine());
        int studentsCount = Integer.parseInt(scanner.nextLine());
        int studentsPerHour = firstEmployeeEfficiency + secondEmployeeEfficiency + Integer.parseInt(scanner.nextLine());
        int hours = 0;
        while (studentsCount > 0) {
            hours++;
            if (hours % 4 != 0) {
            studentsCount -= studentsPerHour;
            }
        }
        System.out.printf("Time needed: %dh.", hours);
    }
}
