import java.io.*;
import java.util.Scanner;

public class GoalPlanner {
    private Node head;
    private Node tail;

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

    private static class Node {
        Goal goal;
        Node next;

        public Node(Goal goal) {
            this.goal = goal;
            this.next = null;
        }
    }

    public GoalPlanner(String goalFile) {
        loadGoalsFromFile(goalFile);
    }

    private void loadGoalsFromFile(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
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
                    addGoal(name, target, savings, contribution);
                }
            }
            System.out.println("Goals loaded successfully.");
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
    }

    public void saveGoalsToFile(String fileName) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName, false))) {
            Node current = head;
            while (current != null) {
                Goal g = current.goal;
                writer.println(g.name + "," + g.targetAmount + "," + g.currentSavings + "," + g.monthlyContribution);
                current = current.next;
            }
            System.out.println("Goals saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving to file: " + e.getMessage());
        }
    }

    public void addGoal(String name, double targetAmount, double currentSavings, double monthlyContribution) {
        Goal goal = new Goal(name, targetAmount, currentSavings, monthlyContribution);
        Node newNode = new Node(goal);
        if (head == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
    }

    public void removeGoal(String goalName) {
        Node current = head;
        Node previous = null;

        while (current != null) {
            if (current.goal.name.equalsIgnoreCase(goalName)) {
                if (previous == null) { // Removing head
                    head = current.next;
                    if (head == null) tail = null; // List is now empty
                } else {
                    previous.next = current.next;
                    if (current == tail) tail = previous; // Removed tail
                }
                System.out.println("Goal '" + goalName + "' removed successfully.");
                return;
            }
            previous = current;
            current = current.next;
        }
        System.out.println("Goal not found.");
    }

    public void displayGoals() {
        System.out.println("\n===== Financial Goals =====");
        if (head == null) {
            System.out.println("No goals set yet.");
            return;
        }

        Node current = head;
        while (current != null) {
            Goal goal = current.goal;
            double remainingAmount = goal.targetAmount - goal.currentSavings;
            double timeLeft = (goal.monthlyContribution > 0) ? remainingAmount / goal.monthlyContribution : -1;

            System.out.println(goal.name + ": $%.2f\n" + goal.currentSavings + " saved out of $%.2f\n" + goal.targetAmount);
            System.out.print("Estimated time to reach goal: ");
            if (timeLeft < 0) {
                System.out.println("Not achievable (monthly contribution is $0).\n");
            } else {
                System.out.printf("%.0f months\n", timeLeft);
                System.out.printf("You must contribute $%.2f/month for %.0f months to reach this goal.\n\n", goal.monthlyContribution, timeLeft);
            }

            current = current.next;
        }
    }

    public void manageGoals(Scanner scanner) {
        while (true) {
            System.out.println("\nManage your Financial Goals:");
            System.out.println("1. View all goals");
            System.out.println("2. Add a new goal");
            System.out.println("3. Update an existing goal");
            System.out.println("4. Remove a goal");
            System.out.println("5. Exit goal management");
            System.out.print("Choose an option (1-5): ");
            int choice;
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number (1-5).");
                scanner.next();
                System.out.print("Choose an option (1-5): ");
            }
            choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                displayGoals();
            } else if (choice == 2) {
                System.out.print("Enter the name of the goal: ");
                String name = scanner.nextLine();
                System.out.print("Enter the target amount: ");
                double targetAmount = scanner.nextDouble();
                System.out.print("Enter the current savings: ");
                double currentSavings = scanner.nextDouble();
                System.out.print("Enter how many months until you want to reach this goal: ");
                int monthsUntil = scanner.nextInt();

                double amountRemaining = targetAmount - currentSavings;
                double monthlyContribution = (monthsUntil > 0) ? amountRemaining / monthsUntil : 0;

                System.out.printf("To reach this goal in %d months, you need to contribute: $%.2f/month\n", monthsUntil, monthlyContribution);

                addGoal(name, targetAmount, currentSavings, monthlyContribution);
                System.out.println("Goal added successfully.");
            } else if (choice == 3) {
                System.out.print("Enter the name of the goal to update: ");
                String goalName = scanner.nextLine();
                Node current = head;
                Goal goalToUpdate = null;

                while (current != null) {
                    if (current.goal.name.equalsIgnoreCase(goalName)) {
                        goalToUpdate = current.goal;
                        break;
                    }
                    current = current.next;
                }

                if (goalToUpdate == null) {
                    System.out.println("Goal not found.");
                } else {
                    System.out.println("What would you like to update?");
                    System.out.println("1. Update current savings");
                    System.out.println("2. Update timeline (months remaining)");
                    System.out.print("Choose an option (1-2): ");
                    int updateChoice = scanner.nextInt();
                    scanner.nextLine();

                    if (updateChoice == 1) {
                        System.out.print("Enter the amount to update current savings by: ");
                        double savingsUpdate = scanner.nextDouble();
                        goalToUpdate.updateSavings(savingsUpdate);
                        System.out.println("Current savings updated successfully.");
                    } else if (updateChoice == 2) {
                        System.out.print("Enter the new number of months remaining to reach this goal: ");
                        int newMonths = scanner.nextInt();
                        double remaining = goalToUpdate.targetAmount - goalToUpdate.currentSavings;
                        double newContribution = (newMonths > 0) ? remaining / newMonths : 0;
                        goalToUpdate.updateContribution(newContribution);
                        System.out.printf("New monthly contribution set to: $%.2f/month to reach the goal in %d months.\n", newContribution, newMonths);
                    } else {
                        System.out.println("Invalid option.");
                    }
                }
            } else if (choice == 4) {
                System.out.print("Enter the name of the goal to remove: ");
                String goalName = scanner.nextLine();
                removeGoal(goalName);
            } else if (choice == 5) {
                System.out.println("Exiting goal management.");
                return;
            } else {
                System.out.println("Invalid choice. Please choose between 1 and 5.");
            }
        }
    }

    public java.util.LinkedList<Goal> getAllGoals() {
        java.util.LinkedList<Goal> goalList = new java.util.LinkedList<>();
        Node current = head;
        while (current != null) {
            goalList.add(current.goal);
            current = current.next;
        }
        return goalList;
    }

    public static void displayAdjustedFreeSpending(String username, String goalFileName, ExpenseTracker tracker) {

        String usernameFile = username + "_goals.txt";
        File goalFile = new File(goalFileName);

        //base case, no adjustment if there is no file, or nothing in file
        if (!goalFile.exists() || goalFile.length() == 0) {
            return;
        }
        //shows the user the updated monthly freespending
        else {

            GoalPlanner planner = new GoalPlanner(goalFileName); // This will load goals from file
            double totalContributions = 0.0;
            for (GoalPlanner.Goal goal : planner.getAllGoals()) {
                totalContributions += goal.monthlyContribution;
            }

            double originalFreeSpending = tracker.getMonthlyFreeSpending();
            double adjustedFreeSpending = originalFreeSpending - totalContributions;

            if (adjustedFreeSpending >= 300) {
                System.out.println("\n===== Adjusted Free Spending Overview =====");
                System.out.printf("Original Monthly Free Spending: $%.2f\n", originalFreeSpending);
                System.out.printf("Total Monthly Goal Contributions: $%.2f\n", totalContributions);
                System.out.printf("Adjusted Monthly Free Spending: $%.2f\n", adjustedFreeSpending);
            }
            else if (adjustedFreeSpending > 0) {
                System.out.print("** WARNING YOUR MONTHLY SPENDING IS VERY LOW **");
                System.out.println("\n===== Adjusted Free Spending Overview =====");
                System.out.printf("Original Monthly Free Spending: $%.2f\n", originalFreeSpending);
                System.out.printf("Total Monthly Goal Contributions: $%.2f\n", totalContributions);
                System.out.printf("Adjusted Monthly Free Spending: $%.2f\n", adjustedFreeSpending);
            }
            else{
                System.out.print("** WARNING YOUR MONTHLY SPENDING IS NEGATIVE **");
                System.out.println("\n===== Adjusted Free Spending Overview =====");
                System.out.printf("Original Monthly Free Spending: $%.2f\n", originalFreeSpending);
                System.out.printf("Total Monthly Goal Contributions: $%.2f\n", totalContributions);
                System.out.printf("Adjusted Monthly Free Spending: $%.2f\n", adjustedFreeSpending);
            }
        }
    }
}