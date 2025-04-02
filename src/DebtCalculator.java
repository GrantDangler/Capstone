import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DebtCalculator {

    static class Debt {
        String name;
        double amount;
        double interestRate;
        double monthlyPayment;

        public Debt(String name, double amount, double interestRate, double monthlyPayment) {
            this.name = name;
            this.amount = amount;
            this.interestRate = interestRate;
            this.monthlyPayment = monthlyPayment;
        }
    }

    public static void askDebt(Scanner scnr, UserInfo user, ExpenseTracker expenseTracker) {
        System.out.println("\n===== Debt Calculator =====");
        List<Debt> debts = new ArrayList<>();

        while (true) {  // Allow user to add multiple debts
            String name;
            while (true) {  // Loop until valid debt type is entered
                System.out.println("\nEnter the name of the debt [Mortgage, Transportation, School].");
                System.out.println("Type 'done' to finish, or 'other' to enter a new debt that is not listed: ");
                name = scnr.next();

                if (name.equalsIgnoreCase("Mortgage") ||
                        name.equalsIgnoreCase("Transportation") ||
                        name.equalsIgnoreCase("School") ||
                        name.equalsIgnoreCase("Other") ||
                        name.equalsIgnoreCase("done")) {
                    break; // Valid input, exit loop
                } else {
                    System.out.println("Invalid input. Please enter one of the listed options.");
                }
            }

            if (name.equalsIgnoreCase("done")) {
                break; // Exit loop if user is done entering debts
            }

            double debtAmount = 0.0;

            if (name.equalsIgnoreCase("Mortgage")) {
                debtAmount = user.getHousingMortgage();
                System.out.println("Mortgage Debt Amount: $" + debtAmount);
            } else if (name.equalsIgnoreCase("Transportation")) {
                debtAmount = user.getTransportationExpenses();
                System.out.println("Transportation Debt Amount: $" + debtAmount);
            } else if (name.equalsIgnoreCase("School")) {
                debtAmount = user.getSchoolPayments();
                System.out.println("School Debt Amount: $" + debtAmount);
            } else if (name.equalsIgnoreCase("Other")) {
                while (true) { // Ensure valid numeric input for debt amount
                    System.out.print("Enter the total debt amount: $");
                    if (scnr.hasNextDouble()) {
                        debtAmount = scnr.nextDouble();
                        break;
                    } else {
                        System.out.println("Invalid amount. Please enter a valid number.");
                        scnr.next(); // Clear invalid input
                    }
                }
            }

            double interestRate;
            while (true) { // Ensure valid numeric input for interest rate
                System.out.print("Enter the annual interest rate (in %) for " + name + ": ");
                if (scnr.hasNextDouble()) {
                    interestRate = scnr.nextDouble();
                    break;
                } else {
                    System.out.println("Invalid interest rate. Please enter a valid number.");
                    scnr.next(); // Clear invalid input
                }
            }

            double monthlyPayment;
            while (true) { // Ensure valid numeric input for monthly payment
                System.out.print("Enter your planned monthly payment for " + name + ": $");
                if (scnr.hasNextDouble()) {
                    monthlyPayment = scnr.nextDouble();
                    break;
                } else {
                    System.out.println("Invalid monthly payment. Please enter a valid number.");
                    scnr.next(); // Clear invalid input
                }
            }

            debts.add(new Debt(name, debtAmount, interestRate, monthlyPayment));
            System.out.println(name + " debt added successfully!\n");
        }

        // Calculate repayment for all entered debts
        System.out.println("\n===== Debt Repayment Summary =====");
        for (Debt debt : debts) {
            calculateDebtRepayment(debt);
        }
    }

    // Calculates the number of months required to repay debt
    public static void calculateDebtRepayment(Debt debt) {
        double totalDebt = debt.amount;
        double interest = debt.interestRate / 100 / 12;
        int months = 0;

        while (totalDebt > 0) {
            totalDebt += totalDebt * interest;  // Add interest
            totalDebt -= debt.monthlyPayment;  // Make payment
            months++;

            if (totalDebt < 0) {
                totalDebt = 0; // Ensure debt doesn't go negative
            }
        }

        System.out.println("It will take " + months + " months to pay off " + debt.name + ".");
    }
}