public class ExpenseTracker {
    double monthlyFreeSpending;
    double monthlyExpenses;
    double monthlySavings;
    double monthlyInvestments;

    // Constructor
    public ExpenseTracker() {
        this.monthlyFreeSpending = 0.0;
        this.monthlyExpenses = 0.0;
        this.monthlySavings = 0.0;
        this.monthlyInvestments = 0.0;
    }

    //set methods
    public void setMonthlyExpenses(double monthlyExpenses) {
        this.monthlyExpenses = monthlyExpenses;
    }
    public void setMonthlyFreeSpending(double monthlyFreeSpending) {
        this.monthlyFreeSpending = monthlyFreeSpending;
    }
    public void setMonthlySavings(double monthlySavings) {
        this.monthlySavings = monthlySavings;
    }
    public void setMonthlyInvestments(double monthlyInvestments) {
        this.monthlyInvestments = monthlyInvestments;
    }

    //get methods
    public double getMonthlyFreeSpending() {
        return monthlyFreeSpending;
    }
    public double getMonthlyExpenses() {
        return monthlyExpenses;
    }
    public double getMonthlySavings() {
        return monthlySavings;
    }
    public double getMonthlyInvestments() {
        return monthlyInvestments;
    }

    //calculates monthly money for everything and displays the data
    public void calculateMonthly(double freeSpending, double expenses, double savings, double investments) {

        setMonthlyFreeSpending(freeSpending / 12.0);
        setMonthlyExpenses(expenses / 12.0);
        setMonthlySavings(savings / 12.0);
        setMonthlyInvestments(investments / 12.0);

        System.out.println();
        System.out.println("===== Monthly Breakdown =====");

        System.out.printf("Monthly Free Spending: $%.2f\n", getMonthlyFreeSpending());
        System.out.printf("Monthly Expenses: $%.2f\n", getMonthlyExpenses());
        System.out.printf("Monthly Savings: $%.2f\n", getMonthlySavings());
        System.out.printf("Monthly Investments: $%.2f\n", getMonthlyInvestments());
    }
}
