package For_Loop_4.Exercise;

import java.util.Scanner;

public class p05_Salary {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int openTabs = Integer.parseInt(scanner.nextLine());
        int salary = Integer.parseInt(scanner.nextLine());

        int facebook = 150;
        int instagram = 100;
        int reddit = 50;

        for (int i = 1; i <= openTabs; i++) {
            String site = scanner.nextLine();
            switch (site) {
                case "Facebook":
                    salary -= facebook;
                    break;
                case "Instagram":
                    salary -= instagram;
                    break;
                case "Reddit":
                    salary -= reddit;
                    break;
                default:
                    salary = salary;
                    break;
            }
            if (salary == 0) {
                System.out.println("You have lost your salary.");
                break;
            }
        }
        if (salary!=0) {
            System.out.println(salary);
        }

    }
}
