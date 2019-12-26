package ru.job4j.stream;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ProfileTest {

    @Test
    public void whenClassA() {
        Address a1 = new Address("Moscow", "Volhonka", 1, 1);
        Address a2 = new Address("Moscow", "Mahovaya", 1, 1);
        Profile p1 = new Profile(a1);
        Profile p2 = new Profile(a2);
        List<Address> result = Profile.collect(Arrays.asList(p1, p2));
        List<Address> expected = Arrays.asList(a1, a2);
        assertThat(result, is(expected));
    }

}
