package DefiningClasses_06.Exercise.p05_CarSalesman;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int numberOfEngines = Integer.parseInt(scanner.nextLine());

        Map<String, Engine> engineModels = new HashMap<>(numberOfEngines);

        for (int i = 0; i < numberOfEngines; i++) {
            String[] engineComponents = scanner.nextLine().split(" ");
            Engine engine = assembleEngine(engineComponents);
            String model = engine.getModel();
            engineModels.putIfAbsent(model, engine);
        }

        int numberOfCars = Integer.parseInt(scanner.nextLine());

        Set<Car> cars = new LinkedHashSet<>(numberOfCars);

        for (int i = 0; i < numberOfCars; i++) {
            String[] carComponents = scanner.nextLine().split(" ");
            Car car = assembleCar(carComponents, engineModels);
            cars.add(car);
        }

        cars.forEach(System.out::println);
    }

    private static Car assembleCar(String[] carComponents, Map<String, Engine> engineModels) {

        String model = carComponents[0];
        String engineModel = carComponents[1];
        Engine engine = engineModels.get(engineModel);

        switch (carComponents.length) {
            case 2:
                return new Car(model, engine);
            case 3:
                String thirdComponent = carComponents[2];
                return new Car(model, engine, thirdComponent);
            case 4:
                String weight = carComponents[2];
                String color = carComponents[3];
                return new Car(model, engine, weight, color);
            default:
                throw new IllegalArgumentException("Illegal Input");
        }
    }

    private static Engine assembleEngine(String[] engineComponents) {

        String model = engineComponents[0];
        int power = Integer.parseInt(engineComponents[1]);

        switch (engineComponents.length) {

            case 2:
                return new Engine(model, power);

            case 3:
                String thirdComponent = engineComponents[2];
                return new Engine(model, power, thirdComponent);

            case 4:
                String displacement = engineComponents[2];
                String efficiency = engineComponents[3];
                return new Engine(model, power, displacement, efficiency);

            default:
                throw new IllegalArgumentException("Illegal Input");
        }
    }
}
