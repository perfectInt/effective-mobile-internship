package ru.effectivemobile;

public class Consumer implements Runnable {

    private final RingBuffer<Integer> ringBuffer;

    public Consumer(RingBuffer<Integer> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                ringBuffer.poll();
                Thread.sleep(1500);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
