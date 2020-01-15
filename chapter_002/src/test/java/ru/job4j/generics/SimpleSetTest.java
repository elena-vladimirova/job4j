package ru.job4j.generics;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class SimpleSetTest {

    @Test
    public void  whenIncreaseSize() {
        SimpleSet<Integer> set = new SimpleSet<>();
        assertThat(set.add(1), is(true));
        assertThat(set.add(2), is(true));
        assertThat(set.add(2), is(false));
    }

}