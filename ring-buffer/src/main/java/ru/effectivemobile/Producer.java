package ru.effectivemobile;

public class Producer implements Runnable {

    private final RingBuffer<Integer> ringBuffer;

    public Producer(RingBuffer<Integer> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                ringBuffer.put(i);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
