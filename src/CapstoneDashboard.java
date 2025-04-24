import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CapstoneDashboard extends JFrame {

    // Labels to display financial metrics
    private JLabel incomeLabel, savingsLabel, freeSpendingLabel, investmentLabel;
    private JLabel adjustedSpendingLabel, goalContributionLabel, projectionResultLabel;

    // Input components for user-defined projection
    private JButton projectionButton;
    private JTextField apyField, yearsField;

    // Reference to the user's financial data
    private ExpenseTracker expenses;

    // Constructor
    public CapstoneDashboard(String username, ExpenseTracker expenses, double totalGoalContributions) {
        this.expenses = expenses;

        // Set window title and layout
        setTitle("Financial Overview - " + username);
        setSize(450, 500);
        setLayout(new GridLayout(10, 1, 10, 10));
        getContentPane().setBackground(new Color(30, 30, 60));

        // Financial breakdown labels
        incomeLabel = createLabel("Monthly Income Estimate: $" + String.format("%.2f", (expenses.getMonthlyExpenses() + expenses.getMonthlySavings() + expenses.getMonthlyInvestments() + expenses.getMonthlyFreeSpending())));
        savingsLabel = createLabel("Monthly Savings: $" + String.format("%.2f", expenses.getMonthlySavings()));
        investmentLabel = createLabel("Monthly Investments: $" + String.format("%.2f", expenses.getMonthlyInvestments()));
        freeSpendingLabel = createLabel("Original Free Spending: $" + String.format("%.2f", expenses.getMonthlyFreeSpending()));

        // Goal contributions and adjusted free spending labels
        goalContributionLabel = createLabel("Goal Contributions: $" + String.format("%.2f", totalGoalContributions));
        double adjusted = expenses.getMonthlyFreeSpending() - totalGoalContributions;
        adjustedSpendingLabel = createLabel("Adjusted Free Spending: $" + String.format("%.2f", adjusted));

        // Label to show projection result or instructions
        projectionResultLabel = createLabel("Enter APY and years, then click below:");

        // Input fields for user-defined APY and number of years
        apyField = new JTextField("0.0041"); // Default: 0.41% APY
        yearsField = new JTextField("20");   // Default: 20 years

        // Button to run the savings projection
        projectionButton = new JButton("Run Projection");
        projectionButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    // Parse input values
                    double apy = Double.parseDouble(apyField.getText());
                    int years = Integer.parseInt(yearsField.getText());

                    // Run projection and display result
                    double future = SavingsCalculator.calculateFutureSavings(expenses.getMonthlySavings() * 12, years, apy);
                    projectionResultLabel.setText("Projection from personalized yearly savings: $" + String.format("%.2f", future) + " after " + years + " years at " + (apy * 100) + "% APY");
                } catch (NumberFormatException ex) {
                    // Handle invalid inputs
                    projectionResultLabel.setText("Invalid input. Enter valid numbers for APY and years.");
                }
            }
        });

        // Add all components to the GUI
        add(incomeLabel);
        add(savingsLabel);
        add(investmentLabel);
        add(freeSpendingLabel);
        add(goalContributionLabel);
        add(adjustedSpendingLabel);
        add(projectionResultLabel);
        add(apyField);
        add(yearsField);
        add(projectionButton);

        // Configure window behavior
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    // Helper method to create styled labels
    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.white);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("Times", Font.PLAIN, 14)); // You may want to change "Times" to "SansSerif" if Times is unavailable
        return label;
    }
}