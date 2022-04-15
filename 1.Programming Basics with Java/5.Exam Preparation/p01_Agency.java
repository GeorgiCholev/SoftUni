package ExamPreparation;


import java.util.Scanner;

public class p01_1_Agency {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String name = scanner.nextLine();
        int adultTickets = Integer.parseInt(scanner.nextLine());
        int kidTickets = Integer.parseInt(scanner.nextLine());
        double netAdult = Double.parseDouble(scanner.nextLine());
        double tax = Double.parseDouble(scanner.nextLine());
        
        double netKid = netAdult * 0.3;
        double adultActualPrize = netAdult + tax;
        double kidActualPrize = netKid + tax;
        double totalPrize = (adultTickets * adultActualPrize) + (kidTickets * kidActualPrize);
        double profit = totalPrize * 0.2;

        System.out.printf("The profit of your agency from %s tickets is %.2f lv.", name, profit);
    }
}
