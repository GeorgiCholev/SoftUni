package BasicSyntax_1.More.Exercises;

import java.util.Scanner;

public class p03_Gaming_Store {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double balance = Double.parseDouble(scanner.nextLine());
        String game = scanner.nextLine();
        double totalSpent = 0.0;
        while (!game.equals("Game Time")) {
            switch (game) {
                case "OutFall 4":
                    if (balance >= 39.99) {
                        balance -= 39.99;
                        totalSpent += 39.99;
                        System.out.println("Bought OutFall 4");
                    } else {
                        System.out.println("Too Expensive");
                        game = scanner.nextLine();
                        continue;
                    }
                    break;
                case "CS: OG":
                    if (balance >= 15.99) {
                        balance -= 15.99;
                        totalSpent += 15.99;
                        System.out.println("Bought CS: OG");
                    } else {
                        System.out.println("Too Expensive");
                        game = scanner.nextLine();
                        continue;
                    }
                    break;
                case "Zplinter Zell":
                    if (balance >= 19.99) {
                        balance -= 19.99;
                        totalSpent += 19.99;
                        System.out.println("Bought Zplinter Zell");
                    } else {
                        System.out.println("Too Expensive");
                        game = scanner.nextLine();
                        continue;
                    }
                    break;
                case "Honored 2":
                    if (balance >= 59.99) {
                        balance -= 59.99;
                        totalSpent += 59.99;
                        System.out.println("Bought Honored 2");
                    } else {
                        System.out.println("Too Expensive");
                        game = scanner.nextLine();
                        continue;
                    }
                    break;
                case "RoverWatch":
                    if (balance >= 29.99) {
                        balance -= 29.99;
                        totalSpent += 29.99;
                        System.out.println("Bought RoverWatch");
                    } else {
                        System.out.println("Too Expensive");
                        game = scanner.nextLine();
                        continue;
                    }
                    break;
                case "RoverWatch Origins Edition":
                    if (balance >= 39.99) {
                        balance -= 39.99;
                        totalSpent += 39.99;
                        System.out.println("Bought RoverWatch Origins Edition");
                    } else {
                        System.out.println("Too Expensive");
                        game = scanner.nextLine();
                        continue;
                    }
                    break;
                default:
                    System.out.println("Not Found");
                    game = scanner.nextLine();
                    continue;
            }
            if (balance == 0) {
                System.out.println("Out of money!");
                break;
            }
            game = scanner.nextLine();
        }
        if (balance != 0) {
            System.out.printf("Total spent: $%.2f. Remaining: $%.2f", totalSpent, balance);
        }
    }
}
