package ru.job4j.generics;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class SimpleArrayTest {

    SimpleArray<Integer> simpleArray = new SimpleArray<>(5);

    @Before
    public void setUp() {
        simpleArray.add(1);
        simpleArray.add(2);
        simpleArray.add(3);
        simpleArray.add(4);
        simpleArray.add(5);
    }

    @Test
    public void  whenGet() {
        Integer expected = simpleArray.get(4);
        assertThat(5, is(expected));
    }

    @Test
    public void  whenSet() {
        simpleArray.set(2, 30);
        assertThat(30, is(simpleArray.get(2)));
    }

    @Test
    public void  whenRemove() {
        simpleArray.remove(2);
        assertThat(1, is(simpleArray.get(0)));
        assertThat(2, is(simpleArray.get(1)));
        assertThat(4, is(simpleArray.get(2)));
        assertThat(5, is(simpleArray.get(3)));
        assertThat(null, is(simpleArray.get(4)));
    }

    @Test
    public void  whenIterator() {
        Iterator<Integer> it = simpleArray.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(3));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(4));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(5));
        assertThat(it.hasNext(), is(false));
    }
}