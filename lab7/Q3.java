package lab7;

class MyException extends Exception {
}

class SynchronizedStack {
    private final int[] dataBuffer;
    private int stackPointer;
    private final int maxCapacity;
    private volatile boolean productionIsComplete = false;

    public SynchronizedStack(int capacity) {
        this.maxCapacity = capacity;
        this.dataBuffer = new int[this.maxCapacity];
        this.stackPointer = -1;
    }

    public synchronized void push(int value) throws InterruptedException {
        while (this.stackPointer == this.maxCapacity - 1) {
            wait();
        }
        this.dataBuffer[++this.stackPointer] = value;
        System.out.println(Thread.currentThread().getName() + " pushed: " + value);
        notifyAll();
    }

    public synchronized void pop() throws InterruptedException, MyException {
        while (this.stackPointer == -1) {
            if (this.productionIsComplete) {
                throw new MyException();
            }
            wait();
        }
        int value = this.dataBuffer[this.stackPointer--];
        System.out.println(Thread.currentThread().getName() + " popped: " + value);
        notifyAll();
    }

    public synchronized void signalProductionComplete() {
        this.productionIsComplete = true;
        notifyAll();
    }
}

class Producer implements Runnable {
    private final SynchronizedStack sharedStack;
    private final int numberOfItemsToProduce;

    public Producer(SynchronizedStack stack, int itemsToProduce) {
        this.sharedStack = stack;
        this.numberOfItemsToProduce = itemsToProduce;
    }

    @Override
    public void run() {
        for (int i = 1; i <= this.numberOfItemsToProduce; i++) {
            try {
                this.sharedStack.push(i); // Pushes values 1 to numberOfItemsToProduce
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.err.println(Thread.currentThread().getName() + " (Producer) was interrupted.");
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}

class Consumer implements Runnable {
    private final SynchronizedStack sharedStack;

    public Consumer(SynchronizedStack stack) {
        this.sharedStack = stack;
    }

    @Override
    public void run() {
        try {
            while (true) {
                this.sharedStack.pop();
                Thread.sleep(150);
            }
        } catch (MyException e) {
            System.out.println(Thread.currentThread().getName() + " (Consumer) finished: No more items expected.");
        } catch (InterruptedException e) {
            System.err.println(Thread.currentThread().getName() + " (Consumer) was interrupted.");
            Thread.currentThread().interrupt();
        }
    }
}

public class Q3 {
    public static void main(String[] args) throws InterruptedException {
        int stackCapacity = 5;
        SynchronizedStack sharedStack = new SynchronizedStack(stackCapacity);

        int producerCount = 3;
        int itemsForEachProducer = 5;
        Thread[] producerThreads = new Thread[producerCount];
        for (int i = 0; i < producerCount; i++) {
            producerThreads[i] = new Thread(new Producer(sharedStack, itemsForEachProducer), "Producer-" + (i + 1));
            producerThreads[i].start();
        }

        int consumerCount = 2;
        Thread[] consumerThreads = new Thread[consumerCount];
        for (int i = 0; i < consumerCount; i++) {
            consumerThreads[i] = new Thread(new Consumer(sharedStack), "Consumer-" + (i + 1));
            consumerThreads[i].start();
        }

        for (Thread producerThread : producerThreads) {
            producerThread.join();
        }
        System.out.println("All producer threads have completed their push attempts.");

        sharedStack.signalProductionComplete();
        System.out.println("Production completion signaled. Consumers will stop once the stack is empty.");

        for (Thread consumerThread : consumerThreads) {
            consumerThread.join();
        }
        System.out.println("All consumer threads have completed.");
    }
}