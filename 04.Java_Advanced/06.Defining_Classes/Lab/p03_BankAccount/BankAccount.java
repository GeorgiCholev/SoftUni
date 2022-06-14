package DefiningClasses_06.Lab.p03_BankAccount;

public class BankAccount {

    private static double interestRate = 0.02;
    private static int numberOfIds = 1;

    private int id;
    private double balance;

    public BankAccount() {
        this.id = numberOfIds;
        numberOfIds++;
    }

    public static void setInterestRate(double interestRate) {
        BankAccount.interestRate = interestRate;
    }

    public double getInterest(double years) {
        return BankAccount.interestRate * years * this.balance;
    }

    public void deposit(double amount) {
        this.balance += amount;
    }

    public int getId() {
        return this.id;
    }
}
