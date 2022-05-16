package For_Loop_4.Extra;

import java.util.Scanner;

public class p03_Logistics {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int cargoQuantity = Integer.parseInt(scanner.nextLine());

        double cargoPrice = 0.0;
        int microBus = 0;
        int truck = 0;
        int train = 0;

        for (int i = 1; i <= cargoQuantity; i++) {
            int cargoWeight = Integer.parseInt(scanner.nextLine());
            if (cargoWeight <= 3) {
                microBus+= cargoWeight;
                cargoPrice += cargoWeight * 200;
            } else if (cargoWeight <= 11) {
                truck+= cargoWeight;
                cargoPrice += cargoWeight * 175;
            } else {
                train+= cargoWeight;
                cargoPrice += cargoWeight * 120;
            }
        }
        double totalWeight = microBus + truck + train;
        double averagePrice = cargoPrice / totalWeight;
        double percentBus = (microBus / totalWeight) * 100;
        double percentTruck = (truck / totalWeight) * 100;
        double percentTrain = (train / totalWeight) * 100;
        System.out.printf("%.2f%n", averagePrice);
        System.out.printf("%.2f%%%n", percentBus);
        System.out.printf("%.2f%%%n", percentTruck);
        System.out.printf("%.2f%%%n", percentTrain );
    }
}
