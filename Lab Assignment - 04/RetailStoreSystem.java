import java.util.Scanner;

class LinkedList<T> {
    private Node<T> head;
    private int size;

    private static class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    public LinkedList() {
        head = null;
        size = 0;
    }

    public void add(T data) {
        Node<T> newNode = new Node<>(data);
        if (head == null) {
            head = newNode;
        } else {
            Node<T> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            System.out.println("Index out of bounds!");
        }
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}

// Payment Method Interface
interface PaymentMethod {
    boolean payment(double amount);

    void deposit(double amount);

    String getDetails();
}

// Credit Card Implementation
class CreditCard implements PaymentMethod {
    private final String cardNumber;
    private final double creditLimit;
    private double balance;

    public CreditCard(String cardNumber, double creditLimit) {
        this.cardNumber = cardNumber;
        this.creditLimit = creditLimit;
        this.balance = creditLimit;
    }

    @Override
    public boolean payment(double amount) {
        if (balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }

    @Override
    public void deposit(double amount) {
        balance += amount;
        if (balance > creditLimit)
            balance = creditLimit;
    }

    @Override
    public String getDetails() {
        return String.format("Credit Card [Number: %s, Balance: $%.2f]", cardNumber, balance);
    }
}

// Debit Card Implementation
class DebitCard implements PaymentMethod {
    private final String cardNumber;
    private double balance;

    public DebitCard(String cardNumber, double initialBalance) {
        this.cardNumber = cardNumber;
        this.balance = initialBalance;
    }

    @Override
    public boolean payment(double amount) {
        if (balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }

    @Override
    public void deposit(double amount) {
        balance += amount;
    }

    @Override
    public String getDetails() {
        return String.format("Debit Card [Number: %s, Balance: $%.2f]", cardNumber, balance);
    }
}

// Abstract Customer Class
abstract class Customer {
    protected String name;
    protected String contact;
    protected String membershipId;
    protected LinkedList<PaymentMethod> paymentMethods;

    public Customer(String name, String contact, String membershipId) {
        this.name = name;
        this.contact = contact;
        this.membershipId = membershipId;
        this.paymentMethods = new LinkedList<>();
    }

    public void addPaymentMethod(PaymentMethod method) {
        paymentMethods.add(method);
    }

    public abstract String getCustomerType();

    public abstract String getDetails();
}

// Regular Customer Implementation
class RegularCustomer extends Customer {
    public RegularCustomer(String name, String contact, String membershipId) {
        super(name, contact, membershipId);
    }

    @Override
    public String getCustomerType() {
        return "Regular Customer";
    }

    @Override
    public String getDetails() {
        return String.format("%s [Name: %s, Contact: %s, Membership: %s]",
                getCustomerType(), name, contact, membershipId);
    }
}

// Premium Customer Implementation
class PremiumCustomer extends Customer {
    private final double discountRate;

    public PremiumCustomer(String name, String contact, String membershipId, double discountRate) {
        super(name, contact, membershipId);
        this.discountRate = discountRate;
    }

    @Override
    public String getCustomerType() {
        return "Premium Customer";
    }

    @Override
    public String getDetails() {
        return String.format("%s [Name: %s, Contact: %s, Membership: %s, Discount: %.1f%%]",
                getCustomerType(), name, contact, membershipId, discountRate);
    }
}

// Store Class
class Store {
    String name;
    private final String address;
    private final LinkedList<Customer> customers;

