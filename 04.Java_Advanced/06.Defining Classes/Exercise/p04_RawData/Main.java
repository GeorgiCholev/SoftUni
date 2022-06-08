package DefiningClasses_06.Exercise.p04_RawData;

import java.util.*;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int numberCars = Integer.parseInt(scanner.nextLine());

        Map<String, List<Car>> carsByCargoTypes = new HashMap<>(2);
        carsByCargoTypes.put("flamable", new ArrayList<>());
        carsByCargoTypes.put("fragile", new ArrayList<>());

        for (int i = 0; i < numberCars; i++) {
            String[] components = scanner.nextLine().split("\\s+");
            Car car = assembleCar(components);
            String cargoType = car.getCargoType();
            carsByCargoTypes.get(cargoType).add(car);
        }

        String typeInput = scanner.nextLine();
        switch (typeInput) {
            case "flamable":
                Predicate<Car> filterByEnginePower = car -> car.getEnginePower() > 250;

                carsByCargoTypes.get(typeInput).stream()
                        .filter(filterByEnginePower)
                        .forEach(car -> System.out.println(car.getModel()));
                break;

            case "fragile":
                Predicate<Car> filterByTirePressure = car -> Arrays.stream(car.getFourTires())
                        .anyMatch(t -> t.getPressure() < 1);

                carsByCargoTypes.get(typeInput).stream()
                        .filter(filterByTirePressure)
                        .forEach(car -> System.out.println(car.getModel()));
                break;
        }
    }

    private static Car assembleCar(String[] components) {

        String model = components[0];

        int engineSpeed = Integer.parseInt(components[1]);
        int enginePower = Integer.parseInt(components[2]);
        Engine engine = new Engine(engineSpeed, enginePower);

        int cargoWeight = Integer.parseInt(components[3]);
        String cargoType = components[4];
        Cargo cargo = new Cargo(cargoWeight, cargoType);

        double firstTirePressure = Double.parseDouble(components[5]);
        int firstTireAge = Integer.parseInt(components[6]);
        Tire firstTire = new Tire(firstTirePressure, firstTireAge);

        double secondTirePressure = Double.parseDouble(components[7]);
        int secondTireAge = Integer.parseInt(components[8]);
        Tire secondTire = new Tire(secondTirePressure, secondTireAge);

        double thirdTirePressure = Double.parseDouble(components[9]);
        int thirdTireAge = Integer.parseInt(components[10]);
        Tire thirdTire = new Tire(thirdTirePressure, thirdTireAge);

        double fourthTirePressure = Double.parseDouble(components[11]);
        int fourthTireAge = Integer.parseInt(components[12]);
        Tire fourthTire = new Tire(fourthTirePressure, fourthTireAge);

        Tire[] fourTires = new Tire[]{firstTire, secondTire, thirdTire, fourthTire};

        return new Car(model, engine, cargo, fourTires);
    }
}
