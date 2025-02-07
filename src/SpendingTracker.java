public class SpendingTracker {
    //This is an overall budget people should strive to set for the year for a MINIMUM
    public static void budgetAllocation(UserInfo user){
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

        // Output the recommendations
        System.out.printf("Recommended savings: $%.2f%n", savings);
        System.out.printf("Recommended investment: $%.2f%n", investment);
        System.out.printf("Recommended free spending amount: $%.2f%n", freeSpending);
    }
}
