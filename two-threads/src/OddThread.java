public class OddThread implements Runnable {

    private final Object lock;

    public OddThread(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        synchronized (lock) {
            for (int i = 1; i <= 99; i += 2) {
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
