package lab6;

class MyRunnable implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 4; i++) {
            System.out.println("Child: " + Thread.currentThread().threadId() + " " + Thread.currentThread().getName());
            try { Thread.sleep(100); } catch (InterruptedException _) {}
        }
    }
}

public class Q1_2 {
    public static void main(String[] args) {
        Thread th1 = new Thread(new MyRunnable());
        Thread th2 = new Thread(new MyRunnable());

        th1.setName("Slave-A");
        th2.setName("Slave-B");

        th1.start();
        th2.start();

        for (int i = 0; i < 4; i++) {
            System.out.println("Main: " + Thread.currentThread().threadId() + " " + Thread.currentThread().getName());
            try { Thread.sleep(100); } catch (InterruptedException _) {}
        }

        try {
            th1.join();
            th2.join();
        } catch (InterruptedException _) {}
        System.out.println("All threads completed.");

    }
}
