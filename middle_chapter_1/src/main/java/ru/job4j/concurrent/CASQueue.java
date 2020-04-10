package ru.job4j.concurrent;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
public class CASQueue<T> {
    private final AtomicReference<Node<T>> head = new AtomicReference<>();
    private final AtomicReference<Node<T>> tail = new AtomicReference<>();

    public void push(T value) {
        Node<T> tmp = new Node<>(value);
        Node<T> refHead;
        Node<T> refTail;
        do {
            refTail = tail.get();
            refHead = head.get();
            if (refTail != null) {
                refTail.prev = tmp;
            }
            do {
                if (refHead != null){
                    break;
                }
            } while (!head.compareAndSet(refHead, tmp));
        } while (!tail.compareAndSet(refTail, tmp));
    }

    public T poll() {
        Node<T> ref;
        Node<T> tmp;
        T result;
        do {
            ref = head.get();
            if (ref == null) {
                throw new IllegalStateException("Queue is empty");
            }
            result = ref.value;
            tmp = ref.prev;
            ref.prev = null;
        } while (!head.compareAndSet(ref, tmp));
        return result;
    }

    private static final class Node<T> {
        final T value;
        Node<T> prev;
        public Node(final T value) {
            this.value = value;
        }
    }
}
