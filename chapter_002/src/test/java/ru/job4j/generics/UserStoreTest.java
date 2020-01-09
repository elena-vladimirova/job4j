package ru.job4j.generics;

import org.junit.Before;
import org.junit.Test;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class UserStoreTest {

    Store<User> userStore = new UserStore(5);

    @Before
    public void setUp() {
        userStore.add(new User("1", "Lena"));
        userStore.add(new User("2", "Dima"));
    }

    @Test
    public void  whenReplace() {
        User newUser = new User("3", "Tanya");
        userStore.replace("2", newUser);
        assertThat("Tanya", is(userStore.findById("3").getUserName()));
    }

    @Test
    public void  whenDelete() {
        userStore.delete("2");
        assertThat(null, is(userStore.findById("2")));
    }
}