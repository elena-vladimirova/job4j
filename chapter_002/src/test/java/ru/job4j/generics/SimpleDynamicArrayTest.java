package ru.job4j.generics;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class SimpleDynamicArrayTest {

    SimpleDynamicArray<Integer> simpleDynamicArray = new SimpleDynamicArray<>();

    @Before
    public void setUp() {
        simpleDynamicArray.add(0);
        simpleDynamicArray.add(1);
        simpleDynamicArray.add(2);
        simpleDynamicArray.add(3);
        simpleDynamicArray.add(4);
        simpleDynamicArray.add(5);
        simpleDynamicArray.add(6);
        simpleDynamicArray.add(7);
        simpleDynamicArray.add(8);
        simpleDynamicArray.add(9);
    }

    @Test
    public void  whenIncreaseSize() {
        simpleDynamicArray.add(10);
        assertThat(simpleDynamicArray.get(10), is(10));
    }

    @Test
    public void  whenIterator() {
        Iterator<Integer> it = simpleDynamicArray.iterator();
        it.next();
        it.next();
        it.next();
        it.next();
        it.next();
        it.next();
        it.next();
        it.next();
        it.next();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(9));
        assertThat(it.hasNext(), is(false));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void  whenConcurrentModificationException() {
        Iterator<Integer> it = simpleDynamicArray.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(0));
        simpleDynamicArray.add(10);
        it.next();
    }
}