package ru.job4j.concurrent;

import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.IntStream;

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
    @Test
    public void whenFetchAllThenGetIt() throws InterruptedException {
        final var buffer = new CopyOnWriteArrayList<Integer>();
        final SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>();
        Thread producer = new Thread(
                () -> {
                    IntStream.range(0, 5).forEach(
                            value -> {
                                try {
                                    queue.offer(value);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                    );
                }
        );
        producer.start();
        Thread consumer = new Thread(
                () -> {
                    while (!queue.isEmpty() || !Thread.currentThread().isInterrupted()) {
                        try {
                            buffer.add(queue.poll());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );
        consumer.start();
        producer.join();
        consumer.interrupt();
        consumer.join();
        assertThat(buffer, is(Arrays.asList(0, 1, 2, 3, 4)));
    }
}