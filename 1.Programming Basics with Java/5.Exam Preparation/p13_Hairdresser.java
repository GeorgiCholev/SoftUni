package PreExam_7;

import java.util.Scanner;

public class p05_Hairdresser {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int goal = Integer.parseInt(scanner.nextLine());
        String service = scanner.nextLine();

        int totalProfit = 0;
        String serviceType = "";
        boolean success = false;

        while (!service.equals("closed")) {
            switch (service) {
                case "haircut":
                    serviceType = scanner.nextLine();
                    switch (serviceType) {
                        case "mens":
                          totalProfit += 15;
                            break;
                        case "ladies":
                            totalProfit += 20;
                            break;
                        case "kids":
                            totalProfit += 10;
                            break;
                    }
                    break;
                case "color":
                    serviceType = scanner.nextLine();
                    switch (serviceType){
                        case "touch up":
                            totalProfit += 20;
                            break;
                        case "full color":
                            totalProfit += 30;
                            break;
                    }
                    break;

            }
            if (totalProfit >= goal) {
                success = true;
                break;
            }
            service = scanner.nextLine();
        }
        if (success) {
            System.out.printf("You have reached your target for the day!%n" + "Earned money: %dlv.", totalProfit);
        } else {
            System.out.printf("Target not reached! You need %dlv. more.%n" + "Earned money: %dlv.", (goal - totalProfit), totalProfit);
        }
    }
}