    public Store(String name, String address) {
        this.name = name;
        this.address = address;
        this.customers = new LinkedList<>();
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public void displayStoreInfo() {
        System.out.println("\nStore: " + name);
        System.out.println("Address: " + address);
        System.out.println("Customers:");
        for (int i = 0; i < customers.size(); i++) {
            Customer customer = customers.get(i);
            System.out.println("  - " + customer.getDetails());
        }
    }
}

// Main Program
public class RetailStoreSystem {
    public static void main(String[] args) {
        Menu.run();
    }
}

class Menu {
    private static final LinkedList<Store> stores = new LinkedList<>();
    private static final LinkedList<Customer> customers = new LinkedList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void run() {
        while (true) {
            printMainMenu();
            int choice = getIntInput("Enter your choice: ");

            switch (choice) {
                case 1 -> createStore();
                case 2 -> createCustomer();
                case 3 -> addPaymentMethod();
                case 4 -> addCustomerToStore();
                case 5 -> processPayment();
                case 6 -> displayStoreInfo();
                case 7 -> {
                    System.out.println("Exiting system...");
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void printMainMenu() {
        System.out.println("\nRetail Store System Menu:");
        System.out.println("1. Create New Store");
        System.out.println("2. Create New Customer");
        System.out.println("3. Add Payment Method to Customer");
        System.out.println("4. Add Customer to Store");
        System.out.println("5. Process Payment");
        System.out.println("6. Display Store Information");
        System.out.println("7. Exit");
    }

    private static void createStore() {
        System.out.print("Enter store name: ");
        String name = scanner.nextLine();
        System.out.print("Enter store address: ");
        String address = scanner.nextLine();
        stores.add(new Store(name, address));
        System.out.println("Store created successfully!");
    }

    private static void createCustomer() {
        System.out.println("Select Customer Type:");
        System.out.println("1. Regular Customer");
        System.out.println("2. Premium Customer");
        int type = getIntInput("Enter choice: ");

        System.out.print("Enter customer name: ");
        String name = scanner.nextLine();
        System.out.print("Enter contact info: ");
        String contact = scanner.nextLine();
        System.out.print("Enter membership ID: ");
        String membershipId = scanner.nextLine();

        Customer customer;
        switch (type) {
            case 1 -> customer = new RegularCustomer(name, contact, membershipId);
            case 2 -> {
                double discount = getDoubleInput("Enter discount rate (%): ");
                customer = new PremiumCustomer(name, contact, membershipId, discount);
            }
            default -> {
                System.out.println("Invalid customer type");
                return;
            }
        }

        customers.add(customer);
        System.out.println("Customer created successfully!");
    }

    private static void addPaymentMethod() {
        if (customers.isEmpty()) {
            System.out.println("No customers available!");
            return;
        }

        Customer customer = selectCustomer();
        System.out.println("Select Payment Method:");
        System.out.println("1. Credit Card");
        System.out.println("2. Debit Card");
        int choice = getIntInput("Enter choice: ");

        System.out.print("Enter card number: ");
        String cardNumber = scanner.nextLine();

        PaymentMethod method;
        switch (choice) {
            case 1 -> {
                double limit = getDoubleInput("Enter credit limit: $");
                method = new CreditCard(cardNumber, limit);
            }
            case 2 -> {
                double balance = getDoubleInput("Enter initial balance: $");
                method = new DebitCard(cardNumber, balance);
            }
            default -> {
                System.out.println("Invalid choice");
                return;
            }
        }

        customer.addPaymentMethod(method);
        System.out.println("Payment method added successfully!");
    }

    private static void addCustomerToStore() {
        if (stores.isEmpty() || customers.isEmpty()) {
            System.out.println("No stores or customers available!");
            return;
        }

        Store store = selectStore();
        Customer customer = selectCustomer();
        store.addCustomer(customer);
        System.out.println("Customer added to store successfully!");
    }

    private static void processPayment() {
        if (customers.isEmpty()) {
            System.out.println("No customers available!");
            return;
        }

        Customer customer = selectCustomer();
        if (customer.paymentMethods.isEmpty()) {
            System.out.println("No payment methods available for this customer!");
            return;
        }

        System.out.println("Available Payment Methods:");
        for (int i = 0; i < customer.paymentMethods.size(); i++) {
            System.out.println((i + 1) + ". " + customer.paymentMethods.get(i).getDetails());
        }

        int pmIndex = getIntInput("Select payment method: ") - 1;
        PaymentMethod method = customer.paymentMethods.get(pmIndex);
        double amount = getDoubleInput("Enter payment amount: $");

        if (method.payment(amount)) {
            System.out.println("Payment processed successfully!");
        } else {
            System.out.println("Payment failed: Insufficient funds!");
        }
    }

    private static void displayStoreInfo() {
        if (stores.isEmpty()) {
            System.out.println("No stores available!");
            return;
        }

        selectStore().displayStoreInfo();
    }

    private static Customer selectCustomer() {
        System.out.println("\nAvailable Customers:");
        for (int i = 0; i < customers.size(); i++) {
            System.out.println((i + 1) + ". " + customers.get(i).getDetails());
        }
        int index = getIntInput("Select customer: ") - 1;
        return customers.get(index);
    }

    private static Store selectStore() {
        System.out.println("\nAvailable Stores:");
        for (int i = 0; i < stores.size(); i++) {
            Store store = stores.get(i);
            System.out.println((i + 1) + ". " + store.name);
        }
        int index = getIntInput("Select store: ") - 1;
        return stores.get(index);
    }

    private static int getIntInput(String prompt) {
        System.out.print(prompt);
        int value = scanner.nextInt();
        scanner.nextLine();
        return value;
    }

    private static double getDoubleInput(String prompt) {
        System.out.print(prompt);
        double value = scanner.nextDouble();
        scanner.nextLine();
        return value;
    }
}