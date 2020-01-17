package ru.job4j.generics;

import java.util.Arrays;
import java.util.Iterator;

public class SimpleDynamicArray<E> implements Iterable<E> {

    private int size = 10;
    private Object[] objects;
    private int index = 0;
    private int modCount = 0;

    public SimpleDynamicArray() {
        this.objects = new Object[size];
    }

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

    public int getPosition(E value) {
        int result = -1;
        for (int i = 0; i < index; i++) {
            if (this.objects[i].equals(value)) {
                result = i;
                break;
            }
        }
        return result;
    }

    public boolean contains(E value) {
        return getPosition(value) != -1;
    }

    @Override
    public Iterator<E> iterator() {
        return new SimpleDynamicArrayIterator<>(this);
    }
}