import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class PersonalPortfolios {

    public void downloadExisting(String username) {
        Scanner scanner = new Scanner(System.in);
        SpendingTracker track = new SpendingTracker();
        ExpenseTracker expenseTracker = new ExpenseTracker();
        UserInfo user = new UserInfo();
        String file = username + "_financial_data.txt";

        // Check if the file exists
        File userFile = new File(file);
        if (userFile.exists()) {
            // Load previous data using Scanner instead of BufferedReader
            System.out.println("\n===== Loading saved financial data for " + username + " =====");
            try (Scanner fileScanner = new Scanner(userFile)) {
                // Skip the header
                fileScanner.nextLine(); // Skipping header line "===== Financial Details for ..."

                // Read each line and parse data
                while (fileScanner.hasNextLine()) {
                    String line = fileScanner.nextLine();
                    String[] parts = line.split(": ");
                    if (parts.length == 2) {
                        String label = parts[0];
                        double value = Double.parseDouble(parts[1]);

                        // Directly map to the user info fields
                        if (label.equals("Yearly Income")) {
                            user.setYearlyIncome(value);
                        } else if (label.equals("Payday Amount")) {
                            user.setPaydayAmount(value);
                        } else if (label.equals("Bonus Income")) {
                            user.setBonusIncome(value);
                        } else if (label.equals("Housing/Rent Expenses")) {
                            user.setHousingMortgage(value);
                        } else if (label.equals("Utilities Expenses")) {
                            user.setUtilitiesExpenses(value);
                        } else if (label.equals("School Payments")) {
                            user.setSchoolPayments(value);
                        } else if (label.equals("Transportation Expenses")) {
                            user.setTransportationExpenses(value);
                        } else if (label.equals("Insurance Expenses")) {
                            user.setInsuranceExpenses(value);
                        } else if (label.equals("Taxes")) {
                            user.setTaxes(value);
                        } else if (label.equals("Other Expenses")) {
                            user.setOtherExpenses(value);
                        } else if (label.equals("Current Savings")) {
                            user.setCurrentSavings(value);
                        } else if (label.equals("Current Investments")) {
                            user.setCurrentInvestments(value);
                        }
                    }
                }
                // Store the user info into the static list
                user.storeUserInfo(user);

            } catch (IOException e) {
                System.out.println("Error reading from file: " + e.getMessage());
            }
        }
        System.out.println();

        //print beginning info
        SpendingTracker.budgetAllocation(track, user);

        while (true) {
            System.out.print("\nAre there any changes to your data? (yes/no): ");
            String update = scanner.nextLine().trim().toLowerCase(); // Get input and normalize case

            if (update.equals("yes")) {
                userFile.delete(); // Delete existing file before creating a new one
                createNew(username); // Call createNew to re-enter data
                break;  // Exit the loop after handling new data creation
            }
            else if (update.equals("no")) {
                expenseTracker.calculateMonthly(track.getFreeSpending(), track.getFullExpenses(), track.getSavings(), track.getInvestment());
                DebtCalculator.askDebt(scanner, user, expenseTracker);
                break;  // Exit the loop and continue with the program
            }
            else {
                System.out.println("Invalid input. Please enter 'yes' or 'no'.");
            }
        }
    }

    public void createNew(String username) {
        Scanner scanner = new Scanner(System.in);
        SpendingTracker track = new SpendingTracker();
        ExpenseTracker expenseTracker = new ExpenseTracker();
        UserInfo user = new UserInfo();
        String file2 = username + "_financial_data.txt";

        System.out.println("\nNo existing data found for " + username + ". Creating a new profile.");
        try (FileOutputStream userMoney = new FileOutputStream(file2);
             PrintWriter writer = new PrintWriter(userMoney)) {

            //output a title to explain to user what the code is collecting
            writer.println("===== Financial Details for " + username + " =====");
            System.out.println("Enter yearly information:");

            double yearlyIncome = getValidInput(scanner, "Yearly Income: ", writer);
            user.setYearlyIncome(yearlyIncome);
            double paydayAmount = getValidInput(scanner, "Payday Amount: ", writer);
            user.setPaydayAmount(paydayAmount);
            double bonusIncome = getValidInput(scanner, "Bonus Income: ", writer);
            user.setBonusIncome(bonusIncome);
            double housingMortgage = getValidInput(scanner, "Housing/Rent Expenses: ", writer);
            user.setHousingMortgage(housingMortgage);
            double utilitiesExpenses = getValidInput(scanner, "Utilities Expenses: ", writer);
            user.setUtilitiesExpenses(utilitiesExpenses);
            double schoolPayments = getValidInput(scanner, "School Payments: ", writer);
            user.setSchoolPayments(schoolPayments);
            double transportationExpenses = getValidInput(scanner, "Transportation Expenses: ", writer);
            user.setTransportationExpenses(transportationExpenses);
            double insuranceExpenses = getValidInput(scanner, "Insurance Expenses: ", writer);
            user.setInsuranceExpenses(insuranceExpenses);
            double taxes = getValidInput(scanner, "Taxes: ", writer);
            user.setTaxes(taxes);
            double otherExpenses = getValidInput(scanner, "Other Expenses: ", writer);
            user.setOtherExpenses(otherExpenses);
            double currentSavings = getValidInput(scanner, "Current Savings: ", writer);
            user.setCurrentSavings(currentSavings);
            double currentInvestments = getValidInput(scanner, "Current Investments: ", writer);
            user.setCurrentInvestments(currentInvestments);

            // Store the user info into the static list
            user.storeUserInfo(user);

            // Ensure data is written to file
            writer.flush();
            System.out.println("\nProfile saved successfully!");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }

        SpendingTracker.budgetAllocation(track, user);
        expenseTracker.calculateMonthly(track.getFreeSpending(), track.getFullExpenses(),track.getSavings(),track.getInvestment());
        DebtCalculator.askDebt(scanner, user, expenseTracker);
    }

    private static double getValidInput(Scanner scanner, String prompt, PrintWriter writer) {
        double value;
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextDouble()) {
                value = scanner.nextDouble();
                writer.println(prompt + value); // Store input in file
                break;
            } else {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.next(); // Clear the invalid input
            }
        }
        return value;
    }
}
