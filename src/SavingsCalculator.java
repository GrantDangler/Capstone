public class SavingsCalculator {

    // Recursive statement implemented
    public static double calculateFutureSavings(double principal, int years, double interestRate) {
        if (years == 0) {
            return principal;
        }
        return calculateFutureSavings(principal * (1 + interestRate), years - 1, interestRate);
    }

    // Display the result
    public static void showProjection(double principal, int years, double yearlyInterestRate) {
        System.out.println("\n===== Future Value of Savings =====");
        double result = calculateFutureSavings(principal, years, yearlyInterestRate);
        System.out.printf("If you invest $%.2f for %d years at %.2f%% yearly interest,\n",
                principal, years, yearlyInterestRate * 100);
        System.out.printf("you could have approximately $%.2f saved!\n", result);
    }
}