/*I will use this to provide extra data for the user about how much their return will be in the future depending on
future value of money with their present value, payments, apr, and periods compounded. I will implement this later
into the overall code
 */
public class SavingsCalculator {

    //finds the rate for the present value if you have future loans to pay off
    public double rate(double apr, String compounded, String received){
        double a = apr / 100;
        int n = 1;
        double m = 1;
        try {
            if (compounded.equals("weekly")) {
                n = 52;
                if (received.equalsIgnoreCase("weekly")) {
                    m = 1;
                } else if (received.equalsIgnoreCase("monthly")) {
                    m = 52 / 12.0;
                } else if (received.equalsIgnoreCase("semi-annually")) {
                    m = 52 / 2.0;
                } else if (received.equalsIgnoreCase("yearly")) {
                    m = 52;
                }
            } else if (compounded.equals("monthly")) {
                n = 12;
                if (received.equalsIgnoreCase("monthly")) {
                    m = 1;
                } else if (received.equalsIgnoreCase("semi-annually")) {
                    m = 6;
                } else if (received.equalsIgnoreCase("yearly")) {
                    m = 12;
                }
            } else if (compounded.equals("quarterly")) {
                n = 4;
                if (received.equalsIgnoreCase("quarterly")) {
                    m = 1;
                } else if (received.equalsIgnoreCase("annually")) {
                    m = 4;
                }
            } else if (compounded.equals("semi-annually")) {
                n = 2;
                if (received.equalsIgnoreCase("semi-annually")) {
                    m = 1;
                } else if (received.equalsIgnoreCase("yearly")) {
                    m = 2;
                }
            } else if (compounded.equals("annually")) {
                n = 1;
                if (received.equalsIgnoreCase("annually")) {
                    m = 1;
                }
            }
        } catch (Exception e) {
            System.out.println("Sorry, you entered an invalid compound or payment received");
        }

        return (Math.pow((1+(a/n)), m) - 1) * 100;
    }

    //calculation for the future value of money depending on the present value, rate, years, and payments they will make
    //I will also use this to make recommendations on how much each person should invest and when they should
    public static double futureValueOfMoney(double PV, double rate, int years, double PMT) {
        double r = rate / 100; // Convert rate to decimal

        // Future value of the initial present value (PV) compounded over all years
        double futureValuePV = PV * Math.pow(1 + r, years);

        // Future value of the periodic payments (PMT) compounded over all years
        double futureValuePMTs = PMT * (Math.pow(1 + r, years) - 1) / r;

        // Total future value is the sum of both values
        return futureValuePV + futureValuePMTs;
    }

}
