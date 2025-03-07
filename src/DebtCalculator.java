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
        UserInfo findDebt = new UserInfo();
        System.out.println();
        System.out.println("===== Debt Calculator =====");
        List<Debt> debts = new ArrayList<>();
        double debtAmount = 0.0;
        boolean addingDebts = true;

        while (addingDebts) {
                for(int i = 0; i < 100; i++) { //100 because it will exit the for loop no matter what once the person types 'done'
                    try {
                        System.out.println("Enter the name of the debt [Mortgage, Transportation, School]. Type 'done' to finish, or 'other' to enter a new debt that is not listed: ");
                        String name = scnr.next();
                        if (name.equalsIgnoreCase("done")) {
                            addingDebts = false;
                            break;
                        }

                        if (name.equalsIgnoreCase("Mortgage")) {
                            debtAmount = user.getHousingMortgage();
                            System.out.println("Mortgage Debt Amount: " + debtAmount);
                        } else if (name.equalsIgnoreCase("Transportation")) {
                            debtAmount = user.getTransportationExpenses();
                            System.out.println("Transportation Debt Amount: " + debtAmount);
                        } else if (name.equalsIgnoreCase("School")) {
                            debtAmount = user.getSchoolPayments();
                            System.out.println("School Debt Amount: " + debtAmount);
                        } else if (name.equalsIgnoreCase("Other")) {
                            System.out.println("Enter the total debt amount: ");
                            debtAmount = scnr.nextDouble();
                            System.out.println(debtAmount);
                        }
                        System.out.println("Enter the annual interest rate (in %) for " + name + ": ");
                        double interestRate = scnr.nextDouble();

                        System.out.println("Enter your planned monthly payment for " + name + ": ");
                        double monthlyPayment = scnr.nextDouble();

                        debts.add(new Debt(name, debtAmount, interestRate, monthlyPayment));
                    } catch (Exception e) {
                        System.out.println("Incorrect debt type entered. Please try again.");
                        scnr.next();
                        i--;
                    }
                }
                break;
            }

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