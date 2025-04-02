import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserInfo user = new UserInfo();
        PersonalPortfolios portfolio = new PersonalPortfolios();
        GoalPlanner goals = new GoalPlanner();


        // Ask for the username
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        user.setUserName(username);
        String file = username + "_financial_data.txt";
        File userFile = new File(file);

        //cleaned up main in order to be more organized and easier to read
        if(userFile.exists()) {
            portfolio.downloadExisting(username);
        }
        else {
            portfolio.createNew(username);
        }

        System.out.println("\nWould you like to manage your financial goals? (Yes/No)");
        while (true) {
            String response = scanner.nextLine();
            if (response.equalsIgnoreCase("Yes")) {
                goals.manageGoals(scanner);
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
