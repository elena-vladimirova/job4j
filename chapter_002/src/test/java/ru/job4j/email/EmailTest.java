package ru.job4j.email;

import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class EmailTest {

    @Test
    public void whenMerge() {
        List<Email.User> users = List.of(new Email.User("user1", List.of("xxx@ya.ru", "foo@gmail.com", "lol@mail.ru")),
                                         new Email.User("user2", List.of("foo@gmail.com", "ups@pisem.net")),
                                         new Email.User("user3", List.of("xyz@pisem.net", "vasya@pupkin.com")),
                                         new Email.User("user4", List.of("ups@pisem.net", "aaa@bbb.ru")),
                                         new Email.User("user5", List.of("xyz@pisem.net"))
        );
        Map<String, List<String>> merged = Email.merge(users);
        Map<String, List<String>> expected = Map.of("user1", List.of("aaa@bbb.ru", "ups@pisem.net", "lol@mail.ru", "xxx@ya.ru", "foo@gmail.com"),
                                                    "user3", List.of("vasya@pupkin.com", "xyz@pisem.net"));
        assertThat(expected, is(merged));
    }

    @Test
    public void whenMerge2() {
        List<Email2.User> users = List.of(
                new Email2.User("user1", List.of("xxx@ya.ru", "foo@gmail.com", "lol@mail.ru")),
                new Email2.User("user2", List.of("foo@gmail.com", "ups@pisem.net")),
                new Email2.User("user3", List.of("xyz@pisem.net", "vasya@pupkin.com")),
                new Email2.User("user4", List.of("ups@pisem.net", "aaa@bbb.ru")),
                new Email2.User("user5", List.of("xyz@pisem.net"))
        );
        Map<String, Set<String>> merged = Email2.merge(users);
        Map<String, Set<String>> expected = Map.of("user1", Set.of("aaa@bbb.ru", "ups@pisem.net", "lol@mail.ru", "xxx@ya.ru", "foo@gmail.com"),
                "user3", Set.of("vasya@pupkin.com", "xyz@pisem.net"));
        assertThat(expected, is(merged));
    }

}
