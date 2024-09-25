package ru.job4j.email;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EmailNotification {

    private final static ExecutorService pool = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors()
    );

    public void emailTo(User user) {
        String subject = String.format("Notification %s to email %s", user.username(), user.email());
        String body = String.format("Add a new event to %s", user.email());
        pool.submit(() -> {
            send(subject, body, user.email());
        });
    }

    public void close() {
        pool.shutdown();
    }

    public void send(String subject, String body, String email) {

    }

    public static void main(String[] args) {
        EmailNotification notification = new EmailNotification();
        notification.emailTo(new User("John", "djhfgsdf@gmail.com"));
        notification.close();
    }
}
