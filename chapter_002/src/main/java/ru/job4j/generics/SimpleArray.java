package ru.job4j.generics;

import java.util.Iterator;

public class SimpleArray<E> implements Iterable<E> {

    private Object[] objects;
    private int index = 0;

    public int getSize() {
        return index;
    }

    public SimpleArray(int size) {
        this.objects = new Object[size];
    }

    public void add(E value) {
        this.objects[index++] = value;
    }

    public void set(int position, E value) {
        this.objects[position] = value;
    }

    public E get(int position) {
        return (E) this.objects[position];
    }

    public void remove(int position) {
        System.arraycopy(this.objects, position + 1, this.objects, position, this.objects.length - position - 1);
        this.set(index-- - 1, null);
    }

    @Override
    public Iterator<E> iterator() {
        return new SimpleArrayIterator<>(this);
    }
}
