package lab7;

class ResourceAlpha {
    public synchronized void performActionAlpha(ResourceBeta betaResource) {
        System.out.println(Thread.currentThread().getName() + ": Acquired lock on ResourceAlpha");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.err.println(Thread.currentThread().getName() + " in ResourceAlpha.performActionAlpha interrupted: " + e.getMessage());
            Thread.currentThread().interrupt();
        }
        System.out.println(Thread.currentThread().getName() + ": Trying to acquire lock on ResourceBeta...");
        betaResource.performActionBeta(this);
        System.out.println(Thread.currentThread().getName() + ": Acquired lock on ResourceBeta");
    }
}

class ResourceBeta {
    public synchronized void performActionBeta(ResourceAlpha alphaResource) {
        System.out.println(Thread.currentThread().getName() + ": Acquired lock on ResourceBeta");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.err.println(Thread.currentThread().getName() + " in ResourceBeta.performActionBeta interrupted: " + e.getMessage());
            Thread.currentThread().interrupt();
        }
        System.out.println(Thread.currentThread().getName() + ": Trying to acquire lock on ResourceAlpha...");
        alphaResource.performActionAlpha(this);
        System.out.println(Thread.currentThread().getName() + ": Acquired lock on ResourceAlpha");
    }
}

class WorkerTaskOne implements Runnable {
    private final ResourceAlpha resourceAlphaInstance;
    private final ResourceBeta resourceBetaInstance;

    public WorkerTaskOne(ResourceAlpha alpha, ResourceBeta beta) {
        this.resourceAlphaInstance = alpha;
        this.resourceBetaInstance = beta;
    }

    @Override
    public void run() {
        resourceAlphaInstance.performActionAlpha(resourceBetaInstance);
    }
}

class WorkerTaskTwo implements Runnable {
    private final ResourceAlpha resourceAlphaInstance;
    private final ResourceBeta resourceBetaInstance;

    public WorkerTaskTwo(ResourceAlpha alpha, ResourceBeta beta) {
        this.resourceAlphaInstance = alpha;
        this.resourceBetaInstance = beta;
    }

    @Override
    public void run() {
        resourceBetaInstance.performActionBeta(resourceAlphaInstance);
    }
}

public class Q4 {
    public static void main(String[] args) {
        final ResourceAlpha sharedAlpha = new ResourceAlpha();
        final ResourceBeta sharedBeta = new ResourceBeta();

        Thread thread1 = new Thread(new WorkerTaskOne(sharedAlpha, sharedBeta), "Thread-1");
        Thread thread2 = new Thread(new WorkerTaskTwo(sharedAlpha, sharedBeta), "Thread-2");

        System.out.println("Starting Thread-1 and Thread-2...");
        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
            System.out.println("Both threads finished execution.");
        } catch (InterruptedException e) {
            System.err.println("Main thread interrupted while waiting for worker threads: " + e.getMessage());
            Thread.currentThread().interrupt();
        }
        System.out.println("Main application finished.");
    }
}