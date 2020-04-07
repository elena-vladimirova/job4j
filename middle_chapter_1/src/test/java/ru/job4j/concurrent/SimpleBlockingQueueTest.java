package ru.job4j.concurrent;

import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class SimpleBlockingQueueTest {

    @Test
    public void whenGetValueFromQueue() throws InterruptedException {
        SimpleBlockingQueue<Integer> q = new SimpleBlockingQueue<>();
        Thread first = new Thread(
                () -> {
                    try {
                        q.offer(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        );
        final int[] result = new int[1];
        Thread second = new Thread(() -> {
            try {
                result[0] = q.poll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        first.start();
        second.start();
        first.join();
        second.join();
        assertThat(1, is(result[0]));
    }

}
