import java.io.*;
import java.util.*;

public class GoalPlanner {
    private List<Goal> goals;
    private static final String FILE_NAME = "goals.txt";

    static class Goal {
        String name;
        double targetAmount;
        double currentSavings;
        double monthlyContribution;

        public Goal(String name, double targetAmount, double currentSavings, double monthlyContribution) {
            this.name = name;
            this.targetAmount = Math.max(targetAmount, 0);
            this.currentSavings = Math.max(currentSavings, 0);
            this.monthlyContribution = Math.max(monthlyContribution, 0);
        }

        public void updateSavings(double amount) {
            this.currentSavings += amount;
        }

        public void updateContribution(double amount) {
            this.monthlyContribution = Math.max(amount, 0);
        }
    }

    public GoalPlanner(String goalFile) {
        this.goals = new ArrayList<>();
        loadGoalsFromFile(goalFile);
    }

    private void loadGoalsFromFile(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();  // This creates the file if it doesn't exist
                System.out.println("New file created: " + fileName);
            } catch (IOException e) {
                System.out.println("Error creating file: " + e.getMessage());
                return;
            }
        }
        try (Scanner fileScanner = new Scanner(file)) {
            while (fileScanner.hasNextLine()) {
                String[] data = fileScanner.nextLine().split(",");
                if (data.length == 4) {
                    String name = data[0];
                    double target = Double.parseDouble(data[1]);
                    double savings = Double.parseDouble(data[2]);
                    double contribution = Double.parseDouble(data[3]);
                    goals.add(new Goal(name, target, savings, contribution));
                }
            }
            System.out.println("Goals loaded successfully.");
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
    }


    public void saveGoalsToFile(String fileName) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName, false))) {  // 'false' ensures file is overwritten
            for (Goal goal : goals) {
                writer.println(goal.name + "," + goal.targetAmount + "," + goal.currentSavings + "," + goal.monthlyContribution);
            }
            System.out.println("Goals saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving to file: " + e.getMessage());
        }
    }

    public void addGoal(String name, double targetAmount, double currentSavings, double monthlyContribution) {
        goals.add(new Goal(name, targetAmount, currentSavings, monthlyContribution));
    }

    public void displayGoals() {
        System.out.println("\n===== Financial Goals =====");
        if (goals.isEmpty()) {
            System.out.println("No goals set yet.");
            return;
        }
        for (Goal goal : goals) {
            double remainingAmount = goal.targetAmount - goal.currentSavings;
            double timeLeft = (goal.monthlyContribution > 0) ? remainingAmount / goal.monthlyContribution : -1;
            System.out.println(goal.name + ": $" + goal.currentSavings + " saved out of $" + goal.targetAmount);
            System.out.print("Estimated time to reach goal: ");
            if (timeLeft < 0) {
                System.out.println("Not achievable (monthly contribution is $0).\n");
            } else {
                System.out.printf("%.0f months\n", timeLeft);
            }
        }
    }

    // This is the new manageGoals method
    public void manageGoals(Scanner scanner) {
        while (true) {
            System.out.println("\nManage your Financial Goals:");
            System.out.println("1. View all goals");
            System.out.println("2. Add a new goal");
            System.out.println("3. Update an existing goal");
            System.out.println("4. Exit goal management");
            System.out.print("Choose an option (1-4): ");
            int choice;
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number (1-4).");
                scanner.next(); // Discard invalid input
                System.out.print("Choose an option (1-4): ");
            }
            choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline character

            if (choice == 1) {
                // Option 1: View all goals
                displayGoals();
            } else if (choice == 2) {
                // Option 2: Add a new goal
                System.out.print("Enter the name of the goal: ");
                String name = scanner.nextLine();
                System.out.print("Enter the target amount: ");
                double targetAmount = scanner.nextDouble();
                System.out.print("Enter the current savings: ");
                double currentSavings = scanner.nextDouble();
                System.out.print("Enter the monthly contribution: ");
                double monthlyContribution = scanner.nextDouble();
                addGoal(name, targetAmount, currentSavings, monthlyContribution);
                saveGoalsToFile(FILE_NAME);
                System.out.println("Goal added successfully.");
            } else if (choice == 3) {
                // Option 3: Update an existing goal
                System.out.print("Enter the name of the goal to update: ");
                String goalName = scanner.nextLine();
                Goal goalToUpdate = null;
                for (Goal g : goals) {
                    if (g.name.equalsIgnoreCase(goalName)) {
                        goalToUpdate = g;
                        break;
                    }
                }
                if (goalToUpdate == null) {
                    System.out.println("Goal not found.");
                } else {
                    System.out.println("What would you like to update?");
                    System.out.println("1. Update current savings");
                    System.out.println("2. Update monthly contribution");
                    System.out.print("Choose an option (1-2): ");
                    int updateChoice = scanner.nextInt();
                    scanner.nextLine();  // Consume newline

                    if (updateChoice == 1) {
                        // Option 1: Update current savings
                        System.out.print("Enter the amount to update current savings by: ");
                        double savingsUpdate = scanner.nextDouble();
                        goalToUpdate.updateSavings(savingsUpdate);
                        System.out.println("Current savings updated successfully.");
                    } else if (updateChoice == 2) {
                        // Option 2: Update monthly contribution
                        System.out.print("Enter the new monthly contribution: ");
                        double newContribution = scanner.nextDouble();
                        goalToUpdate.updateContribution(newContribution);
                        System.out.println("Monthly contribution updated successfully.");
                    } else {
                        System.out.println("Invalid option.");
                    }
                    saveGoalsToFile(FILE_NAME);
                }
            } else if (choice == 4) {
                // Option 4: Exit goal management
                System.out.println("Exiting goal management.");
                return;
            } else {
                // Invalid input
                System.out.println("Invalid choice. Please choose between 1 and 4.");
            }
        }
    }
}