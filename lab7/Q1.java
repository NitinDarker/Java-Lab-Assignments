package lab7;

class object_counter {
    private int count1 = 0;
    public synchronized void inc() {
        this.count1++;
        System.out.println(Thread.currentThread().getName() + ": Object count incremented to " + count1);
        try {
            Thread.sleep(100);
        } catch (Exception e) {
            System.err.println("Error");
        }
    }

    public int getCount1() {
        return count1;
    }
}

class class_counter {
    private static int count2 = 0;
    public static synchronized void inc() {
        count2++;
        System.out.println(Thread.currentThread().getName() + ": Class count incremented to " + count2);
        try {
            Thread.sleep(100); // Simulate some work
        } catch (Exception e) {
            System.err.println("Error");
        }
    }

    public static int getCount2() {
        return count2;
    }
}

class A1 implements Runnable {
    private final object_counter objCounter;

    public A1(object_counter objCounter, class_counter clCounter) {
        this.objCounter = objCounter;
    }

    public void run() {
        for (int i = 0; i < 5; i++) {
            objCounter.inc();
            class_counter.inc();
        }
    }
}

class q1 {
    public static void main(String[] args) throws InterruptedException {
        object_counter oc = new object_counter();
        class_counter cc = new class_counter();
        Thread t1 = new Thread(new A1(oc, cc), "Thread-1");
        Thread t2 = new Thread(new A1(oc, cc), "Thread-2");
        Thread t3 = new Thread(new A1(oc, cc), "Thread-3");
        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();
        System.out.println("\nFinal Object Count: " + oc.getCount1());
        System.out.println("Final Class Count: " + class_counter.getCount2());
    }
}