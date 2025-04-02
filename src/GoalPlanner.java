import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GoalPlanner {
    String name;
    double targetAmount;
    double currentSavings;
    double monthlyContribution;

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

    //set methods
    public void setName(String name){
        this.name = name;
    }
    public void setTargetAmount(double targetAmount){
        this.targetAmount = targetAmount;
    }
    public void setCurrentSavings(double currentSavings){
        this.currentSavings = currentSavings;
    }
    public void setMonthlyContribution(double monthlyContribution){
        this.monthlyContribution = monthlyContribution;
    }

    //get methods
    public String getName(){
        return name;
    }
    public double getTargetAmount(){
        return targetAmount;
    }
    public double getCurrentSavings(){
        return currentSavings;
    }
    public double getMonthlyContribution(){
        return monthlyContribution;
    }

    private List<Goal> goals;

    public GoalPlanner() {
        this.goals = new ArrayList<>();
    }

    public void addGoal(String name, double targetAmount, double currentSavings, double monthlyContribution) {
        goals.add(new Goal(name, targetAmount, currentSavings, monthlyContribution));
    }

    public void displayGoals(double months) {
        System.out.println("\n===== Financial Goals =====");
        for (Goal goal : goals) {
            System.out.println(goal.name + ": $" + goal.currentSavings + " saved out of $" + goal.targetAmount);
            System.out.print("Estimated time to reach goal: ");
            System.out.printf("%.0f", months);
            System.out.println(" months");
        }
    }

    public void updateGoal(Scanner scnr) {
        if (goals.isEmpty()) {
            System.out.println("No goals to update.");
            return;
        }

        System.out.println("\nEnter the name of the goal you want to update: ");
        String goalName = scnr.next();

        for (Goal goal : goals) {
            if (goal.name.equalsIgnoreCase(goalName)) {
                System.out.println("1. Update savings");
                System.out.println("2. Update monthly contribution");
                System.out.print("Choose an option: ");
                int choice = scnr.nextInt();

                if (choice == 1) {
                    System.out.print("Enter additional savings amount: $");
                    double amount = scnr.nextDouble();
                    goal.updateSavings(amount);
                    System.out.println("Updated savings: $" + goal.currentSavings);
                } else if (choice == 2) {
                    System.out.print("Enter new monthly contribution: $");
                    double amount = scnr.nextDouble();
                    goal.updateContribution(amount);
                    System.out.println("Updated monthly contribution: $" + goal.monthlyContribution);
                } else {
                    System.out.println("Invalid choice.");
                }
                return;
            }
        }
        System.out.println("Goal not found.");
    }

    public void manageGoals(Scanner scnr) {
        while (true) {
            System.out.println("\n===== Goal Planner Menu =====");
            System.out.println("1. Add a new goal");
            System.out.println("2. View goals");
            System.out.println("3. Update goal");
            System.out.println("4. Return to main menu");
            System.out.print("Enter your choice: ");

            int choice = scnr.nextInt();
            scnr.nextLine(); // Consume newline

            if (choice == 1) {
                System.out.print("Enter goal name: ");
                String name = scnr.nextLine();
                System.out.print("Enter target amount: $");
                double target = scnr.nextDouble();
                setTargetAmount(target);
                System.out.print("Enter current savings: $");
                double savings = scnr.nextDouble();
                setCurrentSavings(savings);
                System.out.print("Enter monthly contribution: $");
                double contribution = scnr.nextDouble();
                setMonthlyContribution(contribution);
                addGoal(name, target, savings, contribution);
                System.out.println("Goal added successfully!");
            } else if (choice == 2) {
                displayGoals(((getTargetAmount() - getCurrentSavings()) / getMonthlyContribution()));
            } else if (choice == 3) {
                updateGoal(scnr);
            } else if (choice == 4) {
                System.out.println("Returning to main menu.");
                break;
            } else {
                System.out.println("Invalid choice. Try again.");
            }
        }
    }
}