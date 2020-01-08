package ru.job4j.generics;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleArrayIterator<E> implements Iterator<E> {

    private SimpleArray<E> simpleArray;
    private int index = 0;

    public SimpleArrayIterator(SimpleArray<E> simpleArray) {
        this.simpleArray = simpleArray;
    }

    @Override
    public boolean hasNext() {
        return this.index < this.simpleArray.getSize();
    }

    @Override
    public E next() {
        if (!this.hasNext()) {
            throw new NoSuchElementException();
        } else {
            return this.simpleArray.get(index++);
        }
    }
}
