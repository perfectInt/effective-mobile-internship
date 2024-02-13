package ru.effectivemobile;

public class Main {
    public static void main(String[] args) {
        RingBuffer<Integer> ringBuffer = new RingBuffer<>(15);

        Thread producerThread = new Thread(new Producer(ringBuffer), "ProducerThread");
        Thread consumerThread = new Thread(new Consumer(ringBuffer), "ConsumerThread");

        producerThread.start();
        consumerThread.start();
    }
}