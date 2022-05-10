package MidExam_6.Exam;

import java.util.Scanner;

public class p02_Space {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String[] route = input.split("\\|\\|");
        int fuel = Integer.parseInt(scanner.nextLine());
        int ammunition = Integer.parseInt(scanner.nextLine());
        boolean fail = false;
        int index = 0;
            while (!route[index].equals("Titan")) {
            String[] components = route[index].split(" ");
            String type = components[0];
                switch (type) {
                    case "Travel":
                        int lightYears = Integer.parseInt(components[1]);
                        if (fuel >= lightYears) {
                            fuel -= lightYears;
                            System.out.printf("The spaceship travelled %d light-years.%n", lightYears);
                        } else {
                            fail = true;
                            System.out.println("Mission failed.");
                        }
                        break;
                    case "Enemy":
                        int enemyArmour = Integer.parseInt(components[1]);
                        if (ammunition >= enemyArmour) {
                            ammunition -= enemyArmour;
                            System.out.printf("An enemy with %d armour is defeated.%n", enemyArmour);
                        } else {
                            int fuelToRun = enemyArmour * 2;
                            if (fuelToRun <= fuel) {
                                fuel -= fuelToRun;
                                System.out.printf("An enemy with %d armour is outmaneuvered.%n", enemyArmour);
                            } else {
                                fail = true;
                                System.out.println("Mission failed.");
                            }
                        }
                        break;
                    case "Repair":
                        int addition = Integer.parseInt(components[1]);
                        ammunition += (addition * 2);
                        fuel += addition;
                        System.out.printf("Ammunitions added: %d.%n" + "Fuel added: %d.%n", addition * 2, addition);
                        break;
                }
                if (fail) {
                    break;
                }
                index++;
            }
            if (!fail) {
                System.out.println("You have reached Titan, all passengers are safe.");
            }
        }

    }
