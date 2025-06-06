package lab7;

class YourException extends Exception {
}

class BoundedBuffer {
    private final int[] sharedData;
    private final int capacity;
    private int writeIndex;
    private int readIndex;
    private int currentItemCount;
    private volatile boolean productionFinalized = false;

    public BoundedBuffer(int capacity) {
        this.capacity = capacity;
        this.sharedData = new int[this.capacity];
        this.writeIndex = 0;
        this.readIndex = 0;
        this.currentItemCount = 0;
    }

    public synchronized void put(int value) throws InterruptedException {
        while (this.currentItemCount == this.capacity) {
            wait();
        }
        this.sharedData[this.writeIndex] = value;
        this.writeIndex = (this.writeIndex + 1) % this.capacity;
        this.currentItemCount++;
        System.out.println(Thread.currentThread().getName() + " produced: " + value + " (Buffer count: " + this.currentItemCount + ")");
        notifyAll();
    }

    public synchronized void take() throws InterruptedException, YourException {
        while (this.currentItemCount == 0) {
            if (this.productionFinalized) {
                throw new YourException();
            }
            wait();
        }
        int value = this.sharedData[this.readIndex];
        this.readIndex = (this.readIndex + 1) % this.capacity;
        this.currentItemCount--;
        System.out.println(Thread.currentThread().getName() + " consumed: " + value + " (Buffer count: " + this.currentItemCount + ")");
        notifyAll();
    }

    public synchronized void signalNoMoreProduction() {
        this.productionFinalized = true;
        notifyAll();
    }
}

class ProducerTask implements Runnable {
    private final BoundedBuffer buffer;
    private final int itemsToProduceCount;

    public ProducerTask(BoundedBuffer buffer, int itemsToProduceCount) {
        this.buffer = buffer;
        this.itemsToProduceCount = itemsToProduceCount;
    }

    @Override
    public void run() {
        for (int i = 1; i <= this.itemsToProduceCount; i++) {
            try {
                this.buffer.put(i);
                Thread.sleep((long) (Math.random() * 120) + 30);
            } catch (InterruptedException e) {
                System.err.println(Thread.currentThread().getName() + " (Producer) was interrupted.");
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}

class ConsumerTask implements Runnable {
    private final BoundedBuffer buffer;

    public ConsumerTask(BoundedBuffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            while (true) {
                this.buffer.take();
                Thread.sleep((long) (Math.random() * 180) + 70);
            }
        } catch (YourException e) {
            System.out.println(Thread.currentThread().getName() + " (Consumer) finished: No more items expected.");
        } catch (InterruptedException e) {
            System.err.println(Thread.currentThread().getName() + " (Consumer) was interrupted.");
            Thread.currentThread().interrupt();
        }
    }
}

public class Q5 {
    public static void main(String[] args) {
        int bufferCapacity = 4;
        BoundedBuffer sharedBuffer = new BoundedBuffer(bufferCapacity);

        int numberOfProducers = 3;
        int itemsPerProducer = 5;
        Thread[] producerThreads = new Thread[numberOfProducers];
        for (int i = 0; i < numberOfProducers; i++) {
            producerThreads[i] = new Thread(new ProducerTask(sharedBuffer, itemsPerProducer), "Producer-" + (i + 1));
            producerThreads[i].start();
        }

        int numberOfConsumers = 2;
        Thread[] consumerThreads = new Thread[numberOfConsumers];
        for (int i = 0; i < numberOfConsumers; i++) {
            consumerThreads[i] = new Thread(new ConsumerTask(sharedBuffer), "Consumer-" + (i + 1));
            consumerThreads[i].start();
        }

        System.out.println("All producer and consumer threads have been started.");

        try {
            for (Thread producerThread : producerThreads) {
                producerThread.join();
            }
            System.out.println("All producers have completed their item production tasks.");

            sharedBuffer.signalNoMoreProduction();
            System.out.println("Production finalization signaled. Consumers will now clear the buffer and then terminate.");

            for (Thread consumerThread : consumerThreads) {
                consumerThread.join();
            }
            System.out.println("All consumers have completed their consumption tasks.");

        } catch (InterruptedException e) {
            System.err.println("Main thread was interrupted: " + e.getMessage());
            Thread.currentThread().interrupt();
        }
        System.out.println("Multi-Producer/Multi-Consumer simulation has finished.");
    }
}