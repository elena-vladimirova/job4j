package ru.job4j.concurrent.email;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EmailNotification {

    private ExecutorService pool = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors()
    );

    public static void send(String subject, String body, String email) {
        System.out.println(subject + " " + email);
    }

    public void emailTo(User user) {
        pool.submit(new Runnable() {
            @Override
            public void run() {
                String subject = String.format("Notification %s to email %s", user.username, user.email);
                String body = String.format("Add a new event to %s", user.username);
                EmailNotification.send(subject, body, user.email);
            }
        });
    }

    public void close() {
        pool.shutdown();
    }

    public static void main(String[] args) {
        EmailNotification en = new EmailNotification();
        en.emailTo(new User("Lena", "lena@mail.ru"));
        en.emailTo(new User("Dima", "dima@mail.ru"));
        en.close();
    }

}
