package ru.job4j.tree;

import java.util.*;

public class TreeIterator<E extends Comparable<E>> implements Iterator<E> {

    E currentElement;
    Queue<Node<E>> nodes = new LinkedList<>();

    public TreeIterator(Node<E> root) {
        this.nodes.add(root);
        setCurrentElement();
    }

    private void setCurrentElement() {
        Node<E> currentNode = nodes.poll();
        if (currentNode != null) {
            currentElement = currentNode.getValue();
            this.nodes.addAll(currentNode.leaves());
        } else {
            currentElement = null;
        }
    }

    @Override
    public boolean hasNext() {
        return currentElement != null;
    }

    @Override
    public E next() {
        if (!this.hasNext()) {
            throw new NoSuchElementException();
        }
        E result = currentElement;
        setCurrentElement();
        return result;
    }
}
