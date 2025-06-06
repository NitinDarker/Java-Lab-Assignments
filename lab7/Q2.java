package lab7;

class ObjCounter {
    private int count = 0;
    private final Object objLock = new Object();

    public void incObj() {
        synchronized (objLock) {
            count++;
            System.out.println(Thread.currentThread().getName() + ": Obj count incremented to " + count);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.err.println(Thread.currentThread().getName() + ": Sleep interrupted in ObjCounter.incObj()");
                Thread.currentThread().interrupt();
            }
        }
    }

    public int getObjCount() {
        return count;
    }
}

class ClsCounter {
    private static int count = 0;
    private static final Object clsLock = new Object();

    public static void incCls() {
        synchronized (clsLock) {
            count++;
            System.out.println(Thread.currentThread().getName() + ": Cls count incremented to " + count);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.err.println(Thread.currentThread().getName() + ": Sleep interrupted in ClsCounter.incCls()");
                Thread.currentThread().interrupt();
            }
        }
    }

    public static int getClsCount() {
        return count;
    }
}

class A2 implements Runnable {
    private final ObjCounter objCounter;

    public A2(ObjCounter objCounter) {
        this.objCounter = objCounter;
    }

    public void run() {
        for (int i = 0; i < 5; i++) {
            objCounter.incObj();
            ClsCounter.incCls();
        }
    }
}

public class Q2 {
    public static void main(String[] args) {
        ObjCounter oc = new ObjCounter();

        Thread t1 = new Thread(new A2(oc), "T1");
        Thread t2 = new Thread(new A2(oc), "T2");
        Thread t3 = new Thread(new A2(oc), "T3");

        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            System.err.println("Main thread interrupted while waiting for worker threads.");
            Thread.currentThread().interrupt();
        }

        System.out.println("\nFinal Object Count (Block): " + oc.getObjCount());
        System.out.println("Final Class Count (Block): " + ClsCounter.getClsCount());
    }
}