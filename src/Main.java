import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserInfo user = new UserInfo();
        PersonalPortfolios portfolio = new PersonalPortfolios();

        // Ask for the username
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        user.setUserName(username);

        String financialFile = username + "_financial_data.txt";
        File userFile = new File(financialFile);

        // Check if financial data exists, load or create new
        if (userFile.exists()) {
            portfolio.downloadExisting(username);
        } else {
            portfolio.createNew(username);
        }

        // Initialize GoalPlanner with user-specific goal file
        String goalFile = username + "_goals.txt";
        GoalPlanner goals = new GoalPlanner(goalFile);

        // Ask the user if they want to manage goals
        System.out.println("\nWould you like to manage your financial goals? (Yes/No)");
        while (true) {
            String response = scanner.nextLine();
            if (response.equalsIgnoreCase("Yes")) {
                goals.manageGoals(scanner);
                goals.saveGoalsToFile(goalFile); // Save after managing
                break;
            } else if (response.equalsIgnoreCase("No")) {
                System.out.println("Okay! You can manage goals later.");
                break;
            } else {
                System.out.println("Invalid input. Please enter 'Yes' or 'No'.");
            }
        }
    }
}