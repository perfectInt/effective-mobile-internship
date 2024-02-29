package ru.effectivemobile;

public class RingBuffer<T> {
    private final Integer capacity;
    private T[] data;
    private int reader = 0, writer = -1;
    private int size = 0;

    public RingBuffer() {
        this(10);
    }

    public RingBuffer(Integer capacity) {
        this.capacity = capacity;
        this.data = (T[]) new Object[capacity];
    }

    public synchronized void put(T el) {
        writer = (writer + 1) % capacity;
        data[writer] = el;
        if (size != capacity) size++;
        System.out.println("Added to ring buffer: " + el);
    }

    public synchronized T poll() {
        T val = data[reader % capacity];
        data[reader % capacity] = null;
        if (size > 0) {
            reader++;
            size--;
        }
        System.out.println("Get from ring buffer: " + val);
        return val;
    }

    public int size() {
        return size;
    }
}
