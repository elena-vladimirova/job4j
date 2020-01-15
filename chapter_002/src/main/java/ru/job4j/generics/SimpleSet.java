package ru.job4j.generics;

import java.util.Iterator;

/**
 * Коллекция Set на базе массива
 */
public class SimpleSet<T> implements Iterable<T> {

    private SimpleDynamicArray<T> values = new SimpleDynamicArray<>();

    public boolean add(T value) {
        boolean result = false;
        if (!this.values.contains(value)) {
            values.add(value);
            result = true;
        }
        return result;
    }

    @Override
    public Iterator<T> iterator() {
        return values.iterator();
    }
}
