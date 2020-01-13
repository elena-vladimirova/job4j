package ru.job4j.generics;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleDynamicArrayIterator<E> implements Iterator<E> {

    private SimpleDynamicArray<E> simpleDynamicArray;
    private int index = 0;
    private int modCount;

    public SimpleDynamicArrayIterator(SimpleDynamicArray<E> simpleDynamicArray) {
        this.simpleDynamicArray = simpleDynamicArray;
        this.modCount = simpleDynamicArray.getModCount();
    }

    @Override
    public boolean hasNext() {
        return this.index < this.simpleDynamicArray.getSize();
    }

    @Override
    public E next() {
        if (!this.hasNext()) {
            throw new NoSuchElementException();
        }
        if (this.modCount != simpleDynamicArray.getModCount()) {
            throw new ConcurrentModificationException();
        }
        return this.simpleDynamicArray.get(index++);
    }
}
