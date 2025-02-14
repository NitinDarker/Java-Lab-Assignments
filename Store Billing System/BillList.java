public class BillList {
    // Class to represent a node in a linked list
    private double data;
    private boolean isPaid;
    private BillList next;

    public BillList(double data) {
        this.data = data;
        isPaid = false;
        this.next = null;
    }

    // Getter
    public double getBill() {
        return data;
    }

    public boolean getPaidStatus() {
        return isPaid;
    }

    public BillList getNext() {
        return next;
    }

    // Setter
    public void addBill(double bill) {
        BillList newBill = new BillList(bill);
        newBill.next = this;
        // this = newBill;
    }
}

// class LinkedList {
// private Node head;

// public LinkedList() {
// head = null;
// }

// public void append_list(int data) {
// Node newNode = new Node(data);
// if (head == null) {
// head = newNode;
// } else {
// Node temp = head;
// while (temp.next != null) {
// temp = temp.next;
// }
// temp.next = newNode;
// }
// }

// public int delete_from_end() {
// if (head == null) {
// return -1;
// } else if (head.next == null) {
// int data = head.data;
// head = null;
// return data;
// } else {
// Node temp = head;
// while (temp.next.next != null) {
// temp = temp.next;
// }
// int data = temp.next.data;
// temp.next = null;
// return data;
// }
// }

// public void display() {
// Node temp = head;
// while (temp != null) {
// System.out.print(temp.data + " ");
// temp = temp.next;
// }
// System.out.println();
// }

// }