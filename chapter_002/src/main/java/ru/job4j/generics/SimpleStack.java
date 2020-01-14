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
     * Метод помещает значение в контейнер.
     */
    public void push(T value) {
        values.add(value);
    }

    public int getSize() {
        return values.getSize();
    }
}