package ru.job4j.collection;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class UserTest {
    @Test
    public void whenAscByName() {
        Set<User> users = new TreeSet<>();
        users.add(new User("Petr", 32));
        users.add(new User("Ivan", 31));
        Iterator<User> it = users.iterator();
        assertThat(it.next(), is(new User("Ivan", 31)));
        assertThat(it.next(), is(new User("Petr", 32)));
    }
    @Test
    public void whenAscByAge() {
        Set<User> users = new TreeSet<>();
        users.add(new User("Petr", 32));
        users.add(new User("Petr", 31));
        Iterator<User> it = users.iterator();
        assertThat(it.next(), is(new User("Petr", 31)));
        assertThat(it.next(), is(new User("Petr", 32)));
    }

    @Test
    public void whenComparePertVSIvan() {
        int rsl = new User("Petr", 32)
                .compareTo(
                        new User("Ivan", 31)
                );
        assertThat(rsl, greaterThan(0));
    }

    @Test
    public void whenComparatorUserAscByName() {
        int rsl = new UserAscByName().compare(new User("Boris", 32), new User("Anton", 31));
        assertThat(rsl, greaterThan(0));
    }

    @Test
    public void whenComparatorUserAscByAge() {
        int rsl = new UserAscByAge().compare(new User("Boris", 32), new User("Anton", 31));
        assertThat(rsl, greaterThan(0));
    }

    @Test
    public void whenComparatorUserDescByName() {
        int rsl = new UserDescByName().compare(new User("Boris", 32), new User("Anton", 31));
        assertThat(rsl, lessThan(0));
    }

    @Test
    public void whenComparatorUserDescByAge() {
        int rsl = new UserDescByAge().compare(new User("Boris", 32), new User("Anton", 31));
        assertThat(rsl, lessThan(0));
    }

    @Test
    public void whenCombinedComparator() {
        Comparator<User> cmpNameDescAgeAsc = new UserDescByName().thenComparing(new UserAscByAge());
        Set<User> users = new TreeSet<>(cmpNameDescAgeAsc);
        users.add(new User("Boris", 32));
        users.add(new User("Boris", 31));
        users.add(new User("Anna", 20));
        Iterator<User> it = users.iterator();
        assertThat(it.next(), is(new User("Boris", 31)));
        assertThat(it.next(), is(new User("Boris", 32)));
        assertThat(it.next(), is(new User("Anna", 20)));
    }
}