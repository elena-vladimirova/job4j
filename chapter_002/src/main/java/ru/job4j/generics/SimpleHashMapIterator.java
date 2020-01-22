package ru.job4j.generics;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

class SimpleHashMapIterator<K, V> implements Iterator {

    private SimpleHashMap<K, V> simpleHashMap;
    private int modCount;
    private Iterator<K> keys;

    public SimpleHashMapIterator(SimpleHashMap<K, V> simpleHashMap) {
        this.simpleHashMap = simpleHashMap;
        this.modCount = simpleHashMap.getModCount();
        this.keys = simpleHashMap.getKeys().iterator();
    }

    @Override
    public boolean hasNext() {
        return this.keys.hasNext();
    }

    @Override
    public V next() {
        if (!this.hasNext()) {
            throw new NoSuchElementException();
        }
        if (this.modCount != simpleHashMap.getModCount()) {
            throw new ConcurrentModificationException();
        }
        return this.simpleHashMap.get(keys.next());
    }
}
