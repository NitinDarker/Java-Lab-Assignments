package lab6;

class YieldingTask implements Runnable {
    @Override
    public void run() {
        for (int i = 1; i <= 3; i++) {
            System.out.println("[Yielding Thread] y = " + i);
            Thread.yield();
            // Letting Other threads run first
        }
    }
}

class SleepingTask implements Runnable {
    @Override
    public void run() {
        for (int i = 1; i <= 3; i++) {
            System.out.println("[Sleeping Thread] i = " + i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println("[Sleeping Thread] Interrupted.");
            }
        }
    }
}

class JoiningTask implements Runnable {
    private final Thread threadToJoin;

    JoiningTask(Thread threadToJoin) {
        this.threadToJoin = threadToJoin;
    }

    @Override
    public void run() {
        try {
            System.out.println("[Joining Thread] Waiting for SleepingTask to finish...");
            threadToJoin.join(); // Waits for the sleeping thread
        } catch (InterruptedException e) {
            System.out.println("[JoiningTask] Interrupted.");
        }
        System.out.println("[Joining Thread] Continuing after SleepingTask is done.");
    }
}

public class Q3 {
    public static void main(String[] args) {
        Thread yieldThread = new Thread(new YieldingTask(), "YieldingThread");
        Thread sleepThread = new Thread(new SleepingTask(), "SleepingThread");
        Thread joinThread = new Thread(new JoiningTask(sleepThread), "JoiningThread");

        yieldThread.start(); // Shows yield()
        sleepThread.start(); // Demonstrates sleep()
        joinThread.start();  // Waits for sleepThread to complete
    }
}
