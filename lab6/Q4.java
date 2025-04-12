package lab6;

class PriorityTask implements Runnable {
    @Override
    public void run() {
        Thread current = Thread.currentThread();
        System.out.println("[START] " + current.getName() +
                ", Priority: " + current.getPriority());

        // Some CPU-intensive work
        long result = 0;
        for (int i = 0; i < 1_000_000; i++) {
            result += i;
        }

        System.out.println("[END] " + current.getName() +
                ", Priority: " + current.getPriority() +
                ", Computation Result: " + result);
    }
}

public class Q4 {
    public static void main(String[] args) {
        Thread t1 = new Thread(new PriorityTask());
        Thread t2 = new Thread(new PriorityTask());
        Thread t3 = new Thread(new PriorityTask());

        // Set names
        t1.setName("Low Priority Thread");
        t2.setName("Normal Priority Thread");
        t3.setName("High Priority Thread");

        // Set priorities
        t1.setPriority(Thread.MIN_PRIORITY);    // Priority 1
        t2.setPriority(Thread.NORM_PRIORITY);   // Priority 5
        t3.setPriority(Thread.MAX_PRIORITY);    // Priority 10

        // Log before starting
        System.out.println("\n=== Thread Configuration ===");
        System.out.println(t1.getName() + " Priority: " + t1.getPriority());
        System.out.println(t2.getName() + " Priority: " + t2.getPriority());
        System.out.println(t3.getName() + " Priority: " + t3.getPriority());
        System.out.println("============================\n");

        // Start all threads
        t1.start();
        t2.start();
        t3.start();
    }
}

