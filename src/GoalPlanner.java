import java.util.ArrayList;
import java.util.List;

public class GoalPlanner {
    static class Goal {
        String name;
        double targetAmount;
        double currentSavings;
        double monthlyContribution;

        public Goal(String name, double targetAmount, double currentSavings, double monthlyContribution) {
            this.name = name;
            this.targetAmount = targetAmount;
            this.currentSavings = currentSavings;
            this.monthlyContribution = monthlyContribution;
        }

        // Method to calculate months needed to reach goal
        public int calculateTimeToGoal() {
            if (monthlyContribution <= 0) return Integer.MAX_VALUE; // Prevent division by zero
            double remainingAmount = targetAmount - currentSavings;
            return (int) Math.ceil(remainingAmount / monthlyContribution);
        }
    }

    private List<Goal> goals;

    public GoalPlanner() {
        this.goals = new ArrayList<>();
    }

    // Add a new financial goal
    public void addGoal(String name, double targetAmount, double currentSavings, double monthlyContribution) {
        goals.add(new Goal(name, targetAmount, currentSavings, monthlyContribution));
    }

    // Display all goals
    public void displayGoals() {
        System.out.println("\n===== Financial Goals =====");
        for (Goal goal : goals) {
            int monthsLeft = goal.calculateTimeToGoal();
            System.out.println(goal.name + ": $" + goal.currentSavings + " saved out of $" + goal.targetAmount);
            System.out.println("Estimated time to reach goal: " + (monthsLeft == Integer.MAX_VALUE ? "N/A (Increase contributions)" : monthsLeft + " months"));
        }
    }
}
