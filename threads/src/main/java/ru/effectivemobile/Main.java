package ru.effectivemobile;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Object lock = new Object();

        Thread t1 = new Thread(new NumberPrintThread(true, lock));
        Thread t2 = new Thread(new NumberPrintThread(false, lock));

        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }
}