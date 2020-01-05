package ru.job4j.iterator;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Converter {
    Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {

        return new Iterator<Integer>() {

            Iterator<Integer> currentIterator;

            private void setCurrentIterator() {
                if (currentIterator != null && currentIterator.hasNext()) {
                    return;
                }
                currentIterator = null;
                while (it.hasNext()) {
                    Iterator<Integer> nextIterator = it.next();
                    if (nextIterator.hasNext()) {
                        currentIterator = nextIterator;
                        break;
                    }
                }
            }

            @Override
            public boolean hasNext() {
                setCurrentIterator();
                return (currentIterator != null && currentIterator.hasNext());
            }

            @Override
            public Integer next() {
                setCurrentIterator();
                if (currentIterator == null) {
                    throw new NoSuchElementException();
                }
                return currentIterator.next();
            }
        };
    }
}
