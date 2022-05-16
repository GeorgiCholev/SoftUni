package Fundamentals_ExamPrep_11.April14th2020;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class p03_NeedForSpeed {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        Map<String, Car> carsByName = new LinkedHashMap<>();
        for (int i = 0; i < n; i++) {
            String nextCar = scanner.nextLine();
            String[] paramsCar = nextCar.split("\\|");
            Car car = new Car(paramsCar[0], Integer.parseInt(paramsCar[1]), Integer.parseInt(paramsCar[2]));
            carsByName.put(paramsCar[0], car);
        }
        String input = scanner.nextLine();
        while (!input.equals("Stop")) {
            String[] components = input.split("\\s:\\s");
            String command = components[0];
            switch (command) {
                case "Drive" -> handleDrive(carsByName, components);
                case "Refuel" -> handleRefuel(carsByName, components);
                case "Revert" -> handleRevert(carsByName, components);
            }

            input = scanner.nextLine();
        }
        carsByName.values().forEach(v -> System.out.println(v.getInfo()));
    }


    private static void handleDrive(Map<String, Car> carsByName, String[] components) {
        String name = components[1];
        int distance = Integer.parseInt(components[2]);
        int fuel = Integer.parseInt(components[3]);
        Car car = carsByName.get(name);
        boolean tookTheDrive = car.drive(distance, fuel);
        if (tookTheDrive) {
            System.out.println(name + " driven for " + distance + " kilometers. " + fuel + " liters of fuel consumed.");
            if (car.getMileage() >= 100000) {
                System.out.println("Time to sell the " + name + "!");
                carsByName.remove(name);
            }
        } else {
            System.out.println("Not enough fuel to make that ride");
        }
    }

    private static void handleRefuel(Map<String, Car> carsByName, String[] components) {
        String name = components[1];
        int fuel = Integer.parseInt(components[2]);
        Car car = carsByName.get(name);
        int refilledLitres = car.refill(fuel);
        System.out.println(name + " refueled with " + refilledLitres + " liters");
    }

    private static void handleRevert(Map<String, Car> carsByName, String[] components) {
        String name = components[1];
        int kilometers = Integer.parseInt(components[2]);
        Car car = carsByName.get(name);
        int revertedKilometers = car.revert(kilometers);
        if (car.getMileage() > 10000) {
            System.out.println(name + " mileage decreased by " + revertedKilometers + " kilometers");
        }
    }

    static class Car {

        String name;
        int mileage;
        int fuel;

        Car(String name, int mileage, int fuel) {
            this.name = name;
            this.mileage = mileage;
            this.fuel = fuel;
        }

        String getInfo() {
            return this.name + " -> Mileage: " + this.mileage + " kms, Fuel in the tank: " + this.fuel + " lt.";
        }

        boolean drive(int distance, int fuelSpent) {
            if (this.fuel >= fuelSpent) {
                this.mileage += distance;
                this.fuel -= fuelSpent;
                return true;
            } else {
                return false;
            }
        }

        public int getMileage() {
            return mileage;
        }

        int refill(int quantity) {
            int beforeRefill = this.fuel;
            this.fuel = Math.min(this.fuel + quantity, 75);
            return this.fuel - beforeRefill;
        }

        int revert(int quantity) {
            int beforeRevert = this.mileage;
            this.mileage = Math.max(this.mileage - quantity, 10000);
            return beforeRevert - this.mileage;
        }
    }
}
