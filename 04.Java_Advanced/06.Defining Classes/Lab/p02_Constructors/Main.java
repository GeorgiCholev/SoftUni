package DefiningClasses_06.Lab.p02_Constructors;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < n; i++) {
            String input = scanner.nextLine();
            String[] components = input.split("\\s+");
            String brand = components[0];

            Car car;
            if (components.length == 1) {
                car = new Car(brand);
            } else {
                String model = components[1];
                int horsePower = Integer.parseInt(components[2]);

                car = new Car(brand, model, horsePower);
            }
            System.out.println(car.carInfo());
        }

    }
}
