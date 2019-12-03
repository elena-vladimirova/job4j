package ru.job4j.condition;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SqMaxTest {
    @Test
    public void whenFirstMax() {
        int result = SqMax.max(4, 1, 2, 3);
        assertThat(result, is(4));
    }

    @Test
    public void whenSecondMax() {
        int result = SqMax.max(1, 4, 2, 3);
        assertThat(result, is(4));
    }

    @Test
    public void whenThirdMax() {
        int result = SqMax.max(1, 2, 4, 3);
        assertThat(result, is(4));
    }

    @Test
    public void whenFourthMax() {
        int result = SqMax.max(1, 2, 3, 4);
        assertThat(result, is(4));
    }

    @Test
    public void whenEqual() {
        int result = SqMax.max(4, 4, 4, 4);
        assertThat(result, is(4));
    }
}
