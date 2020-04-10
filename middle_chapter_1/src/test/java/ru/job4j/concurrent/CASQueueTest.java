package ru.job4j.concurrent;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class CASQueueTest {

@Test
public void whenExecute() {
    CASQueue<Integer> cq = new CASQueue<>();
    cq.push(1);
    cq.push(2);
    cq.push(3);
    assertThat(1, is(cq.poll()));
    assertThat(2, is(cq.poll()));
    assertThat(3, is(cq.poll()));
}
}
