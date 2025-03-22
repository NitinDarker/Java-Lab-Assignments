// Q2. Java class to implement a stack
package lab3;
class Stack {
    private final int[] stack; // Array to implement the stack
    private final int size; // Size of the stack array
    private int top; // To store index of top most element

    public Stack(int size) { // Constructor for stack
        this.size = size;
        stack = new int[size];
        top = -1;
    }

    public void push(int data) {
        if (top == size - 1) {
            System.out.println("Stack is full");
            return;
        }
        stack[++top] = data;
    }

    public int pop() {
        if (top == -1) {
            System.out.println("Stack is empty");
            return -1;
        }
        return stack[top--];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == size - 1;
    }

    public int top() {
        if (top == -1) {
            System.out.println("Stack is empty");
            return -1;
        }
        return stack[top];
    }
}

public class Q2 {
    public static void main(String[] args) {
        Stack stack = new Stack(5);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);

        System.out.println("Top element: " + stack.top());
        System.out.println("Popped element: " + stack.pop());
        System.out.println("Top element: " + stack.top());
        System.out.println("Is stack empty: " + stack.isEmpty());
        System.out.println("Is stack full: " + stack.isFull());
    }
}