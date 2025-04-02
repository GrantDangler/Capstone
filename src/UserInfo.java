import java.util.ArrayList;

//This class is used to store basic information about the user such as their userName that is used to store their file and their financials
public class UserInfo {

    String userName;
    double yearlyIncome;
    double paydayAmount;
    double bonusIncome;
    double housingMortgage;
    double utilitiesExpenses;
    double schoolPayments;
    double transportationExpenses;
    double insuranceExpenses;
    double taxes;
    double otherExpenses;
    double currentSavings;
    double currentInvestments;

    private static ArrayList<UserInfo> info = new ArrayList<>();

    //default constructor
    public UserInfo(String userName, double yearlyIncome, double paydayAmount, double bonusIncome, double housingMortgage, double utilitiesExpenses, double schoolPayments, double transportationExpenses, double insuranceExpenses, double taxes, double otherExpenses, double currentSavings, double currentInvestments) {
        this.userName = userName;
        this.yearlyIncome = yearlyIncome;
        this.paydayAmount = paydayAmount;
        this.bonusIncome = bonusIncome;
        this.housingMortgage = housingMortgage;
        this.utilitiesExpenses = utilitiesExpenses;
        this.schoolPayments = schoolPayments;
        this.transportationExpenses = transportationExpenses;
        this.insuranceExpenses = insuranceExpenses;
        this.taxes = taxes;
        this.otherExpenses = otherExpenses;
        this.currentSavings = currentSavings;
        this.currentInvestments = currentInvestments;
    }

    public UserInfo(){
        userName = null;
        yearlyIncome = 0.0;
        paydayAmount = 0.0;
        bonusIncome = 0.0;
        housingMortgage = 0.0;
        utilitiesExpenses = 0.0;
        schoolPayments = 0.0;
        transportationExpenses = 0.0;
        insuranceExpenses = 0.0;
        taxes = 0.0;
        otherExpenses = 0.0;
        currentSavings = 0.0;
        currentInvestments = 0.0;
    }

    //set method
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public void setYearlyIncome(double yearlyIncome) {
        this.yearlyIncome = yearlyIncome;
    }
    public void setPaydayAmount(double paydayAmount) {
        this.paydayAmount = paydayAmount;
    }
    public void setBonusIncome(double bonusIncome) {
        this.bonusIncome = bonusIncome;
    }
    public void setHousingMortgage(double housingMortgage) {
        this.housingMortgage = housingMortgage;
    }
    public void setUtilitiesExpenses(double utilitiesExpenses) {
        this.utilitiesExpenses = utilitiesExpenses;
    }
    public void setSchoolPayments(double schoolPayments) {
        this.schoolPayments = schoolPayments;
    }
    public void setTransportationExpenses(double transportationExpenses) {
        this.transportationExpenses = transportationExpenses;
    }
    public void setInsuranceExpenses(double insuranceExpenses) {
        this.insuranceExpenses = insuranceExpenses;
    }
    public void setTaxes(double taxes) {
        this.taxes = taxes;
    }
    public void setOtherExpenses(double otherExpenses) {
        this.otherExpenses = otherExpenses;
    }
    public void setCurrentSavings(double currentSavings) {
        this.currentSavings = currentSavings;
    }
    public void setCurrentInvestments(double currentInvestments) {
        this.currentInvestments = currentInvestments;
    }
    public void setInfo(ArrayList<UserInfo> info) {
        UserInfo.info = info;
    }


    //get methods
    public String getUserName() {
        return userName;
    }
    public double getYearlyIncome() {
        return yearlyIncome;
    }
    public double getPaydayAmount() {
        return paydayAmount;
    }
    public double getBonusIncome() {
        return bonusIncome;
    }
    public double getHousingMortgage() {
        return housingMortgage;
    }
    public double getUtilitiesExpenses() {
        return utilitiesExpenses;
    }
    public double getSchoolPayments() {
        return schoolPayments;
    }
    public double getTransportationExpenses() {
        return transportationExpenses;
    }
    public double getInsuranceExpenses() {
        return insuranceExpenses;
    }
    public double getTaxes() {
        return taxes;
    }
    public double getOtherExpenses() {
        return otherExpenses;
    }
    public double getCurrentSavings() {
        return currentSavings;
    }
    public double getCurrentInvestments() {
        return currentInvestments;
    }
    public ArrayList<UserInfo> getInfo() {
        return info;
    }

    //store user information
    public void storeUserInfo(UserInfo user){
        info.add(user);
    }

    public int incomeAndExpenses() {
        // Calculate the total yearly expenses
        double totalYearlyExpenses = getInsuranceExpenses() + getTaxes() + getHousingMortgage() + getUtilitiesExpenses() + getSchoolPayments() + getTransportationExpenses() + getOtherExpenses();

        // Debugging output to check the total expenses
        System.out.printf("Total Yearly Expenses: $" + "%.2f" , totalYearlyExpenses);
        System.out.println();

        // Calculate the expense percentage
        double expensePercentage = totalYearlyExpenses / (getYearlyIncome() + getBonusIncome());

        // Debugging output to check the percentage
        System.out.printf("Expense Percentage: " + "%.2f", expensePercentage * 100);
        System.out.println("%");

        // Check if the expense percentage is greater than a certain threshold and return the appropriate value
        if (expensePercentage >= 0.40) {
            return 5;
        } else if (expensePercentage >= 0.30) {
            return 4;
        } else if (expensePercentage >= 0.25) {
            return 3;
        } else if (expensePercentage >= 0.20) {
            return 2;
        }
        return 1;
    }
}