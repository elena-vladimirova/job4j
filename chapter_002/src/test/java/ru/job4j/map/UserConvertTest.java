package ru.job4j.map;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class UserConvertTest {

    @Test
    public void whenConvert() {
        User user1 = new User(1, "Elena", "Moscow");
        User user2 = new User(2, "Egor", "Omsk");
        User user3 = new User(3, "Freddie", "London");
        List<User> listUser = Arrays.asList(user1, user2, user3);
        HashMap<Integer, User> result =  new UserConvert().process(listUser);
        HashMap<Integer, User> expect = new HashMap();
        expect.put(1, user1);
        expect.put(2, user2);
        expect.put(3, user3);
        assertThat(result, is(expect));
    }


}
