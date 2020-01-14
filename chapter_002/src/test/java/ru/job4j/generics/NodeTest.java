package ru.job4j.generics;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class NodeTest {

    @Test
    public void  whenHasCycleInTheEndThenTrue() {
        Node first = new Node(1);
        Node two = new Node(2);
        Node third = new Node(3);
        Node four = new Node(4);
        first.next = two;
        two.next = third;
        third.next = four;
        four.next = first;
        assertThat(Node.hasCycle(first), is(true));
    }

    @Test
    public void  whenHasCycleInTheMiddleThenTrue() {
        Node first = new Node(1);
        Node two = new Node(2);
        Node third = new Node(3);
        Node four = new Node(4);
        first.next = two;
        two.next = third;
        third.next = two;
        assertThat(Node.hasCycle(first), is(true));
    }

    @Test
    public void  whenHasCycleThenFalse() {
        Node first = new Node(1);
        Node two = new Node(2);
        Node third = new Node(3);
        Node four = new Node(4);
        first.next = two;
        two.next = third;
        third.next = four;
        assertThat(Node.hasCycle(first), is(false));
    }
}
