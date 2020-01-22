package ru.job4j.generics;

import java.util.*;

/**
 * Реализовать собственную структуру данных - HashMap[#219672]
 *
 * Ассоциативный массив на базе хэш-таблицы должен быть унифицирован через генерики и иметь методы:
 * boolean insert(K key, V value);
 * V get(K key);
 * boolean delete(K key);
 *
 * Реализовывать итератор.
 * Внутренняя реализация должна использовать массив. Нужно обеспечить фиксированное время вставки и получение.
 * Предусмотрите возможность роста хэш-таблицы при нехватке места для нового элемента.
 *
 * Методы разрешения коллизий реализовывать не надо. Например: если при добавлении ключ уже есть, то возвращать false.
 */
public class SimpleHashMap<K, V> implements Iterable<K> {

    /**
     * Массив для хранения пары ключ-значение
     */
    private Node<Object, Object>[] table;
    /**
     * Размер table. В рамках учебной задачи - неизменный.
     */
    private int tableSize = 128;
    private int modCount = 0;
    /**
     * Множество ключей
     */
    private Set<K> keys = new HashSet();

    public SimpleHashMap() {
        this.table = new Node[this.tableSize];
    }

    public boolean insert(K key, V value) {
        boolean result = false;
        int index = getIndex(key);
        if (table[index] == null) {
            table[index] = new Node(key, value);
            result = true;
            modCount++;
            keys.add(key);
        }
        return result;
    }
    public V get(K key) {
        return (V) table[getIndex(key)].getValue();
    }

    public boolean delete(K key) {
        boolean result = false;
        int index = getIndex(key);
        if (table[index] != null) {
            table[index] = null;
            result = true;
            modCount++;
            keys.remove(key);
        }
        return result;
    }

    /**
     * Хэширование ключа. Метод заимствован из реализации HashMap.
     */
    private int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    /**
     * Метод нахождения индекса элемента в table. Метод заимствован из реализации HashMap.
     */
    private int getIndex(K key) {
        // Аналог  hash(key) % this.tableSize;
        // Разотает при условии, что this.tableSize - степень числа 2.
        return (this.tableSize - 1) & hash(key);
    }

    public int getModCount() {
        return modCount;
    }

    /**
     * Структура для хранения пары ключ-значение.
     * В рамках учебной задачи связанные списки для разрешения коллизий не используются.
     */
    static class Node<K, V> {
        private K key;
        private V value;
        //Node<K,V> next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public V getValue() {
            return value;
        }

        @Override
        public String toString() {
            return "Node{" + "key=" + key + ", value=" + value + '}';
        }
    }

    @Override
    public Iterator iterator() {
        return new SimpleHashMapIterator<>(this);
    }

    public Set<K> getKeys() {
        return this.keys;
    }
}