package ru.effectivemobile;

public class NumberPrintThread implements Runnable {

    private final boolean isEven;
    private final Object lock;

    public NumberPrintThread(boolean isEven, Object lock) {
        this.isEven = isEven;
        this.lock = lock;
    }

    @Override
    public void run() {
        synchronized (lock) {
            for (int i = 0; i <= 100; i++) {
                if (isEven && i % 2 == 0)
                    System.out.println(i);
                else if (!isEven && i % 2 == 1)
                    System.out.println(i);
                lock.notify();
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            lock.notify();
        }
    }
}
