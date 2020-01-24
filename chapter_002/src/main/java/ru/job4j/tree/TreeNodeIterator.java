package ru.job4j.tree;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

public class TreeNodeIterator<E extends Comparable<E>> implements Iterator<Node<E>> {

    Node<E> currentNode;
    Queue<Node<E>> nodes = new LinkedList<>();

    public TreeNodeIterator(Node<E> root) {
        this.nodes.add(root);
        setCurrentNode();
    }

    private void setCurrentNode() {
        Node<E> currentNode = nodes.poll();
        if (currentNode != null) {
            this.currentNode = currentNode;
            this.nodes.addAll(currentNode.leaves());
        } else {
            this.currentNode = null;
        }
    }

    @Override
    public boolean hasNext() {
        return currentNode != null;
    }

    @Override
    public Node<E> next() {
        if (!this.hasNext()) {
            throw new NoSuchElementException();
        }
        Node<E> result = currentNode;
        setCurrentNode();
        return result;
    }
}
