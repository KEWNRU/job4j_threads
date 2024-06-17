package ru.job4j.concurrent;

public class ConsoleProgress implements Runnable {
    public static void main(String[] args) throws InterruptedException {
        Thread progress = new Thread(new ConsoleProgress());
        progress.start();
        Thread.sleep(5000); /* симулируем выполнение параллельной задачи в течение 5 секунд. */
        progress.interrupt();
    }

    @Override
    public void run() {
        try {
            int i = 0;
            char[] process = new char[]{'-', '\\', '|', '/'};
            while (!Thread.currentThread().isInterrupted()) {
                if (i == process.length) {
                    i = 0;
                }
                System.out.print("\r load: " + process[i++]);
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            System.out.println("Loading is complete");
        }
    }
}
