package ru.job4j.generics;

import java.util.Arrays;
import java.util.Iterator;

public class SimpleDynamicArray<E> implements Iterable<E> {

    private int size = 10;
    private Object[] objects;
    private int index = 0;
    private int modCount = 0;

    private void increaseObjectsSize() {
        this.size *= 2;
        this.objects = Arrays.copyOf(this.objects, size);
    }

    public int getModCount() {
        return modCount;
    }

    public int getSize() {
        return this.index;
    }

    public SimpleDynamicArray() {
        this.objects = new Object[size];
    }

    public void add(E value) {
        if (index == size) {
            increaseObjectsSize();
        }
        this.objects[index++] = value;
        modCount++;
    }

    public E get(int position) {
        return (E) this.objects[position];
    }

    @Override
    public Iterator<E> iterator() {
        return new SimpleDynamicArrayIterator<>(this);
    }

}