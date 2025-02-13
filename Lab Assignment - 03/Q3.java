// Q3. Java program to represent a queue of complex numbers
class ComplexNum {
    // Class to represent a complex number
    private final double real;
    private final double img;

    public ComplexNum(double real, double img) {
        this.real = real;
        this.img = img;
    }

    public ComplexNum() {
        this.real = 0;
        this.img = 0;
    }

    public void display() {
        if (img < 0) {
            System.out.println(real + " - " + (-1 * img) + "i");
        } else {
            System.out.println(real + " + " + img + "i");
        }
    }
}

// Circular Queue
class Queue {
    private final ComplexNum[] queue; // Queue Array
    private final int size; // Size of array
    private int front; // front pointer
    private int back; // back pointer

    public Queue(int size) { // Constructor
        this.size = size;
        queue = new ComplexNum[size];
        front = -1;
        back = -1;
    }

    private boolean isFull() {
        return front == (back + 1) % size;
    }

    private boolean isEmpty() {
        return front == -1;
    }

    public void enqueue(ComplexNum num) {
        if (isFull()) {
            System.out.println("Queue is full! Cannot Enqueue");
            return;
        }
        back = (back + 1) % size;
        if (front == -1) {
            front = 0;
        }
        queue[back] = num;
    }

    public ComplexNum dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is empty! Cannot Dequeue");
            return null;
        }
        ComplexNum val = queue[front];

        if (front == back) {
            // Only one element left in queue
            front = back = -1; 
        } else {
            front = (front + 1) % size;
        }
        return val;
    }
}

public class Q3 {
    public static void main(String[] args) {
        Queue queue = new Queue(5);
        queue.enqueue(new ComplexNum(1, 2));
        queue.enqueue(new ComplexNum(3, 4));
        queue.enqueue(new ComplexNum(5, 6));
        queue.enqueue(new ComplexNum(7, 8));
        queue.enqueue(new ComplexNum(9, 10));
        queue.enqueue(new ComplexNum(11, 12)); // full

        queue.dequeue().display();
        queue.dequeue().display();
        queue.dequeue().display();
        queue.dequeue().display();
        queue.dequeue().display();
        queue.dequeue().display(); // error return null
    }
}
