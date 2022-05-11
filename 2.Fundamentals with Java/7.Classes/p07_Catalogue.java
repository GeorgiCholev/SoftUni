package Classes_7.Exercises;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class p06_Catalogue {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        List<Vehicle> vehicles = new ArrayList<>();
        int powerTrucks = 0;
        int numberTrucks = 0;
        int powerCars = 0;
        int numberCars = 0;
        while (!line.equals("End")) {
            String[] components = line.split("\\s+");
            String type = components[0];
            String model = components[1];
            String colour = components[2];
            int horsePower = Integer.parseInt(components[3]);
            switch (type) {
                case "car":
                    powerCars += horsePower;
                    numberCars++;
                    break;
                case "truck":
                    powerTrucks += horsePower;
                    numberTrucks++;
                    break;
            }
            Vehicle nextVehicle = new Vehicle(type, model, colour, horsePower);
            vehicles.add(nextVehicle);
            line = scanner.nextLine();
        }
        line = scanner.nextLine();
        while (!line.equals("Close the Catalogue")) {
            printModels(line, vehicles);
            line = scanner.nextLine();
        }
        double averageCars = 0.0;
        if (numberCars != 0) {
            averageCars = powerCars * 1.0 / numberCars;
        }
        System.out.printf("Cars have average horsepower of: %.2f.\n", averageCars);
        double averageTrucks = 0.0;
        if (numberTrucks != 0) {
            averageTrucks = powerTrucks * 1.0 / numberTrucks;
        }
        System.out.printf("Trucks have average horsepower of: %.2f.", averageTrucks);
    }

    private static void printModels(String line, List<Vehicle> vehicles) {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getModel().equals(line)) {
                System.out.println(vehicle.getData());
            }
        }
    }

    static class Vehicle {
        String type;
        String model;
        String colour;
        int horsePower;

        Vehicle(String type, String model, String colour, int horsePower) {
            this.type = type;
            this.model = model;
            this.colour = colour;
            this.horsePower = horsePower;
        }

        String getModel() {
            return this.model;
        }

        String getData() {
            if (this.type.equals("car")) {
                return String.format("Type: Car\n" + "Model: %s\n" + "Color: %s\n" + "Horsepower: %d",
                        this.model, this.colour, this.horsePower);
            } else {
                return String.format("Type: Truck\n" + "Model: %s\n" + "Color: %s\n" + "Horsepower: %d",
                        this.model, this.colour, this.horsePower);
            }
        }
    }
}
