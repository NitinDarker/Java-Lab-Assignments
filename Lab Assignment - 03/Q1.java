class Loan {
    protected double principal;
    protected double rate;
    protected int noOfYear;

    public void setLoanValues(double principal, double rate) {
        this.principal = principal;
        this.rate = rate;
    }

    public double calculateEMI(int noOfYear) {
        this.noOfYear = noOfYear;
        double rateMonthly = rate / (12 * 100);
        double noOfMonth = noOfYear * 12;
        double emi = (principal * rateMonthly * Math.pow(1 + rateMonthly, noOfMonth))
                / (Math.pow(1 + rateMonthly, noOfMonth) - 1);
        return emi;
    }
}

class HomeLoan extends Loan {
    private double repoRate;
    // private String homeType;

    public void setHomeLoanDetails(double principal, double rate, double repoRate, String homeType) {
        setLoanValues(principal, rate);
        this.repoRate = repoRate;
        // this.homeType = homeType;
    }

    public void changeRepoRate(double repoRate) {
        this.repoRate = repoRate;
    }

    @Override
    public double calculateEMI(int noOfYear) {
        this.noOfYear = noOfYear;
        double rateMonthly = (rate + repoRate) / (12 * 100);
        double noOfMonth = noOfYear * 12;
        double emi = (principal * rateMonthly * Math.pow(1 + rateMonthly, noOfMonth))
                / (Math.pow(1 + rateMonthly, noOfMonth) - 1);
        return emi;
    }
}

class CarLoan extends Loan {
    private static final int ALTO_DISCOUNT = 10000;
    private String carModel;

    public void setCarLoanDetails(double principal, double rate, String carModel) {
        setLoanValues(principal, rate);
        this.carModel = carModel;
    }

    @Override
    public double calculateEMI(int noOfYear) {
        this.noOfYear = noOfYear;
        double Price = principal;
        if (carModel.equals("Alto")) {
            Price -= ALTO_DISCOUNT;
        }
        double rateMonthly = rate / (12 * 100);
        double noOfMonth = noOfYear * 12;
        double emi = (Price * rateMonthly * Math.pow(1 + rateMonthly, noOfMonth))
                / (Math.pow(1 + rateMonthly, noOfMonth) - 1);
        return emi;
    }
}

public class Q1 {
    public static void main(String[] args) {
        Loan personalLoan = new Loan();
        personalLoan.setLoanValues(100000, 12.0);
        System.out.println("Personal Loan EMI: Rs. "
                + personalLoan.calculateEMI(2));

        HomeLoan homeLoan = new HomeLoan();
        homeLoan.setHomeLoanDetails(2000000, 8.5, 1.5, "flat");
        System.out.println("Home Loan EMI: Rs. "
                + homeLoan.calculateEMI(20));

        CarLoan swiftLoan = new CarLoan();
        swiftLoan.setCarLoanDetails(800000, 9.5, "Swift");
        System.out.println("Swift Car Loan EMI: Rs. "
                + swiftLoan.calculateEMI(5));

        CarLoan altoLoan = new CarLoan();
        altoLoan.setCarLoanDetails(400000, 9.5, "Alto");
        System.out.println("Alto Car Loan EMI: Rs. "
                + altoLoan.calculateEMI(5));
    }
}
