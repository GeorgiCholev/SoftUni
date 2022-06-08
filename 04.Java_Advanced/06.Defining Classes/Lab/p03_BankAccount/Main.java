package DefiningClasses_06.Lab.p03_BankAccount;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<Integer, BankAccount> accountOfUsers = new HashMap<>();
        String input = scanner.nextLine();

        while (!input.equals("End")) {
            String[] components = input.split("\\s+");
            String command = components[0];
            String output = "default";
            switch (command) {
                case "Create":
                    output = createAccount(accountOfUsers);
                    break;
                case "Deposit":
                    output = depositToAccount(accountOfUsers, components);
                    break;
                case "SetInterest":
                    setBanksInterest(components[1]);
                    break;
                case "GetInterest":
                    output = getInterestForAccount(accountOfUsers, components);
                    break;
            }

            if (!output.equals("default")) {
                System.out.println(output);
            }
            input = scanner.nextLine();
        }
    }

    private static String getInterestForAccount(Map<Integer, BankAccount> accountOfUsers, String[] components) {

        int id = Integer.parseInt(components[1]);

        if (accountOfUsers.containsKey(id)) {
            int years = Integer.parseInt(components[2]);
            BankAccount bankAccount = accountOfUsers.get(id);
            double interestAcquired = bankAccount.getInterest(years);
            return String.format("%.2f", interestAcquired);
        }
        return accountNotFound();
    }

    private static void setBanksInterest(String component) {
        double interest = Double.parseDouble(component);
        BankAccount.setInterestRate(interest);
    }

    private static String depositToAccount(Map<Integer, BankAccount> accountOfUsers, String[] components) {

        int id = Integer.parseInt(components[1]);

        if (accountOfUsers.containsKey(id)) {
            double amount = Double.parseDouble(components[2]);
            BankAccount bankAccount = accountOfUsers.get(id);
            bankAccount.deposit(amount);
            return "Deposited " + (int) amount + " to ID" + id;
        }
        return accountNotFound();
    }

    private static String accountNotFound() {
        return "Account does not exist";
    }

    private static String createAccount(Map<Integer, BankAccount> accountOfUsers) {

        BankAccount bankAccount = new BankAccount();
        int id = bankAccount.getId();
        accountOfUsers.put(id, bankAccount);
        return "Account ID" + id + " created";
    }
}
