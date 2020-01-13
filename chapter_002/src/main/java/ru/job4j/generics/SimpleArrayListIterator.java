package ru.job4j.generics;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleArrayListIterator<E> implements Iterator<E> {

    private SimpleArrayList<E> simpleArrayList;
    private SimpleArrayList.Node<E> currentNode;
    private int modCount;

    private void checkBeforeNext() {
        if (!this.hasNext()) {
            throw new NoSuchElementException();
        }
        if (this.modCount != simpleArrayList.getModCount()) {
            throw new ConcurrentModificationException();
        }
    }

    public SimpleArrayListIterator(SimpleArrayList<E> simpleArrayList) {
        this.simpleArrayList = simpleArrayList;
        this.currentNode = simpleArrayList.getFirst();
        this.modCount = simpleArrayList.getModCount();
    }

    @Override
    public boolean hasNext() {
        return currentNode != null;
    }

    @Override
    public E next() {
        checkBeforeNext();
        E result = currentNode.data;
        currentNode = currentNode.next;
        return result;
    }
}
