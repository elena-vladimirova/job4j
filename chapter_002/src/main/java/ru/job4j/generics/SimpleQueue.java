package ru.job4j.generics;

/**
 * Очередь на двух стеках
 */
public class SimpleQueue<T> {

    SimpleStack<T> pushStack = new SimpleStack<>();
    SimpleStack<T> pollStack = new SimpleStack<>();

    /**
     * Метод помещает значение в контейнер.
     */
    public void push(T value) {
        pushStack.push(value);
    }

    /**
     * Метод возвращает значение и удаляет его из контейнера.
     */
    public T poll() {
        if (pollStack.getSize() == 0) {
            fillPollStack();
        }
        return pollStack.poll();
    }

    public void fillPollStack() {
        while (pushStack.getSize() != 0) {
            pollStack.push(pushStack.poll());
        }
    }
}
