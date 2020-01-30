package ru.job4j.analize;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class AnalizeTest {

    Analize.User u1 = new Analize.User(1, "111");
    Analize.User u2 = new Analize.User(2, "222");
    Analize.User u3 = new Analize.User(3, "333");
    Analize.User u22 = new Analize.User(2, "changed_222");
    Analize.User u5 = new Analize.User(5, "555");


    @Test
    public void whenPreviousChanged() {
        List<Analize.User> previous = List.of(u1, u2, u3);
        List<Analize.User> current = List.of(u1, u22, u5);
        Analize.Info info = Analize.diff(previous, current);
        assertThat(1, is(info.added));
        assertThat(1, is(info.deleted));
        assertThat(1, is(info.changed));
    }

    @Test
    public void whenPreviousEmpty() {
        List<Analize.User> previous = List.of();
        List<Analize.User> current = List.of(u1, u22, u5);
        Analize.Info info = Analize.diff(previous, current);
        assertThat(3, is(info.added));
        assertThat(0, is(info.deleted));
        assertThat(0, is(info.changed));
    }

    @Test
    public void whenCurrentEmpty() {
        List<Analize.User> previous = List.of(u1, u2, u3);
        List<Analize.User> current = List.of();
        Analize.Info info = Analize.diff(previous, current);
        assertThat(0, is(info.added));
        assertThat(3, is(info.deleted));
        assertThat(0, is(info.changed));
    }

}
