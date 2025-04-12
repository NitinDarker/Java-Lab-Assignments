package lab6;

import java.time.Duration;
import java.time.Instant;

class FinderThread implements Runnable {
    int[] arr;
    int start, end, element; // Start & End are both inclusive

    FinderThread(int[] arr, int s, int e, int x) {
        this.arr = arr;
        start = s;
        end = e;
        element = x;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        Instant startTime = Instant.now();
        int count = 0;
        for (int i = start; i <= end; i++) {
            if (arr[i] == element) {
                System.out.println("[" + name + "] Element Found at index: " + i);
                count++;
            }
        }
        Instant endTime = Instant.now();
        Duration totalTime = Duration.between(startTime, endTime);
        System.out.println("[" + name + "] Done. Found " + count + " matches. Time Taken: " + totalTime.toMillis() + "ms");
    }
}

public class Q2 {
    public static void main(String[] args) throws InterruptedException {
        int[] arr = new int[50_000_000]; // 50 Million
        int ele = 5; // Element to be found

        // Filling the array
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i % (arr.length/50) == 0 ? ele : 0;  // ~50 matches
        }

        int threadsCount = 10;
        Thread[] threads = new Thread[threadsCount]; // Array of threads

        for (int i = 0; i < threadsCount; i++) {
            int start = arr.length / threadsCount * i;
            int end = (arr.length / threadsCount * (i + 1)) - 1;
            Thread t = new Thread(new FinderThread(arr, start, end, ele));
            t.setName("Finder-" + (i + 1));
            threads[i] = t;
            t.start();
        }

        for (Thread t : threads) {
            t.join();
        }
        System.out.println("All Threads have completed their search.");
    }
}
