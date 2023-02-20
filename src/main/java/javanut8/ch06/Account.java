package javanut8.ch06;

public class Account {
    private double balance = 0.0;

    public Account(double openingBal) {
        balance = openingBal;
    }

    public boolean withdraw(double amount) {
        if (balance > amount) {
            try {
                Thread.sleep(2000); // Simulate bank's risk checks
            } catch (InterruptedException e) {
                return false;
            }
            balance = balance - amount;
            return true;
        }
        return false;
    }
}
