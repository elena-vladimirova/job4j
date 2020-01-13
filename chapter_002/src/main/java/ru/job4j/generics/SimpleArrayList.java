package ru.job4j.generics;

import java.util.Iterator;

/**
 * Класс SimpleArrayList.
 */
public class SimpleArrayList<E> implements Iterable<E> {

    private int size;
    private Node<E> first;
    private int modCount = 0;

    /**
     * Метод вставляет в начало списка данные.
     */
    public void add(E data) {
        Node<E> newLink = new Node<>(data);
        newLink.next = this.first;
        this.first = newLink;
        this.size++;
        this.modCount++;
    }

    /**
     * Метод удаления первого элемент в списке.
     * @return Удаленный элемент
     */
    public E delete() {
        E result = first.data;
        Node<E> deletedNode = first;
        this.first = first.next;
        deletedNode.next = null;
        deletedNode.data = null;
        this.size--;
        this.modCount++;
        return result;
    }

    /**
     * Метод получения элемента по индексу.
     */
    public E get(int index) {
        Node<E> result = this.first;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result.data;
    }

    /**
     * Метод получения размера коллекции.
     */
    public int getSize() {
        return this.size;
    }

    public Node<E> getFirst() {
        return first;
    }

    /**
     * Метод получения счетчика изменений.
     */
    public int getModCount() {
        return modCount;
    }

    /**
     * Класс предназначен для хранения данных.
     */
    public static class Node<E> {
        E data;
        Node<E> next;
        Node(E data) {
            this.data = data;
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new SimpleArrayListIterator<>(this);
    }
}
