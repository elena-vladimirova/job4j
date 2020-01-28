package ru.job4j.tree;

import java.util.*;

public class TreeIterator<E extends Comparable<E>> implements Iterator<E> {

    Queue<Node<E>> nodes = new LinkedList<>();

    public TreeIterator(Node<E> root) {
        this.nodes.add(root);
    }

    private E getElement() {
        E result = null;
        if (this.hasNext()) {
            Node<E> currentNode = nodes.poll();
            result = currentNode.getValue();
            this.nodes.addAll(currentNode.leaves());
        }
        return result;
    }

    @Override
    public boolean hasNext() {
        return this.nodes.size() > 0;
    }

    @Override
    public E next() {
        if (!this.hasNext()) {
            throw new NoSuchElementException();
        }
        return getElement();
    }
}
