package If_Continuation_3.Exercise;

import java.util.Scanner;

public class p07_HotelRoom {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String month = scanner.nextLine();
        int numberOvernights = Integer.parseInt(scanner.nextLine());

        double totalPriceStudio = 0.0;
        double totalPriceApartment = 0.0;
        double studioOvernight = 0.0;
        double apartmentOvernight = 0.0;

        switch (month) {
            case "May":
            case "October":
                studioOvernight = 50;
                apartmentOvernight = 65;
                break;
            case "June":
            case "September":
                studioOvernight = 75.20;
                apartmentOvernight = 68.70;
                break;
            case "July":
            case "August":
                studioOvernight = 76;
                apartmentOvernight = 77;
                break;
        }
        totalPriceStudio = studioOvernight * numberOvernights;
        totalPriceApartment = apartmentOvernight * numberOvernights;
        if ((numberOvernights > 7 && numberOvernights <=14) && ((month.equals("May")) || (month.equals("October")))) {
            totalPriceStudio *= 0.95;
        } else if ((numberOvernights > 14) && ((month.equals("May")) || (month.equals("October")))){
            totalPriceStudio *= 0.70;
        } else if ((numberOvernights > 14) && ((month.equals("June")) || (month.equals("September")))) {
            totalPriceStudio *= 0.80;
        }
        if (numberOvernights > 14) {
            totalPriceApartment *= 0.90;
        }
        System.out.printf("Apartment: %.2f lv.%n", totalPriceApartment);
        System.out.printf("Studio: %.2f lv.", totalPriceStudio);
    }
}
