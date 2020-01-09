package ru.job4j.generics;

import org.junit.Before;
import org.junit.Test;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class RoleStoreTest {

    Store<Role> roleStore = new RoleStore(5);

    @Before
    public void setUp() {
        roleStore.add(new Role("1", "Admin"));
        roleStore.add(new Role("2", "Guest"));
    }

    @Test
    public void  whenReplace() {
        Role newRole = new Role("3", "Student");
        roleStore.replace("2", newRole);
        assertThat("Student", is(roleStore.findById("3").getRoleName()));
    }

    @Test
    public void  whenDelete() {
        roleStore.delete("2");
        assertThat(null, is(roleStore.findById("2")));
    }

}
