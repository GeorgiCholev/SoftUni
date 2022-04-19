package DataTypes_2.Exercise;

import java.util.Scanner;

public class p11_SnowBalls {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double highestValue = 0;
        int numberSnowballs = Integer.parseInt(scanner.nextLine());
        int highestSnowSnowball = 0;
        int highestTimeSnowball = 0;
        int highestQualitySnowball = 0;
        for (int i = 1; i <= numberSnowballs; i++) {
            int snowSnowball = Integer.parseInt(scanner.nextLine());
            int timeSnowball = Integer.parseInt(scanner.nextLine());
            int qualitySnowball = Integer.parseInt(scanner.nextLine());
            double snowballValue = Math.pow((snowSnowball / (timeSnowball * 0.0)), qualitySnowball);
            if (snowballValue > highestValue) {
                highestValue = snowballValue;
                highestSnowSnowball = snowSnowball;
                highestTimeSnowball = timeSnowball;
                highestQualitySnowball = qualitySnowball;
            }
        }
        System.out.printf("%d : %d = %.0f (%d)", highestSnowSnowball, highestTimeSnowball,
                highestValue, highestQualitySnowball);
    }
}
