package lab6;

class MyThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println("Child: " + this.threadId() + " " + this.getName());
            try { Thread.sleep(100); } catch (InterruptedException _) {}
        }
    }
}

public class Q1 {
    public static void main(String[] args) {
        MyThread th1 = new MyThread();
        MyThread th2 = new MyThread();

        th1.setName("Worker-A");
        th2.setName("Worker-B");

        th1.start();
        th2.start();

        for (int i = 0; i < 5; i++) {
            System.out.println("Main: " + Thread.currentThread().threadId() + " " + Thread.currentThread().getName());
            try { Thread.sleep(100); } catch (InterruptedException _) {}

        }
    }
}
