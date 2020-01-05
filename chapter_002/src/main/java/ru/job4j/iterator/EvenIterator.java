package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenIterator implements Iterator {

    private final int[] values;
    private int index = -1;

    public EvenIterator(int[] values) {
        this.values = values;
        this.index = this.findNext();
    }

    public int findNext() {
        int result = values.length;
        for (int i = index + 1; i < values.length; i++) {
            if (values[i] % 2 == 0) {
                result = i;
                break;
            }
        }
        return result;
    }

    @Override
    public boolean hasNext() {
        return this.index != values.length;
    }

    @Override
    public Object next() {
        if (this.index == values.length) {
            throw new NoSuchElementException();
        } else {
            Integer result = values[this.index];
            this.index = this.findNext();
            return result;
        }
    }
}
