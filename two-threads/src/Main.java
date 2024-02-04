public class Main {
    public static void main(String[] args) {
        Object lock = new Object();

        new Thread(new EvenThread(lock)).start();
        new Thread(new OddThread(lock)).start();
    }
}