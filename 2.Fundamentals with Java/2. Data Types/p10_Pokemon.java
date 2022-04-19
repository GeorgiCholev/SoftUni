package DataTypes_2.Exercise;

import java.util.Scanner;

public class p10_Pokemon {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int pokePower = Integer.parseInt(scanner.nextLine());
        int distanceTargets = Integer.parseInt(scanner.nextLine());
        int exhaustionFactor = Integer.parseInt(scanner.nextLine());
        int targetsHit = 0;
        double pokeHalving = pokePower * 0.5;
        while (pokePower >= distanceTargets) {
            pokePower -= distanceTargets;
            targetsHit++;
            if (pokeHalving == pokePower && exhaustionFactor != 0) {
                pokePower /= exhaustionFactor;
            }
        }
        System.out.print(pokePower + "\n" + targetsHit);
    }
}
