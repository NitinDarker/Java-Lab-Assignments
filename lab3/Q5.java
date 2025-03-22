// Q5. Program to implement a linked list of integers
package lab3;

class Node {
    // Class to represent a node in a linked list
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

class LinkedList {
    private Node head;

    public LinkedList() {
        head = null;
    }

    public void append_list(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
        } else {
            Node temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
    }

    public int delete_from_end() {
        if (head == null) {
            return -1;
        } else if (head.next == null) {
            int data = head.data;
            head = null;
            return data;
        } else {
            Node temp = head;
            while (temp.next.next != null) {
                temp = temp.next;
            }
            int data = temp.next.data;
            temp.next = null;
            return data;
        }
    }

    public void display() {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }

}

public class Q5 {
    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        list.append_list(1);
        list.append_list(2);
        list.append_list(3);
        list.append_list(4);
        list.append_list(5);
        list.display(); // 1 2 3 4 5 
        list.delete_from_end();
        list.display(); // 1 2 3 4
    }
}