package DefiningClasses_06.Exercise.p03_SpeedRacing;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner (System.in);
        int numberOfCars = Integer.parseInt(scanner.nextLine());
        Map<String, Car> carsByModel = new LinkedHashMap<>(numberOfCars);

        for (int i = 0; i < numberOfCars; i++) {
            Car car = constructCar(scanner.nextLine().split("\\s"));
            storeCarIn(carsByModel, car, car.getModel());
        }

        String command;
        while (!"End".equals(command = scanner.nextLine())){

            findTargetCarIn(carsByModel, command.split("\\s"));
        }

        printCarStatisticsInInputOrder(new LinkedHashMap<>(carsByModel));

    }

    static void printCarStatisticsInInputOrder(LinkedHashMap<String, Car> carsByModel) {
        carsByModel.values().forEach(c -> System.out.println(c.getStatistics()));
    }

    static void handleDriveAttempt(Car car, int kmToDrive) {
        boolean ableToDrive = car.drive(kmToDrive);
        if (!ableToDrive) {
            System.out.println("Insufficient fuel for the drive");
        }
    }

    static void findTargetCarIn(Map<String, Car> carsByModel, String[] components) {
        String modelOfTargetCar = components[1];
        Car targetCar = carsByModel.get(modelOfTargetCar);
        int kmToDrive = Integer.parseInt(components[2]);

        handleDriveAttempt(targetCar, kmToDrive);
    }

    static void storeCarIn(Map<String, Car> storedCarsByModel, Car car, String model) {
        storedCarsByModel.putIfAbsent(model, car);
    }

    static Car constructCar(String[] carInfo) {
        String model = carInfo[0];
        double fuelAmount = Double.parseDouble(carInfo[1]);
        double costPerKm = Double.parseDouble(carInfo[2]);
        return new Car(model, fuelAmount, costPerKm);
    }
}
