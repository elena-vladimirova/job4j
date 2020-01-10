package ru.job4j.generics;

import java.lang.reflect.ParameterizedType;
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

    public int getPosition(E value) {
        int result = -1;
        for (int i = 0; i < index; i++) {
            if (this.objects[i] == value) {
                result = i;
            }
        }
        return result;
    }

    @Override
    public Iterator<E> iterator() {
        return new SimpleArrayIterator<>(this);
    }

    public void printObjectType() {
        Class<E> t = (Class<E>) ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        System.out.println(t.toString());
    }

    public static void main(String[] args) {
        SimpleArray<String> s = new SimpleArray<>(5);
        s.printObjectType();
    }
}