package task_4;

public class ThreadSignal {

    private static final Object lock = new Object();

    public static void main(String[] args) {
        Thread waitingThread = new Thread(() -> {
            synchronized (lock) {
                try {
                    System.out.println("Waiting for the signal...");
                    lock.wait();
                    System.out.println("Signal received, proceeding...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread notifyingThread = new Thread(() -> {
            synchronized (lock) {
                System.out.println("Sending the signal...");
                lock.notify();
            }
        });

        waitingThread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        notifyingThread.start();
    }
}
