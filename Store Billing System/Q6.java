import java.util.Scanner;

public class Q6 {
    public static void main(String args[]) {
        Terminal ob = new Terminal();
        ob.welcomePage();
    }
}

class Terminal {
    private CustomerList customers;
    private ProductNode products;
    Scanner sc = new Scanner(System.in);

    Terminal() {
        // customers = new CustomerNode("Shivam");
    }

    void welcomePage() {
        while (true) {
            System.out.println("\nWelcome to N-Mart\n");
            System.out.println("1. Login as Customer");
            System.out.println("2. Login as an Employee");
            int welcomeInput = sc.nextInt();
            switch (welcomeInput) {
                case 1 -> customerPage();
                case 2 -> EmployeePage();
                default -> System.out.println("Enter a valid input!");
            }
        }
    }

    void customerPage() {
        System.out.println("Please Enter your name: ");
        String name = sc.next();
        if (customers == null) {
            // customers = new CustomerNode(name);
        } else {
            customers.insert_at_start(name);
        }
    }

    void EmployeePage() {

    }
}