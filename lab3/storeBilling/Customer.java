package lab3.storeBilling;

public class Customer {
    private final String name;
    private double currentBill;
    private BillList bills;
    private Customer next;

    Customer(String name) {
        this.name = name;
        currentBill = 0;
        bills = new DoubleNode(currentBill);
        this.next = null;
    }

    void showBill() {
        
    }
}
