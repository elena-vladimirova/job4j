package ru.job4j.generics;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.junior.map.User;

import java.util.Calendar;
import java.util.ConcurrentModificationException;
import java.util.GregorianCalendar;
import java.util.Iterator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class SimpleHashMapTest {

    SimpleHashMap<String, User> simpleHashMap = new SimpleHashMap<>();

    @Before
    public void setUp() {
        simpleHashMap.insert("Lena", new User("Lena", 2, new GregorianCalendar(1984, 8, 8)));
        simpleHashMap.insert("Eugene", new User("Eugene", 2, new GregorianCalendar(1974, 8, 3)));
    }

    @Test
    public void  whenGet() {
        User user1 = new User("Lena", 2, new GregorianCalendar(1984, 8, 8));
        assertThat(simpleHashMap.get("Lena"), is(user1));
    }


    @Test
    public void  whenDelete() {
        SimpleHashMap<String, User> map = new SimpleHashMap<>();
        map.insert("Lena", new User("Lena", 2, new GregorianCalendar(1984, 8, 8)));
        map.insert("Eugene", new User("Eugene", 2, new GregorianCalendar(1974, 8, 3)));
        map.delete("Lena");
        Iterator it = map.iterator();
        User user2 = new User("Eugene", 2, new GregorianCalendar(1974, 8, 3));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(user2));
        assertThat(it.hasNext(), is(false));
    }
}
