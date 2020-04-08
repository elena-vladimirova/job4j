package ru.job4j.concurrent;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {

    @GuardedBy("this")
    private Queue<T> queue = new LinkedList<>();
    private static final int MAX_SIZE = 10;

    public synchronized void offer(T value) throws InterruptedException {
        while (queue.size() == MAX_SIZE) {
            wait();
        }
        queue.add(value);
        notifyAll();
    }

    public synchronized T poll() throws InterruptedException {
        while (queue.size() == 0) {
            wait();
        }
        T result = queue.remove();
        notifyAll();
        return result;
    }

    public synchronized boolean isEmpty() {
            return queue.isEmpty();
        }
}