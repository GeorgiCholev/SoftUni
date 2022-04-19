package BasicSyntax_1.Exercise;

import java.util.Scanner;

public class p10_Padawan {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double moneyAvailable = Double.parseDouble(scanner.nextLine());
        int students = Integer.parseInt(scanner.nextLine());
        double saberPrice = Double.parseDouble(scanner.nextLine());
        double robePrice = Double.parseDouble(scanner.nextLine());
        double beltPrice = Double.parseDouble(scanner.nextLine());

        double saberTotal = Math.ceil((students * 1.1)) * saberPrice;
        int beltsFree = students / 6;
        double beltsTotal = (students - beltsFree) * beltPrice;
        double robeTotal = robePrice * students;
        double whole = saberTotal + beltsTotal + robeTotal;

        if (moneyAvailable >= whole) {
            System.out.printf("The money is enough - it would cost %.2flv.", whole);
        } else {
            System.out.printf("George Lucas will need %.2flv more.", (whole - moneyAvailable));
        }
    }
}
