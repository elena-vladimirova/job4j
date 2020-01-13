package ru.job4j.generics;

/**
 * Контейнер Stack, реализованный на базе связанного списка
 */
public class SimpleStack<T> {

    private SimpleArrayList<T> values = new SimpleArrayList<>();

    /**
     * Метод возвращает значение и удаляет его из контейнера.
     */
    public T poll() {
        return values.delete();
    }
    /**
     * Метод возвращает значение и удаляет его из контейнера.
     */
    public void push(T value) {
        values.add(value);
    }
}