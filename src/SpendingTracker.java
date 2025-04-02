import java.util.Scanner;

public class SpendingTracker {

    double savings;
    double investment;
    double freeSpending;
    double fullExpenses;

    public SpendingTracker(){
        savings = 0.0;
        investment = 0.0;
        freeSpending = 0.0;
        fullExpenses = 0.0;
    }

    public SpendingTracker(double savings, double investment, double freeSpending, double fullExpenses){
        this.savings = savings;
        this.investment = investment;
        this.freeSpending = freeSpending;
        this.fullExpenses = fullExpenses;
    }

    //set methods
    public void setSavings(double savings){
        this.savings = savings;
    }
    public void setInvestment(double investment){
        this.investment = investment;
    }
    public void setFreeSpending(double freeSpending){
        this.freeSpending = freeSpending;
    }
    public void setFullExpenses(double fullExpenses){
        this.fullExpenses = fullExpenses;
    }

    //get methods
    public double getSavings(){
        return savings;
    }
    public double getInvestment(){
        return investment;
    }
    public double getFreeSpending(){
        return freeSpending;
    }
    public double getFullExpenses(){
        return fullExpenses;
    }

    //This is an overall budget people should strive to set for the year for a MINIMUM
    public static void budgetAllocation(SpendingTracker track, UserInfo user){
        // Calculate budget with all income sources
        double budget = user.getYearlyIncome() + user.getBonusIncome();

        double essentialExpense;
        double savings = 0;
        double investment = 0;
        double freeSpending = 0;

        // Get the value of income and expenses
        int expenseCategory = user.incomeAndExpenses();

        // Calculate based on the expense category
        if (expenseCategory == 5) {
            essentialExpense = user.getInsuranceExpenses() + user.getTaxes() + user.getHousingMortgage() + user.getUtilitiesExpenses() + user.getSchoolPayments() + user.getTransportationExpenses() + user.getOtherExpenses();
            savings = (budget - essentialExpense) * 0.55;
            investment = (budget - essentialExpense) * 0.23;
            freeSpending = budget - essentialExpense + savings + investment;
        } else if (expenseCategory == 4) {
            essentialExpense = user.getInsuranceExpenses() + user.getTaxes() + user.getHousingMortgage() + user.getUtilitiesExpenses() + user.getSchoolPayments() + user.getTransportationExpenses() + user.getOtherExpenses();
            savings = (budget - essentialExpense) * 0.54;
            investment = (budget - essentialExpense) * 0.21;
            freeSpending = budget - (essentialExpense + savings + investment);
        } else if (expenseCategory == 3) {
            essentialExpense = user.getInsuranceExpenses() + user.getTaxes() + user.getHousingMortgage() + user.getUtilitiesExpenses() + user.getSchoolPayments() + user.getTransportationExpenses() + user.getOtherExpenses();
            savings = (budget - essentialExpense) * 0.52;
            investment = (budget - essentialExpense) * 0.21;
            freeSpending = budget - (essentialExpense + savings + investment);
        } else if (expenseCategory == 2) {
            essentialExpense = user.getInsuranceExpenses() + user.getTaxes() + user.getHousingMortgage() + user.getUtilitiesExpenses() + user.getSchoolPayments() + user.getTransportationExpenses() + user.getOtherExpenses();
            savings = (budget - essentialExpense) * 0.52;
            investment = (budget - essentialExpense) * 0.20;
            freeSpending = budget - (essentialExpense + savings + investment);
        } else if (expenseCategory == 1) {
            essentialExpense = user.getInsuranceExpenses() + user.getTaxes() + user.getHousingMortgage() + user.getUtilitiesExpenses() + user.getSchoolPayments() + user.getTransportationExpenses() + user.getOtherExpenses();
            savings = (budget - essentialExpense) * 0.50;
            investment = (budget - essentialExpense) * 0.25;
            freeSpending = budget - (essentialExpense + savings + investment);
        }

        track.setSavings(savings);
        track.setInvestment(investment);
        track.setFreeSpending(freeSpending);

        double fullExpense = user.getInsuranceExpenses() + user.getTaxes() + user.getHousingMortgage() + user.getUtilitiesExpenses() + user.getSchoolPayments() + user.getTransportationExpenses() + user.getOtherExpenses();
        track.setFullExpenses(fullExpense);

        // Output the recommendations
        System.out.printf("Recommended savings (yearly): $%.2f%n", savings);
        System.out.printf("Recommended investment (yearly): $%.2f%n", investment);
        System.out.printf("Recommended free spending amount (yearly): $%.2f%n", freeSpending);
    }
}
